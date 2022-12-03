create database bbs;

use bbs;

create table user(
	uid int primary key auto_increment,
	uname varchar(20),
	upass varchar(100),
	head varchar(100),
	regtime datetime,
	gender int
);

select * from user;
delete from user;
insert into user(uname,upass,head,regtime,gender) values('a','d7afde3e7059cd0a0fe09eec4b0008cd','1.gif',now(),1);

create table board(
	boardid int primary key auto_increment,
	boardname varchar(50),
	parentid int
);

select * from board order by parentid;

insert into board(boardname,parentid) values('.net',0);
insert into board(boardname,parentid) values('java',0);
insert into board(boardname,parentid) values('数据库',0);
insert into board(boardname,parentid) values('其它',0);


insert into board(boardname,parentid) values('ado.net',1);
insert into board(boardname,parentid) values('asp.net',1);
insert into board(boardname,parentid) values('vb.net',1);


insert into board(boardname,parentid) values('jsp',2);
insert into board(boardname,parentid) values('struts',2);
insert into board(boardname,parentid) values('hibernate',2);


insert into board(boardname,parentid) values('sql',3);
insert into board(boardname,parentid) values('oracle',3);
insert into board(boardname,parentid) values('mysql',3);





create table topic(
	topicid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	boardid int
);

select board.boardid,boardname,parentid
from board
inner join topic
on board.boardid=topic.boardid
where topic.topicid=1;



select topicid,title,content,
       date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,
       date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime, 
       topic.uid as userid,
       uname,
       head,
        date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime, 
       boardid   
from topic
inner join user
on topic.uid=user.uid
where topicid=5;

select * from topic

go;

alter table topic
   add constraint FK_topic_uid
      foreign key(uid) references user(uid);
      
alter table topic
   add constraint FK_topic_boardid
     foreign key(boardid) references board(boardid);
     
create table reply(
	replyid int primary key auto_increment,
	title varchar(50),
	content varchar(1000),
	publishtime datetime,
	modifytime datetime,
	uid int,
	topicid int
);




select replyid,title,content,  date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,
    date_format(modifytime,'%Y-%m-%d %H:%I:%S') as modifytime, reply.uid as userid, topicid,
    uname,
    head,
    date_format(regtime,'%Y-%m-%d %H:%I:%S') as  regtime
from reply
inner join user
on reply.uid=user.uid
where topicid=1
order by modifytime desc
limit 0,2;

alter table reply
   add constraint FK_reply_uid
      foreign key(uid) references user(uid);
      
alter table reply
	add constraint FK_reply_topicid
	   foreign key(topicid) references topic(topicid);
	   
	   
	   
	   

	   
-- 案例需求: 请用一次查询得到界面中所有要求显示的数据
insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('jsp  good',' good,i agree',now(), now(),1,8);

insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('jsp is very good','very good,i agree',now(), now(),1,8);

insert into topic(title,content,publishtime,modifytime,uid,boardid)
values('ado.net is very good','very good,i agree',now(), now(),1,5);

select * from topic;
	   
-- 步骤:1. 先查出每个版块最新贴子的发贴时间
select boardid,max(modifytime) as modifytime
			from topic
			group by boardid;

-- 步骤:2. 查出每个贴子的信息，关键是贴子的发贴人名.
select topicid, title, modifytime, uname, boardid
		from topic
		left join user
		on topic.uid=user.uid;

-- 步骤:3. 将以上整合，得到每个版块最新的贴子信息
select topicid,title,a.modifytime,uname,a.boardid
	from
	(	select topicid, title, modifytime, uname, boardid
		from topic
		left join user
		on topic.uid=user.uid
        ) a,
		(
			select boardid,max(modifytime) as modifytime
			from topic
			group by boardid
		) b
	where  a.boardid=b.boardid and a.modifytime=b.modifytime;



-- 步骤:4. 统计每个版块下的贴子总数
select board.boardid,boardname,parentid , count( topicid ) as total
	from board
	left join topic
	on board.boardid=topic.boardid
	group by board.boardid,boardname,parentid ;

  
-- 步骤:5. 最后使用左外联接，将版块信息与最新的贴子信息整合在一起.
select a.boardid,boardname,parentid,total, topicid,title,modifytime,uname
from
(
	select board.boardid,boardname,parentid , count( topicid ) as total
	from board
	left join topic
	on board.boardid=topic.boardid
	group by board.boardid,boardname,parentid
) a
left join 
(
	select topicid,title,a.modifytime,uname,a.boardid
	from
	(	select topicid, title, modifytime, uname, boardid
		from topic
		left join user
		on topic.uid=user.uid
        ) a,
		(
			select boardid,max(modifytime) as modifytime
			from topic
			group by boardid
		) b
	where  a.boardid=b.boardid and a.modifytime=b.modifytime
)b
on a.boardid=b.boardid;



# 将以上查到的数据存到一个Map:
#       Map<父版块编号,   子版块集合>
# 0                    List<   (.net技术,java技术,数据库技术)>
# 1                    List<(c#语言,winforms,ado.net)>





-- 需求二: 求出某个版块下按分页条件查询的贴子.
-- 输出要求: 1. 分页    2. 统计每个贴子下有多少回复数  3. 某一个版块
-- 图片二的效果
-- 基础数据
-- 插入回复

insert into reply(title,content,publishtime,modifytime,uid,topicid)
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into reply(title,content,publishtime,modifytime,uid,topicid)
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into reply(title,content,publishtime,modifytime,uid,topicid)
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);

insert into reply(title,content,publishtime,modifytime,uid,topicid)
 values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);
 
insert into reply(title,content,publishtime,modifytime,uid,topicid)
values('jsp is very good reply','very good,i agree, reply',now(),now(),1,2);


-- 步骤一: 按贴子编号分组查出每个贴子下的回复数
select topicid, count(*) as total 
from reply
group by topicid;

-- 步骤二: 查出某个版块(比如8号版块)下所有的贴子
select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  user.uid,  uname,boardid
from topic
inner join user
on topic.uid=user.uid
where boardid=8
order by modifytime desc;

-- 步骤三: 加入分页查询
select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  user.uid,  uname,boardid
from topic
inner join user
on topic.uid=user.uid
where boardid=8
order by modifytime desc
limit 0,2;


-- 步骤四: 以左外联接查询求出
select a.topicid,title,content,publishtime,modifytime,uid,uname,boardid, total
from
      (
     select topicid,title,content,date_format(publishtime,'%Y-%m-%d %H:%I:%S') as publishtime,date_format(modifytime,'%Y-%m-%d %H:%I:%S') as  modifytime,  user.uid,  uname,boardid
	from topic
	inner join user
	on topic.uid=user.uid
	where boardid=8
	order by modifytime desc
	limit 2,2) a
left join 
      (select topicid, count(*) as total from reply
      group by topicid) b
on a.topicid=b.topicid;

