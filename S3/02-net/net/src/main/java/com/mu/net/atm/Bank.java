package com.mu.net.atm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2023-01-02 12:19
 **/

public class Bank {
    private List<BankAccount> accounts = new ArrayList<BankAccount>();

    public Bank() {
        for (int i = 0; i < 10; i++) {
            accounts.add(new BankAccount(i, 10));
        }
    }

    /**
     * @description : 获取账户余额
     * @param id
     * @return : double
     */
    public BankAccount search(int id) throws Exception {
        for (BankAccount account : accounts){
            if (account.getId() == id){
                return account;
            }
        }
        throw new Exception("账号不存在");
    }

    /**
     * 存款
     * @param id
     * @param money
     * @return
     * @throws Exception
     */
    public BankAccount deposit(int id,double money) throws Exception {
        BankAccount account = search(id);
        synchronized (account) {
            account.setBalance(account.getBalance() + money);
        }
        return account;
    }

    /**
     * 取款
     * @param id
     * @param money
     * @return
     * @throws Exception
     */
    public BankAccount withdraw(int id,double money) throws Exception {
        BankAccount account = search(id);
        synchronized (account) {
            account.setBalance(account.getBalance() - money);
        }
        return account;
    }
}
