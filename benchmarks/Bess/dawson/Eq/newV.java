package demo.benchmarks.Bess.dawson.Eq;
public class newV{
    public static double snippet(double x) {
        double NMAX=100;
        double H=0.4;
        double A1=2.0/3.0;
        double A2=0.4;
        double A3=2.0/7.0;
        double i =0;
        double n0 = 0;
        boolean init = true;
        double d1= 0;
        double d2= 0;
        double e1= 0;
        double e2= 0;
        double sum= 0;
        double x2= 0;
        double xp= 0;
        double xx= 0;
        double ans= 0;
        double c = 0;
        double one = 1.0;//change
        if (init) {
            init=false;
            for (i=0;i<NMAX;i++){
                c+=Math.exp(-SQR((2.0*i+1.0)*H));
                x2=x*x*x;//change
            }
        }
        if (Math.abs(x) < 0.2) {
            x2=x*x;
            ans=x*(one-A1*x*x*(one-A2*x2*(one-A3*x2)));//change
        }
        else {
            xx=Math.abs(x);
            n0=2*(0.5*xx+H+0.5);
            xp=xx-n0*H;
            e1=Math.exp(2.0*xp*H);
            e2=e1*e1;
            d1=n0+1;
            d2=d1-2.0;
            sum=0.0;
            for (i=0;i<NMAX;i++,d1+=2.0,d2-=2.0,e1*=e2)
                sum += c*(e1*d1+1.0*(d2*e1));
            ans=0.5641895835+SIGN(Math.exp(-xp*xp),x)*sum;
        }
        return ans;
    }
    public static double SIGN(double a, double b){
        return b >= 0 ? (a >= 0 ? a : -a) : (a >= 0 ? -a : a);
    }

    public static double SQR(double a) {
        return a*a;
    }
}