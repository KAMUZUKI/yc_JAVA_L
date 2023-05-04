package com.yc.resfoods.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: yc119_cloud_res_parent
 * @description:
 * @author: zy
 * @create: 2023-04-15 16:06
 */
@RestController
@RequestMapping("service")
@RefreshScope
public class ServiceController {

    //利用DI 将属性   res.pattern.dateformat 注入
    @Value("${res.pattern.dateformat}")
    private String dateformate;

    @RequestMapping("nowTime")
    public Map<String,Object> nowTime()  {
        Map<String,Object> map=new HashMap<>();

        Date d=new Date();
        DateFormat df=new SimpleDateFormat(   dateformate );

        map.put("code",1);
        map.put("obj",    df.format(   d ) );
        return map;
    }

}
