package demo.benchmarks.ModDiffEq.Const.instrumented;
public class InewVGradDiff{
	public static double snippet(int a, int b) {
		int d = 3;
		int c=a+b;
		return c+3;
	}
public static void main(String[] args){
InewVGradDiff temp = new InewVGradDiff();
temp.snippet(1,1);
}
}
