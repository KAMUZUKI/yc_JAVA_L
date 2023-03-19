package com.mu.springmvc.controller;

import com.mu.springmvc.dao.StuMapper;
import com.mu.springmvc.domain.Stu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2023-03-19 15:05
 **/

@Controller
@Slf4j
public class FileUploadController {

    @Autowired(required = false)
    private StuMapper stuMapper;

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam MultipartFile head,
                                      @RequestParam MultipartFile[] lifes) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("head", head.getName());
        map.put("lifes", lifes.length + "");

        String user_home = System.getProperty("user.home");
        String fileName = head.getOriginalFilename();
        File f = new File(user_home + File.separator + fileName);
        log.info(f.getPath());
        try {
            head.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lifes.length; i++) {
            fileName = lifes[i].getOriginalFilename();
            f = new File(user_home + File.separator + fileName);
            log.info(f.getPath());
            lifes[i].transferTo(f);
        }
        return map;
    }

    @PostMapping("/register")
    @ResponseBody
    public int register(@RequestBody Stu stu) {
        Stu s = new Stu();
        s.setUname(stu.getUname());
        s.setPwd(stu.getPwd());
        s.setHead(stu.getHead());
        s.setLifes(stu.getLifes());
        int res = stuMapper.insert(s);
        log.info("res:{}", res);
        return res;
    }
}
