package com.mu.dao;

import com.mu.pojo.Account;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 14:30
 **/

public interface AccountDao {
    /**
     * 添加Account账号
     * @param money
     * @return 这个新账号的编号
     */
    public int insert(double money);

    /**
     * 更新账号的余额
     * @param accountdid
     * @param money
     */
    public void update(int accountdid,double money);

    /**
     * 删除账号
     * @param accountdid
     */
    public int delete(int accountdid);

    /**
     * 查询账号总数
     * @return
     */
    public int findCount();

    /**
     * 查询所有账号
     * @return
     */
    public List<Account> findAll();

    /**
     * 根据id查询账号
     * @param accountid
     * @return
     */
    public Account findById(int accountid);
}
