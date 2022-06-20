import java.sql.*;
import java.util.Scanner;

public class test6 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入登录用户名:");
        String name=sc.nextLine();
        System.out.println("请输入登录密码:");
        String pwd=sc.nextLine();

        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
//        String sql = "insert into dept values(12,'统计部','湖南')";
        String sql = "select  * from dept where name='"+ name + "'and pwd='" + pwd + "'" ;
        System.out.println("语句为："+sql);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            System.out.println("欢迎您：" +name + "登录成功");
        }else {
            System.out.println("密码或账号错误");
        }
        rs.close();
        stmt.close();
        con.close();
    }
}
