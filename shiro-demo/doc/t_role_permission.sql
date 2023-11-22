/*
 Navicat Premium Data Transfer

 Source Server         : local_mysql
 Source Server Type    : MySQL
 Source Server Version : 50742
 Source Host           : 127.0.0.1:3306
 Source Schema         : local

 Target Server Type    : MySQL
 Target Server Version : 50742
 File Encoding         : 65001

 Date: 22/11/2023 11:24:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 2);
INSERT INTO `t_role_permission` VALUES (2, 1, 3);
INSERT INTO `t_role_permission` VALUES (3, 2, 1);
INSERT INTO `t_role_permission` VALUES (4, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
