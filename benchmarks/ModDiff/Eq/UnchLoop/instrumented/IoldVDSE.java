package demo.benchmarks.ModDiffEq.UnchLoop.instrumented;
public class IoldVDSE{
	public static double snippet(int a, int b) {
		int c=0;
int i = UF_i_1(a);
c = UF_c_1(a,b,c);
		return c+1;
	}
public static int UF_i_1(int Una){
return 1;
}
public static int UF_c_1(int Una,int Unb,int Unc){
return 1;
}
public static void main(String[] args){
IoldVDSE temp = new IoldVDSE();
temp.snippet(1,1);
}
}
