package com.mu.movie.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @author : MUZUKI
* 影院售票-影厅表-3
* @TableName sp_hall
*/

@Data
public class SpHall implements Serializable {

    /**
    * 编号: 自增列
    */
    private Integer id;
    /**
    * 影厅名
    */
    private String name;
    /**
    * 是否3D: 1 3D, 0 2D
    */
    private Integer is3d;
    /**
    * 大小: 大,中,小
    */
    private String size;
    /**
    * 座位数
    */
    private Integer seats;

}
