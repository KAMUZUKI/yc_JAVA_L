package com.mu.dao.impl;

import com.mu.dao.AccountDao;
import com.mu.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 14:37
 **/

@Repository
public class AccountDaoImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int insert(double money) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = "insert into accounts(balance) values(?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setDouble(1, money);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(int accountdid, double money) {
        jdbcTemplate.update("update accounts set balance = ? where accountid = ?",
                money,accountdid);
    }

    @Override
    public int delete(int accountdid) {
        return jdbcTemplate.update("delete from accounts where accountid = " + accountdid);
    }

    @Override
    public int findCount() {
        return 0;
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query(
                "select * from accounts",
                (resultSet,rowNum)->{
                    Account account1 = new Account();
                    account1.setAccountid(resultSet.getInt("accountid"));
                    account1.setBalance(resultSet.getDouble("balance"));
                    return account1;
                });
    }

    @Override
    public Account findById(int accountid){
        return jdbcTemplate.queryForObject(
                "select * from accounts where accountid = ?",
                (resultSet,rowNum)->{
                    Account account1 = new Account();
                    account1.setAccountid(resultSet.getInt(1));
                    account1.setBalance(resultSet.getDouble(2));
                    return account1;
                },
                accountid);
    }
}
