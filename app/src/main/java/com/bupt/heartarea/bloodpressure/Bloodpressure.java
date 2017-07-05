package com.bupt.heartarea.bloodpressure;

//import java.awt.Point;

import java.util.ArrayList;
import java.util.List;

public class Bloodpressure {

    public static int[] calculate(double[] data) {
        //  System.out.println ("here");
        List<Point> points = new ArrayList<>();

        //ArrayList<Integer> samples = new ArrayList<Integer>();
        //data是未处理的原始数据
//        double[] data = {4.5636, 4.5585, 4.5487, 4.537, 4.5268, 4.5206, 4.519, 4.5205, 4.5233, 4.526, 4.5286, 4.532, 4.5367, 4.5422, 4.5464, 4.5469, 4.5422, 4.5329, 4.5217, 4.512, 4.5062, 4.5049, 4.5069, 4.5104, 4.514, 4.5175, 4.5216, 4.5269, 4.5332, 4.5393, 4.5432, 4.5429, 4.5374, 4.5281, 4.5177, 4.5094, 4.5052, 4.5053, 4.5081, 4.5118, 4.5155, 4.5191, 4.5232, 4.5284, 4.5341, 4.5387, 4.5403, 4.5373, 4.5299, 4.52, 4.5106, 4.5042, 4.502, 4.5036, 4.5072, 4.5114, 4.5155, 4.5197, 4.5247, 4.5307, 4.5368, 4.5412, 4.5419, 4.5378, 4.5298, 4.5204, 4.5128, 4.5091, 4.5095, 4.5128, 4.5168, 4.5206, 4.524, 4.528, 4.533, 4.5386, 4.5427, 4.5431, 4.5388, 4.5306, 4.5212, 4.5141, 4.5112, 4.5125, 4.5165, 4.5213, 4.5256, 4.5298, 4.5346, 4.5402, 4.5456, 4.5489, 4.5481, 4.5422, 4.5324, 4.5216, 4.5129, 4.5082, 4.5078, 4.5105, 4.5147, 4.5192, 4.5238, 4.5289, 4.535, 4.5417, 4.5476, 4.5505, 4.5486, 4.5415, 4.5311, 4.5208, 4.5136, 4.511, 4.5125, 4.5164, 4.5206, 4.5244, 4.528, 4.5321, 4.5374, 4.5435, 4.5486, 4.5503, 4.547, 4.5387, 4.5277, 4.5179, 4.512, 4.5111, 4.5141, 4.5189, 4.5237, 4.5283, 4.5332, 4.5392, 4.5457, 4.5509, 4.5525, 4.5487, 4.54, 4.529, 4.5193, 4.5139, 4.5134, 4.5165, 4.521, 4.5252, 4.5292, 4.5337, 4.5394, 4.5456, 4.5505, 4.5516, 4.5475, 4.539, 4.5288, 4.5203, 4.5162, 4.5168, 4.5206, 4.5254, 4.53, 4.5344, 4.5394, 4.5453, 4.5509, 4.554, 4.5525, 4.5456, 4.5352, 4.5247, 4.5175, 4.5153, 4.5176, 4.5223, 4.5274, 4.532, 4.5365, 4.5418, 4.5481, 4.554, 4.5569, 4.5546, 4.5467, 4.5353, 4.5242, 4.5165, 4.5138, 4.5154, 4.5195, 4.5241, 4.5283, 4.5325, 4.5374, 4.5436, 4.5503, 4.5557, 4.5572, 4.5533, 4.544, 4.532, 4.521, 4.5142, 4.5127, 4.5155, 4.5205, 4.5259, 4.5308, 4.5355, 4.5407, 4.5468, 4.5532, 4.558, 4.559, 4.5543, 4.5444, 4.5317, 4.5202, 4.513, 4.5112, 4.5137, 4.5183, 4.5232, 4.5277, 4.5321, 4.5372, 4.5435, 4.55, 4.5546, 4.555, 4.5498, 4.5399, 4.5282, 4.5186, 4.5141, 4.5149, 4.5194, 4.5253, 4.5308, 4.5354, 4.54, 4.5454, 4.5514, 4.5563, 4.5575, 4.5533, 4.5442, 4.5328, 4.5232, 4.5183, 4.5188, 4.523, 4.5285, 4.5336, 4.5379, 4.5426, 4.5482, 4.5541, 4.5582, 4.558, 4.5521, 4.5416, 4.5295, 4.5196, 4.5149, 4.5157, 4.5205, 4.5267, 4.5325, 4.5373, 4.5421, 4.5477, 4.5543, 4.5603, 4.563, 4.56, 4.5509, 4.5382, 4.5259, 4.5177, 4.5152, 4.5177, 4.523, 4.5288, 4.5341, 4.539, 4.5444, 4.5508, 4.5577, 4.563, 4.5641, 4.5594, 4.5486, 4.5362, 4.5261, 4.5189, 4.5168, 4.5193, 4.5345, 4.5547};      // double[] signal=new double[data.length];

        List<Double> sbp_list = new ArrayList<>();
        List<Double> dbp_list = new ArrayList<>();

        int[] loc = FindPeaks.findPeaks1(data, data.length);//loc为极小值位置,这里是用于切割独立波的，但是要是想实时显示的话，这部分得在安卓写，可能和你实时显示心率的非常相似，应该不难
        for (int k = 0; k < loc.length - 1; k++) {
            double[] onewave = cut(data, loc[k], loc[k + 1]);//注意！！如果要实时显示的话，这里你要检测出来最低值（我这里是切割成独立波了，但这并不符合实时要求），
            // 也就是在安卓端分割成独立波，这部分的代码我真的不会写，如果要实时显示血压值的话，从此部分以下是你安卓端的核心代码。有疑问打电话。
            int[] loc2 = FindPeaks.findPeaks(onewave, onewave.length);//找到每一个独立波的顶点


            double[] upwave = cut(onewave, 0, loc2[0]);
            double[] newup = newdata(upwave);//插值成10个
            double[] upindex = diff(newup);
            double[] downwave = cut(onewave, loc2[0], onewave.length - 1);
            double[] newdown = newdata(downwave);//插值成10个
            double[] downindex = diff(newdown);
            double[] index = new double[18];
            for (int i = 0; i < 9; i++) {
                index[i] = upindex[i];
            }
            for (int i = 9; i < 18; i++) {
                index[i] = downindex[i - 9];
            }
            double[] mapindex = mapminmax(index);
            double[] finalindex = new double[19];
            finalindex[0] = 1;
            for (int i = 1; i < 19; i++) {
                finalindex[i] = mapindex[i - 1];
            }
            double[] fithigh = {119.376225278711, -0.305571633292133, 1.19318852348189, -6.08284079626649, 14.0227075982178, -5.13483182986299, 0.319741461566916, -2.77099797610392, 4.68064281144992, -2.25421992621366, -1.32760981453699, -4.02844850644198, -0.186846953939758, 3.94111159441125, -0.119432595036343, -2.38621403239952, 5.05457275677564, -2.17434012250342, 0.473188680067318};
            //拟合高压
            double[] fitlow = {65.7853155886311, -0.434894265698159, 4.29348123918363, -3.71647104926520, 0.0594299498641194, 2.68674986529549, -0.536834440856911, 0.686221062434584, -1.34476976371414, 1.54496590963798, -0.475588028832916, -1.91816155326171, 1.46078575759197, -1.97586319060665, 0.840836495260148, -1.36314663161985, -0.776285275737714, 0.0647984528293472, 2.58173500805846};
            //拟合低压

            double SBP = 0;
            double DBP = 0;
            for (int z = 0; z < 19; z++) {
                SBP = SBP + finalindex[z] * fithigh[z];
                DBP = DBP + finalindex[z] * fitlow[z];
            }
            SBP = SBP - 10;
            DBP = DBP + 10;

            System.out.println("高压：" + SBP);
            System.out.println("低压：" + DBP);
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

