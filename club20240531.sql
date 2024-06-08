-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: club
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '活动编号',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名字',
  `organizer` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动负责人',
  `image` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动照片',
  `description` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动介绍',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动附件',
  `hot` int DEFAULT '0' COMMENT '当前热度',
  `arrange` varchar(100) DEFAULT NULL COMMENT '活动事项安排',
  `time` datetime DEFAULT NULL COMMENT '活动时间',
  `number` int DEFAULT NULL COMMENT '活动人数',
  `place` varchar(100) DEFAULT NULL COMMENT '活动地点',
  `type` int DEFAULT NULL COMMENT '活动类型',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '活动负责社团名字',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_accepted` tinyint(1) DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'对唱跳和篮球关系的研究','吕杰',NULL,NULL,NULL,0,'6667',NULL,0,NULL,0,'aaa',NULL,NULL,NULL,'2024-05-18 16:16:37',1);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '社团编号\n',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '社团名字',
  `leader` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '社团负责人',
  `image` varchar(1200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '社团照片',
  `description` varchar(700) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '社团介绍',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '社团附件',
  `hot` int DEFAULT '0' COMMENT '当前热度',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_accepted` tinyint(1) DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
INSERT INTO `group` VALUES (1,'足球社','B费','https://img1.baidu.com/it/u=2830895879,1858512659&fm=253&fmt=auto&app=138&f=JPEG?w=499&h=367','C罗siuuuuuu!',NULL,9,NULL,'2024-05-18 16:16:37',NULL,'2024-05-18 16:16:37',1),(2,'飞盘社','陈建铧','https://pic3.zhimg.com/v2-d44ffaccc42c2639b20bae7f980994aa_r.jpg','大神飞盘教学',NULL,2,NULL,NULL,NULL,NULL,1),(3,'文学社','贾宝玉','https://img1.baidu.com/it/u=4003599851,1156152734&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=982158c0ce50753b778bd348cd23c72f','红楼梦',NULL,0,NULL,NULL,NULL,NULL,1),(4,'说唱社','丁真','https://img1.baidu.com/it/u=3079149788,2510641134&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=b4fdd67eb0eb877a23df58dfe2dffca6','理塘丁真',NULL,0,NULL,NULL,NULL,NULL,1),(5,'唱跳社','坤坤','https://img2.baidu.com/it/u=4022135548,2767225744&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1685293200&t=c7fb5fb46e1d0af0a5cda28717085867','基尼太美',NULL,0,NULL,NULL,NULL,NULL,1),(6,'羽毛球社','hh','https://img.zcool.cn/community/01828f595df60ba8012193a35a606e.JPG@1280w_1l_2o_100sh.jpg','badminton',NULL,1,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individual_activity`
--

DROP TABLE IF EXISTS `individual_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `individual_activity` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户编号',
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户职位',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
  `status` int DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `activity_id` int DEFAULT NULL COMMENT '活动编号',
  `is_accepted` tinyint(1) DEFAULT NULL COMMENT '是否通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual_activity`
--

LOCK TABLES `individual_activity` WRITE;
/*!40000 ALTER TABLE `individual_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `individual_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `individual_group`
--

DROP TABLE IF EXISTS `individual_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `individual_group` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户编号',
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户职位',
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
  `status` int DEFAULT NULL COMMENT '权限级别',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `group_id` int DEFAULT NULL COMMENT '社团编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `individual_group`
--

LOCK TABLES `individual_group` WRITE;
/*!40000 ALTER TABLE `individual_group` DISABLE KEYS */;
INSERT INTO `individual_group` VALUES (1,'21311366',NULL,'吕杰',1,NULL,NULL,NULL,NULL,1),(2,'21311366',NULL,'吕杰',1,NULL,NULL,NULL,NULL,2),(3,'21311366',NULL,'吕杰',0,NULL,NULL,NULL,NULL,3),(4,'21311366',NULL,'吕杰',0,NULL,NULL,NULL,NULL,4);
/*!40000 ALTER TABLE `individual_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inspection`
--

DROP TABLE IF EXISTS `inspection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inspection` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '年检记录编号',
  `attachment` varchar(100) DEFAULT NULL COMMENT '年检附件',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '年检负责社团名字',
  `is_accepted` tinyint DEFAULT NULL COMMENT '是否通过',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `year` year DEFAULT NULL COMMENT '年份',
  `submitter_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '提交者编号',
  `feedback` varchar(700) DEFAULT NULL COMMENT '年检反馈',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inspection`
--

LOCK TABLES `inspection` WRITE;
/*!40000 ALTER TABLE `inspection` DISABLE KEYS */;
/*!40000 ALTER TABLE `inspection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stu_app`
--

DROP TABLE IF EXISTS `stu_app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stu_app` (
  `application_id` int NOT NULL AUTO_INCREMENT COMMENT '招聘信息ID',
  `group_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '社团ID',
  `stu_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学生姓名',
  `stu_number` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学生学号',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `gender` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `major` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '专业',
  `self_intro` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '自我陈述',
  `attachment` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '附件',
  `is_accepted` tinyint DEFAULT NULL COMMENT '是否通过',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`application_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stu_app`
--

LOCK TABLES `stu_app` WRITE;
/*!40000 ALTER TABLE `stu_app` DISABLE KEYS */;
INSERT INTO `stu_app` VALUES (1,'足球社','吴舜宇','21311272','18928622313','男','软件工程','你干嘛~','附件1',1,NULL,'2024-05-18 16:16:37',NULL,'2024-05-18 16:16:37'),(3,'足球社','陈建铧','21952107','15664736059','男','软件工程','siuuuuuuu','附件3',1,NULL,NULL,NULL,NULL),(4,'羽毛球社','龚敬','21311303','18022192013','男','软件工程','妙啊！','附件4',1,NULL,NULL,NULL,NULL),(5,'足球社','杨沛粤','21311296','15219368970','男','软件工程','太煎熬了。','附件5',1,NULL,NULL,NULL,NULL),(6,'篮球社','建华大神','21956666','1568978978','男','软件工程专业','我想加入','附件6',0,NULL,NULL,NULL,NULL),(13,'足球社','陈建铧','21311245','18899998874','男','人工智能学院','收拾收拾',NULL,0,NULL,NULL,NULL,NULL),(14,'足球社','大神','21311272','15898765494','男','微电子技术学院','哈哈哈',NULL,0,NULL,NULL,NULL,NULL),(15,'飞盘社','111','21311272','11111111111','男','人工智能学院','111',NULL,NULL,NULL,NULL,NULL,NULL),(16,'足球社','是是是','22222222','12222222222','男','人工智能学院','2222',NULL,0,NULL,NULL,NULL,NULL),(18,'足球社','大师','21318888','13823465987','男','人工智能学院','想来玩',NULL,0,NULL,NULL,NULL,NULL),(19,'足球社','吴舜宇','21311272','18928622313','男','测绘与遥感学院','想玩',NULL,NULL,NULL,NULL,NULL,NULL),(20,'足球社','陈建铧','21952107','12547854789','女','微电子技术学院','1111','123',NULL,NULL,NULL,NULL,NULL),(21,'飞盘社','陈建铧','21952107','15664736059','男','软件工程学院','test complete',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `stu_app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户编号',
  `user_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名字',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户密码',
  `campus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户校区',
  `major` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户专业',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户简介',
  `status` int DEFAULT NULL COMMENT '用户身份',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐值',
  `create_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `school` varchar(100) DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('12345678',NULL,'15888888888',NULL,'666666',NULL,NULL,NULL,1,NULL,NULL,'abc',NULL,'2024-05-17 22:51:24',NULL,'2024-05-17 22:51:24',NULL),('12345679',NULL,'15888888888',NULL,'666666',NULL,NULL,NULL,1,NULL,NULL,'abc',NULL,'2024-05-17 22:53:24',NULL,'2024-05-17 22:53:24',NULL),('12345689',NULL,'15888888888',NULL,'666666',NULL,NULL,NULL,0,NULL,NULL,'abc',NULL,'2024-05-23 22:03:52',NULL,'2024-05-23 22:03:52',NULL),('21311272',NULL,NULL,NULL,'666666',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('21311366','吕杰','luik@mail2.sysu.edu.cn',NULL,'D7C9F17B47F39B3994233F517323CF43',NULL,NULL,NULL,10,'minislother1',NULL,'CF32C1BE-0710-44EF-B539-54B2927B5444',NULL,'2024-05-16 23:02:56',NULL,'2024-05-28 21:28:59',NULL),('21952107',NULL,NULL,NULL,'888888',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31 21:30:29
