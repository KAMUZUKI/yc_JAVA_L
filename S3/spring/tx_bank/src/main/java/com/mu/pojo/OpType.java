package com.mu.pojo;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 19:05
 **/

public enum OpType {
    WITHDRAW("withdraw",1), DEPOSITE("deposite",2), TRANSFER("transfer",3);

    private String key;
    private int value;

    OpType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public int setValue(int value) {
        return this.value = value;
    }

    public String setKey(String key) {
        return this.key = key;
    }
}
