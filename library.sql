/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 28/09/2024 20:47:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bookid` int NOT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`bookid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (0, 'c语言程序设计', '未知', '长篇');
INSERT INTO `book` VALUES (1, '老人与海', '海明威', '中篇小说');
INSERT INTO `book` VALUES (2, '西游记', '吴承恩', '长篇小说');
INSERT INTO `book` VALUES (3, '斗罗大陆', '唐家三少', '长篇小说');
INSERT INTO `book` VALUES (4, '平凡的世界', '路遥', '中篇小说');
INSERT INTO `book` VALUES (5, '活着', '余华', '中篇小说');
INSERT INTO `book` VALUES (6, '红楼梦', '曹雪芹', '长篇小说');
INSERT INTO `book` VALUES (7, '水浒传', '罗贯中', '长篇小说');
INSERT INTO `book` VALUES (8, '十八岁第一次出门远行', '余华', '短篇小说');
INSERT INTO `book` VALUES (9, '狂人日记', '鲁迅', '短篇小说');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '方畅', '123456', '19711833185');
INSERT INTO `user` VALUES (2, '李四', '123456', '13456781');
INSERT INTO `user` VALUES (3, 'fangchang', '123456', '12121212122');
INSERT INTO `user` VALUES (4, 'fcc', '123456', '12341231');

SET FOREIGN_KEY_CHECKS = 1;
