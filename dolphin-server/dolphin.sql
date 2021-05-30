/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : dolphin

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 30/05/2021 21:33:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article_class
-- ----------------------------
DROP TABLE IF EXISTS `t_article_class`;
CREATE TABLE `t_article_class` (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '类别姓名',
  `sort` int NOT NULL COMMENT '类别排序',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `fid` int DEFAULT '0' COMMENT '父id',
  `cover` varchar(900) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `type` tinyint DEFAULT '0' COMMENT '类型',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fid` (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_article_class
-- ----------------------------
BEGIN;
INSERT INTO `t_article_class` VALUES ('1111', 100, 11, 0, 'http://localhost:8089/static/images/product/f0bda3ec-8f12-4580-8a4d-c6af169d0fd3.jpeg', '2021-05-27 20:19:32', '2021-05-27 20:28:20', 0, 1622210931);
INSERT INTO `t_article_class` VALUES ('111-222', 100, 12, 11, 'http://localhost:8089/static/images/product/004e74c1-9e8e-4de5-ae45-482ac7a11960.jpeg', '2021-05-27 20:32:51', '2021-05-27 20:32:51', 0, 0);
INSERT INTO `t_article_class` VALUES ('1111', 100, 13, 0, 'http://localhost:8089/static/images/product/54396f93-491d-4ead-8717-77f9c93e9a29.jpeg', '2021-05-28 22:15:19', '2021-05-28 22:15:19', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_articles
-- ----------------------------
DROP TABLE IF EXISTS `t_articles`;
CREATE TABLE `t_articles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '新闻标题',
  `fclass` int DEFAULT NULL COMMENT '父类id',
  `content` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'remark',
  `published_at` date DEFAULT NULL COMMENT '发表日期',
  `cover` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'cover',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fclass` (`fclass`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_articles
-- ----------------------------
BEGIN;
INSERT INTO `t_articles` VALUES (10, '11', 144, '<p>11</p>', '22', '2021-05-26', 'http://localhost:8089/static/images/eb310b2b-f235-480a-b457-9049ae265877.jpeg,http://localhost:8089/static/images/81ff45d2-c067-40d8-a2df-4b5d89ddb0c9.jpg,http://localhost:8089/static/images/d4ab35ed-d09a-4762-a029-7563d8e29cc4.png,http://localhost:8089/static/images/b25fd523-bda2-4128-b080-5bd25f2d38d1.jpg,http://localhost:8089/static/images/cc898133-ad19-4cfd-99d6-8cd1f347b2e2.jpeg', 0, '2021-05-26 17:36:32', '2021-05-26 18:03:23');
INSERT INTO `t_articles` VALUES (11, '111', 144, '<p>3331111</p>', '222', '2021-05-27', 'http://localhost:8089/static/images/article/13059dc7-a92d-40c6-a26e-f2f0617f9d83.jpg', 0, '2021-05-27 08:31:19', '2021-05-27 09:14:03');
INSERT INTO `t_articles` VALUES (12, '11', 144, '<p>333</p>', '222', '2021-05-27', 'http://localhost:8089/static/images/article/49b1aeb5-ce09-40cb-bda5-2bd47b68e2bc.jpg', 0, '2021-05-27 08:34:21', '2021-05-27 09:12:22');
INSERT INTO `t_articles` VALUES (13, '你好', 144, '<p>111</p>', '111', '2021-05-27', 'http://localhost:8089/static/images/article/866bd2c6-c050-42d8-9068-98ffe17d21e7.jpeg,http://localhost:8089/static/images/article/973b7122-cb10-49c8-92d2-2dd15635db74.jpeg', 0, '2021-05-27 09:14:18', '2021-05-27 20:13:17');
INSERT INTO `t_articles` VALUES (14, '111', 144, '<p>333</p>', '22', '2021-05-28', 'http://localhost:8089/static/images/article/039ab7a2-ec10-4e6f-9b65-17cad9197c1e.jpeg', 0, '2021-05-28 22:08:38', '2021-05-28 22:08:38');
COMMIT;

-- ----------------------------
-- Table structure for t_menu_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_menu_roles`;
CREATE TABLE `t_menu_roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `mid` int DEFAULT NULL COMMENT '菜单id',
  `rid` int DEFAULT NULL COMMENT '权限id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `mid` (`mid`) USING BTREE,
  KEY `rid` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_menu_roles
-- ----------------------------
BEGIN;
INSERT INTO `t_menu_roles` VALUES (111, 120, 19, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menu_roles` VALUES (112, 121, 19, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menu_roles` VALUES (113, 122, 19, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menu_roles` VALUES (114, 123, 19, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menu_roles` VALUES (115, 124, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menu_roles` VALUES (116, 125, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menu_roles` VALUES (117, 126, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menu_roles` VALUES (118, 127, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menu_roles` VALUES (119, 128, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menu_roles` VALUES (120, 129, 20, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_menus
-- ----------------------------
DROP TABLE IF EXISTS `t_menus`;
CREATE TABLE `t_menus` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '后台url',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '组件',
  `parent_id` int DEFAULT NULL COMMENT '父id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parentId` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_menus
-- ----------------------------
BEGIN;
INSERT INTO `t_menus` VALUES (120, '/user/lists/create', 'user.lists.create', NULL, 1, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menus` VALUES (121, '/user/lists/edit', 'user.lists.edit', NULL, 1, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menus` VALUES (122, '/user', 'user', NULL, 1, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menus` VALUES (123, '/user/lists', 'user.lists', NULL, 1, '2021-05-22 17:47:12', '2021-05-22 17:47:12', 0);
INSERT INTO `t_menus` VALUES (124, '/user/lists/del', 'user.lists.del', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menus` VALUES (125, '/user/lists/show', 'user.lists.show', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menus` VALUES (126, '/user/lists/create', 'user.lists.create', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menus` VALUES (127, '/user/lists/edit', 'user.lists.edit', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menus` VALUES (128, '/user', 'user', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
INSERT INTO `t_menus` VALUES (129, '/user/lists', 'user.lists', NULL, 1, '2021-05-22 17:49:29', '2021-05-22 17:49:29', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `deleted_at` int DEFAULT '0',
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `num` tinyint NOT NULL DEFAULT '1',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `weight` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '重量',
  `goods_position` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品位置',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `pay_method` tinyint NOT NULL DEFAULT '2' COMMENT '支付方式',
  `shop_id` int NOT NULL,
  `finish_date` datetime DEFAULT NULL,
  `take_date` datetime DEFAULT NULL COMMENT '取货日期',
  `createor` int DEFAULT NULL COMMENT '新建者userId',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `createor` (`createor`),
  KEY `shop_id` (`shop_id`),
  KEY `order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES (67, 0, '2021-05-30 21:01:34', '2021-05-30 21:01:34', NULL, 16, 1, NULL, NULL, '7544937923567611001', 1, '洗衣液', '3.04', '111', 5.00, 2, 3, NULL, '2021-05-30 21:01:34', 1);
COMMIT;

-- ----------------------------
-- Table structure for t_poster
-- ----------------------------
DROP TABLE IF EXISTS `t_poster`;
CREATE TABLE `t_poster` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` int NOT NULL COMMENT '广告类型',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '名称',
  `pic` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '图片',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'url',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'remark',
  `is_index` tinyint NOT NULL DEFAULT '0' COMMENT '是否首页',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_poster
-- ----------------------------
BEGIN;
INSERT INTO `t_poster` VALUES (259, 1, '11112222', 'http://localhost:8089/static/images/article/f287d57d-691b-4497-ac4b-5c8fb6fc9593.jpeg', '222', 3331111, NULL, 0, 1622123221, NULL, '2021-05-27 21:46:58');
INSERT INTO `t_poster` VALUES (260, 2, '222', 'http://localhost:8089/static/images/article/21193683-7bbf-4e7c-b761-198ba70d38c3.jpeg', '33', 111, NULL, 0, 0, '2021-05-27 21:47:20', '2021-05-27 21:47:20');
COMMIT;

-- ----------------------------
-- Table structure for t_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_roles`;
CREATE TABLE `t_roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `name_zh` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `menu_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色菜单',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_roles
-- ----------------------------
BEGIN;
INSERT INTO `t_roles` VALUES (19, '测试111223331', '111112', 'user.lists.create,user.lists.edit', NULL, '2021-05-22 17:47:12', 0);
INSERT INTO `t_roles` VALUES (20, '角色一', NULL, NULL, NULL, NULL, 1622000459);
COMMIT;

-- ----------------------------
-- Table structure for t_shops
-- ----------------------------
DROP TABLE IF EXISTS `t_shops`;
CREATE TABLE `t_shops` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '联系人',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '联系人电话',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '门店名称',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_shops
-- ----------------------------
BEGIN;
INSERT INTO `t_shops` VALUES (3, '11', '22', '门店一', NULL, '2021-05-22 17:41:23', 0);
INSERT INTO `t_shops` VALUES (4, '22', '33', '门店二', '2021-05-22 00:00:00', '2021-05-30 11:38:11', 0);
INSERT INTO `t_shops` VALUES (5, '22', '33', '门店三', '2021-05-22 17:41:42', '2021-05-30 11:38:19', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_site_config
-- ----------------------------
DROP TABLE IF EXISTS `t_site_config`;
CREATE TABLE `t_site_config` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `value` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9313 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_site_config
-- ----------------------------
BEGIN;
INSERT INTO `t_site_config` VALUES (9312, 'web_logo', '1222', '11', NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_user_infos`;
CREATE TABLE `t_user_infos` (
  `user_id` int NOT NULL COMMENT '关联user表的id',
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员姓名',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '管理员手机号码',
  `telephone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系地址',
  `user_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户头像',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_infos
-- ----------------------------
BEGIN;
INSERT INTO `t_user_infos` VALUES (1, 1, '总管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_user_infos` VALUES (12, 12, 'xiaozhu', '13328805395', NULL, NULL, NULL, NULL, '2021-05-30 12:05:38', '2021-05-30 12:30:07', 1622349124);
INSERT INTO `t_user_infos` VALUES (16, 16, '小朱会员', '13326805391', NULL, '广东省江门市江海区五邑路368号', NULL, '111', '2021-05-30 19:04:17', '2021-05-30 19:04:17', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int DEFAULT NULL COMMENT '用户id',
  `role_id` int DEFAULT NULL COMMENT '权限id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `rid` (`role_id`) USING BTREE,
  KEY `adminId` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_roles
-- ----------------------------
BEGIN;
INSERT INTO `t_user_roles` VALUES (1, 1, 9, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `role` tinyint DEFAULT '1' COMMENT '用户角色',
  `amount` decimal(10,2) DEFAULT '0.00' COMMENT '余额',
  `shop_id` int NOT NULL COMMENT '关联shop表的id',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted_at` int DEFAULT '0' COMMENT '删除时间',
  `role_id` int DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `shop_id` (`shop_id`) USING BTREE,
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_users
-- ----------------------------
BEGIN;
INSERT INTO `t_users` VALUES (1, 1, 'admin', '$2a$10$77x7140ul4MbGPn.HXU/DulcGGU.Lnv6MH6Pv6Cn4NFxsIoR5bfca', 1, 0.00, 3, NULL, NULL, 0, NULL);
INSERT INTO `t_users` VALUES (12, 0, '13328805395', '$2a$10$Tx6AweQbu22Sl37MfxzKfu7pB7YXGAztHum.b.PpKLWg5mSxZBwba', 3, 0.00, 5, '2021-05-30 12:05:38', '2021-05-30 12:30:07', 1622349124, 19);
INSERT INTO `t_users` VALUES (16, 1, '13326805391', '$2a$10$0lalb9r6eFKB2D8gmFIE1OQobziBM8tKpXAGLlO0smgGxRX9Nb0O6', 2, 0.00, 3, '2021-05-30 19:04:17', '2021-05-30 19:04:17', 0, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
