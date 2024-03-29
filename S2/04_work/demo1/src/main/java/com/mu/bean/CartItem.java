package com.mu.bean;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 19:40
 **/

public class CartItem {
    private Resfood food;
    private Integer num;
    private Double smallCount;

    public Double getSmallCount() {
        if (food!=null){
            smallCount = this.food.getRealprice()*this.num;
        }
        return smallCount;
    }

    public Resfood getFood() {
        return food;
    }

    public void setFood(Resfood food) {
        this.food = food;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setSmallCount(Double smallCount) {
        this.smallCount = smallCount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "food=" + food +
                ", num=" + num +
                ", smallCount=" + smallCount +
                '}';
    }
}
