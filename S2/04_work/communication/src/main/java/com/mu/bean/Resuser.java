package com.mu.bean;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 09:20
 * @description : 用户信息
 **/

@Data
public class Resuser {
    private Integer userid;
    private String username;
    private String pwd;
    private String valcode;
}
