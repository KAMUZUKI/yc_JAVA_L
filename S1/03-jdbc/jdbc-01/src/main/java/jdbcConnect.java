import java.sql.*;

public class jdbcConnect {
    public static void main(String[] args) throws SQLException {
        final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        final String USER = "scott";
        final String PASS = "a";

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.prepareStatement("select deptno,dname,loc from dept");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("area_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){         // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){             // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null) stmt.close();
            }catch(SQLException se2){}
            try{
                if(conn != null) conn.close();
            }catch(SQLException se){}
        }

    }

}
