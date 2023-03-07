#mysql中不支持check约束, 但我的程序要判断  balance<0,要回滚报错.

#使用触发器来完成数据检测
CREATE TRIGGER after_balance_update
after update
ON accounts FOR EACH ROW
BEGIN
IF (NEW.balance < 0 ) THEN
update accounts set balance=OLD.balance where accountid=OLD.accountid;
END IF;
END;


#   balance在操作后，<0, 数据库会出现一个error
#  ->  java的jdbc  将error包装成  SQLException
#  ->  spring @Repository将这个 SQLException转成 RuntimeException(DataAccessException )的子类对象  ->抛给业务层,
#->在业务层我们会加入事务，就会发生回滚.