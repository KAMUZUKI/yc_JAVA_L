import java.sql.*;

public class test4 {
    public static void main(String[] args) throws SQLException {
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","a");

//        String sql = "insert into dept values(12,'统计部','湖南')";
        String sql = "select * from dept";
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
