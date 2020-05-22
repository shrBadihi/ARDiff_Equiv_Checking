package demo.benchmarks.ModDiffEq.UnchLoop.instrumented;
public class InewVImp{
	public static double snippet(int a, int b) {
		int c=1;
		for (int i=0;i<a;++i) {
			c=c+b;
		}
		return c;
	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet(1,1);
}
}
