/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80400
 Source Host           : localhost:3306
 Source Schema         : club

 Target Server Type    : MySQL
 Target Server Version : 80400
 File Encoding         : 65001

 Date: 09/07/2024 09:29:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名字',
  `organizer` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动负责人',
  `image` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动照片',
  `description` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动介绍',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动附件',
  `hot` int NULL DEFAULT 0 COMMENT '当前热度',
  `arrange` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动事项安排',
  `time` datetime NULL DEFAULT NULL COMMENT '活动时间',
  `number` int NULL DEFAULT NULL COMMENT '活动人数',
  `place` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动地点',
  `type` int NULL DEFAULT NULL COMMENT '活动类型',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动负责社团名字',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_accepted` tinyint(1) NULL DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '对唱跳和篮球关系的研究', '吕杰', NULL, NULL, NULL, 0, '6667', NULL, 0, NULL, 0, 'aaa', NULL, NULL, NULL, '2024-05-18 16:16:37', 1);
INSERT INTO `activity` VALUES (2, '对唱跳和篮球关系的研究', '吕杰', NULL, NULL, NULL, 0, '6667', NULL, 0, NULL, 0, '足球社', NULL, NULL, NULL, '2024-05-18 16:16:37', 1);
INSERT INTO `activity` VALUES (3, 'GenshinImapact', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-27 09:11:33', 0, '活动室', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', NULL);
INSERT INTO `activity` VALUES (4, 'WutheringWaves', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-01 09:11:30', 0, '活动室', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (5, '学习会', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-09 09:11:23', 0, '活动室', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 0);
INSERT INTO `activity` VALUES (6, '训练', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-06-05 09:11:26', 0, '操场', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (7, '团建', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-06-06 09:11:36', 0, '校外', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (8, 'YuanShen', 'Sora', NULL, NULL, NULL, 0, '2222', '1919-08-10 11:45:14', 0, 'Earth', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-08 22:51:56', NULL);
INSERT INTO `activity` VALUES (9, 'StarRail', 'Sora', NULL, NULL, NULL, 0, '2222', '1919-08-10 11:45:14', 0, 'Earth', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-08 22:51:56', NULL);
INSERT INTO `activity` VALUES (10, 'ZenlessZoneZero', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-27 09:11:33', 0, '活动室', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', NULL);
INSERT INTO `activity` VALUES (11, 'YuanShen', 'Sora', NULL, NULL, NULL, 0, '2222', '1919-08-10 11:45:14', 0, 'Earth', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-08 22:51:56', NULL);
INSERT INTO `activity` VALUES (12, 'Yuanshen', 'Sora', NULL, NULL, NULL, 0, '3333', '1919-08-10 11:45:14', 0, 'Earth', 0, '足球社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (13, 'GenshinImapact', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-27 09:11:33', 0, '活动室', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', NULL);
INSERT INTO `activity` VALUES (14, 'WutheringWaves', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-01 09:11:30', 0, '活动室', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (15, '学习会', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-07-09 09:11:23', 0, '活动室', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 0);
INSERT INTO `activity` VALUES (16, '训练', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-06-05 09:11:26', 0, '操场', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);
INSERT INTO `activity` VALUES (17, '团建', '吕杰', NULL, NULL, NULL, 0, '1111', '2024-06-06 09:11:36', 0, '校外', 0, '飞盘社', NULL, '2024-07-08 22:51:56', NULL, '2024-07-09 08:34:18', 1);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '社团编号\n',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名字',
  `leader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团负责人',
  `image` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团照片',
  `description` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团介绍',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团附件',
  `hot` int NULL DEFAULT 0 COMMENT '当前热度',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_accepted` tinyint(1) NULL DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES (1, '足球社', 'B费', 'https://img1.baidu.com/it/u=2830895879,1858512659&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=367', 'C罗siuuuuuu!', NULL, 9, NULL, '2024-05-18 16:16:37', NULL, '2024-05-18 16:16:37', 1);
INSERT INTO `group` VALUES (2, '飞盘社', '陈建铧', 'https://pic3.zhimg.com/v2-d44ffaccc42c2639b20bae7f980994aa_r.jpg', '大神飞盘教学', NULL, 2, NULL, '2024-05-14 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (3, '文学社', '贾宝玉', 'https://img1.baidu.com/it/u=4003599851,1156152734&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=982158c0ce50753b778bd348cd23c72f', '红楼梦', NULL, 0, NULL, '2024-05-01 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (4, '说唱社', '丁真', 'https://img1.baidu.com/it/u=3079149788,2510641134&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=b4fdd67eb0eb877a23df58dfe2dffca6', '理塘丁真', NULL, 0, NULL, '2024-04-18 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (5, '唱跳社', '坤坤', 'https://img2.baidu.com/it/u=4022135548,2767225744&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=c7fb5fb46e1d0af0a5cda28717085867', '基尼太美', NULL, 0, NULL, '2024-03-18 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (6, '羽毛球社', 'hh', 'https://img.zcool.cn/community/01828f595df60ba8012193a35a606e.JPG@1280w_1l_2o_100sh.jpg', 'badminton', NULL, 1, NULL, '2024-05-08 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (7, '书社', '张三', 'https://img.cc0.cn/pixabay/2019103119133454194.jpg%21860.jpg', '书中自有黄金屋', NULL, 5, NULL, '2024-04-28 16:16:37', NULL, NULL, 1);
INSERT INTO `group` VALUES (8, '茶会社', '田中律', 'https://img.moegirl.org.cn/common/thumb/6/6a/%E6%94%BE%E5%AD%A6%E5%90%8ETEA_TIME.jpg/1920px-%E6%94%BE%E5%AD%A6%E5%90%8ETEA_TIME.jpg', '放学后TEA TIME', NULL, 1, NULL, '2024-06-18 16:16:37', NULL, NULL, 1);

-- ----------------------------
-- Table structure for individual_activity
-- ----------------------------
DROP TABLE IF EXISTS `individual_activity`;
CREATE TABLE `individual_activity`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户职位',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `status` int NULL DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `activity_id` int NULL DEFAULT NULL COMMENT '活动编号',
  `is_accepted` tinyint(1) NULL DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of individual_activity
-- ----------------------------

-- ----------------------------
-- Table structure for individual_group
-- ----------------------------
DROP TABLE IF EXISTS `individual_group`;
CREATE TABLE `individual_group`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户职位',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `status` int NULL DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `group_id` int NULL DEFAULT NULL COMMENT '社团编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of individual_group
-- ----------------------------
INSERT INTO `individual_group` VALUES (1, '21311366', NULL, '吕杰', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `individual_group` VALUES (2, '21311366', NULL, '吕杰', 1, NULL, NULL, NULL, NULL, 2);
INSERT INTO `individual_group` VALUES (3, '21311366', NULL, '吕杰', 0, NULL, NULL, NULL, NULL, 3);
INSERT INTO `individual_group` VALUES (4, '21311366', NULL, '吕杰', 0, NULL, NULL, NULL, NULL, 4);
INSERT INTO `individual_group` VALUES (5, '21301011', NULL, '王五', 1, NULL, NULL, NULL, NULL, 7);
INSERT INTO `individual_group` VALUES (6, '21301011', NULL, '王五', 1, NULL, NULL, NULL, NULL, 6);
INSERT INTO `individual_group` VALUES (7, '21301011', NULL, '王五', 1, NULL, NULL, NULL, NULL, 5);
INSERT INTO `individual_group` VALUES (8, '21301011', NULL, '王五', 1, NULL, NULL, NULL, NULL, 8);
INSERT INTO `individual_group` VALUES (9, '21301666', NULL, '哈基米', 1, NULL, NULL, NULL, NULL, 7);
INSERT INTO `individual_group` VALUES (10, '21301666', NULL, '哈基米', 1, NULL, NULL, NULL, NULL, 4);
INSERT INTO `individual_group` VALUES (11, '21301666', NULL, '哈基米', 1, NULL, NULL, NULL, NULL, 3);
INSERT INTO `individual_group` VALUES (12, '21301666', NULL, '哈基米', 1, NULL, NULL, NULL, NULL, 9);
INSERT INTO `individual_group` VALUES (13, '21301666', NULL, '小王', 1, NULL, NULL, NULL, NULL, 4);
INSERT INTO `individual_group` VALUES (14, '21301666', NULL, '小王', 1, NULL, NULL, NULL, NULL, 5);
INSERT INTO `individual_group` VALUES (15, '21301666', NULL, '小王', 1, NULL, NULL, NULL, NULL, 9);
INSERT INTO `individual_group` VALUES (16, '21301666', NULL, '小王', 1, NULL, NULL, NULL, NULL, 8);

-- ----------------------------
-- Table structure for inspection
-- ----------------------------
DROP TABLE IF EXISTS `inspection`;
CREATE TABLE `inspection`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '年检记录编号',
  `attachment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年检附件',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年检负责社团名字',
  `is_accepted` tinyint NULL DEFAULT NULL COMMENT '是否通过',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `year` year NULL DEFAULT NULL COMMENT '年份',
  `submitter_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '提交者编号',
  `feedback` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年检反馈',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inspection
-- ----------------------------

-- ----------------------------
-- Table structure for stu_app
-- ----------------------------
DROP TABLE IF EXISTS `stu_app`;
CREATE TABLE `stu_app`  (
  `application_id` int NOT NULL AUTO_INCREMENT COMMENT '招聘信息ID',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '社团ID',
  `stu_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `stu_number` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生学号',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `gender` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `major` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `self_intro` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自我陈述',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '附件',
  `is_accepted` tinyint NULL DEFAULT NULL COMMENT '是否通过',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`application_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stu_app
-- ----------------------------
INSERT INTO `stu_app` VALUES (1, '足球社', '吴舜宇', '21311272', '18928622313', '男', '软件工程', '你干嘛~', '附件1', 1, NULL, '2024-05-18 16:16:37', NULL, '2024-05-18 16:16:37');
INSERT INTO `stu_app` VALUES (3, '足球社', '陈建铧', '21952107', '15664736059', '男', '软件工程', 'siuuuuuuu', '附件3', 1, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (4, '羽毛球社', '龚敬', '21311303', '18022192013', '男', '软件工程', '妙啊！', '附件4', 1, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (5, '足球社', '杨沛粤', '21311296', '15219368970', '男', '软件工程', '太煎熬了。', '附件5', 1, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (6, '篮球社', '建华大神', '21956666', '1568978978', '男', '软件工程专业', '我想加入', '附件6', 0, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (13, '足球社', '陈建铧', '21311245', '18899998874', '男', '人工智能学院', '收拾收拾', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (14, '足球社', '大神', '21311272', '15898765494', '男', '微电子技术学院', '哈哈哈', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (15, '飞盘社', '111', '21311272', '11111111111', '男', '人工智能学院', '111', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (16, '足球社', '是是是', '22222222', '12222222222', '男', '人工智能学院', '2222', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (18, '足球社', '大师', '21318888', '13823465987', '男', '人工智能学院', '想来玩', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (19, '足球社', '吴舜宇', '21311272', '18928622313', '男', '测绘与遥感学院', '想玩', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (20, '足球社', '陈建铧', '21952107', '12547854789', '女', '微电子技术学院', '1111', '123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (21, '飞盘社', '陈建铧', '21952107', '15664736059', '男', '软件工程学院', 'test complete', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (22, '足球社', '张三', '21114514', '12345432123', '女', '土木工程学院', 'intro', NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `stu_app` VALUES (23, '文学', '李四', '21114515', '12345432124', '女', '土木工程学院', 'INTRO', NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号',
  `user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名字',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `campus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户校区',
  `major` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户专业',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户简介',
  `status` int NULL DEFAULT NULL COMMENT '用户身份',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐值',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `school` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12345678', NULL, '15888888888', NULL, '666666', NULL, NULL, NULL, 1, NULL, NULL, 'abc', NULL, '2024-05-17 22:51:24', NULL, '2024-05-17 22:51:24', NULL);
INSERT INTO `user` VALUES ('12345679', NULL, '15888888888', NULL, '666666', NULL, NULL, NULL, 1, NULL, NULL, 'abc', NULL, '2024-05-17 22:53:24', NULL, '2024-05-17 22:53:24', NULL);
INSERT INTO `user` VALUES ('12345689', NULL, '15888888888', NULL, '666666', NULL, NULL, NULL, 0, NULL, NULL, 'abc', NULL, '2024-05-23 22:03:52', NULL, '2024-05-23 22:03:52', NULL);
INSERT INTO `user` VALUES ('21311272', NULL, NULL, NULL, '666666', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES ('21311366', '吕杰', 'luik@mail2.sysu.edu.cn', NULL, 'D7C9F17B47F39B3994233F517323CF43', NULL, NULL, NULL, 10, 'minislother1', NULL, 'CF32C1BE-0710-44EF-B539-54B2927B5444', NULL, '2024-05-16 23:02:56', NULL, '2024-05-28 21:28:59', NULL);
INSERT INTO `user` VALUES ('21952107', NULL, NULL, NULL, '888888', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
