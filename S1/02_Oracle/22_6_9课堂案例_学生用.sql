

--查询的语法
--select * from 表名
--select 列名[，列名] from 表名

--select * from account;--（账号id，余额）
--select * from dept;--部门信息表（部门标号，部门名称，地址  审计部，纽约；研发部，达拉斯；销售部，芝加哥；运营部，波士顿）

select * from dept;
select deptno,dname,loc from dept;
select deptno,dname from dept;
select deptno as 部门号 ,dname as 部门名 from dept;
select deptno 部门,dname 部门名 from dept;

select * from emp;   --雇员号
select empno as 雇员号,ename as 雇员名 from emp;

--条件列 where 条件

--select * from emp;--雇员表

--1、算术运算符
--查询每位员工的工资=计本工资+奖金  (算术运算符 +，-，* /  )
select * from emp;
select empno,ename,sal+comm from emp;
select empno,ename,sal+comm as 实发工资 from emp;
select empno,ename,sal+nvl(comm,0) as 实发工资 from emp;
select empno,ename,sal+nvl2(comm,comm,0) as 实发工资 from emp;

--nvl(a,b) 如果a为null，那么就使用b的值
select empno as 部门编号,ename as  员工名,sal as 基本工资,comm as 补助,(sal + nvl(comm,0)) as 实发工资 from emp;
select empno as 部门编号,ename as  员工名,sal as 基本工资,comm as 补助,(sal + nvl(comm,comm,0)) as 实发工资 from emp;

--难看，重命名列 （as 可以省略）
select empno 员工编码,ename,(sal+nvl(comm,0)) 总工资 from emp;


--比较运算符
--带条件的查询
--查询工资在3000元以上的员工信息（ 比较运算符 <,>,=,between...and,in,like,is null）
select * from emp where sal>=3000;

--查询 总工资在3000元以上的员工信息（ 比较运算符）
select * from emp where (sal+nvl(comm,0))>=3000;

--查询 工资在2000-3000的员工（需要关爱，中间力量）
select * from emp where (sal+nvl(comm,0)) between 2000 and 3000;
select * from emp where (sal+nvl(comm,0)) between 3000 and 2000;  --错误注意：小值在前，大值在后
select * from emp where (sal+nvl(comm,0))>=3000 and (sal+nvl(comm,0))<=3000;

--查询  职位是 业务员，销售 的员工信息。不在一个区间（not in）  orcle对值是区分大小写的
select * from emp where job in ('CLERK','SALESMAN');  -- ''字符串的值oracle区分大小写 / 队列名，表名不区分大小写
select * from emp where job in ('clerk','salesman');  --in 相当于 or 的连接操作
select * from emp where job='CLERK' or job='SALESMAN';

--查询哪些员工没有奖金，
select * from emp where comm is not null and comm>0;  --查有资金comm的人员
select * from emp where comm is null or comm<=0;   --查没有奖金的人
--不能用 =null
select * from emp where comm = null;

--查询一个员工，他的名字是M开头的，
select * from emp where ename like 'M%';  --姓名以M开头
select * from emp where ename like '_%M%_';  --姓名中有M的
select * from emp where ename like '%M';  --以M结尾的
select * from emp where ename like '_M%';  --查第二个字母是M的
--不能用 = 来做模糊查询
select * from emp where ename = 'M%';

--3、逻辑运算符(and / or / not)
--查询名字是 M开头的，而且是 审计部 部门的。查到部门编号是10
select * from dept where dname = 'ACCOUNTING';
select * from emp where 1=1 and ename like 'M%' and deptno = 10;

--一条语句查询  用子查询来完成操作     因为用了 = 号，子查询只能返回一个部门
select * from emp where 1=1 and ename like 'M%' and deptno = (select deptno from dept where dname = 'ACCOUNTING');

--not 是加在  一个表达式之前的。
--姓名不以M开头的人
select * from emp where ename not like 'M%';
--注意：不能加is not
--select * from emp where ename is not like 'M%';  -- is 只能与null放在一起用，不能与 not 一起用

--4、连接操作符
select empno||'对应的名字是'||ename||',他(她)的工作是'||job as 句子 from emp;

--5.集合运算符，从两张表抽取数据以行的形式合并一张新表
-- union,union all,intersect 交集, minus差集
--以下是案例： 供应商表
drop table supplier;
CREATE TABLE supplier(
    id int primary key,
    sname varchar2(20)
);

drop sequence seq_supplier;
create sequence seq_supplier;
insert into supplier values(seq_supplier.nextval,'联想');
insert into supplier values(seq_supplier.nextval,'小米');

-- 客户表
drop table customer;
CREATE table customer(
    cid int primary key,
    cname varchar2(20)
);

drop sequence seq_customer;
create sequence seq_customer;
insert into customer values(seq_customer.nextval,'联想');
insert into customer values(seq_customer.nextval,'华为');
select * from customer;

--求交集 union, union all  查询的列全都相同
select * from supplier
union
select * from customer;
--不去重交集
select * from supplier
union all
select * from customer;

-- 差集 MINUS
select * from supplier
minus
select * from customer;

-- 交集 intersect
select * from supplier
intersect
select * from customer;

--优先级，算术运算符（乘除高于加减）>连接操作符>比较运算符>逻辑运算符（not>and>or）
--1、同一优先级运算符从左向右执行；
--2、括号内的运算先执行。


--sql中 函数分为（单行行数/分组函数/分析函数）
--单行函数

--create table stuInfo(
--                        sid int primary key,
--                       sname varchar2(10) not null,
--                        age number(3),
--
--)
-- create table mathScore(
--                           sid int primary key,
--
--
-- )
--1、日期函数
--查询当前日期
select sysdate from dual;

select * from emp;

--查找入职已经超过35年的超级老员工
select empno,ename,hiredate, months_between(sysdate,hiredate) as 上班的时长月 from emp;

select * from emp where months_between(sysdate,hiredate)>35*12;   -- 用 months_between(d1,d2)
select * from emp where add_months(sysdate,-35*12)>=hiredate;  -- 用add_months(d1,n)

--对于每个员工，显示其加入公司天数
select empno,ename,sysdate-hiredate as 天数 from emp;
select empno,ename,trunc((sysdate-hiredate)) as 入职天数 from emp;  -- trunc() 截断
select empno,ename,round(trunc((sysdate-hiredate))) as 入职天数 from emp;

--2、数字函数
--对于每个员工，显示其加入公司天数,去整数天数 (floor()向下取整)
select empno,ename,trunc((sysdate-hiredate)) as trunc入职天数,
       ceil(sysdate-hiredate) as ceil入职天数,
       round(sysdate-hiredate) as round入职天数,
       floor(sysdate-hiredate) as floor入职天数 from emp;


--3、字符函数
--将job转换成小写，认单词困难
select empno,ename,lower(job),upper(job),initcap(job),nls_initcap(job) from emp;

--我想看到哪些岗位，之前有重复的(售货员、职员、董事长、经理、分析师) distinct，去重
select distinct(lower(job)) from emp;

--显示正好为5个字符的员工的姓名
select * from emp;
select * from emp where length(ename)=5;
select ename,length(ename),lengthB(ename) from emp;

--显示所有员工姓名的前三个字符
select empno,ename,substr(ename,2,3) from emp;

--显示所有员工姓名的最后三个字符
select empno,ename,substr(ename,-3,3) from emp;

--以首字母大写的方式显示所有员工的姓名
select empno,ename,initcap(ename) from emp;

--字符替换
select replace('he love you','he','i') test from dual;

--计算一个月30天的情况下，每人的日薪数，忽略余数
select trunc(sal/30) 日薪,ename from emp;

--4、日期和时间转换函数
insert into emp values (7289,'Hehehe','PRESIDENT','',sysdate,20000,8000,10);
select * from emp;
--假设给的日期不是一个sysdate,而是一个字符串，则篇要使用to dat∈进行字符串到日期的格式转换
insert into emp values(7288,'Hehehe','PRESIDENT','',to_date('1978-11-25','yyyy-mm-dd'),20000,8000,10);
--在其他编程语言中，可能对应的数据类型是string,因为标准的数据的日期类型它的格式是西方格式
--解决方亲：将西方的日期格式转为指定的格式t。char
select empno,ename,to_char(hiredate,'yyyy-mm-dd')from emp;
select empno,ename,to_char(hiredate,'yyyy/mm/dd')from emp;
--转化其他格式
select empno,ename,sal,to_char(sal,'$9999.99')from emp;
--1.查找已经入跃5个月多的员工
select * from emp;
update emp set hiredate=add_months(sysdate,-5) where empno=7289;
select * from emp where add_months (hiredate,5)<sysdate;    --2011.6+8->2012.2
--2.显示满10年服务年限的员工的姓名和受雇日期
select * from emp where add_months (hiredate,12*10)<sysdate;
--3.时于每个员工，显示其加入司天数
--floor(),ceil()round()trunc()
select empno,ename,trunc((sysdate-hiredate))as入职天数,
    round(sysdate-hiredate)as入职天数,
    floor(sysdate-hiredate)as入职天数,
    ceil(sysdate-hiredate)as入职天数
from emp;
--4.找出各月倒数第3天受雇的所有员工，
select ename,hiredate from emp where hiredate=last_day (hiredate)-2;
--5、其他函数(nv1nv12nu11if)
--nv1,如果comm为空，则显示0
select ename,nvl(comm,0)as资金1,nvl2(comm,comm,0)as奖金2 from emp;
--查词员工信息，10是会计部，如果αptno为20，则显示开发部，如果为30，则显示销售部，否刚显示生产部
--decode:switch..case
select * from emp;
select * from dept;
select empno,ename,sal,
    decode(deptno,10,'会计部',20,'开发部',30,'销售部','生产部')from emp;
--6、分组函数(avg,min,ma,Sm,count）
--策合函数
--多个输入一个输出
--平均工资是多少(5451.5625)
select sum(comm),count(empno),sum(comm)/count(empno)as平均奖金l,avg(comm)平均奖金2 from emp;
select avg(sal) 平均工资 from emp;
select avg(comm) 平均奖金 from emp;
select avg(nvl(comm,0)) 平均奖金 from emp;
select trunc(avg(sal + nvl(comm,0))) as 平均收入 from emp;
--最低收入是多少
select min (sal+nvl(comm,0))from emp;
--最高收入是多少
select max (sal+nvl(comm,0))from emp;
--本月司应该支出多少工资
select sum(sal+nvl(comm,0)) as 工资总支出 from emp;
--给多少人发工资(16)
select count (empno)from emp;
select count (comm) from emp;


--7、分析函数
--我想按工资高低排序--order by   desc 降序  asc升序
select * from emp order by sal desc,ename asc;

--升级：要求还要输出排名
--TODO：用rownum试试
select a.*,rownum from emp a;

--以全部员工工资从高到低排序(跳跃的rank)
select e.*,rank()over(order by sal desc) "跳跃的名次" from emp e;

--以全部员工工资从高到低排序，连续的排名
select e.*,dense_rank()over(order by sal desc) "稠密的名次" from emp e;

--以部门排名。部门内部排名
select e.*,rank()over(partition by deptno order by sal desc) 按工资降序跳跃排序 from emp e;
select e.*,dense_rank()over(partition by deptno order by sal desc) 工资降序稠密排序 from emp e;

--row number()编号
select e.*,row_number() over (order by sal desc ) "编号" from emp e;
select e.*,row_number() over (partition by deptno order by sal desc ) "编号" from emp e;

        
