package demo.benchmarks.ModDiffEq.Add.instrumented;
public class InewVDSE{
	public static double snippet(int a, int b) {
		int d = 3;
		int c=a+b;
		return c+d;
	}
public static void main(String[] args){
InewVDSE temp = new InewVDSE();
temp.snippet(1,1);
}
}
