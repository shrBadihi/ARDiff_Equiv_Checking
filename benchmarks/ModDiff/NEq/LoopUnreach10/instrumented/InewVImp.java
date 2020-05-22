package demo.benchmarks.ModDiffNeq.LoopUnreach10.instrumented;
public class InewVImp{
	public static int snippet(int x) {
		if (x>=9 && x<12) {
			int c = 0;
			if (x < 0) {
				for (int i = 1; i <= 10; ++i)
					c -= x;
			}
			return c;
		}
		return 0;

	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet(1);
}
}
