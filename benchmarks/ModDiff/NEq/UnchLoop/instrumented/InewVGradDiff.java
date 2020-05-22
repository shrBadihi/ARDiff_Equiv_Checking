package demo.benchmarks.ModDiffNeq.UnchLoop.instrumented;
public class InewVGradDiff{
	public static int snippet(int a, int b) {
		int c=1;
		for (int i=0;i<a+1;++i) {
			c=c+b;
		}
		return c;
	}
public static void main(String[] args){
InewVGradDiff temp = new InewVGradDiff();
temp.snippet(1,1);
}
}
