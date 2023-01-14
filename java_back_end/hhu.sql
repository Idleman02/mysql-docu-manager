/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - cloud_class
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud_class` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `cloud_class`;

/*Table structure for table `oc_course` */

DROP TABLE IF EXISTS `oc_course`;

CREATE TABLE `oc_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id 主键',
  `course_title` varchar(128) DEFAULT NULL COMMENT '课程标题 课程标题',
  `course_subtitle` varchar(128) DEFAULT NULL COMMENT '课程副标题 课程副标题',
  `course_desc` varchar(512) DEFAULT NULL COMMENT '课程描述 课程描述',
  `course_detail` text COMMENT '详情 课程详情',
  `is_free` varchar(1) DEFAULT '0' COMMENT '是否免费',
  `course_price` decimal(32,8) DEFAULT NULL COMMENT '课程价格 课程价格',
  `is_discount` varchar(1) DEFAULT '0' COMMENT '是否打折 是否打折',
  `discount_desc` varchar(128) DEFAULT NULL COMMENT '打折表述 打折描述',
  `discount_price` decimal(32,8) DEFAULT NULL COMMENT '折后价 折后价',
  `course_level` varchar(32) DEFAULT NULL COMMENT '课程难度 课程难度，关联数据字典',
  `participations_count` int(11) DEFAULT '0' COMMENT '参与人数 参与人数',
  `course_cover` varchar(255) DEFAULT NULL COMMENT '课程封面 列表界面课程的展示，和文件表关联',
  `course_banner` int(11) DEFAULT NULL COMMENT '课程轮播图 banner图',
  `is_carousel` varchar(1) DEFAULT '0' COMMENT '是否轮播 是否进行轮播',
  `is_recommend` varchar(1) DEFAULT '0' COMMENT '是否精品推荐 是否精品推荐',
  `order_by` int(11) NOT NULL DEFAULT '200' COMMENT '排序字段 排序字段',
  `course_status` varchar(32) DEFAULT '0' COMMENT '课程状态 课程状态',
  `show_detail_img` varchar(1) DEFAULT '0' COMMENT '是否展示图片详情',
  `detail_img` int(11) DEFAULT NULL COMMENT '图片详情id',
  `enable` varchar(1) DEFAULT '1' COMMENT '是否启用 是否启用',
  `is_public` varchar(1) DEFAULT '1',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '是否删除 是否删除',
  `remark` varchar(512) DEFAULT NULL COMMENT 'remark 备注说明',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='课程';

/*Data for the table `oc_course` */

insert  into `oc_course`(`course_id`,`course_title`,`course_subtitle`,`course_desc`,`course_detail`,`is_free`,`course_price`,`is_discount`,`discount_desc`,`discount_price`,`course_level`,`participations_count`,`course_cover`,`course_banner`,`is_carousel`,`is_recommend`,`order_by`,`course_status`,`show_detail_img`,`detail_img`,`enable`,`is_public`,`del_flag`,`remark`,`REVISION`,`CREATED_BY`,`CREATED_TIME`,`UPDATED_BY`,`UPDATED_TIME`) values (116,'javaweb','javaweb','<p>javaweb</p>',NULL,'1','0.10000000','','javaweb','0.00000000',NULL,1,'https://edu-ambition.oss-cn-hangzhou.aliyuncs.com/2022/11/07/e0c7f2fd5dec4891b5a8accdde11ddc9微信图片_20200723195808.png',NULL,'0','1',0,'1','0',NULL,'1','0','0','javaweb',NULL,NULL,'2022-11-07 10:33:03',NULL,'2022-11-07 10:33:03');

/*Table structure for table `oc_course_section` */

DROP TABLE IF EXISTS `oc_course_section`;

CREATE TABLE `oc_course_section` (
  `section_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '章节id 章节id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id 课程',
  `section_name` varchar(128) DEFAULT NULL COMMENT '章节名称 章节名称',
  `section_type` varchar(32) DEFAULT '0' COMMENT '章节类型 章节类型0父章节1字章节',
  `section_video` int(11) DEFAULT NULL COMMENT '章节视频 章节视频关联文件表',
  `can_try` varchar(1) DEFAULT '0' COMMENT '试看，0不可以1可以默认0',
  `order_by` int(11) DEFAULT '200' COMMENT '章节序列 章节序列',
  `parent_id` int(11) DEFAULT NULL COMMENT '父章节id 父章节id',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注 备注',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 删除标志',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`section_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=789 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='课程章节表';

/*Data for the table `oc_course_section` */

insert  into `oc_course_section`(`section_id`,`course_id`,`section_name`,`section_type`,`section_video`,`can_try`,`order_by`,`parent_id`,`remark`,`del_flag`,`REVISION`,`CREATED_BY`,`CREATED_TIME`,`UPDATED_BY`,`UPDATED_TIME`) values (787,NULL,'python','0',NULL,'0',1,0,'python','0',NULL,NULL,'2022-11-07 10:34:57',NULL,'2022-11-07 10:34:57'),(788,NULL,'爬虫','1',NULL,'0',0,787,'爬虫','0',NULL,NULL,'2022-11-07 10:35:04',NULL,'2022-11-07 10:35:04');

/*Table structure for table `oc_subject` */

DROP TABLE IF EXISTS `oc_subject`;

CREATE TABLE `oc_subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 专题主键',
  `subject_title` varchar(128) DEFAULT NULL COMMENT '专题名称 专题名称',
  `subject_desc` varchar(1024) DEFAULT NULL COMMENT '专题描述 专题描述',
  `subject_banner` int(11) DEFAULT NULL COMMENT '专题轮播 专题的banner图',
  `order_by` int(11) DEFAULT NULL COMMENT '排序字段 排序字段',
  `subject_status` varchar(32) DEFAULT '0' COMMENT '状态 专题状态',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 删除标志',
  `remark` varchar(512) DEFAULT NULL COMMENT 'remark 备注说名',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `enable` int(11) DEFAULT '1' COMMENT '是否启用\r\n',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='专题表';

/*Data for the table `oc_subject` */

insert  into `oc_subject`(`subject_id`,`subject_title`,`subject_desc`,`subject_banner`,`order_by`,`subject_status`,`del_flag`,`remark`,`REVISION`,`CREATED_BY`,`CREATED_TIME`,`UPDATED_BY`,`UPDATED_TIME`,`enable`) values (17,'Java','Java',NULL,1,'0','0','Java',NULL,NULL,'2022-11-07 10:25:44',NULL,'2022-11-07 10:25:44',1);

/*Table structure for table `oc_subject_course` */

DROP TABLE IF EXISTS `oc_subject_course`;

CREATE TABLE `oc_subject_course` (
  `subject_id` int(11) NOT NULL COMMENT '专题id 专题id',
  `course_id` int(11) NOT NULL COMMENT '课程id 课程id',
  `del_flag` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`subject_id`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='关联关系表专题和课程';

/*Data for the table `oc_subject_course` */

insert  into `oc_subject_course`(`subject_id`,`course_id`,`del_flag`) values (17,116,'0');

/*Table structure for table `ucenter_member` */

DROP TABLE IF EXISTS `ucenter_member`;

CREATE TABLE `ucenter_member` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `ucenter_member` */

insert  into `ucenter_member`(`id`,`username`,`password`) values ('1','admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
