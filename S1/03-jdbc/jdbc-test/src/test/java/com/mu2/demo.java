package com.mu2;

import com.mu02.controller.StudentController;
import com.mu02.utils.JDBCUtils;
import com.mu02.utils.MD5;
import com.mu02.utils.Utils;

import java.sql.*;
import java.util.Scanner;


public class demo {
    public static void main(String[] args) throws SQLException {
        StudentController studentController = new StudentController();
        Scanner sc = new Scanner(System.in);
        int flag = 1;
        int count = 0;
        boolean access = login();
        while(flag!=0){
            if(!access){
                count++;
                if (count >= 3){
                    System.out.println("输错超过三次，程序退出！");
                    break;
                }
                System.out.println("输入错误"+count+"次!");
                continue;
            }
            menu();
            System.out.print("请选择：>");
            flag = sc.nextInt();
            if (flag!=0){
                switch(flag){
                    case 1:
                        studentController.findAll();
                        break;
                    case 2:
                        studentController.insert();
                        break;
                    case 3:
                        studentController.delete();
                        break;
                    case 4:
                        studentController.update();
                        break;
                    case 5:
                        studentController.findById();
                        break;
                    case 6:
                        studentController.batchInsert();
                        break;
                    case 7:
                        Utils.showSystemInfo();
                        break;
                    default:
                        System.out.println("输入无效值，请按菜单输入");
                }
            }
        }
        System.out.println("退出成功！");
    }

    public static void menu(){
        System.out.println("==============学生信息管理中心=================");
        System.out.println("====1.查询全部===========");
        System.out.println("====2.添加==============");
        System.out.println("====3.删除==============");
        System.out.println("====4.修改==============");
        System.out.println("====5.查询==============");
        System.out.println("====6.批量导入===========");
        System.out.println("====7.系统信息===========");
        System.out.println("====0.退出==============");
        System.out.println("============================================");
    }

    public static boolean login(){
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
            String sql = "select  * from userinfo where name=? and pwd=?" ;
            System.out.println("语句为："+sql);
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();
            if(rs.next()){
                System.out.println("欢迎您：" +name + "登录成功");
                return true;
            }else {
                System.out.println("密码或账号错误");
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(con,stat,rs);
        }
        return false;
    }
}
