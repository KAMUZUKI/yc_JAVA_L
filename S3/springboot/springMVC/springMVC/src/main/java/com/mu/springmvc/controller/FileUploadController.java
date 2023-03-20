package com.mu.springmvc.controller;

import com.mu.springmvc.dao.StuMapper;
import com.mu.springmvc.domain.Stu;
import com.mu.springmvc.utils.JsonModel;
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
    public JsonModel upload(@RequestParam("head") MultipartFile head,@RequestParam("file") MultipartFile... lifes) throws IOException {
        Map<String, String> map = new HashMap<>();
        JsonModel jm = new JsonModel();
        map.put("lifes", lifes.length + "");
        String userHome = System.getProperty("user.home");

        String fileName = null;
        File f = null;

        for (int i = 0; i < lifes.length; i++) {
            fileName = lifes[i].getOriginalFilename();
            f = new File(userHome + File.separator + fileName);
            map.put("pictures" + i, fileName);
            log.info(f.getPath());
            try {
                lifes[i].transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        jm.setCode(1).setData(map);
        return jm;
    }

    @PostMapping("/register")
    @ResponseBody
    public JsonModel register(@RequestParam("uname") String uname,
                              @RequestParam("pwd") String pwd,
                              @RequestParam("head") String head,
                              @RequestParam("lifes") String[] lifes) {
        JsonModel jm = new JsonModel();
        Stu s = new Stu();
        s.setUname(uname);
        s.setPwd(pwd);
        s.setHead(head);
        s.setLifes(String.join(",", lifes));
        jm.setCode(stuMapper.insert(s));;
        return jm;
    }
}
