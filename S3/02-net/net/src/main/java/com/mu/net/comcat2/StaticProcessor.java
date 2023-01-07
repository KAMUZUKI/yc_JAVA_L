package com.mu.net.comcat2;

import com.mu.net.comcat2.javax.servlet.ServletRequest;
import com.mu.net.comcat2.javax.servlet.ServletResponse;
import com.mu.net.comcat2.javax.servlet.http.HttpServletResponse;

/**
 * @description : 静态资源处理接口
 * @author : MUZUKI
 * @date : 2023-01-06 20:25
 **/

public class StaticProcessor implements Processor {
    /**
     * 处理静态资源
     * @param request
     * @param response
     */
    @Override
    public void process(ServletRequest request, ServletResponse response) {
//        ((HttpServletResponse)response).send();
        response.send();
    }
}
