package com.mu.movie.bean;

import java.io.Serializable;
import java.util.Date;
import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @author : MUZUKI
* 影院售票-售票表-7
* @TableName sp_ticket
*/

@Data
public class SpTicket implements Serializable {

    /**
    * 编号: 自增列
    */
    private Integer id;
    /**
    * 排片id: 关联 sp_plan.id
    */
    private Integer pid;
    /**
    * 金额
    */
    private Double money;
    /**
    * 支付方式: 现金, 支付宝, 微信支付
    */
    private String payType;
    /**
    * 支付时间
    */
    private Date payTime;
    /**
    * 座位号: 座位号 > 0 and 座位号 <= 影厅座位数
    */
    private Integer seatsn;
}
