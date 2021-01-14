create table USER(id int(4),openid varchar(28),name varchar(12),carid varchar(7));
create table ADMIN(id int(4),password varchar(18),name varchar(8));
create table OPERATOR(id int(4),operatorHistory varchar(8));
/*test*/
insert into USER VALUES (0001,'ASHDJK123','张三','鲁C88888');
insert into ADMIN VALUES (0001,'123456','李四');
insert into  OPERATOR values (0001,'添加');