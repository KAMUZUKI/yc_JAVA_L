package com.mu.web.servlet;

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

@WebServlet(name = "CodeServlet",value = "/code.action")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width=100;
        int height=50;

        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);

        //2.美化图片
        //2.1.填充背景色
        Graphics graphics=image.getGraphics();//画笔对象
        graphics.setColor(Color.pink);//画笔的颜色
        graphics.fillRect(0,0,width,height);//fillRect方法可以画背景颜色

        //2.2画边框
        graphics.setColor(Color.RED);
        graphics.drawRect(0,0,width-1,height-1);//drawRect可以画边框

        //2.3写验证码
        String str="qwertyuiopasdfghjklzxcvbnm1234567890";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<=4;i++){
            int index=random.nextInt(str.length());
            char ch=str.charAt(index);
            sb.append(ch);
            graphics.drawString(ch+"",width/5*i,height/2);
        }

        HttpSession session = request.getSession();
        session.setAttribute("code",sb.toString());

        //画干扰线
        graphics.setColor(Color.green);
        for(int i=0;i<=10;i++){
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(height);
            int y2=random.nextInt(height);
            graphics.drawLine(x1,x2,y1,y2);

        }
//        //允许跨域访问
//        response.setHeader("Access-Cotrol-Allow-Origin","*");

        //禁止图像在浏览器缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        //设置响应的数据类型
        response.setContentType("image/png");//让浏览器识别到是一张图片
        //输出图片
//        //PrintWriter out = response.getWriter();//因为是图片，不用这个
//        ServletOutputStream sos=response.getOutputStream();
//        ImageIO.write(image,"jpg",sos);
//        sos.close();
        //简写
        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
