package com.mu.favorite.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2022-12-27 20:30
 **/

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;
}
