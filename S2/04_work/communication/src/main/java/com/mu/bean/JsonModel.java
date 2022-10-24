package com.mu.bean;

import lombok.Data;

/**
 * @author MUZUKI
 */

@Data
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
     * 当code=1时，操作的结果数据
     */
    private Object data;
}
