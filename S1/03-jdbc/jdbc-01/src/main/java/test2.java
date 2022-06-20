import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test2 {
    public static void main(String[] args) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");
        System.out.println(con);

//        String sql = "insert into dept values(12,'统计部','湖南')";
        String sql = "delete from dept where deptno=12";
        Statement stmt = con.createStatement();
        int result = stmt.executeUpdate(sql);
        if(result>0){
            System.out.println("删除数据成功");
        }else {
            System.out.println("失败");
        }
        stmt.close();
        con.close();
    }
}
