package demo.benchmarks.Bess.bessi.NEq;
public class oldV{
    public static double snippet(int n, double x) {
        double ACC=200.0;
        int IEXP= 2^1024;
        int j = 0;
        int k = 0;
        double bi = 0;
        double bim = 0;
        double bip = 0;
        double dum = 0;
        double tox = 0;
        double ans = 0;
        if (n < 2)
            return -10000; 
        if (x*x <= 8.0*(2 ^(1074)))
            return 0.0;
        else {
            tox=2.0/Math.abs(x);
            bip=ans=0.0;
            bi=1.0;
            for (j=(int) (2*(n+(int)(Math.sqrt(ACC*n))));j>0;j--) {
                bim=bip+j*tox*bi;
                bip=bi;
                bi=bim;
                k = (int)(20.0*bi);
                dum=bi/Math.pow(2, bi);
                if (k > IEXP) {
                    ans=ans*Math.pow(2, -IEXP);
                    bi=bi*Math.pow(2, -IEXP);
                    bip=bip*Math.pow(2, -IEXP);
                }
                if (j == n)
                    ans = bip;
            }
            ans *= bessi0(x)/bi;
            if (x < 0.0 )
                return -ans;
            else
                return ans;
        }
    }
    private static double bessi0(double x) {
        double ax,ans,y;

        if ((ax=Math.abs(x)) < 3.75) {
            y=x/3.75;
            y*=y;
            ans=1.0+y*(3.5156229+y*(3.0899424+y*(1.2067492
                    +y*(0.2659732+y*(0.360768e-1+y*0.45813e-2)))));
        } else {
            y=3.75/ax;
            ans=(Math.exp(ax)/Math.sqrt(ax))*(0.39894228+y*(0.1328592e-1
                    +y*(0.225319e-2+y*(-0.157565e-2+y*(0.916281e-2
                    +y*(-0.2057706e-1+y*(0.2635537e-1+y*(-0.1647633e-1
                    +y*0.392377e-2))))))));
        }
        return ans;
    }
}