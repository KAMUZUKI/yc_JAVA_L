import java.sql.*;
import java.util.Scanner;

public class test5 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询的条件");
        String location = sc.nextLine();
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");

//        String sql = "insert into dept values(12,'统计部','湖南')";
        String sql = "select * from dept where loc='" + location + "'";
        System.out.println("SQL语句为：" + sql);

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int deptno=rs.getInt("DEPTNO");
            String dname=rs.getString("DNAME");
            String loc=rs.getString(3);
            System.out.println(deptno+"\t"+dname+"\t"+loc);
        }
        rs.close();
        stmt.close();
        con.close();
    }
}
