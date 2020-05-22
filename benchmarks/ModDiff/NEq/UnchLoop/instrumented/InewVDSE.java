package demo.benchmarks.ModDiffNeq.UnchLoop.instrumented;
public class InewVDSE{
	public static int snippet(int a, int b) {
		int c=1;
		for (int i=0;i<a+1;++i) {
c = UF_c_1(b,c);
		}
		return c;
	}
public static int UF_c_1(int Unb,int Unc){
return 1;
}
public static void main(String[] args){
InewVDSE temp = new InewVDSE();
temp.snippet(1,1);
}
}
