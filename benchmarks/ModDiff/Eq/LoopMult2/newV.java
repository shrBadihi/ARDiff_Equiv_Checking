package demo.benchmarks.ModDiffEq.LoopMult2;
public class newV {
	public static double snippet(int a, int b) {
		int c=0;
		if (a==b) {
			for (int i = 1; i <= b; ++i)
				c += a;
		}
		return c;
	}
}