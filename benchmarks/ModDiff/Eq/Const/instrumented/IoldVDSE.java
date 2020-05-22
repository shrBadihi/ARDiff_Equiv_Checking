package demo.benchmarks.ModDiffEq.Const.instrumented;
public class IoldVDSE{
	public static double snippet(int a, int b) {
		int d = 3;
		int c=a+b;
		return c+d;
	}
public static void main(String[] args){
IoldVDSE temp = new IoldVDSE();
temp.snippet(1,1);
}
}
