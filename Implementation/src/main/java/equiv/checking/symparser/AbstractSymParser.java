package equiv.checking.symparser;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.FuncDecl;
import equiv.checking.Utils;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AbstractSymParser {
    protected SymLexer reader;
    protected String spfOutput;
    protected final Context context;
    protected String declarations;
    protected boolean noUFunctions;
    //Needed to reuse the same variables throughout the formulas
    protected final HashSet<String> mathFunctions;
    protected HashMap<Expr,Integer> uFunctions;
    protected HashSet<Expr> interprocCalls;
    protected Map<String, Expr> variables;
    //All the uninterpreted functions mapped to their declaration
    protected Map<String, Pair<FuncDecl, HashSet<Expr>>> allInstances;


    public AbstractSymParser(Context context){
        this.context = context;
        declarations = "";
        uFunctions = new HashMap<>();
        allInstances = new HashMap<>();
        variables = new HashMap<>();
        interprocCalls = new HashSet<>();
        mathFunctions = Utils.mathFunctions;
        noUFunctions = true;
        //initMathFunctions();
    }

    public void initMathFunctions(){
        mathFunctions.add("cos"); //yes
        mathFunctions.add("sin"); //yes
        mathFunctions.add("pow"); //yes
        mathFunctions.add("sqrt"); //yes
        mathFunctions.add("asin");
        mathFunctions.add("exp"); //yes
        mathFunctions.add("asin");
        mathFunctions.add("acos");
        mathFunctions.add("atan");
        mathFunctions.add("atan2");
        mathFunctions.add("abs");
        mathFunctions.add("log"); //yes
        mathFunctions.add("tan"); //yes
    }

    public Context context() {
        return context;
    }

    public HashMap<Expr, Integer> uFunctions() {
        return uFunctions;
    }

    public String declarations(){
        return declarations;
    }

    public void emptyUF() {
        uFunctions = new HashMap<>();
    }

    public Map<String, Expr> varNames() {
        return variables;
    }

    public HashSet<Expr> getInterprocCalls(){
        return interprocCalls;
    }

    public Map<String, Pair<FuncDecl, HashSet<Expr>>> functionInstances() {
        return allInstances;
    }

    public boolean noUFunctions() {
        return noUFunctions;
    }
}
