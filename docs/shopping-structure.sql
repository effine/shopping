CREATE DATABASE IF NOT EXISTS `shopping` DEALLOCATE CHARACTER=UTF8;

USE `shopping`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '商品名称',
  `nameEN` varchar(180) DEFAULT '商品英文名称' COMMENT '商品名称(英)',
  `code` varchar(20) NOT NULL COMMENT '商品编码',
  `alias` varchar(300) DEFAULT NULL COMMENT '商品别名',
  `salesUnit` varchar(255) DEFAULT NULL COMMENT '销售单位',
  `description` varchar(300) DEFAULT NULL COMMENT '商品简介',
  `descriptionEN` varchar(300) DEFAULT NULL COMMENT '商品简介(英)',
  `details` text COMMENT '商品文描',
  `detailsEN` text COMMENT '商品文描(英)',
  `categoryId` int(11) NOT NULL COMMENT '商品分类',
  `brandId` int(11) DEFAULT NULL COMMENT '商品品牌',
  `title` varchar(60) DEFAULT NULL COMMENT '商品副标题',
  `titleEN` varchar(180) DEFAULT '' COMMENT '商品副标题(英文)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createUser` int(11) COMMENT '创建人',
  `lastModifyTime` datetime NOT NULL COMMENT '最好修改时间',
  `lastModifyUser` int(11) NOT NULL COMMENT '最后更新用户',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '商品状态[1正常|0禁用]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE `signin_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  `signin_time` varchar(32) NOT NULL COMMENT '登录时间',
  `client` tinyint(1) DEFAULT '0' COMMENT '[0PC端|1移动端]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录历史表';


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '用户名(昵称)',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `passwd` varchar(32) NOT NULL COMMENT '密码',
  `phone` varchar(32) NOT NULL COMMENT '电话',
  `is_active` tinyint(1) DEFAULT '0' COMMENT '邮箱是否激活[0未激活|1激活]',
  `signup_time` date NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `real_name` varchar(32) DEFAULT NULL COMMENT '用户真实姓名',
  `birthday` varchar(32) DEFAULT NULL COMMENT '出生年月日',
  `gender` tinyint(1) DEFAULT '0' COMMENT '[0男|1女]',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `site` varchar(32) DEFAULT NULL COMMENT '个人站点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细表';
