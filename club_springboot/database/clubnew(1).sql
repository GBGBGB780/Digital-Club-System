/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : clubnew

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 01/06/2023 11:39:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for StuApp
-- ----------------------------
DROP TABLE IF EXISTS `stuApp`;
CREATE TABLE `stuApp`  (
  `applicationid` int NOT NULL AUTO_INCREMENT COMMENT '招聘信息ID',
  `groupname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团ID',
  `stuname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `stunumber` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生学号',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `major` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `selfintro` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自我陈述',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件',
  `time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `isaccepted` tinyint NULL DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`applicationid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of StuApp
-- ----------------------------
INSERT INTO `stuApp` VALUES (1, '足球社', '吴舜宇', '21311272', '18928622313', '男', '软件工程', '你干嘛~', '附件1', '2023-05-27 00:00:00', 1);
INSERT INTO `stuApp` VALUES (3, '足球社', '陈建铧', '21952107', '15664736059', '男', '软件工程', 'siuuuuuuu', '附件3', '2023-05-27 10:39:32', 1);
INSERT INTO `stuApp` VALUES (4, '羽毛球社', '龚敬', '21311303', '18022192013', '男', '软件工程', '妙啊！', '附件4', '2023-05-27 10:39:35', 1);
INSERT INTO `stuApp` VALUES (5, '足球社', '杨沛粤', '21311296', '15219368970', '男', '软件工程', '太煎熬了。', '附件5', '2023-05-27 10:39:38', 1);
INSERT INTO `stuApp` VALUES (6, '篮球社', '建华大神', '21956666', '1568978978', '男', '软件工程专业', '我想加入', '附件6', '2023-05-27 23:06:21', 0);
INSERT INTO `stuApp` VALUES (13, '足球社', '陈建铧', '21311245', '18899998874', '男', '人工智能学院', '收拾收拾', NULL, '2023-05-28 11:21:26', 0);
INSERT INTO `stuApp` VALUES (14, '足球社', '大神', '21311272', '15898765494', '男', '微电子技术学院', '哈哈哈', NULL, '2023-05-28 11:23:59', 0);
INSERT INTO `stuApp` VALUES (15, '飞盘社', '111', '21311272', '11111111111', '男', '人工智能学院', '111', NULL, '2023-05-28 12:00:09', NULL);
INSERT INTO `stuApp` VALUES (16, '足球社', '是是是', '22222222', '12222222222', '男', '人工智能学院', '2222', NULL, '2023-05-28 12:03:05', 0);
INSERT INTO `stuApp` VALUES (18, '足球社', '大师', '21318888', '13823465987', '男', '人工智能学院', '想来玩', NULL, '2023-05-29 13:31:37', 0);
INSERT INTO `stuApp` VALUES (19, '足球社', '吴舜宇', '21311272', '18928622313', '男', '测绘与遥感学院', '想玩', NULL, '2023-05-29 14:58:05', NULL);
INSERT INTO `stuApp` VALUES (20, '足球社', '陈建铧', '21952107', '12547854789', '女', '微电子技术学院', '1111', '123', '2023-05-31 18:28:57', NULL);
INSERT INTO `stuApp` VALUES (21, '飞盘社', '陈建铧', '21952107', '15664736059', '男', '软件工程学院', 'test complete', NULL, '2023-06-01 11:23:20', NULL);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团编号\n',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名字',
  `leader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团负责人',
  `image` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团照片',
  `description` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团介绍',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团附件',
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团管理人员密码',
  `hot` int DEFAULT '0' COMMENT '当前热度',
  PRIMARY KEY (`id`, `name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', '足球社', 'B费', 'https://img1.baidu.com/it/u=2830895879,1858512659&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=367', 'C罗siuuuuuu!', NULL, '111111', 9);
INSERT INTO `group` VALUES ('2', '飞盘社', '陈建铧', 'https://pic3.zhimg.com/v2-d44ffaccc42c2639b20bae7f980994aa_r.jpg', '大神飞盘教学', NULL, '111111', 2);
INSERT INTO `group` VALUES ('3', '文学社', '贾宝玉', 'https://img1.baidu.com/it/u=4003599851,1156152734&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=982158c0ce50753b778bd348cd23c72f', '红楼梦', NULL, '111111', 0);
INSERT INTO `group` VALUES ('4', '说唱社', '丁真', 'https://img1.baidu.com/it/u=3079149788,2510641134&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=b4fdd67eb0eb877a23df58dfe2dffca6', '理塘丁真', NULL, '111111', 0);
INSERT INTO `group` VALUES ('5', '唱跳社', '坤坤', 'https://img2.baidu.com/it/u=4022135548,2767225744&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=c7fb5fb46e1d0af0a5cda28717085867', '基尼太美', NULL, '111111', 0);
INSERT INTO `group` VALUES ('6', '羽毛球社', 'hh', 'https://img.zcool.cn/community/01828f595df60ba8012193a35a606e.JPG@1280w_1l_2o_100sh.jpg', 'badminton', NULL, '111111', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `stunumber` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生学号',
  `stuname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生名字',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生邮箱',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生手机号',
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生密码',
  PRIMARY KEY (`stunumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21311272', NULL, NULL, NULL, '666666');
INSERT INTO `user` VALUES ('21952107', NULL, NULL, NULL, '888888');

SET FOREIGN_KEY_CHECKS = 1;
