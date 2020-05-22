package demo.benchmarks.ModDiffEq.LoopMult10.instrumented;
public class IoldVImp{
	public static int snippet(int x) {
		if (x>=9 && x<12){
			int c=0;
			for (int i=1;i<=10;++i)
				c+=x;
			return c;
		}
		return 0;
	}
public static void main(String[] args){
IoldVImp temp = new IoldVImp();
temp.snippet(1);
}
}
