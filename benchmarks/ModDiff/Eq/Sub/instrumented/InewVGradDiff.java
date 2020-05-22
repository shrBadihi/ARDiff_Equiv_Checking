package demo.benchmarks.ModDiffEq.Sub.instrumented;
public class InewVGradDiff{
	public static double snippet() {
		int a = 900;
		int b = 5;
		int c=b-a;
		return c;
	}
public static void main(String[] args){
InewVGradDiff temp = new InewVGradDiff();
temp.snippet();
}
}
