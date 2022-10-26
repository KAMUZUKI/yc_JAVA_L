#0cc175b9c0f1b6a831c399e269772661
CREATE table testuser(
     userid int not null auto_increment primary key,
     uname varchar(20) not null unique ,
     upwd varchar(32) not null
);

insert into testuser (uname,upwd) values ('a','0cc175b9c0f1b6a831c399e269772661');