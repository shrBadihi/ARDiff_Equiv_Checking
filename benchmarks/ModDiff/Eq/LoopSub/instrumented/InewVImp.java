package demo.benchmarks.ModDiffEq.LoopSub.instrumented;
public class InewVImp{
	public static double snippet() {
		int a = 900;
		int b = 5;
		int c=b;
		for (int i=0;i<3;++i)
			c-=a;
		return c;
	}
public static void main(String[] args){
InewVImp temp = new InewVImp();
temp.snippet();
}
}
