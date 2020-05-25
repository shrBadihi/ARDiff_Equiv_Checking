package instrumented;
import equiv.checking.symbex.UnInterpreted;
import equiv.checking.symbex.UnInterpreted;
public class oldV{
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
}
