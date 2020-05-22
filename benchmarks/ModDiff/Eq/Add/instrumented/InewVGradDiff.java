package demo.benchmarks.ModDiffEq.Add.instrumented;
public class InewVGradDiff{
	public static double snippet(int a, int b) {
		int c = b + a;
		return c;
	}
public static void main(String[] args){
InewVGradDiff temp = new InewVGradDiff();
temp.snippet(1,1);
}
}
