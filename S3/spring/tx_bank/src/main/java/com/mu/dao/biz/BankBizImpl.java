package com.mu.dao.biz;

import com.mu.dao.AccountDao;
import com.mu.dao.OpRecordDao;
import com.mu.pojo.Account;
import com.mu.pojo.OpRecord;
import com.mu.pojo.OpType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : MUZUKI
 * @date : 2023-03-04 20:41
 **/

@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation= Isolation.DEFAULT,timeout=2000,
        readOnly=false,rollbackFor=RuntimeException.class)
public class BankBizImpl implements BankBiz{
    private Logger logger = LoggerFactory.getLogger(BankBizImpl.class.getName());

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private OpRecordDao opRecordDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,
        isolation= Isolation.DEFAULT,timeout=2000,
        readOnly=true,rollbackFor=RuntimeException.class)
    public Account findAccount(int accountId) {
        return this.accountDao.findById(accountId);
    }

    @Override
    public Account openAccount(double money){
        //开户操作 返回新账号的id
        int accountid = this.accountDao.insert(money);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOpType(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
        Account a = new Account();
        a.setAccountid(accountid);
        a.setBalance(money);
        return a;
    }

    @Override
    public Account deposite(int accountid,double money){
        Account a = null;
        try{
           a = this.accountDao.findById(accountid);
        }catch (RuntimeException re){
            a.setBalance(a.getBalance() + money);
        }
        a.setBalance(a.getBalance() + money);
        this.accountDao.update(accountid,a.getBalance());
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOpType(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
        return a;
    }

    @Override
    public Account withdraw(int accountid,double money){
        Account a = null;
        try{
            a = this.accountDao.findById(accountid);
        }catch (RuntimeException re){
            a.setBalance(a.getBalance() - money);
        }
        a.setBalance(a.getBalance() - money);
        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(accountid);
        opRecord.setOpmoney(money);
        opRecord.setOpType(OpType.WITHDRAW);
        this.opRecordDao.insertOpRecord(opRecord);
        this.accountDao.update(accountid,a.getBalance());
        return a;
    }

    @Override
    public Account transfer(int accountid,double money,int toAccountId){
        //收款方
        this.deposite(toAccountId,money);
        Account a = this.withdraw(accountid,money);
        return a;
    }
}
