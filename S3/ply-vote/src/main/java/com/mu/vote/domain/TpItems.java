package com.mu.vote.domain;

import java.io.Serializable;
import lombok.Data;

/**
* 投票系统-题目选项表-9
* @TableName tp_items
*/
@Data
public class TpItems implements Serializable {
    /**
     * 选项的投票数
     */
    private int records;
    /**
    * 编号, 自增列
    */
    private Integer id;
    /**
    * 选项内容
    */
    private String iname;
    /**
    * 题目编号, 关联 tp_vote.id
    */
    private Integer vid;

}
