package com.mu.net.comcat2.javax.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : MUZUKI
 * @date : 2023-01-06 20:07
 **/

public class ServletContext {
    /**
     * <String,Class>
     * url地址， servlet 的字节码路径
     */
    public static Map<String,Class> servletClass = new ConcurrentHashMap<String,Class>();

    /**
     * 每个servlet都是单例 当第一次访问时，创建servlet实例，放入map中
     */
    public static Map<String,Servlet> servletInstance = new ConcurrentHashMap<String,Servlet>();
}