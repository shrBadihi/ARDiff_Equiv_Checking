package demo.benchmarks.ModDiffEq.Add.instrumented;
public class InewVImp{
	public static double snippet(int a, int b) {
		int c = b + a;
		return c;
	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet(1,1);
}
}
