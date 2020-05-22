package demo.benchmarks.ModDiffNeq.LoopUnreach15.instrumented;
public class IoldVGradDiff{
	public static int snippet(int x) {
		if (x>=13 && x<16) {
			int c = 0;
			if (x < 0) {
				for (int i = 1; i <= x; ++i)
					c += 15;
			}
			return c;
		}
		return 0;

	}
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet(1);
}
}
