package com.mu.web.servlet;

import com.mu.bean.CartItem;
import com.mu.bean.Resfood;
import com.mu.dao.DbHelper;
import com.mu.web.model.JsonModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : MUZUKI
 * @date : 2022-10-09 19:24
 **/

@WebServlet(name = "ResorderServlet",value = "/resorder.action")
public class ResorderServlet extends CommonServlet {
    protected void getCartInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        JsonModel jsonModel = new JsonModel();
        if(session.getAttribute("cart")!=null){
            jsonModel.setCode(1);
            jsonModel.setData(cart.values());
        }else{
            jsonModel.setCode(0);
        }
        super.writeJson(jsonModel,response);
    }

    protected void clearAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jsonModel = new JsonModel();
        HttpSession session = request.getSession();
        session.removeAttribute("cart");
        jsonModel.setCode(1);
        writeJson(jsonModel,response);
    }

    /**
     * 购物车的操作
     */
    protected void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        //1.判断 是否登录
        HttpSession session = request.getSession();
        // 使用过滤器
//        if (session.getAttribute("resuser")==null){
//            jm.setCode(-1);
//            super.writeJson(jm,response);
//            return;
//        }
        //根据id查出 热水food对象，再放到 购物车
        // a。根据fid查数据库
        int fid = Integer.parseInt(request.getParameter("fid"));
        int num = Integer.parseInt(request.getParameter("num"));

        String sql = "select * from resfood where fid = ?";
        DbHelper db = new DbHelper();
        List<Resfood> list = db.select(sql,Resfood.class,fid);
        Resfood food = null;
        if(list!=null&&list.size()>0) {
            food=list.get(0);
        }
        //b。根据fid从 session中取
        //List<Resfood> foodlist = (List<Resfood>) session.getAttribute("foodlist");

        //2。添加购物车
        // session：一个客户端一个session(存map<fid,购物车对象>)
        Map<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
        if (session.getAttribute("cart")!=null){
            cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        }else{
            session.setAttribute("cart",cart);
        }
        CartItem ci = null;
        //购物车
        if (cart.containsKey(fid)){
            //如果为true，说明购物车中已经有了这个商品，则增加数量
            ci=cart.get(fid);
            int newNum = ci.getNum()+num;
            ci.setNum(newNum);
        }else {
            ci = new CartItem();
            //设置购物车对象的属性
            ci.setFood(food);
            ci.setNum(num);
        }
        //容错:
        if (ci.getNum()<=0){
            cart.remove(fid);
        }else{
            //计算小计
            ci.getSmallCount();
            //把购物车对象放到购物车中
            cart.put(fid,ci);
        }
        session.setAttribute("cart",cart);
        jm.setCode(1);
        // cart是一个map集合，我们取这个map的值 Set[CartItem,CartItem,CartItem]
        jm.setData(cart.values());
        //购物项对象
        //Resfood对象 数量 小计
        //逻辑：1.先从session.取出map,2.判断这个map中是否有要购物id(containsKey).
        //3.如果有，则从map中取出fid对应的购物项目，舟购物项中的数量+1分
        //如果没有·要建一个购物项时象·以于1d做键·存到map中
        //4.map存到sess1on中.
        super.writeJson(jm,response);
    }
}
