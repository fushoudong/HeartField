package com.bupt.heartarea.bloodpressure;

import Jama.Matrix;

//神经网络拟合
public class fitnet {

    public static double[] fitnnet(Matrix input) {

// SBP//
        //inputlayer
        Matrix Sinput = input.copy();
        Matrix Dinput = input.copy();
        Sinput.minusEquals(NetConfig.SBPinputlayer.Xoffset);
        Sinput.arrayTimesEquals(NetConfig.SBPinputlayer.Gain);
        Sinput.plusEquals(NetConfig.SBPinputlayer.Ymin);
        //hiddenlayer
        Matrix Xp1 = Sinput;
        Matrix Xp2 = NetConfig.SBPhiddenlayer.W.times(Xp1);
        Xp2.plusEquals(NetConfig.SBPhiddenlayer.B);
        Matrix a1 = tansig(Xp2);
        //linelayer
        Matrix a2 = NetConfig.SBPoutputlayer.W.times(a1);
        double SBP = a2.get(0, 0) + NetConfig.SBPoutputlayer.B;
        //outputlayer
        SBP = SBP - NetConfig.SBPoutput.ymin;
        SBP = SBP / NetConfig.SBPoutput.gain;
        SBP = SBP + NetConfig.SBPoutput.xoffset;
        System.out.println("SBP");
        System.out.println(SBP);
        double[] result = new double[2];
        result[0] = SBP;
        // DBP//
        //inputlayer
        Dinput.minusEquals(NetConfig.DBPinputlayer.Xoffset);
        Dinput.arrayTimesEquals(NetConfig.DBPinputlayer.Gain);
        Dinput.plusEquals(NetConfig.DBPinputlayer.Ymin);
        //hiddenlayer
        Matrix DXp1 = Dinput;
        Matrix DXp2 = NetConfig.DBPhiddenlayer.W.times(DXp1);
        DXp2.plusEquals(NetConfig.DBPhiddenlayer.B);
        Matrix Da1 = tansig(DXp2);
        //linelayer
        Matrix Da2 = NetConfig.DBPoutputlayer.W.times(Da1);
        double DBP = Da2.get(0, 0) + NetConfig.DBPoutputlayer.B;
        //outputlayer
        DBP = DBP - NetConfig.DBPoutput.ymin;
        DBP = DBP / NetConfig.DBPoutput.gain;
        DBP = DBP + NetConfig.DBPoutput.xoffset;
        System.out.println("DBP");
        System.out.println(DBP);
        result[1] = DBP;
        return result;
    }

    public static Matrix tansig(Matrix x) {
        double[][] z = new double[x.getRowDimension()][x.getColumnDimension()];

        for (int i = 0; i < x.getRowDimension(); i++) {
            for (int j = 0; j < x.getColumnDimension(); j++) {
                z[i][j] = tansigmoid(x.get(i, j));

            }
        }
        Matrix Z = Matrix.constructWithCopy(z);
        return Z;
    }

    public static double tansigmoid(double t) {
        return (2 / (1 + Math.pow(Math.E, (-2 * t)))) - 1;
    }
}
