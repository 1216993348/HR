-- 删除数据库
--DROP DATABASE IF EXISTS mshop;
-- 创建数据库
--CREATE DATABASE mshop;
-- 使用mshop数据库
USE mshop;
-- 删除数据表
DROP TABLE IF EXISTS details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS admin;
-- 创建数据表
-- 1、创建商品类型表
create table item
(
   iid                  int not null AUTO_INCREMENT,
   ititle               varchar(200) not null,
   CONSTRAINT pk_iid primary key (iid)
)ENGINE=innodb;
-- 2、创建管理员表
create table admin
(
   aid                  varchar(50) not null,
   apassword            varchar(32) not null,
   lastdate             datetime not null,
   CONSTRAINT pk_aid    primary key (aid)
)ENGINE=innodb;
-- 3、创建用户表
create table member
(
   mid                   varchar(50) not null,
   mpassword            varchar(32) not null,
   mname                varchar(50) ,
   mphone               varchar(50),
   maddress             varchar(100),
   mstatus              int not null ,
   mcode                varchar(100),
   mregdate             datetime not null ,
   mphoto               varchar(50),
   CONSTRAINT pk_mid    primary key (mid)
)ENGINE=innodb;
-- 4、创建商品表
create table goods
(
   gid                  int AUTO_INCREMENT,
   iid                  int ,
   aid                  varchar(50),
   gtitle               varchar(200),
   gpubdate             datetime,
   gprice               float,
   gamount              int,
   gphoto               varchar(50),
   gbrow                int,
   gnote                text,
   gstatus              int,
   CONSTRAINT pk_gid   PRIMARY KEY (gid),
   CONSTRAINT fk_iid   FOREIGN KEY (iid) REFERENCES item(iid) ON DELETE SET NULL ,
   CONSTRAINT fk_aid   FOREIGN KEY (aid) REFERENCES admin(aid) ON DELETE SET NULL
)ENGINE=innodb;
-- 5、创建订单表
create table orders
(
   oid                  int AUTO_INCREMENT,
   mid                  varchar(50) not null ,
   mname                varchar(50) not null ,
   mphone               varchar(50),
   maddress             varchar(100),
   credate              datetime,
   mpay                 float,
   CONSTRAINT pk_oid    primary key (oid),
   CONSTRAINT fk_mid FOREIGN KEY (mid) REFERENCES member(mid) ON DELETE CASCADE
)ENGINE=innodb;
-- 6、创建订单详情表
create table details
(
   odid                 int not null,
   gid                  int ,
   oid                  int not null ,
   gname                varchar(200),
   gprice               float,
   odnumber             int,
   CONSTRAINT pk_odid primary key (odid),
   CONSTRAINT fk_gid2 FOREIGN KEY (gid) REFERENCES goods(gid) ON DELETE SET NULL,
   CONSTRAINT fk_oid FOREIGN KEY (oid) REFERENCES orders(oid) ON DELETE CASCADE
)ENGINE=innodb;
-- 添加测试数据
-- 管理员表测试数据 admin/hello
INSERT INTO admin(aid, apassword, lastdate) VALUES('admin','5D41402ABC4B2A76B9719D911017C592','2017-07-23');
-- 商品类型表测试数据
INSERT INTO item(ititle) VALUES ('家用电器');
INSERT INTO item(ititle) VALUES ('生活日用');
INSERT INTO item(ititle) VALUES ('儿童玩具');
INSERT INTO item(ititle) VALUES ('食品');
-- 增加普通用户信息：xhy / java
INSERT INTO member(mid,mpassword,mregdate,mstatus) VALUES ('xhy','93F725A07423FE1C889F448B33D21F46','1999-10-10',1) ;
-- 提交事务
COMMIT ;