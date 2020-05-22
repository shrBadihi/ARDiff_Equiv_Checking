package demo.benchmarks.ModDiffEq.Sub.instrumented;
public class IoldVDSE{
	public static double snippet() {
		int a = 5;
		int b = 900;
		int c=a-b;
		return c;
	}
public static void main(String[] args){
IoldVDSE temp = new IoldVDSE();
temp.snippet();
}
}
