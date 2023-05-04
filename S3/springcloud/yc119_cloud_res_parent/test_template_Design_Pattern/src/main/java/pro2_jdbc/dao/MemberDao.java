package pro2_jdbc.dao;


import pro2_jdbc.JdbcTemplate;
import pro2_jdbc.Member;
import pro2_jdbc.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;


public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    //查询所有的
    public List<?> selectAll(){
        String sql = "select * from resadmin";

        //以一个匿名内部类的方式 传入一个 RowMapper的对象. ,  用来确定resultSet中的一行如何转成一个Member对象。
        return super.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member = new Member();
                //字段过多，原型模式
                member.setRaid(rs.getString(1));
                member.setRaname(rs.getString("raname"));
                member.setRapwd(rs.getString("rapwd"));

                return member;
            }
        },null);
    }
}
