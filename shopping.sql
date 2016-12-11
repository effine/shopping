create database if not exists shopping default charset=utf8;

use shopping;

create table if not exists user (
  id int(11) primary key auto_increment not null,
  name varchar(32) not null comment '用户名(昵称)',
  email varchar(32) not null comment '邮箱',
  passwd varchar(32) not null comment '密码',
  phone varchar(32) not null comment '电话',
  is_active tinyint(1) default 0 comment '邮箱是否激活[0未激活|1激活]',
  signup_time date not null comment '注册时间'
) engine=innodb default charset=utf8 comment='用户表';

create table if not exists user_info (
  id int(11) primary key auto_increment not null,
  uid int(11) not null comment '用户ID',
  real_name varchar(32) default null comment '用户真实姓名',
  birthday varchar(32) default null comment '出生年月日',
  gender tinyint(1) default 0 comment '[0男|1女]',
  avatar varchar(255) default null comment '头像',
  address varchar(255) default null comment '地址',  
  site varchar(32) default null comment '个人站点'
)engine=innodb default charset=utf8 comment '用户详细表';

create table if not exists signin_history (
  id int(11) primary key  auto_increment not null,
  uid int(11) not null comment '用户ID',
  ip varchar(32) default null comment '登录IP',
  signin_time varchar(32) not null comment '登录时间',
  client tinyint(1) default 0 comment '[0PC端|1移动端]'
) engine=innodb default charset=utf8 comment='登录历史表';
