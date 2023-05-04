package com.mu.resfoods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mu.bean.Resfood;
import com.mu.resfoods.biz.ResfoodBiz;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2023-04-08 16:59
 **/

@RestController
@RequestMapping("resfood")
public class ResfoodController {
    @Autowired
    private ResfoodBiz resfoodBiz;

    @RequestMapping("findById/{fid}")
    public Map<String, Object> findById(@PathVariable("fid") Integer id){
        Map<String,Object> map = new HashMap<>();
        Resfood rf = null;
        try {
            rf = this.resfoodBiz.findById(1);
        } catch (Exception e) {
            map.put("code",0);
            map.put("msg",e.getCause());
            return map;
        }
        map.put("code",1);
        map.put("obj",rf);
        return map;
    }

    @RequestMapping("findAll")
    public Map<String, Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        List<Resfood> list = null;
        try {
            list = this.resfoodBiz.findAll();
        } catch (Exception e) {
            map.put("code",0);
            map.put("msg",e.getCause());
            return map;
        }
        map.put("code",1);
        map.put("obj",list);
        return map;
    }

    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(int pageno,int pagesize,String sortby,String sort){
        Map<String,Object> map = new HashMap<>();
        Page<Resfood> page = null;
        try {
            page = this.resfoodBiz.findByPage(pageno,pagesize,sortby,sort);
        } catch (Exception e) {
            map.put("code",0);
            map.put("msg",e.getCause());
            return map;
        }
        map.put("code",1);
        map.put("obj",page);
        return map;
    }
}
