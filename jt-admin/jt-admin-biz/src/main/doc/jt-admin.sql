/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : jt-admin

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2017-08-02 15:23:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jt_data_source
-- ----------------------------
DROP TABLE IF EXISTS `jt_data_source`;
CREATE TABLE `jt_data_source` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `driver_class_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `max_active` int(10) DEFAULT NULL,
  `initial_size` decimal(5,0) DEFAULT NULL,
  `max_wait` decimal(10,0) DEFAULT NULL,
  `minIdle` decimal(5,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_dict
-- ----------------------------
DROP TABLE IF EXISTS `jt_dict`;
CREATE TABLE `jt_dict` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `label` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort` int(5) DEFAULT NULL,
  `parent_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_gen_scheme
-- ----------------------------
DROP TABLE IF EXISTS `jt_gen_scheme`;
CREATE TABLE `jt_gen_scheme` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `package_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `module_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `function_name_simple` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `function_author` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_model
-- ----------------------------
DROP TABLE IF EXISTS `jt_model`;
CREATE TABLE `jt_model` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comments` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_table` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_table_fk` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_model_field
-- ----------------------------
DROP TABLE IF EXISTS `jt_model_field`;
CREATE TABLE `jt_model_field` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `jdbc_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `my_batisJdbc_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `javaType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `javaField` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `isPk` tinyint(255) DEFAULT NULL,
  `isNull` tinyint(255) DEFAULT NULL,
  `isInsert` tinyint(255) DEFAULT NULL,
  `isEdit` tinyint(255) DEFAULT NULL,
  `isList` tinyint(255) DEFAULT NULL,
  `queryType` tinyint(4) DEFAULT NULL,
  `isQuery` tinyint(4) NOT NULL,
  `htmlShowType` tinyint(4) DEFAULT NULL,
  `dictType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sort` tinyint(4) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_permission
-- ----------------------------
DROP TABLE IF EXISTS `jt_permission`;
CREATE TABLE `jt_permission` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_role
-- ----------------------------
DROP TABLE IF EXISTS `jt_role`;
CREATE TABLE `jt_role` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_role_permision_ref
-- ----------------------------
DROP TABLE IF EXISTS `jt_role_permision_ref`;
CREATE TABLE `jt_role_permision_ref` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `permission_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_user
-- ----------------------------
DROP TABLE IF EXISTS `jt_user`;
CREATE TABLE `jt_user` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pwd` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_user_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `jt_user_permission_ref`;
CREATE TABLE `jt_user_permission_ref` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `permission_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for jt_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `jt_user_role_ref`;
CREATE TABLE `jt_user_role_ref` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
