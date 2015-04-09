# Host: localhost  (Version: 5.5.34)
# Date: 2015-04-09 15:56:52
# Generator: MySQL-Front 5.3  (Build 4.196)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "signin_history"
#

DROP TABLE IF EXISTS `signin_history`;
CREATE TABLE `signin_history` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `uid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录历史';

#
# Data for table "signin_history"
#


#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL DEFAULT '' COMMENT '用户ID',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `nike_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `passwd` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `is_active` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮箱是否激活（0:未激活; 1:激活）',
  `signup_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '注册时间',
  `last_signin_time` date NOT NULL DEFAULT '0000-00-00' COMMENT '最后登录时间',
  `last_signin_ip` varchar(32) NOT NULL DEFAULT '' COMMENT '最后登录的ip',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "user"
#

