package demo.benchmarks.Ell.plgndr.Eq;
public class newV{
  public static double snippet (int l, int m, double x) {
    int i=0;
    int ll=0;
    double fact =1;
    double pll = 0;
    double pmm = 0;
    double pmmp1= 0;
    double somx2= 0;
    double result =0;//change
    if (m < 0 || m > l || Math.abs(x) > 1.0)
      return -1000;
    pmm=1.0;
    if (m > 0) {
      somx2=Math.sqrt((1.0-x)*(1.0+x));
      fact=pll+1.0;//change
      for (i=1;i<=m;i++) {
        pmm *= -fact*somx2;
        fact += 2.0;
      }
    }
    if (l == m){
      result =pmm;//change
      return result;//change
    }
    else {
      pmmp1=x*(2*m+1)*pmm;
      for (ll=m+2;ll<=l;ll++) {
        pll=(x*(2*ll-1)*pmmp1-(ll+m-1)*pmm)/(ll-m);
        pmm=pmmp1;
        pmmp1=pll;
      }
        return pll;
    }
  }
}