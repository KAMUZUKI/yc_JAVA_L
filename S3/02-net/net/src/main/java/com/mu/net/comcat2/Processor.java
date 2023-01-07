package com.mu.net.comcat2;

import com.mu.net.comcat2.javax.servlet.ServletRequest;
import com.mu.net.comcat2.javax.servlet.ServletResponse;

/**
 * @desciption : 资源处理接口
 * @author : MUZUKI
 * @date : 2023-01-06 20:23
 **/

public interface Processor {
    /**
     * 处理方法
     * @param request
     * @param response
     */
    public void process(ServletRequest request, ServletResponse response);
}
