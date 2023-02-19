package com.mu.favorite.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName favorite
 */
@Data
public class Favorite implements Serializable {
    /**
     * 
     */
    private Integer fid;

    /**
     * 
     */
    private String flabel;

    /**
     * 
     */
    private String furl;

    /**
     * 
     */
    private String fdesc;

    /**
     * 
     */
    private String ftags;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", flabel=").append(flabel);
        sb.append(", furl=").append(furl);
        sb.append(", fdesc=").append(fdesc);
        sb.append(", ftags=").append(ftags);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}