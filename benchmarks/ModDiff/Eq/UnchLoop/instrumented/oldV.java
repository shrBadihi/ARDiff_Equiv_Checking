package demo.benchmarks.ModDiffEq.UnchLoop.instrumented;
public class oldV{
	public static double snippet(int a, int b) {
		int c=0;
		for (int i=0;i<a;++i) {
			c=c+b;
		}
		return c+1;
	}
}
