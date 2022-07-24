package com.mu02;

import com.mu02.utils.JDBCUtils;
import oracle.sql.BLOB;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class test {
    public static void main(String[] args) {
        String sid="10104";
        String sql="select headpic from student119 where sid=?";
        try(
                Connection con= JDBCUtils.getConnection();
                PreparedStatement pstmt=con.prepareStatement(sql);
        ){
            pstmt.setString(1,sid);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                BLOB blob=(BLOB)rs.getBlob(1);
                long flag=blob.getLength();
                System.out.println(flag);
                if(blob==null || flag==102) {
                    System.out.println("blob为空");
                }
            }
        }catch(Exception ex){
            throw new RuntimeException(ex );
        }
    }

}
