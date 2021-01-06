package demo.benchmarks.Ell.zbrent.Eq;
public class newV{
  public static double snippet (double x1, double x2, double tol) {
    int ITMAX=2;
    double EPS=1e-14;
    int iter = 0;
    double a=x1;
    double b=x2;
    double c=x2;
    double d=0;
    double e=0;
    double min1= 0;
    double min2= 0;
    double fa=Math.sin(a);
    double fb=Math.sin(b);
    double fc= 0;
    double p= 0;
    double q= 0;
    double r= 0;
    double s= 0;
    double tol1= 0;
    double xm= 0;
    fc=fb;
    for (iter=0;iter<ITMAX;iter++) {
      if ((fb > 0.0 && fc > 0.0) || (fb < 0.0 && fc < 0.0)) {
        c=a;
        fc=fa;
        e=b-a;
        d=b-a;
      }
      if (Math.abs(fc) < Math.abs(fb)) {
        a=b;
        b=c;
        c=a;
        fa=fb;
        fb=fc;
        fc=fa;
      }
      tol1=2.0*EPS*Math.abs(b)+0.5*tol;
      xm=0.5*(c-b);
      if (Math.abs(xm) <= tol1 || fb == 0.0)
        return b;
      if (Math.abs(e) >= tol1 && Math.abs(fa) > Math.abs(fb)) {
        s=fb/fa;
        if (a == c) {
          p=2.0*xm*s;
          q=1.0-s;
        } else {
          q=fa/fc;
          r=fb/fc;
          p=s*(2.0*xm*q*(q-fb/fc)-(b-a)*(fb/fc-1.0));//change
          q=(q-1.0)*(r-1.0)*(s-1.0);
        }
        if (p > 0.0) q = -q;
        p=Math.abs(p);
        min1=3.0*xm*q-Math.abs(tol1*q);
        min2=Math.abs(e*q);
        if (2.0*p < (min1 < min2 ? min1 : min2)) {
          e=d;
          d=p/q;
        } else {
          d=xm;
          e=d;
        }
      } else {
        d=xm;
        e=d;
      }
      a=b;
      fa=fb;
      if (Math.abs(d) > tol1)
        b += d;
      else
        b += SIGN(tol1,xm);
      fb=Math.sin(b);
      return Math.sin(b);//change
    }
    return 0.0;
  }
  public static double SQR(double a) {
    return a*a;
  }

  public static double SIGN(double a, double b){
    return b >= 0 ? (a >= 0 ? a : -a) : (a >= 0 ? -a : a);
  }

  public static double MAX(double a, double b){
    return b > a ? (b) : (a);
  }

  public static double MIN(double a, double b){
    return b < a ? (b) : (a);
  }
}