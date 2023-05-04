package com.yc.resfoods.configs;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: yc119_cloud_res_parent
 * @description: spring boot的统一异常处理类,处理的是Controller中的异常.
 * @author: zy
 * @create: 2023-04-22 20:08
 */
@ControllerAdvice    //  Controller控制器,ioc,     Advice: aop中的增强
@Order(-100000)    //
//   AOP技术
public class CustomerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> handleRuntimeException(RuntimeException exception){
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","runtimeException occured");
        return map;
    }
}
