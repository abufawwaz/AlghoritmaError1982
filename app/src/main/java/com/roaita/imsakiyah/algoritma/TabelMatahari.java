package com.roaita.imsakiyah.algoritma;






public class TabelMatahari {


    public static double[] bujurEkliptik(double tau,double koreksiBujurB){

        double l0=0;
        l0+=175347046*Math.cos(0+0*tau);
		l0+=3341656*Math.cos(4.6692568+6283.07585*tau);
        l0+=34896*Math.cos(4.6261+12566.1517*tau);
		l0+=3497*Math.cos(2.7441+5753.3849*tau);
		l0+=3418*Math.cos(2.8289+3.5231*tau);
		l0+=3136*Math.cos(3.6277+77713.7715*tau);
		l0+=2676*Math.cos(4.4181+7860.4194*tau);
		l0+=2343*Math.cos(6.1352+3930.2097*tau);
		l0+=1324*Math.cos(0.7425+11506.7691*tau);
		l0+=1273*Math.cos(2.0371+529.691*tau);
		l0+=1199*Math.cos(1.1096+1577.3435*tau);
		l0+=990*Math.cos(5.233+5884.927*tau);
		l0+=902*Math.cos(2.045+26.298*tau);
		l0+=857*Math.cos(3.508+398.149*tau);
		l0+=780*Math.cos(1.179+5223.694*tau);
		l0+=753*Math.cos(2.533+5507.553*tau);
		l0+=505*Math.cos(4.583+18849.228*tau);
		l0+=492*Math.cos(4.205+775.523*tau);
		l0+=357*Math.cos(2.92+0.067*tau);
		l0+=317*Math.cos(5.849+11790.629*tau);
		l0+=284*Math.cos(1.899+796.298*tau);
		l0+=271*Math.cos(0.315+10977.079*tau);
		l0+=243*Math.cos(0.345+5486.778*tau);
		l0+=206*Math.cos(4.806+2544.314*tau);
		l0+=205*Math.cos(1.869+5573.143*tau);
		l0+=202*Math.cos(2.458+6069.777*tau);
		l0+=156*Math.cos(0.833+213.299*tau);
		l0+=132*Math.cos(3.411+2942.463*tau);
		l0+=126*Math.cos(1.083+20.775*tau);
		l0+=115*Math.cos(0.645+0.98*tau);
		l0+=103*Math.cos(0.636+4694.003*tau);
		l0+=102*Math.cos(0.976+15720.839*tau);
		l0+=102*Math.cos(4.267+7.114*tau);
		l0+=99*Math.cos(6.21+2146.17*tau);
		l0+=98*Math.cos(0.68+155.42*tau);
		l0+=86*Math.cos(5.98+161000.69*tau);
		l0+=85*Math.cos(1.3+6275.96*tau);
		l0+=85*Math.cos(3.67+71430.7*tau);
		l0+=80*Math.cos(1.81+17260*tau);
		l0+=79*Math.cos(3.04+12036.46*tau);
		l0+=75*Math.cos(1.76+5088.63*tau);
		l0+=74*Math.cos(3.5+3154.69*tau);
		l0+=74*Math.cos(4.68+801.82*tau);
		l0+=70*Math.cos(0.83+9437.76*tau);
		l0+=62*Math.cos(3.98+8827.39*tau);
		l0+=61*Math.cos(1.82+7084.9*tau);
		l0+=57*Math.cos(2.78+6286.6*tau);
		l0+=56*Math.cos(4.39+14143.5*tau);
		l0+=56*Math.cos(3.47+6279.55*tau);
		l0+=52*Math.cos(0.19+12139.55*tau);
		l0+=52*Math.cos(1.33+1748.02*tau);
		l0+=51*Math.cos(0.28+5856.48*tau);
		l0+=49*Math.cos(0.49+1194.45*tau);
		l0+=41*Math.cos(5.37+8429.24*tau);
		l0+=41*Math.cos(2.4+19651.05*tau);
		l0+=39*Math.cos(6.17+10447.39*tau);
		l0+=37*Math.cos(6.04+10213.29*tau);
		l0+=37*Math.cos(2.57+1059.38*tau);
		l0+=36*Math.cos(1.71+2352.87*tau);
		l0+=36*Math.cos(1.78+6812.77*tau);
		l0+=33*Math.cos(0.59+17789.85*tau);
		l0+=30*Math.cos(0.44+83996.85*tau);
		l0+=30*Math.cos(2.74+1349.87*tau);
		l0+=25*Math.cos(3.16+4690.48*tau);


		double l1=0;
        l1+= 628331966747L *Math.cos(0+0*tau);
        l1+=206059*Math.cos(2.678235+6283.07585*tau);
        l1+=4303*Math.cos(2.6351+12566.1517*tau);
        l1+=425*Math.cos(1.59+3.523*tau);
        l1+=119*Math.cos(5.796+26.298*tau);
        l1+=109*Math.cos(2.966+1577.344*tau);
        l1+=93*Math.cos(2.59+18849.23*tau);
        l1+=72*Math.cos(1.14+529.69*tau);
        l1+=68*Math.cos(1.87+398.15*tau);
        l1+=67*Math.cos(4.41+5507.55*tau);
        l1+=59*Math.cos(2.89+5223.69*tau);
        l1+=56*Math.cos(2.17+155.42*tau);
        l1+=45*Math.cos(0.4+796.3*tau);
        l1+=36*Math.cos(0.47+775.52*tau);
        l1+=29*Math.cos(2.65+7.11*tau);
        l1+=21*Math.cos(5.34+0.98*tau);
        l1+=19*Math.cos(1.85+5486.78*tau);
        l1+=19*Math.cos(4.97+213.3*tau);
        l1+=17*Math.cos(2.99+6275.96*tau);
        l1+=16*Math.cos(0.03+2544.31*tau);
        l1+=16*Math.cos(1.43+2146.17*tau);
        l1+=15*Math.cos(1.21+10977.08*tau);
        l1+=12*Math.cos(2.83+1748.02*tau);
        l1+=12*Math.cos(3.26+5088.63*tau);
        l1+=12*Math.cos(5.27+1194.45*tau);
        l1+=12*Math.cos(2.08+4694*tau);
        l1+=11*Math.cos(0.77+553.57*tau);
        l1+=10*Math.cos(1.3+6286.6*tau);
        l1+=10*Math.cos(4.24+1349.87*tau);
        l1+=9*Math.cos(2.7+242.73*tau);
        l1+=9*Math.cos(5.64+951.72*tau);
        l1+=8*Math.cos(5.3+2352.87*tau);
        l1+=6*Math.cos(2.65+9437.76*tau);
        l1+=6*Math.cos(4.67+4690.48*tau);
        l1*=tau;

        double l2=0;
		l2+=52919*Math.cos(0+0*tau);
		l2+=8720*Math.cos(1.0721+6283.0758*tau);
		l2+=309*Math.cos(0.867+12566.152*tau);
		l2+=27*Math.cos(0.05+3.52*tau);
		l2+=16*Math.cos(5.19+26.3*tau);
		l2+=16*Math.cos(3.68+155.42*tau);
		l2+=10*Math.cos(0.76+18849*tau);
		l2+=9*Math.cos(2.06+77713.77*tau);
		l2+=7*Math.cos(0.83+775.52*tau);
		l2+=5*Math.cos(4.66+1577.34*tau);
		l2+=4*Math.cos(1.03+7.11*tau);
		l2+=4*Math.cos(3.44+5573.14*tau);
		l2+=3*Math.cos(5.14+796.3*tau);
		l2+=3*Math.cos(6.05+5507.55*tau);
		l2+=3*Math.cos(1.19+242.73*tau);
		l2+=3*Math.cos(6.12+529.69*tau);
		l2+=3*Math.cos(0.31+398.15*tau);
		l2+=3*Math.cos(2.28+553.57*tau);
		l2+=2*Math.cos(4.38+5223.69*tau);
		l2+=2*Math.cos(3.75+0.98*tau);
		l2*=tau*tau;


		double l3=0;
		l3+=289*Math.cos(5.844+6283.076*tau);
		l3+=35*Math.cos(0+0*tau);
		l3+=17*Math.cos(5.49+12566.15*tau);
		l3+=3*Math.cos(5.2+155.42*tau);
		l3+=1*Math.cos(4.72+3.52*tau);
		l3+=1*Math.cos(5.3+18849.23*tau);
		l3+=1*Math.cos(5.97+242.73*tau);
		l3*=tau*tau*tau;

		double l4=0;
		l4+=114*Math.cos(3.142+0*tau);
		l4+=8*Math.cos(4.13+6283.08*tau);
		l4+=1*Math.cos(3.84+12566.15*tau);
		l4*=tau*tau*tau*tau;

		double l5=0;
		l5+=1*Math.cos(3.14+0*tau);
		l5*=tau*tau*tau*tau*tau;
		double l=(l0+l1+l2+l3+l4+l5)/100000000L;
		double l_d=Math.toDegrees(l)%360;
		double theta=(l_d+180)%360;
		double lamdaM=(theta-1.397*koreksiBujurB-0.00031*koreksiBujurB*koreksiBujurB)%360;
		double deltaTheta=-0.09033/3600;
		double thetaTerkoreksi=(theta+deltaTheta)%360;
		return new double[] {0,l_d,theta,lamdaM,deltaTheta,thetaTerkoreksi};
    }

    public static double jarakBumiMat(double tau){
		double r0=0;
		r0+=100013989*Math.cos(0+0*tau);
		r0+=1670700*Math.cos(3.0984635+6283.07585*tau);
		r0+=13956*Math.cos(3.05525+12566.1517*tau);
		r0+=3084*Math.cos(5.1985+77713.7715*tau);
		r0+=1628*Math.cos(1.1739+5753.3849*tau);
		r0+=1576*Math.cos(2.8469+7860.4194*tau);
		r0+=925*Math.cos(5.453+11506.77*tau);
		r0+=542*Math.cos(4.564+3930.21*tau);
		r0+=472*Math.cos(3.661+5884.927*tau);
		r0+=346*Math.cos(0.964+5507.553*tau);
		r0+=329*Math.cos(5.9+5223.694*tau);
		r0+=307*Math.cos(0.299+5573*tau);
		r0+=243*Math.cos(4.273+11790.629*tau);
		r0+=212*Math.cos(5.847+1577.344*tau);
		r0+=186*Math.cos(5.022+10977.079*tau);
		r0+=175*Math.cos(3.012+18849.228*tau);
		r0+=110*Math.cos(5.055+5486.778*tau);
		r0+=98*Math.cos(0.89+6069.78*tau);
		r0+=86*Math.cos(5.69+15720.84*tau);
		r0+=86*Math.cos(1.27+161000.69*tau);
		r0+=65*Math.cos(0.27+17260.15*tau);
		r0+=63*Math.cos(0.92+529.69*tau);
		r0+=57*Math.cos(2.01+83996.85*tau);
		r0+=56*Math.cos(5.24+71430.7*tau);
		r0+=49*Math.cos(3.25+2544.31*tau);
		r0+=47*Math.cos(2.58+775.52*tau);
		r0+=45*Math.cos(5.54+9437.76*tau);
		r0+=43*Math.cos(6.01+6275.96*tau);
		r0+=39*Math.cos(5.36+4694*tau);
		r0+=38*Math.cos(2.39+8827.39*tau);
		r0+=37*Math.cos(0.83+19651.05*tau);
		r0+=37*Math.cos(4.9+12139.55*tau);
		r0+=36*Math.cos(1.67+12036.46*tau);
		r0+=35*Math.cos(1.84+2942.46*tau);
		r0+=33*Math.cos(0.24+7084.9*tau);
		r0+=32*Math.cos(0.18+5088.63*tau);
		r0+=32*Math.cos(1.78+398.15*tau);
		r0+=28*Math.cos(1.21+6286.6*tau);
		r0+=28*Math.cos(1.9+6279.55*tau);
		r0+=26*Math.cos(4.59+10447.39*tau);

		double r1=0;
		r1+=103019*Math.cos(1.10749+6283.07585*tau);
		r1+=1721*Math.cos(1.0644+12566.1517*tau);
		r1+=702*Math.cos(3.142+0*tau);
		r1+=32*Math.cos(1.02+18849.23*tau);
		r1+=31*Math.cos(2.84+5507.55*tau);
		r1+=25*Math.cos(1.32+5223.69*tau);
		r1+=18*Math.cos(1.42+1577.34*tau);
		r1+=10*Math.cos(5.91+10977.08*tau);
		r1+=9*Math.cos(1.42+6275.96*tau);
		r1+=9*Math.cos(0.27+5486.78*tau);
		r1*=tau;

		double r2=0;
		r2+=4359*Math.cos(5.7846+6283.0758*tau);
		r2+=124*Math.cos(5.579+12566.152*tau);
		r2+=12*Math.cos(3.14+0*tau);
		r2+=9*Math.cos(3.63+77713.77*tau);
		r2+=6*Math.cos(1.87+5573.14*tau);
		r2+=3*Math.cos(5.47+18849.23*tau);
		r2*=tau*tau;

		double r3=0;
		r3+=145*Math.cos(4.273+6283.076*tau);
		r3+=7*Math.cos(3.92+12566.15*tau);
		r3*=tau*tau*tau;

		double r4=0;
		r4+=4*Math.cos(2.56+6283.08*tau);
		r4*=tau*tau*tau*tau;

		double R=(r0+r1+r2+r3+r4)/100000000;


		return R;
    }

    public  static double[]lintangEkliptikB(double tau,double lambdaM_r){
		double b0=0;
		b0+=280*Math.cos(3.199+84334.662*tau);
		b0+=102*Math.cos(5.422+5507.553*tau);
		b0+=80*Math.cos(3.88+5223.69*tau);
		b0+=44*Math.cos(3.7+2352.87*tau);
		b0+=32*Math.cos(4+1577.34*tau);
		double b1=0;
		b1+=9*Math.cos(3.9+5507.55*tau);
		b1+=6*Math.cos(1.73+5223.69*tau);
		b1*=tau;

		double B=(b0+b1)/100000000;
		double B_detikBusur=Math.toDegrees(B)*3600;
		double beta=-B_detikBusur;
		double deltaBeta=0.03916*(Math.cos(lambdaM_r)-Math.sin(lambdaM_r));
		double betaTerkoreksi=beta+deltaBeta;

		return new double[]{0,B,betaTerkoreksi};
    }

// l4+=*Math.cos(+*tau);
	//r0+=*Math.cos(+*tau);
}
