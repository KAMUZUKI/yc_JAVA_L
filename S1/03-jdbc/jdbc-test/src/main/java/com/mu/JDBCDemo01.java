package com.mu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo01 {
    public static void main(String[] args) throws Exception{
        //1.导入jar包

        //2.注册驱动
//        Class.forName("oracle.jdbc.OracleDriver");

        //3.获取连接
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");

        //4.获取执行者对象
        Statement stat = con.createStatement();

        //5.执行sql语句，并且接收结果
        String sql = "SELECT * FROM student";
        ResultSet rs = stat.executeQuery(sql);

        //6.处理结果
        while (rs.next()){
            System.out.println(rs.getInt("sage") + "\t" + rs.getString("sname"));
        }

        //7.释放资源
        con.close();
        stat.close();
        rs.close();
    }
}
