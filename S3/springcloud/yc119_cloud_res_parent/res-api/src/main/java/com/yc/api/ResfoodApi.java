package com.yc.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(value="res-foods",path="resfood")
public interface ResfoodApi {

    //@RequestLine("GET /findByPage")
    @RequestMapping("findByPage")
    public Map<String,Object> findByPage(@RequestParam int pageno, @RequestParam int pagesize, @RequestParam String sortby, @RequestParam String sort   );

    // openFeign支持springMVC的注解解析
    @RequestMapping("findById/{fid}")
    public Map<String,Object> findById(@PathVariable Integer fid);

    @RequestMapping("findAll")
    public Map<String,Object> findAll();


}

/**
  //logger对象是用来记录http请求和响应.
 public class XXX implements ResfoodApi{
      private Logger logger=Logger.getLogger();
     将以上接口通过openFeign 的动态代理机制生成一个实现类
     public Map<String,Object> findById(@PathVariable Integer fid){
            String url=  "http://"+value+ path+RequestMapping
           return restTemplate.getForObject(url, Map);

     }

 }





 */
