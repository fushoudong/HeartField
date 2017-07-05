package com.bupt.heartarea.bloodpressure;

import java.util.ArrayList;
import java.util.List;

public class FindPeaks {

    // //参数：数组，数组大小
    public static int[] findPeaks(double[] num, int count) {
        List<Integer> locList = process(num);//经过处理重复数据后的下标
        List<Integer> sign = new ArrayList<Integer>();
        for (int i = 1; i < locList.size(); i++) {
            /* 相邻值做差： *小于0，，赋-1 *大于0，赋1 *等于0，赋0 */
            double diff = num[locList.get(i)] - num[locList.get(i - 1)];
            //System.out.println(num[locList.get(i)] );

            if (diff > 0) {
                sign.add(1);
            } else if (diff < 0) {
                sign.add(-1);
            } else {
                sign.add(0);
            }
        }
        // 再对sign相邻位做差
        // 保存极大值和极小值的位置
        List<Integer> indMax = new ArrayList<Integer>();
        List<Integer> indMin = new ArrayList<Integer>();
        for (int j = 1; j < sign.size(); j++) {
            double diff = sign.get(j) - sign.get(j - 1);
            if (diff < 0) {
                indMax.add(locList.get(j));
            } else if (diff > 0) {
                indMin.add(locList.get(j));
            }
        }
        int[] loc = new int[indMax.size()];
        for (int m = 0; m < indMax.size(); m++) {
            loc[m] = indMax.get(m);
            //System.out.println(loc[m]);
        }
        return loc;
    }

    public static int[] findPeaks1(double[] num, int count) {
        List<Integer> locList = process(num);//经过处理重复数据后的下标
        List<Integer> sign = new ArrayList<Integer>();
        for (int i = 1; i < locList.size(); i++) {
			/* 相邻值做差： *小于0，，赋-1 *大于0，赋1 *等于0，赋0 */
            double diff = num[locList.get(i)] - num[locList.get(i - 1)];
            //System.out.println(num[locList.get(i)] );

            if (diff > 0) {
                sign.add(1);
            } else if (diff < 0) {
                sign.add(-1);
            } else {
                sign.add(0);
            }
        }
        // 再对sign相邻位做差
        // 保存极大值和极小值的位置
        List<Integer> indMax = new ArrayList<Integer>();
        List<Integer> indMin = new ArrayList<Integer>();
        for (int j = 1; j < sign.size(); j++) {
            double diff = sign.get(j) - sign.get(j - 1);
            if (diff < 0) {
                indMax.add(locList.get(j));
            } else if (diff > 0) {
                indMin.add(locList.get(j));
            }
        }
        int[] loc = new int[indMin.size()];
        for (int m = 0; m < indMin.size(); m++) {
            loc[m] = indMin.get(m);
            //System.out.println(loc[m]);
        }
        return loc;
    }

    /**
     * keep only the first of any adjacent pairs of equal values
     *
     * @return
     */
    public static List<Integer> process(double[] array) {
        List<Integer> locList = new ArrayList<Integer>();
        double last = Double.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (last == Double.MAX_VALUE) {
                last = array[i];
                locList.add(i);
            } else {
                if (last != array[i]) {
                    locList.add(i);
                    last = array[i];
                }
            }
        }
        return locList;

    }
}

