package com.mu.vote.domain;

import java.io.Serializable;
import lombok.Data;

/**
* 投票系统-用户表-4
* @author MUZUKI
 * @TableName tp_user
*/
@Data
public class TpUser implements Serializable {

    /**
    * 编号, 自增列
    */
    private Integer id;
    /**
    * 用户名
    */
    private String uname;
    /**
    * 密码
    */
    private String pwd;
}
