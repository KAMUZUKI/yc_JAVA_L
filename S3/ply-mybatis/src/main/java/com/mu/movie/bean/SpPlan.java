package com.mu.movie.bean;

import java.io.Serializable;
import java.util.Date;
import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @author : MUZUKI
* 影院售票-排片表-3
* @TableName sp_plan
*/

@Data
public class SpPlan implements Serializable {

    /**
    * 编号: 自增列
    */
    private Integer id;
    /**
    * 播放时间: 开始播放的时间
    */
    private Date playTime;
    /**
    * 电影id: 关联 sp_movie.id
    */
    private Integer mid;
    /**
    * 影厅id: 关联 sp_hall.id
    */
    private Integer hid;

    /**
     * 一对一关联，定义一个实体对象变量
     */
    private SpMovie movie;
    private SpHall hall;

    /**
    * 价格
    */
    private Double price;
}
