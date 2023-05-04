package com.mu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mu.api.ResfoodApi;
import com.mu.bean.Resfood;
import com.mu.model.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : MUZUKI
 * @date : 2023-04-09 09:40
 **/

@RestController
@Slf4j
@RequestMapping("cart")
public class CartController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ResfoodApi resfoodApi;

    @RequestMapping("/clearAll")
    public Map<String,Object> clearAll(HttpSession session){
        Map<String,Object> result = new HashMap<>();
        String sessionid = session.getId();
        if(this.redisTemplate.hasKey("cart_"+sessionid)){
            Set<Object> keys = this.redisTemplate.opsForHash().keys("cart_"+sessionid);
            this.redisTemplate.opsForHash().delete("cart_"+sessionid,keys.toArray());
            result.put("code", 1);
            result.put("obj", keys);
        }else{
            result.put("code", 0);
        }
        return result;
    }

    @RequestMapping("/getCartInfo")
    public Map<String,Object> getCartInfo(HttpSession session){
        Map<String,Object> result = new HashMap<>();
        String sessionid = session.getId();
        if(this.redisTemplate.hasKey("cart_"+sessionid)){
            Map<Object, Object> cart = this.redisTemplate.opsForHash().entries("cart_"+sessionid);
            log.info("sessionid: {},cart:{}", sessionid,cart);
            result.put("code", 1);
            result.put("obj", cart);
        }else{
            result.put("code", 0);
        }
        return result;
    }

    @RequestMapping("/addCart")
    public Map<String,Object> addCart(@RequestParam Integer fid,
                                      @RequestParam Integer num,
                                        HttpSession session){
        Map<String,Object> result = new HashMap<>();
        String sessionid = session.getId();

        //TODO:到nacos中查找res-foods服务中的resfood/findById/{fid}接口
        Resfood rf = null;
        String url = "http://res-foods/resfood/findById/"+fid;
//        Map<String,Object> resultMap = this.restTemplate.getForObject(url,Map.class);
        Map<String, Object> resultMap = this.resfoodApi.findById(   fid );
        if("1".equalsIgnoreCase(resultMap.get("code").toString())){
            Map m = (Map)resultMap.get("obj");
            // 将一个Map转换为一个JavaBean
            ObjectMapper mapper = new ObjectMapper();
            rf = mapper.convertValue(m,Resfood.class);
        }else{
            result.put("code",0);
            result.put("msg","没有找到对应的菜品"+fid);
            return result;
        }

        CartItem ci = (CartItem)this.redisTemplate.opsForHash().get("cart_"+sessionid,fid+"");
        if(ci==null){
            ci = new CartItem();
            ci.setFood(rf);
            ci.setNum(num);
        }else{
            int newNum = ci.getNum()+num;
            ci.setNum(newNum);
        }
        if(ci.getNum()<=0){
            this.redisTemplate.opsForHash().delete("cart_"+sessionid,fid+"");
        }else{
            ci.getSmallCount();
            this.redisTemplate.opsForHash().put("cart_"+sessionid,fid+"",ci);
        }
        result.put("code",1);
        result.put("obj",redisTemplate.opsForHash().entries("cart_"+sessionid));
        return result;
    }
}
