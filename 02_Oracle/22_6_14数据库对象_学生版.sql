

-----------------------------------------------------------4. 索引----------------------------------------------------------
--索引就是一种进行快速定位和查找的机制
--oracle中的索引是一种建立在表或簇基础上的数据对象，和表一样具有独立的段存储结构，需要在表空间中为其分配存储空间.

--为索引而准备的100W数据
--1. 测试生成10条记录     connect by level  :
--a、利用Oracle特有的“connect by”树形连接语法生成测试记录，“level <= 10”表示要生成10记录；
--b、利用rownum虚拟列生成递增的整数数据；
--c、利用sysdate函数加一些简单运算来生成日期数据，本例中是每条记录的时间加1秒；
--d、利用dbms_random.value函数生成随机的数值型数据，本例中是生成0到100之间的随机整数；
--e、利用dbms_random.string函数生成随机的字符型数据，本例中是生成长度为20的随机字符串，字符串中可以包括字符或数字。


--2. 正式生成100W[
-- 查看当前用户模式下所有的表
--先执行一次查询, 注意查询的时间
--生成索引后，再执行一次查询
--索引的特征:  索引也要存储，索引也占空间，由数据库自动维护索引.

create table myTestTable as
select rownum as id,
       to_char(sysdate + rownum/24/3600, 'yyyy-mm-dd hh24:mi:ss') as inc_datetime,
       trunc(dbms_random.value(0, 100)) as random_id,
       dbms_random.string('x', 20) random_string
from dual
connect by level <= 1000000;

--可以将一个索引建立在与数据文件不同的表空间上, 以提高效率
--1. 创建一个表空间, 一个表空间可以放在一个或多个文件上
--删除，创建表空间的权限


--2. 将索引文件建立在一个表空间上



--重建索引:   当数据经过了大量修改后，索引已经失效， 就要重建索引


--删除索引





--索引的类型:
--B树索引, 位图索引, 反向键索引, 全局与本地索引, 基于函数的索引
--注意: B树索引与位图索引是最基本的索引.
-- 1. B树索引:  平衡树索引.


--   oracle表的主键列上创建唯一索引
--语法:   create unique index 索引名  on 表名(列名);


--组合索引:   WHERE 子句中引用了组合索引的所有列或大多数列，则可以提高检索速度
--语法:   create index 索引名 on 表名(列名1,列名2....)
--案例: 对stu_test_100w  的stu_name,  birthday两个列进行组合索引





--反向键索引反转索引列键值的每个字节     reverse
--案例: 请对id列建立反向键索引


--位图索引:   相似于表分区中的列表分区:      位图用在只有几个值的列上(列的值基数比较小,  例如性别， 地区，省份，国家)
--一个索引对应多条数据.
--案列: 在emp表中建立针对deptno的位图索引


--基于函数的索引:   基于一个或多个列上的函数或表达式创建的索引
--表达式中不能出现聚合函数
--不能在LOB类型的列上创建
--创建时必须具有 QUERY REWRITE 权限
--创建一个函数索引




--oracle提供了一系列关于索引信息的视图
--USER_INDEXES: 用户创建的索引信息

--分区索引:   uSER_IND_PARTITIONS

--USER_IND_COLUMNS － 与索引相关的表列的信息









---------------------------------3. 视图-----------------------------
--why视图要用?
--优点:
--1. 提供了另外一种级别的表安全性
--2. 隐藏的数据的复杂性
--3. 简化的用户的SQL命令
--4. 隔离基表结构的改变
--5. 通过重命名列，从另一个角度提供数据
--6. 视图只需要非常小的空间来存储，而且只保存视图的定义，不保存视图涉及的数据.

--创建语法:
--   CREATE [ OR REPLACE] [FORCE|NOFORCE] VIEW [schema].view_name
--   [(column_name[,column_name]...)]
--   AS Subquery
--   [WITH CHECK OPTION [CONSTRAINT constraint]]
--   [WITH READ ONLY [CONSTRAINT constraint]];

--注意: 创建视图应有权限.
--1. 以sys as sysdba登录，给scott赋权限


--案例一:   将dept部门表以视图形式展示出来


--视图就是一张虚拟的表，所以可以使用select 进行查询

--案例二： 视图可以隐藏复杂的查询
-- 需求：查询出每个员工名及所属部门名



-- 需求: 查询出每个部门及这个部门下员工数



--视图学习深入
--1. force关键字:    忽略查询的错误来强制创建视图
--why   force?


--假设这张表以后会有，但前期必须先创建视图?
--请加入force




--2. 别名机制: 作用=》 隐藏真实的列名


--3. with check option的作用
--案例 :
--1. 创建一个视图，用于查询所有编号小于100的部门


--2. 请使用视图插入一个新部门，编号为 1000



--3. 查询视图，



--对比以上两个查询发现：视图中的数据丢失了.   with check option的作用就是给视图加一个条件约束，
--该约束所限的条件就是视图中子查询的where条件，以后如果想通过该视图执行DML操作，首先会检查.


-- 4. 内联视图
--定义:  在from 子句中使用子查询，这个子句就称为内联视图( inline view). 内联视图是一个命名的sql 语句，但不是真正的视图。
--应用:   翻页，排名,（注意还需要配合rownum一起使用)
-- a. 伪列 rownum : 伪列rownum的作用就是标识返回的结果集中数据行的行号,从1到n编辑.
--观察以下语句的运行结果


--分析以下语句的结果

--那么，如果想要查询到第二行以后的指定区间的记录，如何实现？  =》  使用内联视图.
--首先要清楚： rownum的赋值是在数据库解析完查询语句后，在查询语句做排序或聚合函数执行之前完成的，
--默认情况下rownum的取值是按照记录插入到数据库中的顺序赋值的。
--因此在进行排序查询时，不能直接使用rownum对返回的数据进行限制。
--分析以下案例: 查询出入职时间最早的前5名员工




--解决方案: 使用内联视图来完成top - n 查询
--分析:  先通过内联视图将满足区间的记录全部查出来，并在内联视图中使用rownum编号，然后在主查询中再使用rownum指定满足区间下限的查询条件


--体现视图的安全性:  可以将一个视图只赋权限给某一个人用
--赋权限语法:  grant  对象权限名 on对象名 to 用户名  with grant option




--安全性例子:
--需求:   1. 创建一个用户zy,    2. zy用户只能查看scott.emp中部门编号为20的数据

--1. 创建一个视图


--2. 将这个视图的访问权限赋值给zy2,   注意，请先创建zy2



--在视图上进行DML操作时，要求如下:
--只能修改一个底层的基表
--如果修改违反了基表的约束条件，则无法更新视图
--如果视图包含连接操作符、DISTINCT 关键字、集合操作符、聚合函数或 GROUP BY 子句，则将无法更新视图
--如果视图包含伪列或表达式，则将无法更新视图


--删除视图
--语法:  drop view 视图名




--oracle系统常见视图
--  user_xxx    用户视图
--  dba_xxx     管理员视图
--  all_xxx     所有视图
--  v$_xx       性能视图













-------------------------------1. 同义词------------------------
--以sysdba登录创建一个用户 zy
create user zy  identified by a;
--给zy 赋予登录数据库的权限
grant create session to zy;
--给zy赋予创建数据表的权限
grant create table  to zy;
--如果要创建别名，需要  create synonym权限,所以以sys用户登录赋予zy权限
grant create public synonym to zy;
--登录切换至zy用户中创建表student
create table student(
                        id int primary key,
                        name varchar2(50)
);
--给这个表插入数据
insert into student values( 1, 'zy');
--给scott用户访问这个表的一切权限
grant all on  student to scott;


--以scott用户登录，测试访问zy的student表
select * from zy.student

--上面，当scott中要访问zy中的student表时，必须使用用户名.表名的方式来访问，太麻烦;
--这时，可以使用别名来简化操作
--以zy登录并创建别名
--创建别名语法:
--create [or replace] [public] synonym [schema.]synonym_name
create or replace public synonym zystudent for zy.student@orcl;



--使用scott登录来访问这个student表
select * from zystudent;



--删除同义词
--drop synonym 同义词名;

--删除公有同义词
--drop public synonym 公有同义词名

--查看同义词信息
--通过三张数据字典表
--dba_synonyms, all_synonyms, user_synonyms
select * from dba_synonyms;
select * from all_synonyms;
select * from user_synonyms;




-----------------------------------2.序列-----------------------------
--why 要用 sequence?
create table student1(
                         sid int primary key,
                         sname varchar2(10 char)
);
--oracle中不支持identity自增的关键字，当要自增数据时，需要使用序列来自动生成
--可降也可以升


--序列语法: 查看文档
--CREATE SEQUENCE [schema.]sequence_name
--[INCREMENT BY n]
--[START WITH N]
--[MAXVALUE n|NOMAXVALUE]
--[MINVALUE n|NOMINVALUE]
--[CYCLE n|NOCYCLE]
--[CACHE n|NOCACHE]
--[ORDER|NOORDER];

--创建序列案例: 为student表创建序列
create sequence student1_seq
    start with 1000
    increment by 1
    nomaxvalue
nominvalue
nocycle
cache 10;

--序列中的关键字:  next_val     curr_val
select student1_seq.nextval from dual;    -- nextval: 下一个序列的值
select student1_seq.currval from dual;     --dual  测试表    curravl: 当前序列生成的值

--注意: 一定要先nextval之后，才能通过currval取到值

insert into student1 values(student1_seq.nextval,'a');
insert into student1 values(student1_seq.nextval,'b');

select * from student1;



--修改序列
--语法:   alter sequence 序列名   要修改的值
alter sequence student1_seq increment by 5;     --修改自增值为5, 原来是1

insert into student1 values(student1_seq.nextval,'c');
select * from student1;

--删除序列
--语法: drop sequence 序列名;
drop sequence student1_seq;

insert into student1 values(student1_seq.nextval,'d');






