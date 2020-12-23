/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 23/12/2020 23:19:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('123456', '3454a337e455ed96aaffb3b81a7a27f4509c08de');
COMMIT;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `abbr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) NOT NULL,
  `leader` varchar(255) DEFAULT NULL,
  `leaderjobnumber` varchar(255) DEFAULT NULL,
  `moneyperperson` int NOT NULL,
  `totalnumberofmember` int NOT NULL,
  `restNum` int NOT NULL,
  `restnumfor01` int NOT NULL,
  `restnumfor02` int NOT NULL,
  PRIMARY KEY (`abbr`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of award
-- ----------------------------
BEGIN;
INSERT INTO `award` VALUES ('00', '一等奖', NULL, NULL, 200, 4, 0, 0, 0);
INSERT INTO `award` VALUES ('01', '二等奖', NULL, NULL, 200, 2, 1, 0, 1);
INSERT INTO `award` VALUES ('02', 'adf', '0', '0', 200, 2, 0, 0, 0);
INSERT INTO `award` VALUES ('03', '特别奖', '我', '10228', 2000, 2, 2, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `abbr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) NOT NULL,
  `numberofmember` int NOT NULL,
  PRIMARY KEY (`abbr`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` VALUES ('01', '一', 5);
INSERT INTO `department` VALUES ('02', '二', 9);
COMMIT;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `jobnumber` varchar(255) NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flagofaward` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `flagofaward` (`flagofaward`),
  KEY `member_ibfk_1` (`department`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
BEGIN;
INSERT INTO `member` VALUES (1, '杨晨', '1', '01', NULL);
INSERT INTO `member` VALUES (2, '杨', '2', '01', '00');
INSERT INTO `member` VALUES (3, '啊', '3', '01', '00');
INSERT INTO `member` VALUES (4, '的', '4', '01', '02');
INSERT INTO `member` VALUES (5, '饿', '5', '01', '00');
INSERT INTO `member` VALUES (6, '一天', '6', '02', '02');
INSERT INTO `member` VALUES (7, '据说', '7', '02', '01');
INSERT INTO `member` VALUES (8, '此时', '8', '02', NULL);
INSERT INTO `member` VALUES (9, '请', '9', '02', NULL);
INSERT INTO `member` VALUES (10, '长度', '10', '02', NULL);
INSERT INTO `member` VALUES (11, '根据', '11', '02', NULL);
INSERT INTO `member` VALUES (12, '表', '12', '02', NULL);
INSERT INTO `member` VALUES (13, '阿斗', '13', '02', NULL);
INSERT INTO `member` VALUES (14, '瞎操心', '14', '02', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
