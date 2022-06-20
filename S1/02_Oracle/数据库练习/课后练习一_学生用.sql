

--案例1:  为管理岗位业务培训信息，建立3个表:
--student (sid,sname,deptname,sage)   sid,sname,deptname,sage 分别代表学号、学员姓名、所属单位、学员年龄
--course (cid,cname )        cid,cname      分别代表课程编号、课程名称
--score   (sid,cid,grade)    sid,cid,grade     分别代表学号、所选修的课程编号、学习成绩
drop table score;
drop table student;
drop table course;

create table student
(
    sid int primary key,
    sname varchar2(50),
    deptname varchar2(50),
    sage int
);

create table course
(
    cid int primary key,
    cname varchar2(50)
);

create table score(
                      sid int ,
                      cid int ,
                      grade int
);

insert into student values(1,'张三','广电总局',33);
insert into student values(2,'张四','CCTV',33);
insert into student values(3,'张五','SINA',39);
insert into student values(4,'张六','QQ',36);
insert into student values(5,'张七','yahoo',34);
insert into student values(6,'张八','google',24);
insert into student values(7,'张九','facebook',22);
insert into student values(8,'张十','广电总局',33);
insert into student values(9,'张一','广电总局',33);

insert into course values(1,'税收基础');
insert into course values(2,'民法通则');
insert into course values(3,'论三个代表');


insert into score values(1,1,44);
insert into score values(2,1,94);
insert into score values(3,1,64);
insert into score values(4,1,44);
insert into score values(5,1,84);
insert into score values(6,2,44);
insert into score values(7,2,94);
insert into score values(8,2,64);
insert into score values(1,2,44);
insert into score values(3,2,84);
insert into score values(1,3,88);    --注意只有1号学员选修了所有课程


--问题1:  使用标准SQL子查询选修课程名称为’税收基础’的学员学号和姓名


--问题2:  查询选修课程编号为’2’的学员姓名和所属单位



--问题3: 查询没有选修课程编号为’3’的学员姓名和所属单位



--问题4: 查询选修全部课程的学员姓名和所属单位


--问题5:   查询选修了至少一门课程的学员人数



--问题6:  查询选修课程超过2门的学员学号和所属单位












