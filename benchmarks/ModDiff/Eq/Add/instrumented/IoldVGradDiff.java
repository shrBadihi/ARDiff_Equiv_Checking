package demo.benchmarks.ModDiffEq.Add.instrumented;
public class IoldVGradDiff{
    public static double snippet(int a, int b) {
        int c = a + b;
        return c;
    }
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet(1,1);
}
}
