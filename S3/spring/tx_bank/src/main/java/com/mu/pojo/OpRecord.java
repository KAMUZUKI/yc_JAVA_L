package com.mu.pojo;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 19:07
 **/

@Data
public class OpRecord {
    private int id;
    private int accountid;
    private double opmoney;
    private String optime;
    private OpType opType;
    private Integer transferid;
}
