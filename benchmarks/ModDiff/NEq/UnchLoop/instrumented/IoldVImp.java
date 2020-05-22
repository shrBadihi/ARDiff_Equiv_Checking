package demo.benchmarks.ModDiffNeq.UnchLoop.instrumented;
public class IoldVImp{
	public static int snippet(int a, int b) {
		int c=0;
		for (int i=0;i<a;++i) {
			c=c+b;
		}
		return c+1;
	}
public static void main(String[] args){
IoldVImp temp = new IoldVImp();
temp.snippet(1,1);
}
}
