package demo.benchmarks.ModDiffEq.LoopSub.instrumented;
public class IoldVGradDiff{
	public static double snippet() {
		int a = 5;
		int b = 900;
		int c=a;
		for (int i=0;i<3;++i)
			c-=b;
		return c;
	}
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet();
}
}
