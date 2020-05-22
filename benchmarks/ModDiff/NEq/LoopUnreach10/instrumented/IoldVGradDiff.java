package demo.benchmarks.ModDiffNeq.LoopUnreach10.instrumented;
public class IoldVGradDiff{
	public static int snippet(int x) {
		if (x>=9 && x<12) {
int c = UF_c_1();
			if (x < 0) {
				for (int i = 1; i <= x; ++i)
					c -= 10;
			}
			return c;
		}
		return 0;

	}
public static int UF_c_1(){
return 1;
}
public static void main(String[] args){
IoldVGradDiff temp = new IoldVGradDiff();
temp.snippet(1);
}
}
