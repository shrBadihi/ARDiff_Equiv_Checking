package demo.benchmarks.ModDiffNeq.LoopMult10;
public class newV {
	public static double snippet(int x) {
		if (x>=9 && x<12){
			int c=0;
			for (int i=1;i<=x;++i)
				c+=10;
			return c;
		}
		return 0;
	}
}

