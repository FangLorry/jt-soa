/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : db_shiro

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2017-06-09 17:20:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ss_order
-- ----------------------------
DROP TABLE IF EXISTS `ss_order`;
CREATE TABLE `ss_order` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `amout` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_people
-- ----------------------------
DROP TABLE IF EXISTS `ss_people`;
CREATE TABLE `ss_people` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `age` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_permission
-- ----------------------------
DROP TABLE IF EXISTS `ss_permission`;
CREATE TABLE `ss_permission` (
  `id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `key` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_product
-- ----------------------------
DROP TABLE IF EXISTS `ss_product`;
CREATE TABLE `ss_product` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `price` decimal(10,2) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `market_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
  `chengben_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `head_url` varchar(255) DEFAULT NULL,
  `content_` text,
  `desc_` varchar(1024) DEFAULT NULL,
  `body_urls` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_role
-- ----------------------------
DROP TABLE IF EXISTS `ss_role`;
CREATE TABLE `ss_role` (
  `id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `key` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_role_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `ss_role_permission_ref`;
CREATE TABLE `ss_role_permission_ref` (
  `id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  `permission_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_qwsddswer12dfdsd` (`role_id`),
  KEY `fk_sdfwer123sdfdfer` (`permission_id`),
  CONSTRAINT `fk_qwsddswer12dfdsd` FOREIGN KEY (`role_id`) REFERENCES `ss_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sdfwer123sdfdfer` FOREIGN KEY (`permission_id`) REFERENCES `ss_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_user
-- ----------------------------
DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` varchar(64) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `lock` int(1) DEFAULT NULL,
  `head_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ss_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `ss_user_role_ref`;
CREATE TABLE `ss_user_role_ref` (
  `id` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(100) NOT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_123123asdsad123` (`role_id`),
  KEY `fk_sdfsde1232323` (`user_id`),
  CONSTRAINT `fk_123123asdsad123` FOREIGN KEY (`role_id`) REFERENCES `ss_role` (`id`),
  CONSTRAINT `fk_sdfsde1232323` FOREIGN KEY (`user_id`) REFERENCES `ss_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for ges_user_count
-- ----------------------------
DROP PROCEDURE IF EXISTS `ges_user_count`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ges_user_count`(IN sex_id INT, OUT user_count INT)
BEGIN
IF sex_id=0 THEN
SELECT COUNT(*) FROM mybatis.p_user WHERE p_user.sex='女' INTO user_count;
ELSE
SELECT COUNT(*) FROM mybatis.p_user WHERE p_user.sex='男' INTO user_count;
END IF;
END
;;
DELIMITER ;
