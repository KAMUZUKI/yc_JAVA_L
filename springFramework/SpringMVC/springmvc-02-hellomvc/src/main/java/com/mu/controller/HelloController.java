package com.mu.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//注意:先导入Controller接口
public class HelloController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // ModeAndView模型和视图
        ModelAndView mv = new ModelAndView();

        // 封装对象,放在ModelAndView中
        mv.addObject("msg","hello,SpringMVC!");

        // 封装要跳转的视图,放在ModelAndView中
        mv.setViewName("hello");

        return mv;
    }
}
