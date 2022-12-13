create database bbs;
drop table tbl_topic;
drop table tbl_reply;
drop table tbl_board;
drop table tbl_user;


create table tbl_user(
	uid int primary key auto_increment,
	uname varchar(20),
	upass varchar(100),
	head varchar(100),
	regtime datetime,
	gender int
);

select * from tbl_user;
delete from tbl_user;
insert into tbl_user(uname,upass,head,regtime,gender) values('a','d7afde3e7059cd0a0fe09eec4b0008cd','1.gif',now(),1);
insert into tbl_user(uname,upass,head,regtime,gender) values('a','0cc175b9c0f1b6a831c399e269772661','1.gif',now(),1);

DELETE FROM tbl_user WHERE uid =1;
create table tbl_board(
	boardid int primary key auto_increment,
	boardname varchar(50),
	parentid int
);

select * from tbl_board order by parentid;

insert into tbl_board(boardname,parentid) values('.net',0);
insert into tbl_board(boardname,parentid) values('java',0);
insert into tbl_board(boardname,parentid) values('数据库',0);
insert into tbl_board(boardname,parentid) values('其它',0);


insert into tbl_board(boardname,parentid) values('ado.net',1);
insert into tbl_board(boardname,parentid) values('asp.net',1);
insert into tbl_board(boardname,parentid) values('vb.net',1);


insert into tbl_board(boardname,parentid) values('jsp',2);
insert into tbl_board(boardname,parentid) values('struts',2);
insert into tbl_board(boardname,parentid) values('hibernate',2);


insert into tbl_board(boardname,parentid) values('sql',3);
insert into tbl_board(boardname,parentid) values('oracle',3);
insert into tbl_board(boardname,parentid) values('mysql',3);





create table tbl_topic(
	topicid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	boardid int
);

select tbl_board.boardid,boardname,parentid
from tbl_board
inner join tbl_topic
on tbl_board.boardid=tbl_topic.boardid
where tbl_topic.topicid=1



select topicid,title,content,
       date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,
       date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime, 
       tbl_topic.uid as userid, 
       uname,
       head,
        date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime, 
       boardid   
from tbl_topic 
inner join tbl_user
on tbl_topic.uid=tbl_user.uid
where topicid=5

select * from tbl_topic

go
alter table tbl_topic
   add constraint FK_topic_uid
      foreign key(uid) references tbl_user(uid);
      
alter table tbl_topic
   add constraint FK_topic_boardid
     foreign key(boardid) references tbl_board(boardid);
     
create table tbl_reply(
	replyid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	topicid int
);







select replyid,title,content,  date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,
    date_format(modifytime,'%Y-%m-%d %H:%I:%S') as modifytime, tbl_reply.uid as userid, topicid,
    uname,
    head,
    date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime
from tbl_reply
inner join tbl_user
on tbl_reply.uid=tbl_user.uid
where topicid=1
order by modifytime desc
limit 0,2




go
alter table tbl_reply
   add constraint FK_reply_uid
      foreign key(uid) references tbl_user(uid);
      
alter table tbl_reply
	add constraint FK_reply_topicid
	   foreign key(topicid) references tbl_topic(topicid);
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
--案例需求: 请用一次查询得到界面中所有要求显示的数据
insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp  good',' good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into tbl_topic(title,content,publishtime,modifytime,uid,boardid) 
values('ado.net is very good','very good,i agree',now(), now(),1,5);

select * from tbl_topic;   
	   
--步骤:1. 先查出每个版块最新贴子的发贴时间
select boardid,max(modifytime) as modifytime
			from tbl_topic
			group by boardid
--步骤:2. 查出每个贴子的信息，关键是贴子的发贴人名.
select topicid, title, modifytime, uname, boardid
		from tbl_topic
		left join tbl_user
		on tbl_topic.uid=tbl_user.uid
--步骤:3. 将以上整合，得到每个版块最新的贴子信息
select topicid,title,a.modifytime,uname,a.boardid
	from
	(	select topicid, title, modifytime, uname, boardid
		from tbl_topic
		left join tbl_user
		on tbl_topic.uid=tbl_user.uid
        ) a,
		(
			select boardid,max(modifytime) as modifytime
			from tbl_topic
			group by boardid
		) b
	where  a.boardid=b.boardid and a.modifytime=b.modifytime
  
---------------------------------------------------------------------


--步骤:4. 统计每个版块下的贴子总数
select tbl_board.boardid,boardname,parentid , count( topicid ) as total
	from tbl_board
	left join tbl_topic
	on tbl_board.boardid=tbl_topic.boardid
	group by tbl_board.boardid,boardname,parentid 

  
--步骤:5. 最后使用左外联接，将版块信息与最新的贴子信息整合在一起. 
select a.boardid,boardname,parentid,total, topicid,title,modifytime,uname
from
(
	select tbl_board.boardid,boardname,parentid , count( topicid ) as total
	from tbl_board
	left join tbl_topic
	on tbl_board.boardid=tbl_topic.boardid
	group by tbl_board.boardid,boardname,parentid 
) a
left join 
(
	select topicid,title,a.modifytime,uname,a.boardid
	from
	(	select topicid, title, modifytime, uname, boardid
		from tbl_topic
		left join tbl_user
		on tbl_topic.uid=tbl_user.uid
        ) a,
		(
			select boardid,max(modifytime) as modifytime
			from tbl_topic
			group by boardid
		) b
	where  a.boardid=b.boardid and a.modifytime=b.modifytime
)b
on a.boardid=b.boardid



将以上查到的数据存到一个Map:
      Map<父版块编号,   子版块集合>
0                    List<   (.net技术,java技术,数据库技术)>
1                    List<(c#语言,winforms,ado.net)>





--------------------------------------------------------
--需求二: 求出某个版块下按分页条件查询的贴子. 
-- 输出要求: 1. 分页    2. 统计每个贴子下有多少回复数  3. 某一个版块
-- 图片二的效果
--------------------------------------------------------
--基础数据
--插入回复
insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) 
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) 
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) 
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid)
 values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);
 
insert into tbl_reply(title,content,publishtime,modifytime,uid,topicid) 
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);


--步骤一: 按贴子编号分组查出每个贴子下的回复数
select topicid, count(*) as total 
from tbl_reply
group by topicid;

--步骤二: 查出某个版块(比如8号版块)下所有的贴子 
select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  tbl_user.uid,  uname,boardid
from tbl_topic
inner join tbl_user
on tbl_topic.uid=tbl_user.uid
where boardid=8
order by modifytime desc

--步骤三: 加入分页查询
select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  tbl_user.uid,  uname,boardid
from tbl_topic
inner join tbl_user
on tbl_topic.uid=tbl_user.uid
where boardid=8
order by modifytime desc
limit 0,2


--步骤四: 以左外联接查询求出
select a.topicid,title,content,publishtime,modifytime,uid,uname,boardid, total
from
      (
     select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  tbl_user.uid,  uname,boardid
	from tbl_topic
	inner join tbl_user
	on tbl_topic.uid=tbl_user.uid
	where boardid=8
	order by modifytime desc
	limit 2,2) a
left join 
      (select topicid, count(*) as total from tbl_reply
      group by topicid) b
on a.topicid=b.topicid

