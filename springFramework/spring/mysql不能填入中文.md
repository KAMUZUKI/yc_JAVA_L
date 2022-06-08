
## mysql不能输入中文

### 第一步：找到安装mysql的目录找到 my.ini 文件;
- 没有需要自建
### 第二步：使用记事本打开my.ini文件 
- 找到这个地方 看看default-character-set 是不是 utf8不是的话 改为utf8即可！（以前的版本可能没有这句话 直接加上就好了！）
### 第三步：在mysql数据库cmd中输入：
```shell
  show variables like'%char%';
```
查看数据库的编码格式！

---
`设置后最好重启服务,否则可能依旧插入不了!!!`
> **修改设置编码格式**
```shell
set character_set_database=utf8;
set character_set_server=utf8;
set character_set_client=gbk;
set character_set_connection=gbk;
```

 > 改变数据库默认字符集
 ```shell
  alter database 数据库名 character set utf8;
```

 > 查看表的字符集
 ```shell
  show create table 表名;
```

 > 修改表的默认字符集
```shell
alter table 表名 convert to character set utf8;
```