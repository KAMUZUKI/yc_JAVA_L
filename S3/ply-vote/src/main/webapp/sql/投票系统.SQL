-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 47.106.66.89    Database: C0-S2-Ply-demo
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tp_items`
--

DROP TABLE IF EXISTS `tp_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号, 自增列',
  `iname` varchar(100) DEFAULT NULL COMMENT '选项内容',
  `vid` int(11) DEFAULT NULL COMMENT '题目编号, 关联 tp_vote.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='投票系统-题目选项表-9';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_items`
--

LOCK TABLES `tp_items` WRITE;
/*!40000 ALTER TABLE `tp_items` DISABLE KEYS */;
INSERT INTO `tp_items` VALUES (1,'Java',1),(2,'C',1),(3,'C++',1),(4,'JavaScript',1),(5,'.NET',1),(6,'猫',2),(7,'狗',2),(8,'鹦鹉',2),(9,'土拨鼠',2);
/*!40000 ALTER TABLE `tp_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_record`
--

DROP TABLE IF EXISTS `tp_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号, 自增列',
  `vid` int(11) DEFAULT NULL COMMENT '题目编号: 关联 tp_vote.id',
  `iid` int(11) DEFAULT NULL COMMENT '选项编号: 关联 tp_items.id',
  `usid` int(11) DEFAULT NULL COMMENT '用户表编号: 关联 tp_user.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='投票系统-投票记录表-6';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_record`
--

LOCK TABLES `tp_record` WRITE;
/*!40000 ALTER TABLE `tp_record` DISABLE KEYS */;
INSERT INTO `tp_record` VALUES (1,1,2,1),(2,1,3,2),(3,2,1,2),(4,1,4,3),(5,2,3,1),(6,2,2,4);
/*!40000 ALTER TABLE `tp_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_user`
--

DROP TABLE IF EXISTS `tp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号, 自增列',
  `uname` varchar(100) NOT NULL COMMENT '用户名',
  `pwd` varchar(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='投票系统-用户表-4';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_user`
--

LOCK TABLES `tp_user` WRITE;
/*!40000 ALTER TABLE `tp_user` DISABLE KEYS */;
INSERT INTO `tp_user` VALUES (1,'张三','123'),(2,'李四','234'),(3,'王五','345'),(4,'赵六','456');
/*!40000 ALTER TABLE `tp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_vote`
--

DROP TABLE IF EXISTS `tp_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号, 自增列',
  `vname` varchar(200) NOT NULL COMMENT '标题',
  `vtype` varchar(100) NOT NULL COMMENT '类型: 单选, 多选',
  `start_date` date DEFAULT NULL COMMENT '开始时间: 开始投票的时间',
  `end_date` date DEFAULT NULL COMMENT '结束时间: 投票结束的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `vname` (`vname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='投票系统-题目表-2';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_vote`
--

LOCK TABLES `tp_vote` WRITE;
/*!40000 ALTER TABLE `tp_vote` DISABLE KEYS */;
INSERT INTO `tp_vote` VALUES (1,'最佳编程语言','单选','2022-06-01','2022-06-03'),(2,'你心中最萌的动物','多选','2022-06-01','2022-06-03');
/*!40000 ALTER TABLE `tp_vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-25 12:31:40
