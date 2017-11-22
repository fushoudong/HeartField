package com.bupt.heartarea.bloodpressure;

import java.util.ArrayList;
import java.util.List;

public class Bloodpressure {

    public static int[] calculate(double[] data) {
        List<Point> points = new ArrayList<>();

        //ArrayList<Integer> samples = new ArrayList<Integer>();
        //data是未处理的原始数据
//        double[] data = {4.3535, 4.3459, 4.3431, 4.345, 4.3496, 4.3543, 4.3578, 4.3605, 4.364, 4.3688, 4.3737, 4.376, 4.3734, 4.3656, 4.3552, 4.346, 4.3412, 4.3415, 4.3451, 4.3498, 4.3541, 4.3583, 4.3633, 4.3691, 4.3738, 4.3747, 4.3702, 4.3609, 4.35, 4.3417, 4.3387, 4.3409, 4.3459, 4.351, 4.3548, 4.358, 4.3623, 4.3681, 4.3735, 4.3752, 4.3707, 4.3609, 4.3499, 4.343, 4.3429, 4.3486, 4.3562, 4.362, 4.3656, 4.3691, 4.3751, 4.3832, 4.3894, 4.389, 4.3798, 4.3646, 4.3496, 4.3408, 4.3405, 4.3461, 4.353, 4.358, 4.3606, 4.363, 4.3671, 4.3725, 4.3765, 4.3757, 4.3688, 4.3577, 4.3468, 4.3401, 4.3391, 4.3424, 4.3469, 4.3507, 4.3533, 4.3563, 4.3609, 4.3664, 4.3702, 4.3693, 4.3627, 4.3523, 4.342, 4.3357, 4.3349, 4.3382, 4.343, 4.3471, 4.3499, 4.3527, 4.3568, 4.362, 4.3662, 4.3669, 4.3623, 4.3531, 4.3426, 4.3348, 4.332, 4.3339, 4.3382, 4.3424, 4.3454, 4.3481, 4.352, 4.3573, 4.3622, 4.3637, 4.3598, 4.351, 4.3403, 4.3319, 4.3286, 4.3303, 4.3346, 4.339, 4.3423, 4.3452, 4.3495, 4.3554, 4.3605, 4.3618, 4.357, 4.3472, 4.3365, 4.3299, 4.3301, 4.336, 4.3441, 4.3508, 4.3547, 4.3569, 4.3599, 4.3649, 4.3708, 4.3748, 4.3741, 4.3676, 4.3573, 4.3471, 4.3407, 4.3397, 4.3429, 4.3477, 4.3517, 4.3542, 4.3567, 4.361, 4.3673, 4.3735, 4.376, 4.3717, 4.3611, 4.3479, 4.3376, 4.3343, 4.3379, 4.3454, 4.3525, 4.3569, 4.3589, 4.3609, 4.3648, 4.3706, 4.3758, 4.3767, 4.3713, 4.3602, 4.3474, 4.3378, 4.3349, 4.3385, 4.3455, 4.3521, 4.3566, 4.3595, 4.3628, 4.3675, 4.3726, 4.375, 4.3726, 4.3649, 4.3543, 4.3448, 4.3396, 4.3396, 4.3429, 4.347, 4.3501, 4.3522, 4.3545, 4.3586, 4.3644, 4.37, 4.3723, 4.3692, 4.3607, 4.3498, 4.3406, 4.3363, 4.3371, 4.3413, 4.346, 4.3494, 4.3515, 4.3537, 4.3577, 4.364, 4.371, 4.3754, 4.3738, 4.3651, 4.3517, 4.3388, 4.3315, 4.3319, 4.338, 4.3456, 4.3513, 4.3543, 4.3559, 4.3586, 4.3633, 4.369, 4.373, 4.3728, 4.3674, 4.3582, 4.3486, 4.3421, 4.3407, 4.3434, 4.3479, 4.3517, 4.3542, 4.3562, 4.3594, 4.3645, 4.3701, 4.3737, 4.3724, 4.3655, 4.3549, 4.3444, 4.338, 4.3371, 4.3406, 4.3457, 4.3502, 4.3534, 4.3565, 4.3607, 4.366, 4.3707, 4.372, 4.3679, 4.3588, 4.3477, 4.3385, 4.3342, 4.3352, 4.3393, 4.3438, 4.347, 4.3491, 4.3516, 4.3559, 4.3618, 4.3671, 4.3688, 4.365, 4.3559, 4.3447, 4.3356, 4.3314, 4.3324, 4.3366, 4.3414, 4.3451, 4.3478, 4.3506, 4.3549, 4.3609, 4.3673, 4.3716, 4.371, 4.3645, 4.3538, 4.3427, 4.3355, 4.3344, 4.3375, 4.3434, 4.3491, 4.3509, 4.3515, 4.3536, 4.3684, 4.3889};

        List<Double> sbp_list = new ArrayList<>();
        List<Double> dbp_list = new ArrayList<>();
        int[] loc = FindPeaks.findPeaks1(data, data.length);//loc为极小值位置,这里是用于切割独立波的，但是要是想实时显示的话，这部分得在安卓写，可能和你实时显示心率的非常相似，应该不难
        for (int k = 0; k < loc.length - 1; k++) {
            double[] onewave = cut(data, loc[k], loc[k + 1]);//注意！！如果要实时显示的话，这里你要检测出来最低值（我这里是切割成独立波了，但这并不符合实时要求），
            //也就是在安卓端分割成独立波，这部分的代码我真的不会写，如果要实时显示血压值的话，从此部分以下是你安卓端的核心代码。有疑问打电话。
            int[] loc2 = FindPeaks.findPeaks(onewave, onewave.length);//找到每一个独立波的顶点


            double[] upwave = cut(onewave, 0, loc2[0]);
            double[] newup = newdata(upwave);//插值成10个
            //double[] upindex=diff(newup);
            double[] downwave = cut(onewave, loc2[0], onewave.length - 1);
            double[] newdown = newdata(downwave);//插值成10个


            //double[] downindex=diff(newdown);
            double[] index = new double[20];
            for (int i = 0; i < 10; i++) {
                index[i] = newup[i];
            }

            for (int i = 10; i < 20; i++) {
                index[i] = newdown[i - 10];
            }

            double[] mapindex = mapminmax(index);
            double[] finalindex = new double[21];
            finalindex[0] = 1;
            for (int i = 1; i < 20; i++) {
                finalindex[i] = mapindex[i - 1];
            }
            double[] fithigh = {67.2209120462425, 8.43838095141596, -93.3011749852962, 187.922124759566, -224.686724622193, 219.958636195903, -170.494001618192, 89.8209024262375, -29.2139370530020, 40.4973013560613, 19.5046885968437, -96.8670242888453, 85.9476705734868, 10.7057684344899, 52.5085279996565, -6.06460100429090, -75.6087552259526, -23.0329995666635, 25.8421788908889, -22.0069360942437, 29.6989606262889};
            //拟合高压

            double[] fitlow = {104.9201194646375, -6.74634436681024, -7.59508097424364, 38.7356279797788, -66.5979496355510, 54.0568203922811, -8.21832035090916, -6.05433753615999, 1.23222564072182, 4.22034260412366, -34.0247313473926, -3.33299909147280, 5.74385115445103, 52.5680004609936, -76.7818529386125, 10.3463850757362, 27.9732597065332, -13.8635811221593, -25.1012049391952, 58.4728426238312, -32.588735142420};
            //拟合低压

//            double[] fithigh = {75.1146079090848, 7.31933520616679, -96.5859648047026, 194.465446686062, -229.638700577598, 218.629951245204, -162.161609238748, 79.4485590145590, -17.2083138366855, 32.2350167677314, 13.0418866129931, -91.2687828238840, 83.8830265721410, 26.4685352112174, 42.9848771622389, -11.9919167524770, -75.8274747908873, -17.5003065896723, 19.8203148661514, -14.1873647930435, 24.2914621026758};//拟合高压
//            double[] fitlow = {110.1501727848984, -5.63802672653539, -14.4622098197555, 49.8730469777926, -76.3030387836338, 57.8448022122832, -9.85864910173786, -3.27818366177437, 2.18751985076418, 2.11255334351603, -40.8902577242854, 2.16593921241169, 3.19960854388542, 61.8769036084662, -79.6639157256626, 4.25596455603826, 31.3745959782997, -14.8082974511191, -30.0903706337159, 66.6149914824342, -36.9815836782224};

            double SBP = 0;
            double DBP = 0;
            for (int z = 0; z < 19; z++) {
                SBP = SBP + finalindex[z] * fithigh[z];
                DBP = DBP + finalindex[z] * fitlow[z];
            }


            System.out.println("高压:" + SBP);
            System.out.println("低压:" + DBP);

            if (SBP > 0 && SBP < 200 && DBP > 0 && DBP < 200) {
                sbp_list.add(SBP);
                dbp_list.add(DBP);
            }


        }
        int sum_sbp = 0;
        int sum_dbp = 0;
        int size = sbp_list.size();
        for (int i = 0; i < size; i++) {
            sum_sbp += sbp_list.get(i);
            sum_dbp += dbp_list.get(i);
        }
        int aver_sbp = 0;
        int aver_dbp = 0;
        if (size > 0) {
            aver_sbp = sum_sbp / size;
            aver_dbp = sum_dbp / size;
        }

        sum_sbp = 0;
        sum_dbp = 0;
        for (int i = 0; i < sbp_list.size(); i++) {
            if (sbp_list.get(i) < aver_sbp || sbp_list.get(i) > aver_sbp + 15) {
                sbp_list.remove(i);
                dbp_list.remove(i);
                i--;
            } else {
                sum_sbp += sbp_list.get(i);
                sum_dbp += dbp_list.get(i);
            }
        }

        size = sbp_list.size();
        if (size > 0) {
            aver_sbp = sum_sbp / size;
            aver_dbp = sum_dbp / size;
        }

        int[] res = new int[2];
        res[0] = aver_sbp;
        res[1] = aver_dbp;
        System.out.println("高压平均值：" + aver_sbp);
        System.out.println("低压平均值：" + aver_dbp);
        return res;


    }

    public static double sum(double[] data, int s, int e) {//求和
        double sum = 0.0;
        for (int i = s; i <= e; i++) {
            sum = sum + data[i];
        }
        return sum;
    }

    public static double[] cut(double[] data, int start, int end) {//截取
        double[] cut = new double[end - start + 1];
        for (int i = start; i <= end; i++) {
            cut[i - start] = data[i];
            //System.out.println(data[i]);
        }
        return cut;
    }

    public static double[] diff(double[] data) {//差值，diff
        double[] diff = new double[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            diff[i] = data[i + 1] - data[i];
        }
        return diff;
    }

    public static double max(double[] arr) {
        double max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static double min(double[] arr) {
        double min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double[] mapminmax(double[] data) {//差值，diff
        double[] map = new double[data.length];
        double max = max(data);
        double min = min(data);
        for (int i = 0; i < data.length; i++) {
            map[i] = (data[i] - min) / (max - min);
        }
        return map;
    }

    public static double[] newdata(double[] data) {
        List<Point> points = new ArrayList<Point>();
        for (int j = 0; j < data.length; j++) {
            points.add(new Point(j, (int) (data[j] * 100000)));//points是point（int，int）格式数据，所以把纵坐标加大

        }
        BasicSpline spline = new BasicSpline();//spline插值方法，结果用spline.getPoint得到，在第二个循环
        if (points.size() > 2) {//检验必须有两个点（不然不能进行插值计算）
            for (Point p : points) {
                spline.addPoint(p);
            }
            spline.calcSpline();
        }

        double[] originaldata = new double[10];//定义新的插值后数据
        if (points.size() > 2) {
            int i = 0;
            for (float f = 0; f <= 1; f += 0.1) {//把原来几个个值经过插值计算变成10个值，其实就是使得曲线更平滑，相当于一个重采样（但是这个重采样更接近曲线本身）
                Point px = spline.getPoint(f);
                double y = px.y;
                originaldata[i] = y;
                i = i + 1;
            }
        }
        return originaldata;
    }
}

