package com.bupt.heartarea.bloodpressure;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Bloodpressure {

    public static int[] calculate(double[] data) {
        //  System.out.println ("here");
        List<Point> points = new ArrayList<Point>();
        double SBPmean = 0;
        double DBPmean = 0;
        List<Double> high_list = new ArrayList<>();
        List<Double> low_list = new ArrayList<>();
        //ArrayList<Integer> samples = new ArrayList<Integer>();
        //data是未处理的原始数据
//        double[] data = {4.1952, 4.1814, 4.1746, 4.1769, 4.1864, 4.1995, 4.2123, 4.2217, 4.2255, 4.2223, 4.2119, 4.197, 4.1823, 4.1727, 4.1709, 4.1766, 4.187, 4.1986, 4.2076, 4.2103, 4.2049, 4.1919, 4.1756, 4.1621, 4.1567, 4.1606, 4.1718, 4.1864, 4.2005, 4.2109, 4.2151, 4.2116, 4.2015, 4.1883, 4.1773, 4.1727, 4.1753, 4.1833, 4.1938, 4.2045, 4.213, 4.2173, 4.2151, 4.2063, 4.1933, 4.1808, 4.1732, 4.1721, 4.1764, 4.1836, 4.1919, 4.1996, 4.2048, 4.2053, 4.1996, 4.189, 4.1778, 4.1707, 4.1703, 4.1761, 4.1856, 4.1964, 4.2068, 4.2151, 4.2187, 4.2153, 4.2048, 4.1908, 4.1788, 4.173, 4.1743, 4.1805, 4.1885, 4.1966, 4.204, 4.2096, 4.2112, 4.2071, 4.1981, 4.1877, 4.1804, 4.1789, 4.1831, 4.1912, 4.201, 4.2109, 4.2187, 4.222, 4.2191, 4.2104, 4.1992, 4.1902, 4.187, 4.1898, 4.1967, 4.2052, 4.2135, 4.2202, 4.2236, 4.222, 4.2152, 4.2055, 4.197, 4.1932, 4.195, 4.2011, 4.2094, 4.2182, 4.2258, 4.2297, 4.2276, 4.2187, 4.2053, 4.1925, 4.1852, 4.1852, 4.1908, 4.1991, 4.2072, 4.2134, 4.2163, 4.2145, 4.2073, 4.196, 4.1842, 4.1758, 4.173, 4.1754, 4.1814, 4.1889, 4.1959, 4.2002, 4.1994, 4.1926, 4.1815, 4.17, 4.1625, 4.1611, 4.1648, 4.1713, 4.1785, 4.185, 4.1895, 4.1905, 4.1869, 4.1791, 4.1699, 4.1629, 4.1603, 4.1623, 4.1673, 4.1736, 4.1803, 4.1861, 4.1891, 4.1876, 4.1812, 4.172, 4.1638, 4.1597, 4.1603, 4.1644, 4.1702, 4.1766, 4.1827, 4.1871, 4.1879, 4.1838, 4.1757, 4.1671, 4.1616, 4.1608, 4.1641, 4.1697, 4.176, 4.1823, 4.188, 4.1913, 4.1902, 4.1843, 4.1755, 4.1677, 4.164, 4.1651, 4.1699, 4.1765, 4.1835, 4.1892, 4.1919, 4.1898, 4.183, 4.1736, 4.1658, 4.1629, 4.1656, 4.1721, 4.1801, 4.1877, 4.1934, 4.196, 4.1941, 4.1874, 4.1774, 4.1676, 4.1614, 4.1601, 4.1628, 4.168, 4.1743, 4.1805, 4.1851, 4.1861, 4.182, 4.1736, 4.1643, 4.1577, 4.1562, 4.1592, 4.1649, 4.1715, 4.1779, 4.183, 4.1853, 4.1831, 4.1763, 4.167, 4.1592, 4.1555, 4.1565, 4.1608, 4.1664, 4.1722, 4.1777, 4.1818, 4.1827, 4.179, 4.1714, 4.1628, 4.1567, 4.1552, 4.158, 4.1633, 4.1697, 4.1762, 4.182, 4.1854, 4.1844, 4.1785, 4.1694, 4.1609, 4.1563, 4.1568, 4.161, 4.1671, 4.1739, 4.1809, 4.1871, 4.1906, 4.1893, 4.1831, 4.1743, 4.1667, 4.1632, 4.1643, 4.1686, 4.1744, 4.1806, 4.1865, 4.1906, 4.1907, 4.1857, 4.1769, 4.1675, 4.1615, 4.1603, 4.1631, 4.1678, 4.173, 4.1782, 4.1826, 4.1844, 4.1818, 4.1743, 4.1642, 4.1554, 4.1509, 4.1514, 4.1553, 4.1609, 4.167, 4.1733, 4.1785, 4.1808, 4.1774, 4.1703, 4.162, 4.1539, 4.1497, 4.15, 4.1631, 4.1823};
        int[] loc = FindPeaks.findPeaks1(data, data.length);//loc为极小值位置,这里是用于切割独立波的，但是要是想实时显示的话，这部分得在安卓写，可能和你实时显示心率的非常相似，应该不难
        for (int k = 0; k < loc.length - 1; k++) {
            double[] onewave = cut(data, loc[k], loc[k + 1]);//注意！！如果要实时显示的话，这里你要检测出来最低值（我这里是切割成独立波了，但这并不符合实时要求），
            //也就是在安卓端分割成独立波，这部分的代码我真的不会写，如果要实时显示血压值的话，从此部分以下是你安卓端的核心代码。有疑问打电话。
//    	double[] onewave=new double[onewave1.length];
//    	for (int z=0;z<onewave1.length;z++){
//    		onewave[z]=onewave1[onewave1.length-1-z];
//    	}

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
            double[][] finalindex = new double[18][1];
            //finalindex[0][0]=1;
            for (int i = 0; i < 18; i++) {
                finalindex[i][0] = mapindex[i];
            }
            Matrix Index = Matrix.constructWithCopy(finalindex);//把指标转换成矩阵


            double[] result = fitnet.fitnnet(Index);//resulet存着结果[0]是高压[1]是低压

            if (result[0] > 0 && result[0] < 200 && result[1] > 0 && result[1] < 200) {
                high_list.add(result[0]);
                low_list.add(result[1]);
            }

        }


        int sum_high = 0;
        int sum_low = 0;
        int size = high_list.size();
        for (int i = 0; i < size; i++) {
            sum_high += high_list.get(i);
            sum_low += low_list.get(i);
        }
        int aver_sbp = 0;
        int aver_dbp = 0;
        if (size > 0) {
            aver_sbp = sum_high / size;
            aver_dbp = sum_low / size;
        }

        sum_high = 0;
        sum_low = 0;
        for (int i = 0; i < high_list.size(); i++) {
            if (high_list.get(i) < aver_sbp || high_list.get(i) > aver_sbp + 15) {
                high_list.remove(i);
                low_list.remove(i);
                i--;
            } else {
                sum_high += high_list.get(i);
                sum_low += low_list.get(i);
            }
        }

        size = high_list.size();
        if (size > 0) {
            aver_sbp = sum_high / size;
            aver_dbp = sum_low / size;
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
            map[i] = 2 * ((data[i] - min) / (max - min)) - 1;

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
                originaldata[i] = y / 100000;
                i = i + 1;
            }
        }
        return originaldata;
    }
}

