package demo.benchmarks.Ran.gasdev.NEq;
public class newV{
    public static double snippet ( int idumx,int idum) {//idum is a global var
        idum = idumx;
        int iset=0;
        double gset = 10;//change
        double fac = 0;
        double rsq = 0;
        double v1 = 0;
        double v2 = 0;
        if (idum < 0)
            iset=1;
        if (iset == 1) {//change
            do {
                v1=2.0*ran1(idum)-1.0;
                v2=2.0*ran1(idum)-1.0;
                rsq=v1*v1+v2*v2;
            } while (rsq >= 1.0 || rsq == 0.0);
            fac=Math.sqrt(-2.0*Math.log(rsq)/rsq);
            gset=v1*fac;
            iset=1;
            return v2*fac;
        }
        else {
            iset=0;
            return gset;
        }
    }
    public static double ran1(int idum){
        int IA=16807;
        int IM=2147483647;
        int IQ=127773;
        int IR=2836;
        int NTAB=32;
        int NDIV=(1+(IM-1)/NTAB);
        double EPS=3.0e-16;
        double AM=1.0/IM;
        double RNMX=(1.0-EPS);
        int iy=0;
        int[] iv = new int[NTAB];
        int j,k;
        double temp;
        if (idum <= 0 || iy == 0) {
            if (-idum < 1)
                idum=1;
            else
                idum = -idum;
            for (j=NTAB+7;j>=0;j--) {
                k=idum/IQ;
                idum=IA*(idum-k*IQ)-IR*k;
                if (idum < 0)
                    idum += IM;
                if (j < NTAB)
                    iv[j] = idum;
            }
            iy=iv[0];
        }
        k=idum/IQ;
        idum=IA*(idum-k*IQ)-IR*k;
        if (idum < 0)
            idum += IM;
        j=iy/NDIV;
        iy=iv[j];
        iv[j] = idum;
        if ((temp=AM*iy) > RNMX)
            return RNMX;
        else
            return temp;
    }
}