package demo.benchmarks.ModDiffEq.Add.instrumented;
public class IoldVImp{
    public static double snippet(int a, int b) {
        int c = a + b;
        return c;
    }
public static void main(String[] args){
IoldVImp temp = new IoldVImp();
temp.snippet(1,1);
}
}
