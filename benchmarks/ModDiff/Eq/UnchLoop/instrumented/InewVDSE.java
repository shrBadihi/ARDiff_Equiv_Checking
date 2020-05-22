package demo.benchmarks.ModDiffEq.UnchLoop.instrumented;
public class InewVDSE{
	public static double snippet(int a, int b) {
		int c=1;
int i = UF_i_1(a);
c = UF_c_1(a,b,c);
		return c;
	}
public static int UF_i_1(int Una){
return 1;
}
public static int UF_c_1(int Una,int Unb,int Unc){
return 1;
}
public static void main(String[] args){
InewVDSE temp = new InewVDSE();
temp.snippet(1,1);
}
}
