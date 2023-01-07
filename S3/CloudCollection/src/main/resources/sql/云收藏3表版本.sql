/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : c85-s3-ply-favorite

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2022-04-11 06:10:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `fid` int(11) NOT NULL auto_increment,
  `flabel` varchar(500) default NULL,
  `furl` varchar(500) default NULL,
  `fdesc` varchar(500) default NULL,
  `ftags` varchar(500) default NULL,
  PRIMARY KEY  (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------
INSERT INTO `favorite` VALUES ('4', '淘宝', 'taobao.com', '败家网站', '购物,生活');
INSERT INTO `favorite` VALUES ('5', '网易', '163.com', '好网站', '门户,军事,生活');
INSERT INTO `favorite` VALUES ('6', '百度', 'baidu.com', '搜索网站', '');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tid` int(50) NOT NULL auto_increment,
  `tname` varchar(50) default NULL,
  `tcount` int(11) default NULL,
  PRIMARY KEY  (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '购物', '1');
INSERT INTO `tag` VALUES ('2', '生活', '2');
INSERT INTO `tag` VALUES ('3', '门户', '1');
INSERT INTO `tag` VALUES ('4', '军事', '1');
INSERT INTO `tag` VALUES ('5', 'test', '1');

-- ----------------------------
-- Table structure for tagfavorite
-- ----------------------------
DROP TABLE IF EXISTS `tagfavorite`;
CREATE TABLE `tagfavorite` (
  `tid` int(11) NOT NULL,
  `fid` int(11) NOT NULL default '0',
  PRIMARY KEY  (`tid`,`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tagfavorite
-- ----------------------------
INSERT INTO `tagfavorite` VALUES ('1', '4');
INSERT INTO `tagfavorite` VALUES ('2', '4');
INSERT INTO `tagfavorite` VALUES ('2', '5');
INSERT INTO `tagfavorite` VALUES ('3', '5');
INSERT INTO `tagfavorite` VALUES ('4', '5');
INSERT INTO `tagfavorite` VALUES ('5', '6');
