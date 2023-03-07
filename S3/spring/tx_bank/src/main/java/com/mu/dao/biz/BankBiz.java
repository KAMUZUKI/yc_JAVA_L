package com.mu.dao.biz;

import com.mu.pojo.Account;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 20:53
 **/

public interface BankBiz {
    Account findAccount(int accountId);

    Account openAccount(double money);

    Account deposite(int accountid, double money);

    Account withdraw(int accountid, double money);

    Account transfer(int accountid, double money, int toAccountId);
}
