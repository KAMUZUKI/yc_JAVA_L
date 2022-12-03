package com.yc.utils;

/**
 * 常量类,用于维护系统中的一个属性名
 */
public class YcConstants {
    /**redis中保存的用户历史记录的键名 后缀*/
    public final static String REDIS_VISITED="_visited";

    /**redis中保存的用户历史访问记录的键名 后缀*/
    public final static String REDIS_PRAISE="_praise";

    /**redis中保存的一个菜被哪些用户点过赞 后缀 格式: 菜品id_后缀*/
    public final static String REDIS_FOOD_PRAISE="_food_praise";

    /**redis中保存的 使用某种浏览器登陆网站的用户列表*/
    public final static String REDIS_DEVICE_USERS="_device_users";

    /**redis中保存某个用户登录网站所用的设备列表*/
    public final static String REDIS_USER_DEVICE="_user_device";

    /**系统中保存的登录用户 键名*/
    public final static String RESUSER="resuser";

    /**系统中的购物车的 键名*/
    public final static String CART="cart";
}
