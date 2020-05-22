package demo.benchmarks.ModDiffEq.Const.instrumented;
public class InewVDSE{
	public static double snippet(int a, int b) {
		int d = 3;
		int c=a+b;
		return c+3;
	}
public static void main(String[] args){
InewVDSE temp = new InewVDSE();
temp.snippet(1,1);
}
}
