package com.mu.net.atm;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 15:13
 **/

@Data
public class JsonModel<T> implements Serializable {
    private int code;
    private String message;
    private T data;
}
