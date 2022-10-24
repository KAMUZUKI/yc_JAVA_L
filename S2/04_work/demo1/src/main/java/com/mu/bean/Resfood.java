package com.mu.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 16:57
 * @description : 菜单
 **/

@Data
public class Resfood implements Serializable {
    private Integer fid;
    private String fname;
    private Double normprice;
    private Double realprice;
    private String detail;
    private String fphoto;

    private Long praise;
}
