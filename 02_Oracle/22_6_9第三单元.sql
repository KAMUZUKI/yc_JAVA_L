-- ----------------------------------------------------------------------------------------
--                      DDL:   四个关键:   create ,    alter,    drop    truncate(截断)
----------------------------------------------------------------------------------------------
--create
drop table students;

--unique :唯一
create table students(
                         sid int primary key,
                         sname varchar2(50) unique     check( length(sname)>=2),
                         age int check( age between 15 and 30 ),
                         sex char(5) check( sex in ('男','女')  )
);

--修改表，增加一列，添入户籍   huji
alter table students add (   huji varchar2(60) default '湖南省长沙市'   );

--修改表， 将huji类的长度设为  50
alter table students modify(   huji varchar2(50)  );

--测试插入数据
-- 语法:  insert into 表名( 列名1, 列名2,...) values( 值1, 值2, ....);
insert into students( sid, sname,age, sex, huji ) values(1, '张三',28, '男', '湖南省衡阳市');
--简单插入: 如果所有的列都要插入的话，可省略列名
insert into students                     values(  2,'李四',27, '男', '湖南省长沙市');
--默认值
insert into students                    values( 3, '张三丰',25, '男',default );   --default表示用默认值 插入.
--错误的演示: 如果省略了列名，  ，则values中要插入所有的字段的数据，少一个都不行.
insert into students                    values( 3, '张三丰',25, '男' );  --没有足够的值
insert into students                    values( 4, '赵无忌',225, '男',default );  --年龄违反了检查约束.
insert into students                    values( 1, '张无忌',25, '男',default );  --主键不能重复
insert into students                    values( 5, '赵明',25, '不男',default ); -- 性别取值不对


--查询刚加入的数据
select * from students;


-- 测试 DDL 中的  truncate 与drop
drop table product;

create table product(
                        pid int primary key,
                        pname varchar2(20)
);
insert into product values( 1, 'apple');
insert into product values( 2, 'pear');
insert into product values(  100, 'grape');
--drop后表结构也不存在了
drop table product;
--truncate后数据一次性被 删除，且不记录日志,   表结构还在
truncate table product;

select * from product;
/*
 truncat小结：
  1. 表格里的数据被清空，存储空间被释放。
	2. 运行后会自动提交，包括之前其它未提交的会话，因而一旦清空无法回退。
	3. 只有表格的创建者或者其他拥有删除任意表格权限的用户（如DBA）才能清空表格。
*/

-----------------------------------------truncate与delete的区别:性能上的区别 ---------------------------
--1. dual是什么？ 它是一个空表，是一个测试表
select sysdate from dual;
select rownum from dual;   --rownum 行号   为列
select rownum,pid,pname from product;
--将日期时间对象转为字符串
--as xxx  做一个列的别名
select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') as 格式化后的时间  from dual;
--dbms_random.value(0, 10)  生成随机数  0-10
select dbms_random.value(0, 10) from dual;
--trunc 去掉小数点后的小数位，只保留整数
select trunc(  dbms_random.value(0, 10) )   from dual;
--dbms_random.string('x', 4)  生成随机字符串
select dbms_random.string('x', 4) from dual;

--生成100w条数据存到  testtable表中. 用于测试  delete与truncate在删除数据时，性能上的区别??
drop table testtable;
--随机产生100w条数据，创建表 testtable,并将数据存到表中.
create table testtable as
select rownum as id,
       to_char(sysdate + rownum/24/3600, 'yyyy-mm-dd hh24:mi:ss') as inc_datetime,
       trunc(dbms_random.value(0, 100)) as random_id,
       dbms_random.string('x', 20) random_string
from dual
    connect by level <=1000000;
--统计表中的数据行数.
select count(*) from testtable;

--最终的目标： 看truncate  ,delete的删除性能
truncate table testtable;   --  0.015 second    快， 但不能恢复，因为它不记录删除的日志
delete from testtable;  -- 8.9 second           慢，但可以恢复，


--------------------------------------------------------------------
--                    TCL:事务控制语句
--    commit提交( 还有一些sql语句会自动提交 truncate,drop),   rollback回滚   savepoint保存点
--------------------------------------------------------------------
select * from product;

truncate table product;   --自动提交

insert into product values( 1, 'apple');
insert into product values( 2, 'pear');
insert into product values( 3, 'banana');
insert into product values( 4, 'pear3');
commit;   --必须提交， 这样其它的进程才能看到数据.
--insert 是不会自动提交数据的.

--结合删除来学习  事务处理
savepoint p1;     --记录删除前的状态

delete from product where pid=4;   --删除4

select * from product;

rollback to p1;

select * from product;
--  delete操作是可以回滚的

savepoint p2;
truncate table product;  --truncat会自动提交，所以保存点无效，无法回滚.

select * from product;

rollback to p2;

select * from product;

---------------------------------------------------------------------------------------
--               简单的DML语句  数据操作语言:
--              select   update    delete    insert
---------------------------------------------------------------------------------------
truncate table product;
insert into product values( 1, 'apple');
insert into product values( 2, 'pear');
insert into product values( 3, 'banana');
insert into product values( 4, 'pear3');
commit;
--1. select 语法:   select 列名 from 表名;
select * from product;
select pname,pid from product;
select pid as 编号,pname as 产品名 from product;     --  as 别名
--2. update语法:  update 表名 set 列名1=新值,列名2=新值2  where 条件
update product set pname='snake apple' where pid=1;

select * from product; --更新后的测试

--3. delete语法: delete from 表名  [where 条件]
delete from product where pid=2;
select * from product; --删除后的测试
delete from product;


/*
   sql中的运算符     =相等判断, <>不等于   !取反
*/
drop table product;
create table product(
                        pid int primary key,
                        pname varchar2(20)
);
insert into product values( 1, 'apple');
insert into product values( 2, 'pear');
insert into product values( 3, 'snake pear');
insert into product values( 4, 'snow pear');

select * from product;
--相关运算符
select * from product where pid=1 and pname='zz';
select * from product where pid<>1;
select * from product where 1=1;   --特殊用法
/* 利用伪代码理解
  for( 一条数据  in product表中的数据集合)｛
      if( pid=1 and pname='zz' ){
           提取当前循环的这条数据 i
      }
  }
*/
select * from product where 1<>1;  --特殊用法
/*
  for( i in product)｛
      if( 1<>1 ){
           提取当前循环的这条数据 i
      }
  }
*/

select * from product where 1=1;  --特殊用法
/*
  for( i in product)｛
      if( 1=1 ){
           提取当前循环的这条数据 i
      }
  }
*/

--  1=1   和   1<>1 特殊用法
--需求: 1. 复制一张表test85, 数据与结构与emp一样
select * from emp;

drop table test85;

create table test85
as
select * from emp where 1=1;
--查看一下test85,是否有数据
select * from test85;
--需求: 2. 复制一张表test85_1, 只要结构与emp一样
drop table test85_1;

create table test85_1
as
select * from emp where 1<>1;
--查看一下test85_1,是否有数据
select * from test85_1;

--扩展:  为什么  查询后最后加   1=1.
--java来拼接复杂的查询语句
select * from product ;
select * from product where pid=1;
select * from product where pname='pear';
select * from product where pid=1 and pname='pear';
---只要写一条原始语句，然后根据业务需要来拼接  其它条件
select * from product where 1=1
                        and pid=1
                        and pname='pear';
--反过来说，如果没有1=1
select * from product where pname='pear' and pid=1;


/*
   字符串  相当于正则表达式 通配符:
                _代表一个任意的字符
                %代表任意多个字符
	通配符要与  like一起用 , 用于模糊查询
*/

insert into product values(5,'火龙果');
insert into product values(6,'桃子');
insert into product values(7,'火了');

select * from product;

select * from product where 1=1 and pname like '%火%';
select * from product where 1=1 and pname = '%火%';    --错误，不能用=联接通配符
select * from product where 1=1 and pname like '%子';
select * from product where 1=1 and pname like '火_';


/**
   一个逻辑表达式的案例.
	A and B
  A	or  B
	not  A     取反
*/
--一张支付信息表
drop table payinfo;
--pid:支付号   userid:用户id   paytype:支付类型   creaditcard信用卡号
create table payinfo(
                        pid int primary key,
                        userid int,
                        paytype varchar2(10),
                        creaditcard varchar2(20)
);
insert into payinfo values(1, 1,'信用卡','龙卡');
insert into payinfo values(2, 1,'信用卡','长城卡');
insert into payinfo values(3, 2,'现金','');
insert into payinfo values(4, 2,'微信','');

select * from payinfo;
--问题:以下语句的结果
select * from payinfo where paytype='信用卡';
select * from payinfo where not(paytype='信用卡') ;

select * from payinfo where not(paytype='信用卡') or creaditcard<>'龙卡';   ---


/**
   日期与时间,
	 dual表:测试表，空表     相当于  linux中的   NULL
*/
select * from dual;
--dual是一个空表，用于测试和学习中
SELECT sysdate FROM  dual;  -- ***系统时间  ( oracle 服务器 时间  )
SELECT systimestamp FROM  dual;  -- 当前系统时间戳
SELECT CURRENT_TIMESTAMP FROM  dual;  -- 与时区设置有关，返回的秒是系统的，返回的日期和时间是根据时区转换过的
SELECT current_date FROM  dual;  -- 是对CURRENT_TIMESTAMP准确到秒的四舍五入
select SYSDATE ,systimestamp,current_date,current_timestamp from dual;

/**
 时间日期与字符串的转换
 java : new  SimpleDateFormat( "yyyy年MM月dd HH:mm:ss");
        .format(   new Date() );
*/
-- to_date(  字符串,   字符串的时间格式  ）  ->时间类型   将字符串转时间类型 ****
SELECT to_date('2018-08-23 00:00:00','yyyy-mm-dd hh24:mi:ss') as 新时间 FROM dual;
-- to_char(  时间类型变量,   '要转换的时间格式')  ->  字符串    将时间类型转字符串   ****
select to_char(sysdate,'yyyy-mm-dd hh24::mi:ss') from dual;
select to_char(sysdate,'yyyy-mm-dd') from dual;


/**
    序列 sequence 对象:用于标识列
		CREATE SEQUENCE 序列名
　　[INCREMENT BY n]                  --每次增加 n
　　[START WITH n]                    --从n开始
　　[{MAXVALUE/ MINVALUE n| NOMAXVALUE}]    --最大值
　　[{CYCLE|NOCYCLE}]                    -- cycle 循环使用
　　[{CACHE n| NOCACHE}];                -- n表示一次性生成n个数字
*/
drop sequence seq_test1;

create sequence seq_test1
    start with 100
    minvalue  90
    maxvalue 105
    cycle
    cache 2;      --

select seq_test1.currval from dual;    -- currval  取当前数字
select seq_test1.nextval from dual;    --nextval 生成下一个

--------------------------------使用上面的知识添加数据----
drop table student74;

create table student74(
                          sid int primary key,
                          sname varchar2(20) not null unique,
                          sex char(4) default '男',
                          age int check( age>18 and age<25),
                          birthday date,
                          rolldate varchar2(30)
);

create sequence seq_student
    INCREMENT BY 1
    START WITH 1000;

insert into student74(sid,sname,age) values( seq_student.nextval, '张三',20   );

select * from student74;

insert into student74   values( seq_student.nextval, '张三丰',default, 20 ,
                                to_date('1/1/1980','MM/DD/YYYY'),                          '2020-02-02'       );

insert into student74   values( seq_student.nextval, '张无忌',default, 20 ,
                                to_date('1/1/1980','MM/DD/YYYY'),                          to_char( sysdate,'yyyy-mm-dd')      );


select * from student74;
--数据顺序不一致导致类型一致，所以下面语句出错.
--insert into student74
--values( seq_student.nextval, '张三丰', default, to_date('1/1/1980','MM/DD/YYYY'),20  );





