package IMPs;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.FuncDecl;
import com.microsoft.z3.Symbol;
import equiv.checking.SymbolicExecutionRunner;
import equiv.checking.symparser.AbstractSymParser;
import equiv.checking.symparser.SymParserSMTLib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static equiv.checking.Utils.DEBUG;

public class ImpactedSummariesRunner extends SymbolicExecutionRunner {
    ArrayList<Integer> impacted;

    public ImpactedSummariesRunner(String path, String packageName,String oldFile, String newFile, String targetMethod, int methodInputs, ArrayList<Integer> impacted, ArrayList<Integer> impacted2,int bound, int timeout, String SMTSolver, int minint, int maxint, double mindouble, double maxdouble, long minlong, long maxlong,boolean parseFromSMTLib,boolean z3Terminal) {
        super(path,packageName,oldFile, newFile, targetMethod, methodInputs,bound, timeout, SMTSolver,  minint,  maxint,  mindouble,  maxdouble,  minlong,  maxlong,parseFromSMTLib,z3Terminal);
        this.impacted=new ArrayList<>(impacted);
    }

    public ImpactedSummariesRunner(String oldFile, String newFile, String targetMethod, int methodInputs, ArrayList<Integer> impacted, ArrayList<Integer> impacted2) {
        super(oldFile, newFile, targetMethod, methodInputs);
        this.impacted=new ArrayList<>(impacted);
    }

    @Override
    public String obtainConstraint(String st){
        //here we need to keep only what is impacted
        String[] split=st.split(":");
        //check if the line is in impacted
        if(impacted.contains(Integer.parseInt(split[0]))) {
            if(DEBUG)System.out.println("Contained " + split[0]+"  "+split[1]);
            return split[1].split("&&")[0];
        }
       return null;
    }

    @Override
    public String obtainReturn(String st) {
        String[] split=st.split(": Ret = ");
        if(impacted.contains(Integer.parseInt(split[0]))) {
            if(DEBUG) System.out.println("Contained " + split[0]+"  "+split[1]);
            return split[1];
        }
        return null;
    }
}
