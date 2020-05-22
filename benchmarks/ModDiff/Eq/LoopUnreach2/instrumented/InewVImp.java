package demo.benchmarks.ModDiffEq.LoopUnreach2.instrumented;
public class InewVImp{
	public static int snippet(int a, int b) {
		int c=0;
		if (a<0) {
			if (a==b) {
				for (int i = 1; i <= a; ++i)
					c += b;
			}
		}
		return c;
	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet(1,1);
}
}
