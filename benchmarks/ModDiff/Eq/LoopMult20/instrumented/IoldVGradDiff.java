package demo.benchmarks.ModDiffEq.LoopMult20.instrumented;
public class IoldVGradDiff{
	public static int snippet(int x) {
		if (x>=18 && x<22){
			int c=0;
			for (int i=1;i<=20;++i)
				c+=x;
			return c;
		}
		return 0;
	}
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet(1);
}
}
