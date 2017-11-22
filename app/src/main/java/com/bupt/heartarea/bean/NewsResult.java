package com.bupt.heartarea.bean;

import java.util.List;

/**
 * Created by yuqing on 2017/3/23.
 */
public class NewsResult {
    private int total;
    private int size;
    private List<DataBean> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataBean> tngou) {
        this.dataList = tngou;
    }

    public static class DataBean {
        private int count;
        private String description;
        private int fcount;
        private int id;
        private String img;
        private String keywords;
        private String message;
        private int rcount;
        private long time;
        private String title;
        private int loreclass;


        @Override
        public String toString() {
            return "DataBean{" +
                    "count=" + count +
                    ", description='" + description + '\'' +
                    ", fcount=" + fcount +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    ", keywords='" + keywords + '\'' +
                    ", message='" + message + '\'' +
                    ", rcount=" + rcount +
                    ", time=" + time +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
