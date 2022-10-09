package com.mu.bean;

import java.io.Serializable;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 16:57
 * @description : 菜单
 **/
public class Resfood implements Serializable {
    int fid;
    String fname;
    Double normprice;
    Double realprice;
    String detail;
    String fphoto;

    public Resfood() {
    }

    public Resfood(int fid, String fname, Double normprice, Double realprice, String detail, String fphoto) {
        this.fid = fid;
        this.fname = fname;
        this.normprice = normprice;
        this.realprice = realprice;
        this.detail = detail;
        this.fphoto = fphoto;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public Double getNormprice() {
        return normprice;
    }

    public void setNormprice(Double normprice) {
        this.normprice = normprice;
    }

    public Double getRealprice() {
        return realprice;
    }

    public void setRealprice(Double realprice) {
        this.realprice = realprice;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFphoto() {
        return fphoto;
    }

    public void setFphoto(String fphoto) {
        this.fphoto = fphoto;
    }

    @Override
    public String toString() {
        return "Resfood{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", normprice=" + normprice +
                ", realprice=" + realprice +
                ", detail='" + detail + '\'' +
                ", fphoto='" + fphoto + '\'' +
                '}';
    }
}
