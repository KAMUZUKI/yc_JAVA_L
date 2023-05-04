package com.yc.resfoods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: yc119_cloud_res_parent
 * @description:
 * @author: zy
 * @create: 2023-04-20 19:42
 */
@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

    /**
     * 支付:在这里调用 银联支付接口， 所以比较慢
     * @param flag
     * @return
     * @throws InterruptedException
     */
    @GetMapping("payAction")
    public Map<String,Object> payAction(   Integer flag    ) throws RuntimeException {
        //TODO: 1. 测试慢请求
        if( flag==null ){
            //Thread.sleep(1000);
            throw new RuntimeException("出异常了");
        }
        Map<String,Object> map=new HashMap<>( );
        //取出当前用户的订单金额,调用第三方接口，完成支付.
        map.put("code",1);
        return map;
    }
}
