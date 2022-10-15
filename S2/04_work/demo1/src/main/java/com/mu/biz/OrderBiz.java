package com.mu.biz;

import com.mu.bean.CartItem;
import com.mu.bean.Resorder;
import com.mu.bean.Resuser;
import com.mu.dao.DbHelper;

import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Set;

/**
 * @author : MUZUKI
 * @date : 2022-10-13 21:22
 **/

public class OrderBiz {
    public int order(Resorder resorder, Set<CartItem> cartItems, Resuser resuser)throws Exception{
        if (cartItems==null||cartItems.size()<=0){
            throw new RuntimeException("订单为空...");
        }
        int result = 0;
        DbHelper db = new DbHelper();
        Integer roid = 0;
        Connection conn = db.getConnection();
        try {
            conn.setAutoCommit(false);
            String sql = "insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status) values(?,?,?,now(),?,?,0)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,resuser.getUserid());
            pstmt.setString(2,resorder.getAddress());
            pstmt.setString(3,resorder.getTel());
            pstmt.setString(4,resorder.getDeliverytime());
            pstmt.setString(5,resorder.getPs());
            result = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                roid = rs.getInt(1);
            }

            String sql2 = "insert into resorderitem(roid,fid,dealprice,num) values(?,?,?,?)";
            for (CartItem cartItem : cartItems) {
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1,roid+"");
                pstmt.setString(2,cartItem.getFood().getFid()+"");
                pstmt.setString(3,cartItem.getFood().getRealprice()+"");
                pstmt.setString(4,cartItem.getNum()+"");
                pstmt.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (conn!=null){
                conn.rollback();
            }
            throw e;
        }finally {
            if (conn!=null){
                conn.close();
            }
        }
        return result;
    }
}
