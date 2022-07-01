package com.mu2;

import com.mu02.utils.JDBCUtils;
import com.mu02.utils.MD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class newUser {
    public static void main(String[] args) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入登录用户名:");
        String name = sc.nextLine();
        System.out.println("请输入登录密码:");
        String pwd = MD5.getInstance().getMD5(sc.nextLine());
        try {
            con = JDBCUtils.getConnection();
            String sql = "insert into userinfo values (?,?)" ;
            System.out.println("语句为："+sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat,rs);
        }
    }
}
