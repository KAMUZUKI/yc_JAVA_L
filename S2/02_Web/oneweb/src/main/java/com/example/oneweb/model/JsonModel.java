package com.example.oneweb.model;

public class JsonModel {
    private Integer code;   //0失败  1成功
    private String msg;     //当code=0时的错误信息
    private Object data;    //当code=1时，操作的结果数据

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
