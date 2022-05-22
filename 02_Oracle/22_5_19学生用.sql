--
-- 上章回顾:
-- 	1.数据库的简历(数据库的产生、数据库的发展、数据库与应用程序的关系[心脏])
--
-- 	2.数据库的基本概念
-- 		a.实体：客户存在的、可以描述的事物（一个零件  一个飞机  一张桌子  一个苹果等）
--
-- 		b.实体属性：实体展现出来的一些性质，每一个实体都由各自的一些相关属性组成（显示器有大小 颜色 分辨率  耗能等）
--
-- 		c.数据库中的表：有行和列
--
-- 		d.记录和字段
-- 			表中的行用来存放实体，称为记录（一行存放一个实体信息）
-- 			表中的列用来存放每个实体的属性，称为字段或属性（PPT-6）
--
-- 		e.数据库管理系统（DBMS[DataBase Management System]）
--
-- 		f.数据冗余和数据完整性（PPT-9）
-- 			数据冗余：缺点占用存储空间，但有时必要的冗余也是必须的。
-- 			数据完整性：数据的正确性和相容性（主键）
-- 				实体完整性——主键
-- 				参照完整性(引用完整性)——外键
-- 				用户自定义完整性（域完整性或域约束）——类型约束、范围约束、格式约束
--
--
--
--
-- 本章重点：
-- 	1.了解 Oracle 数据类型
-- 	2.了解数据定义语言和数据操纵语言
-- 	3.了解事务控制语言和数据控制语言
-- 	4.掌握 SQL 操作符和 SQL 函数
--
-- SQL简介
-- 	1.SQL是Structured Query Language(结构化查询语言)
--
-- 	2.创建表的时候，必须为各列指定数据类型，那么Oracle中有那些数据类型呢？
-- 		字符(PPT-6)：char  varchar2  long(最多能存储2GB)
-- 		数值：number(p[,s]x212)  p：表示精度  s：表示小数点的位数
-- 		日期时间：
-- 		raw/long raw
-- 		lob
--
--
--
-- 	3.数据库表的创建
-- 		a.可视化创建方法
-- 			主键的选择原则:
-- 				最小性（尽量选择单个键作为主键）
-- 				稳定性（尽量选择数值更新少的列作为主键）[身份证不宜]
--
-- 				表中没有合适的列做主键怎么办？
-- 				使用标识列
--
--
-- 			设置主键
-- 			建立主外键约束
-- 			创建关系图
-- 			主外键操作注意事项（PPT-24）
-- 				[1]当主表中没有对应的记录时，不能将记录添加到子表
-- 				——成绩表中不能出现在学员信息表中不存在的学号；
--
-- 				[2]不能更改主表中的值而导致子表中的记录孤立
-- 				——把学员信息表中的学号改变了，学员成绩表中的学号也应当随之改变；
--
-- 				[3]子表存在与主表对应的记录，不能从主表中删除该行
-- 				——不能把有成绩的学员删除了
--
-- 				[4]删除主表前，先删子表
-- 				——先删学员成绩表、后删除学员信息表
-- 			建立检查约束
-- 			输入数据验证
-- 			数据的导入导出
--
-- 			可视化创建表的方法——移植性不好，有脚本随时可创建
--
-- 		b.语句
--创建班级信息表，classes(cid int 主键,cname varchar2(100) 非空 唯一,intro varchar2(1000));
CREATE TABLE classes (
     cid int primary key, --primary
     cname VARCHAR2 ( 100 ) NOT NULL UNIQUE,
     intro VARCHAR2 ( 1000 )
);

--创建学生信息表, stuInfo(sid int 主键,sname varchar2(100) 非空,sex char(2) 男或者女 偶然为男,
--age int 15到30之间,address varchar2(200) 默认地址不详,cid int 外键);

CREATE TABLE stuinfo(
    sid int primary key,
    sname VARCHAR2(100) not NULL,
    sex CHAR(3) DEFAULT '男' constraint CK_sex CHECK(sex in('男','女')),
    age int constraint CK_age CHECK(age BETWEEN 15 AND 30),
    address VARCHAR2(200) DEFAULT '地址不详',
    cid int constraint FK_classid references classes(cid)
);


--on delete cascade：级联删除，删除主表数据时，将从表中的数据也删除
--on delete set null：删除主表中的数据时，将从表中的数据置为空。
--创建用户信息表  表级约束

CREATE TABLE users(
    usid int primary key,
    uname varchar2(100) NOT NULL,
    age int default 18 constraint CK_uage CHECK(age BETWEEN 20 and 30),
    pwd varchar2(20),
    tel varchar2(20),
    addr varchar2(200),
    cardId varchar2(20) constraint CK_users_cardId CHECK(length(cardId)=18),
    constraint CK_info CHECK(tel is not null AND tel!='' or addr is not null and addr!='')
);

--创建主键约束
--alter table [table_name] add constraint [主键约束名]  primary key(列名);
CREATE table sales (
    sid int,
    sname varchar2(100)
);

--修改原表，增加主键的约束
alter table sales add constraint PK_sid primary key(sid);
--创建外建约束
--alter table [table_name] add constraint [外键约束名] foreign key(列名) references 表名(列名)
--删除原来的外键约束
alter table stuinfo drop constraint FK_classid;

--再重新创建外键约束
alter table stuinfo add constraint FK_classid foreign KEY(cid) references classes(cid);

--创建检查约束
alter table stuinfo drop constraint CK_age;

--再重新创建年龄的检查约束
alter table stuinfo add constraint s_age CHECK (age BETWEEN 18 and 30);

--删除性别约束
alter table stuinfo drop constraint CK_SEX;

--再重新创建性别的约束
alter table stuinfo add constraint CK_sex CHECK(sex in('男','女'));

--删除非空约束
alter table stuinfo drop constraint SYS_C005471;

--再重建非空约束
alter table stuinfo MODIFY sname not NULL;

--添加唯一约束
alter table stuinfo add constraint CK_stuinfo_cardId unique(cid);

--默认约束愿望清单

--给学生表添加一个愿望清单

alter table stuinfo add(birthdate date);
alter table stuinfo MODIFY birthdate default sysdate;

--查看约束
SELECT * from USER_CONSTRAINTS WHERE table_name='STUINFO';

--禁用或启动约束
-- 语法：alter table table_name rename to new_table_name;

--重命名表，customers为原表名，custs为新表名
alter table stuinfo rename  to stuinfo1;

--重命名列：alter table table_name rename COLUMN 旧列名 to 新列名;

--重命名列名，stuinfo为表名，Id为要修改的列名，stuid为新列名
alter table stuinfo1 rename COLUMN sid to stuid;

--添加新列：alter table table_name add column_name data_type [default value][null/not null],...;
alter table sales add price number(10,2) default 0.0;

--更改列的数据类型：alter table 表名 modify 列名 数据类型 [default value][null/not null],...;
alter table sales modify price number(2,10);

--删除列：alter table sales drop column price;
alter table sales drop column price;

--删除表：drop table table_name
drop table sales;

--创建好表后，查看表结构
select  * from USER_TAB_COLUMNS WHERE table_name='STUINFO1';
