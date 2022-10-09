package com.mu.model;

import java.util.List;

public class PageBean<T> {
    /**
     * 界面给的已知参数
     * 当前第几页
     */
    private int pageno=1;
    /**
     * 每页多少条
     */
    private int pagesize=10;
    /**
     * 数据库查询
     * 总记录数
     */
    private long total;
    /**
     * 需要在业务层计算
     */
    private List<T> dataset;
    /**
     * 上一页的页数
     */
    private int pre;
    /**
     * 下一页的页数
     */
    private int next;
    /**
     * 总共多少页
     */
    private int totalPages;

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
    }

    public long getTotal() {
        return total;
    }

    public int getPageno() {
        return pageno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public int getPre() {
        return pre;
    }

    public int getNext() {
        return next;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getDataset() {
        return dataset;
    }
}
