package com.yc.bean;

import java.util.List;

public class PageBean<T> {
    //以下两个属性是界面给的已知参数
    private int pageno=1;//当前第几页
    private int pagesize=5;//每页几条数据

    //以下两个属性是数据库查询得到的结果
    private long total;//总记录条数
    private List<T> dataset;
    //需要在业务层中计算的属性
    private int totalpages;//总共多少页
    private int pre;//上一页的页数
    private int next;//下一页的页数

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getDataset() {
        return dataset;
    }

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
