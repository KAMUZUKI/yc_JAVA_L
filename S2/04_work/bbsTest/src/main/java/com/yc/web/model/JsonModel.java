package com.yc.web.model;

public class JsonModel {
    private Integer code;//0:失败,1:成功
    private String msg;//当code=0时的报错信息
    private Object data;//当code=1时的操作数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
