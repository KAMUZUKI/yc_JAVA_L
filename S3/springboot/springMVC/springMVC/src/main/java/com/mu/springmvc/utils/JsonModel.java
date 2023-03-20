package com.mu.springmvc.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : MUZUKI
 * @date : 2023-03-20 16:10
 **/

@Data
@Accessors(chain = true)
public class JsonModel {
    /**
     * 0失败  1成功
     */
    private Integer code;
    /**
     * 当code=0时的错误信息
     */
    private String msg;

    /**
     *
     */
    private Object data;
}