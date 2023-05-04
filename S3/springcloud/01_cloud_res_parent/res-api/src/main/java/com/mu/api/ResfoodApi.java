package com.mu.api;

import com.mu.config.FeignLogConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2023-04-13 20:02
 **/

@FeignClient(value="res-foods",path="resfood",configuration= FeignLogConfig.class)
public interface ResfoodApi {

    @RequestMapping("findById/{fid}")
    public Map<String, Object> findById(@PathVariable("fid") Integer id);

    @RequestMapping("findByPage")
    public Map<String,Object> findByPage(@RequestParam int pageno, @RequestParam int pagesize, @RequestParam String sortby, @RequestParam String sort   );
}
