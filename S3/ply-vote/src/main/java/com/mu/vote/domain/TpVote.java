package com.mu.vote.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * 投票系统-题目表-2
 * @author MUZUKI
 * @TableName tp_vote
 */
@Data
public class TpVote implements Serializable {
    /**
     * 参与人数
     */
    private int userNums;
    /**
     * 选项
     */
    private List<TpItems> items;
    /**
     * 编号, 自增列
     */
    private Integer id;
    /**
     * 标题
     */
    private String vname;
    /**
     * 类型: 单选, 多选
     */
    private String vtype;
    /**
     * 开始时间: 开始投票的时间
     */
    private Date startDate;
    /**
     * 结束时间: 投票结束的时间
     */
    private Date endDate;
}
