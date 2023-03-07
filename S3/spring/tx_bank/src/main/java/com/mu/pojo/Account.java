package com.mu.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 15:03
 **/
@Data
public class Account implements Serializable {
    private int accountid;

    private double balance;
}
