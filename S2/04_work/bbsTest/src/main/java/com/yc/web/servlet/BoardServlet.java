package com.yc.web.servlet;

import com.yc.bean.Board;
import com.yc.biz.ShowIndex;
import com.yc.dao.DBHelper;
import com.yc.web.model.JsonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BoardServlet" ,value = "/board.action")
public class BoardServlet extends CommonServlet {
    //获取首页Index需要的数据:op=showIndex
    protected void boarddata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm=new JsonModel();
        DBHelper db=new DBHelper();
        ShowIndex showIndex=new ShowIndex();
        HttpSession session=request.getSession();
        Map<Board, List<Board>> map;
        //1.从数据库查首页index需要展示的数据
        try{
            map= showIndex.getData();
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;

        }
        //2.将数据包装,通过JsonModel返回
        jm.setCode(1);

        /**
         * 下面这一段是将map里面的数据进行处理再返回
         * gson处理map中键是对象的方法就是直接调用这个对象的HashCode,所以就会显示成Board对象中toString的样子
         * 如果要将
         */
        //将Map<Board,<List<Board>>这个结构转成Map<String,List<Board>>
        Map<String,List<Board>>r=new HashMap<String ,List<Board>>();
        for (Map.Entry<Board,List<Board>> entry:map.entrySet()){
            r.put(entry.getKey().getBoardname(),entry.getValue());
        }

        jm.setData(r );
        super.writeJson(jm,response);
    }

}
