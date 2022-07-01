--CARD     借书卡。   CNO 卡号，NAME  姓名，CLASS 班级
create table card(
                     cno int primary key,
                     name varchar2(50),
                     class varchar2(10)
);
create sequence seq_card;

--BOOKS    图书。     BNO 书号，BNAME 书名,AUTHOR 作者，PRICE 单价，QUANTITY 库存册数
create table books(
                      bno int primary key,
                      bname varchar2(50),
                      author varchar2(50),
                      price float,
                      quantity int
);
create sequence seq_books;
--BORROW   借书记录。 CNO 借书卡号，BNO 书号，RDATE 还书日期
create table borrow(
                       cno int,
                       bno int,
                       rdate date
);

alter table borrow
    add constraint fk_borrow_cno  foreign key(cno)  references card(cno) ;
alter table borrow
    add constraint fk_borrow_bno foreign key(bno) references books(bno);

insert into card values(seq_card.nextval,'张三','c01');
insert into card values(seq_card.nextval,'张五','c02');
insert into card values(seq_card.nextval,'张四','c02');
insert into card values(seq_card.nextval,'张六','c01');
insert into card values(seq_card.nextval,'张七','c01');
insert into card values(seq_card.nextval,'张八','c01');

insert into books values(seq_books.nextval,'网络基础','smith',30,5);
insert into books values(seq_books.nextval,'深入网络基础','smith',38,5);
insert into books values(seq_books.nextval,'水浒','施耐俺',30,5);
insert into books values(seq_books.nextval,'计算方法','TOM',30,5);
insert into books values(seq_books.nextval,'计算习题集','John',22,5);
insert into books values(seq_books.nextval,'组合数学','smith',39,5);
insert into books values(seq_books.nextval,'天文学','Jerry',100,2);

select * from card;
select * from books;

insert into borrow values(2,2,to_date('2012-6-5','yyyy-mm-dd')  );
insert into borrow values(2,2,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(2,3,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(2,4,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(2,3,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(3,2,to_date('2012-4-30','yyyy-mm-dd'));
insert into borrow values(4,4,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(6,4,to_date('2012-6-6','yyyy-mm-dd'));
insert into borrow values(6,6,to_date('2012-6-6','yyyy-mm-dd'));
insert into borrow values(2,5,to_date('2012-6-5','yyyy-mm-dd'));
insert into borrow values(2,6,to_date('2012-6-5','yyyy-mm-dd'));
select * from borrow
--备注：限定每人每种书只能借一本；库存册数随借书、还书而改变。
--1． 写出建立BORROW表的SQL语句，要求定义主码完整性约束和引用完整性约束。
--2． 找出借书超过5本的读者,输出借书卡号及所借图书册数。


--3． 查询借阅了"水浒"一书的读者，输出姓名及班级。


--4． 查询过期未还图书，输出借阅者（卡号）、书号及还书日期。

--5． 查询书名包括"网络"关键词的图书，输出书号、书名、作者。

--6． 查询现有图书中价格最高的图书，输出书名及作者。

--7． 查询当前借了"计算方法"但没有借"计算方法习题集"的读者，输出其借书卡号，并按卡号降序排序输出。

--8． 将"C01"班同学所借图书的还期都延长一周 。  sysdate+7

--9． 从BOOKS表中删除当前无人借阅的图书记录。

--13．查询当前同时借有"计算方法"和"组合数学"两本书的读者，输出其借书卡号，并按卡号升序排序输出。

--15．对CARD表做如下修改：
--a. 将NAME最大列宽增加到10个字符（假定原为6个字符）。

--b. 为该表增加1列NAME（系名），可变长，最大20个字符。

