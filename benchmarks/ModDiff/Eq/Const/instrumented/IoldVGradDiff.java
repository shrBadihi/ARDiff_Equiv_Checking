package demo.benchmarks.ModDiffEq.Const.instrumented;
public class IoldVGradDiff{
	public static double snippet(int a, int b) {
		int d = 3;
		int c=a+b;
		return c+d;
	}
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet(1,1);
}
}
