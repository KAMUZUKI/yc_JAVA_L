

create table stuinfo
(
    stuid int primary key,
    sname varchar2(50) unique,
    sage int not null
        constraint CK_age check(sage>=18 and sage<=30),
    saddress varchar2(50)
);

create table course(
                       cid int primary key,
                       cname varchar2(50)
);
create sequence seq_score_id;
create table score(
                      scoreid int primary key,
                      studentid int ,
                      courseid int ,
                      score int
);

insert into course(cid,cname) values(1,'j2se精讲');
insert into course(cid,cname) values(3,'sql server');
insert into course (cid,cname) values(9,'html网页设计');

insert into stuinfo(stuid,sname,sage,saddress) values(31,'张果老',22,null);
insert into stuinfo(stuid,sname,sage,saddress) values(29,'张三在',20,null);
insert into stuinfo(stuid,sname,sage,saddress) values(13,'李豹',22,null);
insert into stuinfo(stuid,sname,sage,saddress) values(15,'老胡',22,'北京');
insert into stuinfo(stuid,sname,sage,saddress) values(17,'老江',24,'湖南');
insert into stuinfo(stuid,sname,sage,saddress) values(19,'张无忌',26,'衡阳');
insert into stuinfo(stuid,sname,sage,saddress) values(32,'二师兄',28,'长沙');
insert into stuinfo(stuid,sname,sage,saddress) values(1,'韦小宝',27,'上海');


insert into score values(seq_score_id.nextval,31,1,95);
insert into score values(seq_score_id.nextval,29,1,67);
insert into score values(seq_score_id.nextval,13,1,56);
insert into score values(seq_score_id.nextval,15,1,81);
insert into score values(seq_score_id.nextval,17,1,82);
insert into score values(seq_score_id.nextval,19,1,78);
insert into score values(seq_score_id.nextval,13,3,81);
insert into score values(seq_score_id.nextval,15,3,92);
insert into score values(seq_score_id.nextval,17,3,81);
insert into score values(seq_score_id.nextval,19,3,66);
insert into score values(seq_score_id.nextval,29,3,36);
insert into score values(seq_score_id.nextval,31,3,73);
insert into score values(seq_score_id.nextval,31,9,57);
insert into score values(seq_score_id.nextval,29,9,76);
insert into score values(seq_score_id.nextval,13,9,78);
insert into score values(seq_score_id.nextval,15,9,89);
insert into score values(seq_score_id.nextval,19,9,93);
insert into score values(seq_score_id.nextval,13,1,80);
insert into score values(seq_score_id.nextval,29,3,88);
insert into score values(seq_score_id.nextval,31,9,69);

select * from stuinfo;
select * from course;
select * from score;

-- DDL: 数据定义语言，  自动提交事务(隐式事事务)
-- DML: select,insert,update,delete   -> 手工提交事务

--查出所姓'张'的学员信息
select * from stuinfo where sname like '张%';

--查出所有saddress字段为'NULL'值的学员的信息
select * from stuinfo where saddress is null;
-- select * from stuinfo where saddress = null;  -- 错误，为null判断 要用 is null
select * from stuinfo where saddress is not null;

--查出成绩在60到70分之间的学员的id号
select * from score where score between 60 and 70;
select * from score where score>=60 and score<=70;

-- 进一步：要显示出更详细的信息
-- 分析：要求从多张表查出数据，当成一张表输出  联接查询
-- 解决方案一：普通查询的多表查询  特点：from 表1,表2,表3 where 联接条件
-- 如果想要显示详细信息的话  则从多表中查取数据，要填写联接条件
select stuid 学生号,sname 学生名,courseid 课程号,cname 课程名,score 成绩
from stuinfo,score,course
where 1=1 and stuinfo.stuid=score.studentid and course.cid=score.courseid
and score between 60 and 70;

-- 多表中的联接列名相同，则要查询出来的列中必须用  表名，列名 来指定从哪张表取数据 一般从主表取
select stuinfo.stuid as 学生号,sname as 学生名,course.cid as 课程号,cname as 课程名,score as 成绩
from score,stuinfo,course
where 1=1 and score.studentid=stuinfo.stuid and score.courseid=course.cid
and score between 60 and 70;

-- 解决方法二：多表的联接查询（内联）
select stuinfo.stuid as 学生号,sname as 学生名,course.cid as 课程号,cname as 课程名,score as 成绩
from score
inner join stuinfo on stuinfo.stuid=score.studentid
inner join course on course.cid=score.courseid
where score between 60 and 70;

--查出地址是北京和上海的学生的信息
-- 方案一： 用in完成选择
select * from stuinfo where saddress in ('北京','上海');

-- 方案二：in 相当于 course
select * from stuinfo
    where saddress='北京' or saddress='上海';

--查出学员在'sql server'这门课程中的总成绩和平均成绩,最高分数，最低分数
--聚合函数 max，min，avg，count，sum 将多个值 合并成一个
-- 方案一：分成几句语句查
select * from course where cname='sql server';
select max(score)最高成绩,min(score)最低成绩,trunc(avg(score))平均成绩,sum(score)总和,count(score)参考人数
from score
    where courseid=3;

select max(score)最高成绩,min(score)最低成绩,trunc(avg(score))平均成绩,sum(score)总和,count(score)参考人数
from score
where courseid=(select cid from course where cname='sql server');
--查出学员在'j2se精讲'这门课程中不及格的学生人数

--深入思考: 以上只求出了某一特定的课目的平均成绩，那如何一次性求出每一门课程的平均成绩呢？
--解决方案: 对课程进行分组,形成多个虚拟的数据集，对分组之后的结果再来求平均值
--语法: select 列名  from 表名   where 条件   group by 分组列
--1.只求各门课程的平均成绩

select courseid 课程号,trunc(avg(score)) 平均成绩
from score
    group by courseid;
--2.求各门课程的平均成绩,最高成绩，最低成绩，参考人数
select courseid 课程号,trunc(avg(score)) 平均成绩,max(score) 最高成绩,min(score) 最低成绩,count(score) 参考人数
from score
group by courseid;

--3.加入课程名,score
-- 小结： 结果集中如果有聚合函数，则返回的列要不就是聚合函数列，要不就是分组列，不可能出现非分组或聚合的列
select courseid 课程号,trunc(avg(score)) 平均成绩,max(score) 最高成绩,min(score) 最低成绩,count(score) 参考人数
from score,course
where score.courseid=course.cid
group by courseid,cname
order by courseid desc;

--需求，按每个学生的每门课程求平均成绩
-- 多列分组
--1.步骤一
select studentid 学生号,courseid 课程号, trunc(avg(score)) 平均成绩
from score
group by studentid,courseid
order by studentid,courseid;

--2.显示学生名及课程名 每个学生的每门课程求平均成绩
--多表联接查询
select studentid 学生编号,sname 学生名,courseid 课程编号,cname 课程名,trunc(avg(score)) 平均成绩
from score,course,stuinfo
where score.studentid = stuinfo.stuid and score.courseid = course.cid
group by studentid,courseid,sname,cname
order by studentid,courseid;
--小结： 分组的意义，就是将大数据集拆成多个小的数据集，才分别对小数据进行聚合处理

--特别提醒: 分组后，select 子句后的查询的结果要不就是分组的列，要不必须是聚合函数的列，而不能是其它的列.
--如下所示:   错误:

--多列分组的情况: 需求描述: 要求求出每次测试不同学员的不同课目的成绩(如果有补考，则为多次成绩的平均值)


--在上面基础上，只想查看参加过补考的学员的补考课目的平均成绩.
--分析，这时要对分组后的数据进行分析，如果是补考的课目，则分组后会有多条记录，
select studentid 学生编号,sname 学生名,courseid 课程编号,cname 课程名,trunc(avg(score)) 平均成绩
from score,course,stuinfo
where score.studentid = stuinfo.stuid and score.courseid = course.cid
group by studentid,courseid,sname,cname
having count(*) > 1
order by studentid,courseid;  -- 分组的子集要加入的条件

--总结：select 语法
-- select 列名
--from 表名
--where 条件
--group by 分组列
--having 条件.

--从多个表中取数据场景:
--需求: 显示学生姓名，课程名及成绩
--解决方案1. 普通查询+多个主外键相等判断



--解决方案2: 使用多表联接查询
--种类:1. 内联结(inner join):   仅当至少有一个同属于两表的行符合联接条件时，内联接才返回行. 内联接消除与另一个表中任何不匹配的行.
--     2. 外联结    ： 外联接会返回from 子句中提到的至少一个表的所有行，只要这些行符合任何where或having条件。
--            左外联接   left join ： 左边表中所有的行，右边表中没有的字段用null代替
--            右外联接   right join : 右边表中所有的行, 左边表中没有的字段用null代替
--            完整外联结  full join : 两表数据都返回，没有的地方用null代替.
--     3. 交叉联结  (cross join)




--1. 内联结语法:  select 列名  from 表名
--                  inner join 表名2
--                  on 联结条件
--案例:   显示学生姓名，课程名及成绩


--深入观察: 以下两条语句查询出来的结果数分别为多少，为什么?
select * from course;
select * from score;
select * from stuinfo;

select c.cname,s.score
from score s
inner join course c
on  s.courseid=c.cid ;
--结果为:     ___20___条记录

select c.cname,s.score
from score s, course c
where s.courseid=c.cid;
--结果为:  _____20____条记录

--左外联接: 语法:  select 列名 from 表名1  left join 表名2 on 联结条件
--特点:  优先查出表名1中所有的符合条件的数据，如果表名2中没有这个对应的数据，则用nULL填充值
--案例描述: 学生表中有一位学员'韦小宝'， 1号，没有参加过任何考试，下面请求出所有没有参加过任何考试的学员

select stuinfo.stuid 学生号,stuinfo.sname 学生名,score.score 成绩
from stuinfo
left join score on score.studentid=stuinfo.stuid;

-- 利用nvl(comm,0)将null 改为数字
select stuinfo.stuid 学生号,stuinfo.sname 学生名,nvl(score.score,0) 成绩
from stuinfo
         left join score on score.studentid=stuinfo.stuid;

-- 利用decode() 将null 改为缺号

select stuinfo.stuid 学生号,stuinfo.sname 学生名,decode(score.score,null,'缺考',score.score) 成绩
from stuinfo
left join score on score.studentid=stuinfo.stuid;

-- 下一个需求：只求缺考的学员的信息

select stuinfo.stuid 学生号,stuinfo.sname 学生名, score.score 成绩
from (select stuinfo.stuid 学生号,stuinfo.sname 学生名,score.score 成绩
      from stuinfo left join score on score.studentid=stuinfo.stuid),stuinfo
where 成绩 is null;

-- 1.查询所有的参考人员
select stuinfo.sname 缺考人员,score
from stuinfo
left join score on score.studentid=stuinfo.stuid;

-- 2.只看成绩为空
select stuinfo.stuid 学生号,stuinfo.sname 学生名,score.score 成绩
from stuinfo
         left join score on score.studentid=stuinfo.stuid
where score.score is null;

--右外联接与左外联接正好相反:
select stuinfo.stuid 学生号,stuinfo.sname 学生名,score.score 成绩
from score
         right join stuinfo on score.studentid=stuinfo.stuid
where score.score is null;

--完整外联接
select * from stuinfo
    full join score
        on stuinfo.stuid=score.studentid;

--结果:    ___________条记录:

--交叉联接:  左表中的每一行与右表中的每行都组合成。   笛卡尔乘积=左表数据行*右表数据行
select * from stuinfo
    cross join score;


create table test1(
                     id int primary key,
                     name varchar(255) not null
);

create table test2(
                     id int primary key,
                     name varchar(255) not null
);

create table test3(
                     id int primary key,
                     name varchar(255) not null,
                     tel number(10) not null
);

create table test4(
                     id int primary key,
                     name varchar(255) not null,
                     tel number(10) not null
);

select * from test1
    cross join test2;

select * from test3
    cross join test4;
---综合案例一:
--需求描述:在数据库表中，数据行位置并不重要，但是一个单位中要根据奇数行和偶数行的数据来汇总，在这个汇总的基础上再得到一个数值，
--因此，要查询数据库表的奇数行和偶数行的总数, 但原表中的id列的值并不是完全连续的，其中有一些数据已经删除了.

create table tab1(
                     id int primary key,
                     total int
);

insert into tab1 values(1,33);
insert into tab1 values(3,44);
insert into tab1 values(4,2);
insert into tab1 values(5,6);
insert into tab1 values(8,88);
insert into tab1 values(9,3);
insert into tab1 values(15,33);
insert into tab1 values(17,34);
insert into tab1 values(19,34);
insert into tab1 values(20,29);

select * from tab1;

--解决方案: 只能依靠标识列的值来进行判断和选取, 但原有主键列已经不能用，所以必须生成一个新表，将原表的total列的数据插过去，并新增加标识列
--然后再在新表中通过新标识列来求奇数列值和偶数列值
--将一个表的数据插入到另一个表语法: create 新表名 as  select 列名, 序列   from 源表名

create sequence seq_tab1_id2;

create table newtable1
as
select seq_tab1_id2.nextval as id,total from tab1;

select * from newtable1;

select sum(total) 奇数列和
from newtable1
    where MOD(id,2)=1;

select sum(total) 偶数列和
from newtable1
    where MOD(id,2)=0;

-- 更高的要求： 将以上的两个结果合成一条语句输出
select a.奇数列和,b.偶数列和
from (select sum(total) 奇数列和 from newtable1 where MOD(id,2)=1) a,
     (select sum(total) 偶数列和 from newtable1 where MOD(id,2)=0) b;

-- 解决方案二：无需创建新表
select decode(item,1,'奇数和',2,'偶数和') as 项目,结果
from (
     select 1 as item,sum(total) as 结果
    from (
         select rownum as rn,total from tab1
    )where mod(rn,2)=1
    union all
    select 2 as item,sum(total)as 结果
    from(
        select rownum as rn,total from tab1
    )where mod(rn,2)=0
);

--案例二:
--一家银行发行了新的信用卡，刚开始的时候推广得很好，但是逐渐废卡也越来越多（卡上的余额少于2元，并且用户长时间不使用该卡），
--因此银行在二月份把这些少于2元的卡从都数据库表中删除了，但是很快问题就来了，用户发现他的卡再也不能使用而投诉，因此只能再把这些卡恢复。
drop table accounts;
drop table cust;
create table cust(
                     cardid int primary key,
                     cname varchar2(50)
);
drop sequence seq_account_id;
create sequence seq_account_id;
create table accounts(
                         accountid int primary key ,
                         cardid int ,
                         score int
);
alter table accounts
    add constraint FK_cardid
        foreign key(cardid) references cust(cardid);


insert into cust values(16,'张三');
insert into cust values(25,'王五');
insert into cust values(29,'刘六');

insert into cust values(23,'李四');
insert into cust values(30,'杨七');

insert into accounts values(seq_account_id.nextval,16,3400);
insert into accounts values(seq_account_id.nextval,25,4565);
insert into accounts values(seq_account_id.nextval,29,456);

select * from cust;
select * from accounts;

--分析: 1. 可以用left join找出cust表中有而account没有的记录（这种记录的特征是account.cardid is null)
--      2. 再将找到的cardid号插入到account表中即可.  因为account表已经存在，这是将一些数据从一个表中提取
--再插入到另一个已存在的表中，所以可以使用insert into 表名1 select 值 from 源表名
-- 步骤一：先查出cust中有的，但accounts中没有的记录
insert into accounts(accountid, cardid, score)
select seq_account_id.nextval,cust.cardid,2
from cust
left join accounts
on cust.cardid = accounts.cardid
where score is null ;

--子查询: 一个查询的结果是另一个查询的条件.
-- 子查询可以放在  from ,或where条件中
--测试联接查询的数据
create table classes3(
                        cid int primary key,
                        cname varchar2(20)
);
insert into  classes3 values(1,'一班');
insert into  classes3 values(2,'二班');
insert into  classes3 values(3,'三班');
insert into  classes3 values(4,'四班');

create table stus(
                     sid int primary key,
                     sname varchar2(20),
                     cid int
);
alter table stus
    add constraint fk_sts_cid foreign key( cid) references classes3(cid);
insert into  stus values(1,'张三',1);
insert into  stus values(2,'李四',1);
insert into  stus values(3,'王五',2);

-- 需求：查询1，2，3班的学生
select * from stus where cid in (1,2,3);
select * from stus where cid=1 or cid=2 or cid=3;

-- 引入子查询：即以sql语句查询出 classes 中所有的班级编号，以它们作为条件到stus中查
select * from stus where cid in (select cid from classes3 where cid<=3);

-- 需求：查询一班中所有姓张的学生
-- 方式一，写在条件中  是两次查询 时间cost为6
-- 用子查询实现
select * from stus where cid=(select cid from classes3 where cname='一班') and sname like '张%';
-- 用联接查询实现
select sid,sname,classes3.cid,cname
from classes3,stus
where classes3.cid=stus.cid and sname like '张%';

-- 方式二：from 中
select a.sid,a.sname,a.cid,a.cname
from (
    select stus.sid,stus.sname,classes3.cid,classes3.cname from stus,classes3
    where cname='一班' and classes3.cid=stus.cid ) a
where a.sname like '张%';

-- 方式三：
select stus.cid,sname,classes3.cid,cname
from stus
    inner join classes3
        on stus.cid=classes3.cid
where classes3.cname='一班' and sname like '张%';


-- 什么是分页：
-- 表中数据比较多，只取对应页的数据
-- 节省网络流量
-- 不同的数据库分页语法有一些区别

select * from emp;
select * from emp order by empno desc;

-- 总共14条数据，加入一个rownum列显示第几条

select rownum rn,emp.* from emp order by empno desc;
select rownum rn,emp.* from emp order by sal desc;

--总共14条数据分成每页5条 当前显示第一页  排序  order by empno desc
-- 边界

--条件：当前是第几页  每页显示多少条 5
-- 计算边界
-- int max= 每页多少条*第几页 + 1
-- int min= 第几页-1*每页多少条

select a.empno,a.ename,a.job,a.mgr,a.hiredate,a.sal,a.comm,a.deptno from (
    select rownum as rn,emp.*
    from emp
    where rownum<11
    order by empno desc
) a
where rn>=6;

-- 条件： 要求显示部门
select rn,a.empno,a.ename,a.job,a.mgr,a.hiredate,a.sal,a.comm,a.deptno,a.dname
from (
    select rownum as rn,emp.*,dept.dname
    from emp
    inner join dept on dept.deptno=emp.deptno
    where rownum<11
    order by empno desc
) a
where rn>=6;

select * from(
    select rownum as rn,a.*
    from (
         select emp.*,dept.dname
        from emp
        inner join dept on dept.deptno=emp.deptno
        order by empno desc
    ) a where rownum<11
) where rn>=6;


-- exists用法  判断 where 是否有这个记录，返回值 true/false
select * from emp a where exists (select * from dept b where a.deptno=b.deptno);
-- 与in的区别 先操作子查询
select * from emp where deptno in (select deptno from dept);
-- 两者的区别： 1.in先执行子查询 exist先执行主查询
--            2.返回值不同， in返回一个集合列表 exists返回true/false值



-- ======================================================
-- ======================================================

-- Create table
create table STUDENT.SCORE
(
    scoreid  VARCHAR2(18) not null,
    stuid    VARCHAR2(11),
    courseid VARCHAR2(9),
    score    NUMBER,
    scdate   DATE
)
    partition by range(scdate)(
                                  partition p_score_2018 values less than (TO_DATE('2019-01-01 00:00:00','yyyy-mm-ddhh24:mi:ss'))
                                      TABLESPACE TS_2018,
                                  partition p_score_2019 values less than (TO_DATE('2020-01-01 00:00:00','yyyy-mm-ddhh24:mi:ss'))
                                      TABLESPACE TS_2019,
                                  partition p_score_2020 values less than (MAXVALUE)
                                      TABLESPACE TS_2020
                              );

-- Add comments to the table
comment on table STUDENT.SCORE
    is '学生成绩表';
-- Add comments to the columns
comment on column STUDENT.SCORE.scoreid
    is '学生成绩id';
comment on column STUDENT.SCORE.stuid
    is '学生学号';
comment on column STUDENT.SCORE.courseid
    is '课程id(年度+上下学期+课程序列)';
comment on column STUDENT.SCORE.score
    is '成绩';
comment on column STUDENT.SCORE.scdate
    is '成绩录入时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table STUDENT.SCORE
    add constraint PK_SCORE primary key (SCOREID)
        using index
            tablespace USERS
            pctfree 10
            initrans 2
            maxtrans 255
            storage
            (
            initial 64K
            next 1M
            minextents 1
            maxextents unlimited
            );

-- ======================================================
-- ======================================================


create table myTestTable as
select rownum as id,
       to_char(sysdate + rownum/24/3600, 'yyyy-mm-dd hh24:mi:ss') as inc_datetime,
       trunc(dbms_random.value(0, 100)) as random_id,
       dbms_random.string('x', 20) random_string
from dual
connect by level <= 100000;


-- 作业
CREATE table TESTProduct(
    ProductID int primary key,
    ProductName varchar(255),
    Price int
);

create table TESTSales(
    ProductID int,
    ClientName varchar2(255),
    ProductNumber int,
    SalesPrice int
);

alter table TESTSales add constraint TESTProduct_id check (ProductID <= 4);

drop sequence seq_product_id;
CREATE sequence seq_product_id;

INSERT INTO SCOTT.TESTPRODUCT VALUES (1, 'HP1200', 2000);
INSERT INTO SCOTT.TESTPRODUCT VALUES (2, 'LX360', 4800);
INSERT INTO SCOTT.TESTPRODUCT VALUES (3, 'IBM 350', 11000);
INSERT INTO SCOTT.TESTPRODUCT VALUES (4, 'IBM 360', 12000);

INSERT INTO SCOTT.TESTSALES VALUES (2, '源辰信息技术有限公司', 10, 4500);
INSERT INTO SCOTT.TESTSALES VALUES (1, '源辰信息技术有限公司', 25, 1800);
INSERT INTO SCOTT.TESTSALES VALUES (3, '联想集团', 10, 11000);
INSERT INTO SCOTT.TESTSALES VALUES (2, '联想集团', 30, 4500);
INSERT INTO SCOTT.TESTSALES VALUES (1, '联想集团', 20, 1800);
INSERT INTO SCOTT.TESTSALES VALUES (3, '北大方正', 40, 10000);
INSERT INTO SCOTT.TESTSALES VALUES (3, '诺基亚', 20, 10500);

select ClientName 客户名称,ProductNumber 购买数量,SalesPrice 销售价格
from TESTSales
where ProductNumber > 15;

select ProductName 产品名称,sum(SalesPrice) 销售总额
from TESTProduct,TESTSales
where TESTProduct.ProductID=TESTSales.ProductID
group by ProductName;

select ClientName 客户姓名,sum(SalesPrice) 销售总金额
from TESTSales
group by ClientName;

select decode(TESTSales.ProductID,3,'IBM 350笔记本') 商品名称,ClientName 客户名称,ProductNumber 购买数量
from TESTSales
where TESTSales.ProductID=3;

update TESTSales
set ClientName='中国联想国际集团公司'
where ClientName='联想集团';

delete from TESTProduct t
where t.ProductID=3;

CREATE table info
as
(select TESTProduct.ProductID,ProductName,Price,ClientName,ProductNumber
    from TESTSales,TESTProduct
    where TESTSales.ProductID=TESTProduct.ProductID);

drop table info;
select * from info;