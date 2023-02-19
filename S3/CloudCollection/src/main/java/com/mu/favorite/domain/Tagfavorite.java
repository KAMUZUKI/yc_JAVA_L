package com.mu.favorite.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tagfavorite
 */
@Data
public class Tagfavorite implements Serializable {
    /**
     * 
     */
    private Integer tid;

    /**
     * 
     */
    private Integer fid;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tid=").append(tid);
        sb.append(", fid=").append(fid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}