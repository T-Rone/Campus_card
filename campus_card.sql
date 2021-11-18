/*
 Navicat Premium Data Transfer

 Source Server         : 本机数据库
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : campus_card

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 18/11/2021 14:34:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stu_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stu_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stu_pwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `balance` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '余额',
  `college` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (8, '1001', '龙涛', '2005059042', '123456', '132664.0', '数学与计算机');
INSERT INTO `account` VALUES (9, '1002', '乔布斯', '2005059111', '753814', '1651.0', '经济学院');
INSERT INTO `account` VALUES (10, '1003', '库克', '2005059222', '445310', '12313.0', '机械学院');
INSERT INTO `account` VALUES (11, '1004', '那路多', '2005059333', '486213', '1566.0', '艺术学院');
INSERT INTO `account` VALUES (12, '1005', 'Juice', '2005059444', '974913', '1002.5', '天文学');
INSERT INTO `account` VALUES (13, '1006', 'PostMalone', '2005059555', '357465', '800', '文学院');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表格' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'root', 'root', '2021-05-30 12:23:10');

-- ----------------------------
-- Table structure for bank_card
-- ----------------------------
DROP TABLE IF EXISTS `bank_card`;
CREATE TABLE `bank_card`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `card_pwd` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `balance` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `bank` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '银行卡' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bank_card
-- ----------------------------
INSERT INTO `bank_card` VALUES (1, '6228147534893134975', '123456', '999300.0', '借记卡', '中国农业银行');

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime(0) NOT NULL,
  `order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `store_order_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pay_income` int(1) NOT NULL,
  `store` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `classify` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (1, '2021-06-07 08:00:00', '45646516', '1231', '烤盘饭', '11.4', 1, '全日升烤盘饭', '餐饮');
INSERT INTO `bill` VALUES (2, '2021-04-30 12:32:31', '6580406764', '6414547994', '肉包子', '2.0', 1, '四季包子', '餐饮');
INSERT INTO `bill` VALUES (3, '2021-05-28 23:08:07', '8356539440', '9547984724', '热干面', '4.0', 1, '麦香园', '餐饮');
INSERT INTO `bill` VALUES (4, '2021-06-01 23:11:57', '3670305575', '6707573981', '豆皮', '6.0', 1, '通城正宗祖传豆皮', '餐饮');
INSERT INTO `bill` VALUES (5, '2021-06-02 23:14:02', '543434', '343434', '土豆酱肉', '11.0', 1, 'F+牛肉饭', '餐饮');
INSERT INTO `bill` VALUES (6, '2021-06-06 08:00:00', '1593682356', '7536941', '酱香饼', '3.0', 1, '土家美味', '餐饮');

SET FOREIGN_KEY_CHECKS = 1;
