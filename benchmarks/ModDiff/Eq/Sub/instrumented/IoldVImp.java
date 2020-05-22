package demo.benchmarks.ModDiffEq.Sub.instrumented;
public class IoldVImp{
	public static double snippet() {
		int a = 5;
		int b = 900;
		int c=a-b;
		return c;
	}
public static void main(String[] args){
IoldVImp temp = new IoldVImp();
temp.snippet();
}
}
