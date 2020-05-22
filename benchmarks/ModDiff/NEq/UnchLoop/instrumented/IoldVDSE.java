package demo.benchmarks.ModDiffNeq.UnchLoop.instrumented;
public class IoldVDSE{
	public static int snippet(int a, int b) {
		int c=0;
		for (int i=0;i<a;++i) {
c = UF_c_1(b,c);
		}
		return c+1;
	}
public static int UF_c_1(int Unb,int Unc){
return 1;
}
public static void main(String[] args){
IoldVDSE temp = new IoldVDSE();
temp.snippet(1,1);
}
}
