package com.yc.resfoods.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.api.ResfoodApi;
import com.yc.bean.Resfood;
import com.yc.resfoods.model.CartItem;
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
 * @program: yc119_cloud_res_parent
 * @description:购物车控制器
 * @author: zy
 * @create: 2023-04-09 09:10
 */
@RestController
@Slf4j
@RequestMapping("cart")
public class CartController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResfoodApi resfoodApi;



    /**
     * 取出sessionid , 根据 键"cart_"+sessionid到redis中进行删除
     * @param session
     * @return
     */
    @RequestMapping("clearAll")
    public Map<String,Object> clearAll(HttpSession session){
        Map<String,Object> result=new HashMap<>();
        String sessionid=session.getId();
        if(  this.redisTemplate.hasKey("cart_"+sessionid)){
            Set<Object> keys= this.redisTemplate.opsForHash().keys(  "cart_"+sessionid);
            // keys: 1,2  =>     "1,2"
            this.redisTemplate.opsForHash().delete(   "cart_"+sessionid,  keys.toArray()  );
            result.put("code",   1   );
            result.put("obj",keys);   //keys中存的是 删除的商品编号
        }else{
            result.put("code",0);
        }
        return result;
    }

    @RequestMapping("getCartInfo")
    public Map<String,Object> getCartInfo(HttpSession session){
        Map<String,Object> result=new HashMap<>();
        String sessionid=session.getId();
        if(  this.redisTemplate.hasKey("cart_"+sessionid)){
            Map<Object, Object> cart=this.redisTemplate.opsForHash().entries("cart_"+sessionid);
            log.info("sessionid为"+sessionid+",的购物车为:"+ cart );
            result.put("code",   1   );
            result.put("obj",cart);   //keys中存的是 删除的商品编号
        }else{
            result.put("code",0);
        }
        return result;
    }

    @RequestMapping("addCart")
    public Map<String,Object> addCart(@RequestParam Integer fid,
                                      @RequestParam Integer num,
                                      HttpSession session){
        Map<String,Object> result=new HashMap<>();
        String sessionid=session.getId();

        //TODO: 到nacos中查找res-foods服务中的   findById ，要得到菜品对象resfood   =>此技术叫服务发现,且调用服务得到结果
        Resfood rf=null;
        //方案一: 利用  url地址直接访问 服务
        //String url="http://localhost:9001/resfood/findById/"+fid;
        //目标: 发一个http请求，url如上,
        //     socket -> URLConnection -> HttpClient (针对是http请求)
        //                                       -> restTemplate  ( 针对 rest, 是spring的封装  )

        //方案二: 利用服务名，通过服务发现功能自动找到url
        //String url="http://res-foods/resfood/findById/"+fid;   //   feign  ->  openfeign
        //Map<String,Object> resultMap=this.restTemplate.getForObject(  url   , Map.class );

        //方案三: 利用 openFeign来发出请求.
        Map<String,Object> resultMap=this.resfoodApi.findById(   fid );

        if( "1".equalsIgnoreCase(  resultMap.get("code").toString())){
            Map m= (Map) resultMap.get("obj");
            //如何将一个Map转为  一个  Resfood对象  -> 反射.
            // spring的底层对json的处理使用 jackson框架，这个框架有将map转为对象的方法
            ObjectMapper mapper=new ObjectMapper();
            rf=mapper.convertValue(     m,  Resfood.class );
        }else{
            result.put("code",0);
            result.put("msg","查无此商品"+fid);
            return result;
        }
        //加购物车的逻辑
        CartItem ci= (CartItem) this.redisTemplate.opsForHash().get("cart_"+sessionid,  fid+""  );
        if( ci==null){
            ci=new CartItem();
            ci.setFood(rf);
            ci.setNum(num);
        }else{
            int newNum=ci.getNum()+ num;
            ci.setNum(   newNum );
        }
        //计算金额
        if( ci.getNum()<=0){
            this.redisTemplate.opsForHash().delete(  "cart_"+sessionid,  fid+"");
        }else{
            ci.getSmallCount();
            this.redisTemplate.opsForHash().put(   "cart_"+sessionid, fid+"",  ci);
        }
        result.put("code",1);
        result.put("obj", redisTemplate.opsForHash().entries("cart_"+sessionid) );
        return result;
    }

}
