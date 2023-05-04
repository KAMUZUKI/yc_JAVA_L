package pro2_jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import pro2_jdbc.dao.MemberDao;

import java.util.List;


public class MemberDaoTest {

    public static void main(String[] args) {
        DruidDataSource ds=new DruidDataSource();
        ds.setUsername("root");
        ds.setPassword("aaaa");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/yc119res?serverTimezone=UTC");

        MemberDao memberDao = new MemberDao(ds );
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
