package com.mu.favorite.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName tag
 */
@Data
public class Tag implements Serializable {
    /**
     * 
     */
    private Integer tid;

    /**
     * 
     */
    private String tname;

    /**
     * 
     */
    private Integer tcount;

    private List<Favorite> fList;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", tname=").append(tname);
        sb.append(", tcount=").append(tcount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}