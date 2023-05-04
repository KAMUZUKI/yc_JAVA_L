package com.yc.resfoods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.Resfood;
import com.yc.resfoods.biz.ResfoodBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("resfood")   //   http://localhost:port/resfood
@Slf4j
public class ResfoodController {
    @Autowired
    private ResfoodBiz resfoodBiz;



    @RequestMapping("findById/{fid}")
    public Map<String,Object> findById(@PathVariable  Integer fid)  {
//        try {
//            Thread.sleep( 10000 );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Map<String,Object> map=new HashMap<>();
        Resfood rf=null;
        try {
             rf = this.resfoodBiz.findById(fid);
        }catch(Exception ex){
            map.put("code",0);
            map.put("msg",ex.getCause());
            return map;
        }
        map.put( "code",1);
        map.put("obj",rf);
        return map;
    }

    public Set<Thread> set= new HashSet<>();

//    private Map<String, Object> exceptionFallback( Throwable t  ){
//        t.printStackTrace();
//        Map<String, Object> map=new HashMap<>();
//        map.put("code",0);
//        map.put("msg","resource is under exception. cause as following:"+ t.getCause() );
//        return map;  //   sentinel :  200
//    }

    @RequestMapping("findAll")
    //@SentinelResource(value="findAll", fallback = "exceptionFallback")
    public Map<String,Object> findAll(){
//        int flag=1;
//        if(  flag==1 ){
//            throw new RuntimeException("业务异常: 查找所有出异常了");   //利用spring boot统一处理
//        }

        Thread thread = Thread.currentThread();   //获取当前的tomcat处理这个请求的线程
        set.add(thread);
        log.info(    "线程数为:"+ set.size() +",当前线程编号为:"+ thread.getId() );

//        try {
//            Thread.sleep(3000 );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Map<String,Object> map=new HashMap<>();
        List<Resfood> list=null;
        try {
            list = this.resfoodBiz.findAll();
        }catch(Exception ex){
            map.put("code",0);
            map.put("msg",ex.getCause());
            return map;
        }
        map.put( "code",1);
        map.put("obj",list);
        return map;
    }

//    /**
//     * 限流发生时, 回调处理方法
//     * @param exception :  流控异常的类型,由sentinel 捕获后注入.
//     * @return
//     */
//    private Map<String, Object> handleBlock(@RequestParam int pageno, @RequestParam int pagesize,
//                                            @RequestParam String sortby, @RequestParam String sort,
//                                            BlockException exception){
//        exception.printStackTrace();
//        Map<String, Object> map=new HashMap<>();
//        map.put("code",0);
//        map.put("msg","资源:"+exception.getRuleLimitApp()+":被限流，规则为:"+   exception.getRule().toString() );
//        return map;  //   sentinel :  200
//    }

    @RequestMapping("findByPage")
    //@SentinelResource(value = "hotkey-page",blockHandler = "handleBlock")   //流控资源名
    public Map<String,Object> findByPage(@RequestParam int pageno, @RequestParam int pagesize, @RequestParam String sortby, @RequestParam String sort   ){

        Map<String,Object> map=new HashMap<>();
        Page<Resfood> page=null;
        try {
            page = this.resfoodBiz.findByPage(pageno, pagesize,sortby,sort);
        }catch(Exception ex){
            map.put("code",0);
            map.put("msg",ex.getCause());
            return map;
        }
        map.put( "code",1);
        map.put("obj",page);
        return map;
    }
}
