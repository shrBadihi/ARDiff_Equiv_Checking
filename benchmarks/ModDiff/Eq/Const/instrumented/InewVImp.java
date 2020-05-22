package demo.benchmarks.ModDiffEq.Const.instrumented;
public class InewVImp{
	public static double snippet(int a, int b) {
		int c=a+b;
		return c+3;
	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet(1,1);
}
}
