package com.mu.web.model;

import java.io.Serializable;
import java.util.List;

public class Pagebean<T> implements Serializable { //在服务器端的java bean，为了让tomcat能缓存数据，最好实现Serializable

    private int pageno=1;
    private int pagesize=4;
    private long total;
    private List<T> dataset;
    private int pre;
    private int next;
    private int totalpages;
    private String sortby; //通过什么排
    private String sort;//排序方法 desc  asc

    @Override
    public String toString() {
        return "Pagebean{" +
                "pageno=" + pageno +
                ", pagesize=" + pagesize +
                ", total=" + total +
                ", dataset=" + dataset +
                ", pre=" + pre +
                ", next=" + next +
                ", totalpages=" + totalpages +
                ", sortby='" + sortby + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

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
