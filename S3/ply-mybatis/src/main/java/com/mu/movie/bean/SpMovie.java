package com.mu.movie.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author : MUZUKI
 * 影院售票-电影信息表-5
 * @TableName sp_movie
 */

@Data
public class SpMovie implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 一对多关联查询 多的属性=>集合
     */
    private List<SpPlan> plans;
    /**
     * 多对多关联
     */
    private List<SpHall> halls;
    /**
     * 编号: 自增列
     */
    private Integer id;
    /**
     * 电影名
     */
    private String name;
    /**
     * 片长: 单位:分钟
     */
    private Integer length;
    /**
     * 类型: 战争片, 科幻片 ...
     */
    private String type;
    /**
     * 上映日期: 上映开始日期
     */
    private Date beginTime;
    /**
     * 下映日期: 上映结束日期
     */
    private Date endTime;
    /**
     * 是否3D: 1:3D, 0:2D
     */
    private Integer is3d;
    /**
     * 影片介绍
     */
    private String intro;
    /**
     * 海报图片: 图片地址
     */
    private String image;
}
