package com.yc.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CodeServlet", value = "/code.action")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建对象，在内存中图片（验证码图片对象）
        int width = 100;
        int height = 50;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        //2.美化图片
        //2.1填充背景色
        Graphics graphics = image.getGraphics();//画笔对象
        graphics.setColor(Color.PINK);//画笔的颜色
        graphics.fillRect(0, 0, width, height);//fillRect方法可以画背景颜色

        //2.2画边框
        graphics.setColor(Color.red);
        graphics.drawRect(0, 0, width - 1, height - 1);//drawRect方法可以画边框

        //2.3 TODO:   验证的范围更大  .      写验证码
        String str = "qwertyuiopasdfghjklzxcvbnm1234567890";
        Random random = new Random();//创建随机对象
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {         //循环4次，产生4位的验证码
            int index = random.nextInt(str.length());        //生成角标
            //随机字符
            char ch = str.charAt(index);
            sb.append(ch);
            //TODO:  每个字的颜色不同， 位置不同.
            //绘制随机字符到  graphics
            graphics.drawString(ch + "", width / 5 * i, height / 2);//验证码
        }

        HttpSession session = request.getSession();
        session.setAttribute("code", sb.toString());//将验证码原码保存到session里面


//        //2.4画干扰线   ( TODO: 升级: 曲线，　每根线的颜色不一样．　)
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, x2, y1, y2);
        }
        response.setContentType("image/png");
        //3.输出图片（字符输出流）
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

