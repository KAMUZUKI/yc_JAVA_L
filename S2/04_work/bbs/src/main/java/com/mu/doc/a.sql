use master;

drop database bbs;

create database bbs;

use bbs;

create table tbl_user(
     uid int primary key auto_increment,
     uname varchar(20),
     upass varchar(100),
     head varchar(100),
     regtime datetime,
     gender int
);


drop table tbl_user;

select * from tbl_user;

insert into tbl_user(uname,upass,head,regtime,gender) values('a','0cc175b9c0f1b6a831c399e269772661','1.gif',now(),1);

create table tbl_board(
      boardid int primary key auto_increment,
      boardname varchar(50),
      parentid int
)

select * from tbl_board order by parentid;

insert into tbl_board(boardname,parentid) values('.net版块',0);
insert into tbl_board(boardname,parentid) values('java版块',0);
insert into tbl_board(boardname,parentid) values('数据库版块',0);
insert into tbl_board(boardname,parentid) values('软件工程版块',0);


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


select * from tbl_topic;


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

# select top pagesize * from tbl_reply where replyid not in
# (
#    select top rows replyid from tbl_reply where topicid=? order by publishtime desc
# )
# and topicid=? order by publishtime desc;



alter table tbl_reply
    add constraint FK_reply_uid
        foreign key(uid) references tbl_user(uid);

alter table tbl_reply
    add constraint FK_reply_topicid
        foreign key(topicid) references tbl_topic(topicid);










insert into tbl_topic(title, content, publishtime, modifytime, uid, boardid) values('a','aa',now(),now(),1,1);
insert into tbl_topic(title, content, publishtime, modifytime, uid, boardid) values('b','bb',now(),now(),1,1);
insert into tbl_topic(title, content, publishtime, modifytime, uid, boardid) values('f','ff',now(),now(),1,1);
insert into tbl_topic(title, content, publishtime, modifytime, uid, boardid) values('c','cc',now(),now(),1,2);
insert into tbl_topic(title, content, publishtime, modifytime, uid, boardid) values('d','dd',now(),now(),1,3);

select  a.boardid,a.num,topicid from (select boardid, count(boardid) num,max(modifytime) lasttime from tbl_topic  group by boardid ) a,tbl_topic
where a.lasttime=tbl_topic.modifytime and a.boardid=tbl_topic.boardid ;


select uname,content,modifytime from tbl_topic






