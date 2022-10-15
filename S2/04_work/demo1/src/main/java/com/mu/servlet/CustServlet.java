package com.mu.servlet;

import com.mu.bean.CartItem;
import com.mu.bean.Resorder;
import com.mu.bean.Resuser;
import com.mu.biz.OrderBiz;
import com.mu.model.JsonModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "CustServlet", urlPatterns = "/custOp.action")
public class CustServlet extends CommonServlet {
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        // 1.获取session 订单的参数
        Resorder resorder = null;
        try {
            resorder = super.parseRequestToT(request, Resorder.class);
            //调用业务层处理
            HttpSession session = request.getSession();
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            Set<CartItem> cartItems = new HashSet<>(cart.values());
            Resuser resuser = (Resuser) session.getAttribute("resuser");
            OrderBiz ob = new OrderBiz();
            int result = ob.order(resorder,cartItems,resuser);

            jm.setCode(1);
            session.removeAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
        }
        super.writeJson(jm, response);
    }
}
