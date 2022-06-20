
--S (S#,SN,SD,SA)   S#,SN,SD,SA 分别代表学号、学员姓名、所属单位、学员年龄
create table S(
                  S# int primary key,
                  SN varchar2(30) not null ,
                  SD varchar2(30) not null,
                  SA int not null
);
create sequence seq_s_s#;
--C (C#,CN )        C#,CN       分别代表课程编号、课程名称
create table C(
                  C# varchar2(30) primary key,
                  CN varchar2(30) not null
);
--SC ( S#,C#,G )    S#,C#,G     分别代表学号、所选修的课程编号、学习成绩
create table SC(
                   S# int ,
                   C# varchar2(30) not null ,
                   G int not null
);

alter table sc
    add constraint FK_SC_S#
        foreign key(S#) references S(S#);
alter table sc
    add constraint FK_C_C#
        foreign key(C#) references C(C#);

insert into S values(seq_s_s#.nextval,'罗路','计算机与信息科学学院',23);
insert into S values(seq_s_s#.nextval,'薛迪','计算机与信息科学学院',24);
insert into S values(seq_s_s#.nextval,'李水平','计算机与信息科学学院',25);
insert into S values(seq_s_s#.nextval,'欧阳进夫','计算机与信息科学学院',26);
insert into S values(seq_s_s#.nextval,'屌丝','计算机与信息科学学院',27);
insert into S values(seq_s_s#.nextval,'宅男','计算机与信息科学学院',28);

select * from S;

insert into C values('C1','税收基础');
insert into C values('C2','管理基础');
insert into C values('C3','调动基础');
insert into C values('C4','薪水管理');
insert into C values('C5','业务管理');
insert into C values('C6','发展规划');
insert into C values('C7','薪资总结');
insert into C values('C8','人员管理');

select * from C;

insert into SC values(1,'C1',90);
insert into SC values(2,'C1',85);
insert into SC values(1,'C2',91);
insert into SC values(1,'C3',92);
insert into SC values(1,'C4',95);
insert into SC values(1,'C5',96);
insert into SC values(1,'C6',87);
insert into SC values(1,'C7',67);
insert into SC values(1,'C8',57);
insert into SC values(2,'C2',34);
insert into SC values(3,'C2',78);
insert into SC values(4,'C5',98);

select * from sc;

--1. 使用标准SQL嵌套语句查询选修课程名称为’税收基础’的学员学号和姓名
select S.S# 学员学号,SN 姓名
from (select S# from SC where C#='C1')a,S
where S.S#=a.S#;

--2. 使用标准SQL嵌套语句查询选修课程编号为’C2’的学员姓名和所属单位
select SN 姓名,SD 所属单位
from (select S# from SC where C#='C2')a,S
where S.S#=a.S#;

--3. 使用标准SQL嵌套语句查询不选修课程编号为’C5’的学员姓名和所属单位
select distinct  SN 姓名,SD 所属单位
from (select S# from SC where C#<>'C2')a,S
where S.S#=a.S#;

--4. 使用标准SQL嵌套语句查询选修全部课程的学员姓名和所属单位
select distinct S.SN 姓名,S.SD 所属单位
from S,(
    select S#
    from SC
    group by SC.S#
    having count(*) > =8
    ) a
where S.S#=a.S#;



--5. 查询选修了课程的学员人数
select SN 姓名,SD 所属单位
from (select distinct S# from SC)a,S
where S.S#=a.S#;

--6. 查询选修课程超过5门的学员学号和所属单位
select distinct S.SN 姓名,S.SD 所属单位
from S,(
    select S#
    from SC
    group by SC.S#
    having count(*) > =5
) a
where S.S#=a.S#;

