/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : miniheadline

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-04 22:51:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `from_uid` int(11) DEFAULT NULL,
  `reply_num` int(11) DEFAULT NULL,
  `like_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('2', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('3', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('4', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('5', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('6', '这是一条评论', '2019-01-01', '1', '0', '0');
INSERT INTO `comments` VALUES ('7', '这是一条评论', '2019-01-01', '1', '0', '0');

-- ----------------------------
-- Table structure for commentstocomments
-- ----------------------------
DROP TABLE IF EXISTS `commentstocomments`;
CREATE TABLE `commentstocomments` (
  `ctcid` int(11) NOT NULL AUTO_INCREMENT,
  `sub_cid` int(11) DEFAULT NULL,
  `obj_cid` int(11) DEFAULT NULL,
  `reply_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ctcid`),
  KEY `sub_cid` (`sub_cid`),
  KEY `obj_cid` (`obj_cid`),
  KEY `reply_uid` (`reply_uid`),
  CONSTRAINT `commentstocomments_ibfk_1` FOREIGN KEY (`sub_cid`) REFERENCES `comments` (`cid`),
  CONSTRAINT `commentstocomments_ibfk_2` FOREIGN KEY (`obj_cid`) REFERENCES `comments` (`cid`),
  CONSTRAINT `commentstocomments_ibfk_3` FOREIGN KEY (`reply_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentstocomments
-- ----------------------------
INSERT INTO `commentstocomments` VALUES ('1', '2', '1', '2');
INSERT INTO `commentstocomments` VALUES ('2', '2', '1', '2');
INSERT INTO `commentstocomments` VALUES ('3', '2', '1', '2');
INSERT INTO `commentstocomments` VALUES ('4', '2', '1', '2');
INSERT INTO `commentstocomments` VALUES ('5', '2', '1', '2');

-- ----------------------------
-- Table structure for commentstonews
-- ----------------------------
DROP TABLE IF EXISTS `commentstonews`;
CREATE TABLE `commentstonews` (
  `ctnid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `nid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ctnid`),
  KEY `cid` (`cid`),
  KEY `nid` (`nid`),
  CONSTRAINT `commentstonews_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `comments` (`cid`),
  CONSTRAINT `commentstonews_ibfk_2` FOREIGN KEY (`nid`) REFERENCES `news` (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentstonews
-- ----------------------------
INSERT INTO `commentstonews` VALUES ('1', '2', '2');
INSERT INTO `commentstonews` VALUES ('2', '2', '2');
INSERT INTO `commentstonews` VALUES ('3', '2', '2');
INSERT INTO `commentstonews` VALUES ('4', '2', '2');
INSERT INTO `commentstonews` VALUES ('5', '2', '2');

-- ----------------------------
-- Table structure for commentstovideos
-- ----------------------------
DROP TABLE IF EXISTS `commentstovideos`;
CREATE TABLE `commentstovideos` (
  `ctvid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  PRIMARY KEY (`ctvid`),
  KEY `cid` (`cid`),
  KEY `vid` (`vid`),
  CONSTRAINT `commentstovideos_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `comments` (`cid`),
  CONSTRAINT `commentstovideos_ibfk_2` FOREIGN KEY (`vid`) REFERENCES `videos` (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentstovideos
-- ----------------------------
INSERT INTO `commentstovideos` VALUES ('1', '2', '2');
INSERT INTO `commentstovideos` VALUES ('2', '2', '2');
INSERT INTO `commentstovideos` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES ('1', 'www.baidu.com');
INSERT INTO `news` VALUES ('2', 'www.baidu.com');
INSERT INTO `news` VALUES ('3', 'www.baidu.com');
INSERT INTO `news` VALUES ('4', 'www.baidu.com');
INSERT INTO `news` VALUES ('5', 'www.baidu.com');
INSERT INTO `news` VALUES ('6', 'www.baidu.com');
INSERT INTO `news` VALUES ('7', 'www.baidu.com');
INSERT INTO `news` VALUES ('8', 'www.baidu.com');
INSERT INTO `news` VALUES ('9', 'www.baidu.com');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `AGE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'Zara', '11');
INSERT INTO `student` VALUES ('2', 'Nuha', '20');
INSERT INTO `student` VALUES ('3', 'Ayan', '15');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('2', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('3', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('4', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('5', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('6', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('7', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('8', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('9', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('10', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');
INSERT INTO `users` VALUES ('11', '蔡倓', '123456', 'urlxxx', '本地测试', '1998-12-11', '内环东路');

-- ----------------------------
-- Table structure for userstocomments
-- ----------------------------
DROP TABLE IF EXISTS `userstocomments`;
CREATE TABLE `userstocomments` (
  `utcid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`utcid`),
  KEY `cid` (`cid`),
  KEY `uid` (`uid`),
  CONSTRAINT `userstocomments_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `comments` (`cid`),
  CONSTRAINT `userstocomments_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userstocomments
-- ----------------------------
INSERT INTO `userstocomments` VALUES ('1', '2', '2');
INSERT INTO `userstocomments` VALUES ('2', '2', '2');
INSERT INTO `userstocomments` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for userstonews
-- ----------------------------
DROP TABLE IF EXISTS `userstonews`;
CREATE TABLE `userstonews` (
  `utnid` int(11) NOT NULL AUTO_INCREMENT,
  `nid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `rel_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`utnid`),
  KEY `nid` (`nid`),
  KEY `uid` (`uid`),
  KEY `cid` (`cid`),
  CONSTRAINT `userstonews_ibfk_1` FOREIGN KEY (`nid`) REFERENCES `news` (`nid`),
  CONSTRAINT `userstonews_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `userstonews_ibfk_3` FOREIGN KEY (`cid`) REFERENCES `comments` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userstonews
-- ----------------------------
INSERT INTO `userstonews` VALUES ('1', '2', '3', '2', '2');
INSERT INTO `userstonews` VALUES ('2', '2', '3', '2', '2');
INSERT INTO `userstonews` VALUES ('3', '2', '3', '2', '2');

-- ----------------------------
-- Table structure for userstousers
-- ----------------------------
DROP TABLE IF EXISTS `userstousers`;
CREATE TABLE `userstousers` (
  `utuid` int(11) NOT NULL AUTO_INCREMENT,
  `from_uid` int(11) DEFAULT NULL,
  `to_uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`utuid`),
  KEY `from_uid` (`from_uid`),
  KEY `to_uid` (`to_uid`),
  CONSTRAINT `userstousers_ibfk_1` FOREIGN KEY (`from_uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `userstousers_ibfk_2` FOREIGN KEY (`to_uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userstousers
-- ----------------------------
INSERT INTO `userstousers` VALUES ('3', '2', '2');
INSERT INTO `userstousers` VALUES ('4', '2', '2');
INSERT INTO `userstousers` VALUES ('5', '2', '2');

-- ----------------------------
-- Table structure for userstovideos
-- ----------------------------
DROP TABLE IF EXISTS `userstovideos`;
CREATE TABLE `userstovideos` (
  `utvid` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `rel_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`utvid`),
  KEY `vid` (`vid`),
  KEY `uid` (`uid`),
  KEY `cid` (`cid`),
  CONSTRAINT `userstovideos_ibfk_1` FOREIGN KEY (`vid`) REFERENCES `videos` (`vid`),
  CONSTRAINT `userstovideos_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`),
  CONSTRAINT `userstovideos_ibfk_3` FOREIGN KEY (`cid`) REFERENCES `comments` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userstovideos
-- ----------------------------
INSERT INTO `userstovideos` VALUES ('1', '2', '2', '2', '2');
INSERT INTO `userstovideos` VALUES ('2', '2', '2', '2', '2');
INSERT INTO `userstovideos` VALUES ('3', '2', '2', '2', '2');

-- ----------------------------
-- Table structure for videos
-- ----------------------------
DROP TABLE IF EXISTS `videos`;
CREATE TABLE `videos` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of videos
-- ----------------------------
INSERT INTO `videos` VALUES ('1', 'www.baidu.com');
INSERT INTO `videos` VALUES ('2', 'www.baidu.com');
INSERT INTO `videos` VALUES ('3', 'www.baidu.com');
INSERT INTO `videos` VALUES ('4', 'www.baidu.com');
INSERT INTO `videos` VALUES ('5', 'www.baidu.com');
INSERT INTO `videos` VALUES ('6', 'www.baidu.com');
INSERT INTO `videos` VALUES ('7', 'www.baidu.com');
INSERT INTO `videos` VALUES ('8', 'www.baidu.com');
INSERT INTO `videos` VALUES ('9', 'www.baidu.com');
