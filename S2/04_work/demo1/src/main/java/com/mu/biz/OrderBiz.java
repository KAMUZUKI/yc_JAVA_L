package com.mu.biz;

import com.mu.bean.CartItem;
import com.mu.bean.Resorder;
import com.mu.bean.Resuser;
import com.mu.dao.DbHelper;
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
        Integer pid = 0;
        Connection conn = db.getConnection();
        conn.setAutoCommit(false);
        String sql = "insert into resorder(userid,address,tel,ordertime,deliverytime,ps,status) values(?,?,?,now(),?,?,0)";
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1,resuser.getUid());
    }
}
