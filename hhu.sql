/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - lunwen_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lunwen_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `lunwen_manager`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '书名',
  `author` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `press` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '出版社',
  `price` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '价格',
  `gmt_create` date DEFAULT NULL,
  `gmt_modified` date DEFAULT NULL,
  `deleted` varchar(4) COLLATE utf8mb4_bin DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `book` */

insert  into `book`(`id`,`book_name`,`author`,`press`,`price`,`gmt_create`,`gmt_modified`,`deleted`) values (1,'java基础','哈哈哈','人民日报出版社','55','2022-11-27',NULL,'0'),(2,'java基础','王五','人民日报出版社','55','2022-11-27',NULL,'0'),(3,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(4,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(5,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(6,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(7,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(8,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(9,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(10,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(11,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(12,'java基础','张三','人民日报出版社','55','2022-11-27','2022-11-25','0'),(13,'python','赵六','背景色','99',NULL,NULL,'0'),(14,'大数据','张自力','北京市','77',NULL,NULL,'0'),(15,'大数据','大数据','大数据','55',NULL,NULL,'0');

/*Table structure for table `buyer` */

DROP TABLE IF EXISTS `buyer`;

CREATE TABLE `buyer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `u_id` int(11) DEFAULT NULL COMMENT '关联的用户id',
  `b_id` int(11) DEFAULT NULL COMMENT '关联的图书id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `buyer` */

insert  into `buyer`(`id`,`u_id`,`b_id`) values (1,1,15),(2,1,1),(3,1,2),(4,1,4),(5,1,3),(6,1,5),(7,1,6),(8,1,7),(9,1,8),(10,1,9),(11,1,10),(12,1,11),(13,1,12),(14,1,13),(15,1,14),(16,1,15);

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '论文id',
  `title` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '论文题目',
  `digest` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '论文摘要',
  `content` text COLLATE utf8mb4_bin COMMENT '论文题目',
  `author` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '论文作者',
  `gmt_create` date DEFAULT NULL COMMENT '发表时间',
  `gmt_modified` date DEFAULT NULL COMMENT '修改时间',
  `deleted` varchar(4) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `document` */

insert  into `document`(`id`,`title`,`digest`,`content`,`author`,`gmt_create`,`gmt_modified`,`deleted`) values (1,'Springboot 学习2','spring 厉害厉害厉害','你好你好XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX','李四','2022-11-28',NULL,'0'),(2,'python','python','pythonpythonpythonpythonpythonpython','python',NULL,NULL,'0'),(3,'java','java','javajavajavajava','java',NULL,NULL,'1');

/*Table structure for table `upload` */

DROP TABLE IF EXISTS `upload`;

CREATE TABLE `upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `u_id` int(11) DEFAULT NULL COMMENT '关联的用户id',
  `d_id` int(11) DEFAULT NULL COMMENT '关联的论文id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `upload` */

insert  into `upload`(`id`,`u_id`,`d_id`) values (1,1,1),(2,1,2),(3,1,3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_id` int(32) DEFAULT NULL,
  `username` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `deleted` varchar(4) COLLATE utf8mb4_bin DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`number_id`,`username`,`password`,`deleted`) values (1,20221005,'admin','admin','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
