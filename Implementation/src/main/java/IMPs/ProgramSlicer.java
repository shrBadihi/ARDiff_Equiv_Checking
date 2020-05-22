package IMPs;

import java.io.*;
import java.util.*;

import br.usp.each.saeg.asm.defuse.*;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithBody;
import com.github.javaparser.ast.stmt.*;
import equiv.checking.CommonBlockExtractor;
import equiv.checking.DefUseExtractor;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.AnalyzerException;

public class ProgramSlicer {
    public ArrayList<Integer> impactedStatements;

    public ProgramSlicer(ArrayList<Integer> changes){

        impactedStatements = new ArrayList<Integer>(changes);
    }

    public ArrayList<Integer> getImpactedStatements() {
        return impactedStatements;
    }

    public void impactedStatements(String prog, MethodNode method) throws FileNotFoundException, AnalyzerException {
        ClassOrInterfaceDeclaration c=(ClassOrInterfaceDeclaration) StaticJavaParser.parse(new File(prog)).getType(0);
        MethodDeclaration root=(MethodDeclaration)c.getMethods().get(0);
        DefUseInterpreter interpreter = new DefUseInterpreter();
        FlowAnalyzer<Value> flowAnalyzer = new FlowAnalyzer<Value>(interpreter);
        DefUseAnalyzer analyzer = new DefUseAnalyzer(flowAnalyzer, interpreter);
        analyzer.analyze("package/ClassName", method);
        Variable[] variables = analyzer.getVariables();

        HashMap<Integer,Integer> lineInst = DefUseExtractor.instructionToLine(method);
        DefUseChain[] chains = new DepthFirstDefUseChainSearch().search(
                analyzer.getDefUseFrames(),
                analyzer.getVariables(),
                flowAnalyzer.getSuccessors(),
                flowAnalyzer.getPredecessors());
        int location=root.getBegin().get().line;
        ArrayList<Integer> previousImpacted = new ArrayList<>();
        forwardSlicing(chains,lineInst,variables,root,location,previousImpacted);
        backwardControlDependence(root.getBody().get(),location);
        backwardDataDependence(chains,lineInst,variables);
        impactedStatements.remove(new Integer(location));
        Collections.sort(impactedStatements);
    }

    //Data dependence
    public void dataDependence(MethodNode method) throws AnalyzerException {
        DefUseInterpreter interpreter = new DefUseInterpreter();
        FlowAnalyzer<Value> flowAnalyzer = new FlowAnalyzer<Value>(interpreter);
        DefUseAnalyzer analyzer = new DefUseAnalyzer(flowAnalyzer, interpreter);
        analyzer.analyze("package/ClassName", method);
        Variable[] variables = analyzer.getVariables();

        HashMap<Integer,Integer> lineInst = DefUseExtractor.instructionToLine(method);
        DefUseChain[] chains = new DepthFirstDefUseChainSearch().search(
                analyzer.getDefUseFrames(),
                analyzer.getVariables(),
                flowAnalyzer.getSuccessors(),
                flowAnalyzer.getPredecessors());
        ///////////////////////////////////////////
       backwardDataDependence(chains,lineInst,variables);
        Collections.sort(impactedStatements);
    }

    public void forwardSlicing(DefUseChain[] chains,HashMap<Integer,Integer> lineInst,Variable[] variables,MethodDeclaration root,int location,ArrayList<Integer> previousImpacted){
            forwardControlDependence(root.getBody().get(),location);
            forwardDataDependence(chains,lineInst,variables);
            if(previousImpacted.size() != impactedStatements.size()){
                previousImpacted = new ArrayList<>(impactedStatements);
                forwardSlicing(chains,lineInst,variables,root,location,previousImpacted);
            }
    }

    public void forwardDataDependence(DefUseChain[] chains,HashMap<Integer,Integer> lineInst,Variable[] variables){
        for (int i = 0; i < chains.length; i++) {
            DefUseChain chain = chains[i];
            for (Integer key: lineInst.keySet()) {
                Integer value=lineInst.get(key);
                if (impactedStatements.contains(value)) {
                    if (key.equals(chain.def)) {
                       Integer use = lineInst.get(chain.use);
                        if (!impactedStatements.contains(use))
                            impactedStatements.add(use);
                    }
                }
            }
        }
    }

    public void backwardDataDependence(DefUseChain[] chains,HashMap<Integer,Integer> lineInst,Variable[] variables){
        for (int i = 0; i < chains.length; i++) {
            DefUseChain chain = chains[i];
            for (Integer key: lineInst.keySet()) {
                Integer value=lineInst.get(key);
                if (impactedStatements.contains(value)) {
                    if (key.equals(chain.use)) {
                        Integer def = lineInst.get(chain.def);
                        if (def!=null && !impactedStatements.contains(def))
                            impactedStatements.add(def);
                    }
                }
            }
        }
    }


    //Forward and backward control dependance
    public void controlDependence(String prog,ArrayList<Integer> impactedStatements) throws FileNotFoundException {
        ClassOrInterfaceDeclaration c=(ClassOrInterfaceDeclaration) StaticJavaParser.parse(new File(prog)).getType(0);
        MethodDeclaration root=(MethodDeclaration)c.getMethods().get(0);
        backwardControlDependence(root.getBody().get(),root.getBegin().get().line);
        //addControlStatements(root.getBody().get(),root.getBegin().get().line,impactedStatements);
        //we add the root statement as well, just to make sure the method signature will be in the file
       // impactedStatements.add(root.getBegin().get().line);
        Collections.sort(impactedStatements);
     //emptySortedSet
    }

    public void forwardControlDependence(Statement controlled,int location){
        boolean impacted=impactedStatements.contains(location);
        if(controlled!=null) {
            if (!(controlled instanceof BlockStmt))
                controlled = new BlockStmt(new NodeList<>(controlled));
            //We add the controlled statements
            for (Statement st : controlled.asBlockStmt().getStatements()) {
                int i = st.getBegin().get().line;
                if(!impactedStatements.contains(i)){
                    if(impacted) {
                        impactedStatements.add(i);
                    }
                }
                //Check if it should be only true controlled or not
                //Recursively
                if (CommonBlockExtractor.isControlSmt(st)) {
                    Statement trueControlled=(st instanceof IfStmt)?(st.asIfStmt()).getThenStmt():((NodeWithBody)st).getBody();
                    forwardControlDependence(trueControlled,st.getBegin().get().line);
                    if(st instanceof IfStmt){
                        IfStmt ist=st.asIfStmt();
                        if (ist.hasElseBranch()) forwardControlDependence(ist.getElseStmt().get(),st.getBegin().get().line);
                    }
                }
            }
        }
    }


    //Backward control dependence only once
    private void backwardControlDependence(Statement st,int location) {
        boolean impacted = impactedStatements.contains(location);
        if(st!=null) {
            ArrayList<Integer> additions = new ArrayList<>();
            if(!(st instanceof BlockStmt))
                st=new BlockStmt(new NodeList<>(st));
            BlockStmt root=st.asBlockStmt();
            for (Statement statement : root.getStatements()) {
                int i = statement.getBegin().get().line;
                if (impactedStatements.contains(i) && !impacted){
                    if(!additions.contains(location))
                        additions.add(location);
                }
                if (CommonBlockExtractor.isControlSmt(statement)) {
                    Statement trueControlled = (statement instanceof IfStmt) ? (statement.asIfStmt()).getThenStmt(): ((NodeWithBody) statement).getBody();
                    backwardControlDependence(trueControlled, statement.getBegin().get().line);
                    if (statement instanceof IfStmt) {
                        IfStmt ist = statement.asIfStmt();
                        if (ist.hasElseBranch())
                            backwardControlDependence(ist.getElseStmt().get(), statement.getBegin().get().line);
                    }
                }
            }
            impactedStatements.addAll(additions);
        }
    }


    private void addControlStatements(Statement controlled,int location,ArrayList<Integer> impactedStatements){
        boolean impacted=impactedStatements.contains(location);
        if(controlled!=null) {
            if (!(controlled instanceof BlockStmt))
                controlled = new BlockStmt(new NodeList<>(controlled));
            //We add the controlled statements
            for (Statement st : controlled.asBlockStmt().getStatements()) {
                int i = st.getBegin().get().line;
                if(!impactedStatements.contains(i)){
                    if(impacted)
                        impactedStatements.add(i);
                }
                else if(!impacted)
                    impactedStatements.add(location);
                //Check if it should be only true controlled or not
                //Recursively
                if (CommonBlockExtractor.isControlSmt(st)) {
                    Statement trueControlled=(st instanceof IfStmt)?(st.asIfStmt()).getThenStmt():((NodeWithBody)st).getBody();
                    addControlStatements(trueControlled,st.getBegin().get().line,impactedStatements);
                   // Statement falseControlled = null;
                }
            }
        }
    }
}
