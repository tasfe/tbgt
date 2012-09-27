/*
MySQL Data Transfer
Source Host: localhost
Source Database: tbgt
Target Host: localhost
Target Database: tbgt
Date: 2012-9-25 15:45:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for baobei
-- ----------------------------
CREATE TABLE `baobei` (
  `id` int(5) NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `taobaoId` varchar(250) default NULL,
  `saleTitle` varchar(250) default NULL,
  `incomingTime` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for express
-- ----------------------------
CREATE TABLE `express` (
  `id` int(5) NOT NULL auto_increment,
  `orderId` int(5) default NULL,
  `fee` decimal(7,2) default NULL,
  `sendTime` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_order` (`orderId`),
  CONSTRAINT `FK_order` FOREIGN KEY (`orderId`) REFERENCES `order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
CREATE TABLE `orders` (
  `id` int(5) NOT NULL auto_increment,
  `orderNo` varchar(20) default NULL,
  `address` varchar(200) default NULL,
  `contactPerson` varchar(200) default NULL,
  `phone` varchar(50) default NULL,
  `actualPrice` decimal(7,2) default NULL,
  `soldTime` date default NULL,
  `deliverTime` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for price
-- ----------------------------
CREATE TABLE `price` (
  `id` int(5) NOT NULL auto_increment,
  `baobeiId` int(5) NOT NULL,
  `purchasePrice` decimal(7,2) default NULL,
  `recommendedPrice` decimal(7,2) default NULL,
  `salePrice` decimal(7,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_baobei` (`baobeiId`),
  CONSTRAINT `FK_baobei` FOREIGN KEY (`baobeiId`) REFERENCES `baobei` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for soldbaobei
-- ----------------------------
CREATE TABLE `soldbaobei` (
  `id` int(5) NOT NULL auto_increment,
  `orderId` int(5) default NULL,
  `baobeiId` int(5) default NULL,
  `quantity` int(5) default NULL,
  `color` varchar(10) default NULL,
  `spec` varchar(20) default NULL,
  `name` varchar(200) default NULL,
  `purchasePrice` decimal(7,2) default NULL,
  `salePrice` decimal(7,2) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_soldbaobei` (`baobeiId`),
  KEY `FK_soldorder` (`orderId`),
  CONSTRAINT `FK_soldorder` FOREIGN KEY (`orderId`) REFERENCES `order` (`id`),
  CONSTRAINT `FK_soldbaobei` FOREIGN KEY (`baobeiId`) REFERENCES `baobei` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



