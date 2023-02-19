package com.mu.vote.domain;

import java.io.Serializable;
import lombok.Data;

/**
* 投票系统-投票记录表-6
* @author MUZUKI
 * @TableName tp_record
*/
@Data
public class TpRecord implements Serializable {

    /**
    * 编号, 自增列
    */
    private Integer id;
    /**
    * 题目编号: 关联 tp_vote.id
    */
    private Integer vid;
    /**
    * 选项编号: 关联 tp_items.id
    */
    private Integer iid;
    /**
    * 用户表编号: 关联 tp_user.id
    */
    private Integer usid;
}
