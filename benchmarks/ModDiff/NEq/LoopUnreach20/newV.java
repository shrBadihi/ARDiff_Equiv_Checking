package demo.benchmarks.ModDiffNeq.LoopUnreach20;
public class newV{
	public static double snippet(int x) {
		if (x>=18 && x<22) {
			int c = 0;
			if (x < 0) {
				for (int i = 1; i <= 20; ++i)
					c -= x;
			}
			return c;
		}
		return 0;

	}
}