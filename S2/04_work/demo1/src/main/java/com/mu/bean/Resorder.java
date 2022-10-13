package com.mu.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : MUZUKI
 * @date : 2022-10-13 21:19
 **/

@Data
public class Resorder implements Serializable {
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private String ordertime;
    private String deliveryTime;
    private String ps;
    private Integer status;
}
