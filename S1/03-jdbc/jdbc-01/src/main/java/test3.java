import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class test3 {
    public static void main(String[] args) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改的部门地址:");
        String loc = sc.nextLine();
        System.out.println("请输入要修改的部门号:");
        String deptno = sc.nextLine();
//        String sql = "insert into dept values(12,'统计部','湖南')";
        String sql = "update dept set loc='"+ loc + "'where deptno="+deptno;

        System.out.println("SQL语句为：" + sql);

        Statement stmt = con.createStatement();

        int result = stmt.executeUpdate(sql);
        if(result>0){
            System.out.println("修改部门数据成功");
        }else {
            System.out.println("失败");
        }
        stmt.close();
        con.close();
    }
}
