-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.25a - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 check_work_db 的数据库结构
CREATE DATABASE IF NOT EXISTS `check_work_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;
USE `check_work_db`;

-- 导出  表 check_work_db.time_attendance 结构
CREATE TABLE IF NOT EXISTS `time_attendance` (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '签到用户id',
  `course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `course_code` varchar(30) DEFAULT NULL COMMENT '签到课程代码',
  `user_name` varchar(30) DEFAULT NULL COMMENT '签到用户姓名',
  `course_name` varchar(40) DEFAULT NULL COMMENT '签到课程名称',
  `attendance_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '签到时间',
  `attendance_status` varchar(40) DEFAULT NULL COMMENT '签到状态（0正常，1迟到，2请假，3旷课）',
  `attendance_code` varchar(40) DEFAULT NULL COMMENT '签到码',
  `attendance_addr` varchar(200) DEFAULT NULL COMMENT '签到地址',
  PRIMARY KEY (`attendance_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_attendance 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `time_attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_attendance` ENABLE KEYS */;

-- 导出  表 check_work_db.time_course 结构
CREATE TABLE IF NOT EXISTS `time_course` (
  `course_id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL COMMENT '任课用户id',
  `major_id` varchar(50) DEFAULT NULL COMMENT '所属专业id',
  `course_code` varchar(30) DEFAULT NULL COMMENT '课程代码',
  `user_name` varchar(30) DEFAULT NULL COMMENT '任课用户姓名',
  `major_name` varchar(30) DEFAULT NULL COMMENT '所属专业名称',
  `course_name` varchar(40) DEFAULT NULL COMMENT '课程名称',
  `course_start` timestamp NULL DEFAULT NULL COMMENT '课程开始时间',
  `course_end` timestamp NULL DEFAULT NULL COMMENT '课程结束时间',
  `attendance_code` varchar(40) DEFAULT NULL COMMENT '签到码',
  `course_addr` varchar(200) DEFAULT NULL COMMENT '上课地址',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_course 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `time_course` DISABLE KEYS */;
REPLACE INTO `time_course` (`course_id`, `user_id`, `major_id`, `course_code`, `user_name`, `major_name`, `course_name`, `course_start`, `course_end`, `attendance_code`, `course_addr`) VALUES
	('1225597287745589248', '1233434', '1224935473508651008', 'qq', 'admin', '移动应用开发', 'qq', '2020-02-07 09:48:50', '2020-02-07 09:48:53', 'qwqw', 'qwqw');
/*!40000 ALTER TABLE `time_course` ENABLE KEYS */;

-- 导出  表 check_work_db.time_major 结构
CREATE TABLE IF NOT EXISTS `time_major` (
  `major_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '专业id',
  `major_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '专业名称',
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='专业管理';

-- 正在导出表  check_work_db.time_major 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `time_major` DISABLE KEYS */;
REPLACE INTO `time_major` (`major_id`, `major_name`) VALUES
	('1224935436628135936', '计算机'),
	('1224935473508651008', '移动应用开发');
/*!40000 ALTER TABLE `time_major` ENABLE KEYS */;

-- 导出  表 check_work_db.time_questions 结构
CREATE TABLE IF NOT EXISTS `time_questions` (
  `questions_id` varchar(50) NOT NULL,
  `major_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '所属专业id',
  `course_id` varchar(50) DEFAULT NULL COMMENT '课程id',
  `course_code` varchar(30) DEFAULT NULL COMMENT '课程代码',
  `course_name` varchar(40) DEFAULT NULL COMMENT '课程名称',
  `questions_title` varchar(30) DEFAULT NULL COMMENT '随堂提问题目标题',
  `questions_type` varchar(30) DEFAULT NULL COMMENT '随堂提问类型（0单选择题，1多选择题，2判断题，3简答题）',
  `questions_a` varchar(30) DEFAULT NULL COMMENT '随堂提问题目A',
  `questions_b` varchar(30) DEFAULT NULL COMMENT '随堂提问题目B',
  `questions_c` varchar(30) DEFAULT NULL COMMENT '随堂提问题目C',
  `questions_d` varchar(30) DEFAULT NULL COMMENT '随堂提问题目D',
  `questions_content` varchar(30) DEFAULT NULL COMMENT '随堂提问简答题答案',
  `questions_answer` varchar(30) DEFAULT NULL COMMENT '随堂提问选择题或判断题答案',
  PRIMARY KEY (`questions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_questions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `time_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_questions` ENABLE KEYS */;

-- 导出  表 check_work_db.time_user 结构
CREATE TABLE IF NOT EXISTS `time_user` (
  `user_id` varchar(50) NOT NULL,
  `user_type` varchar(50) DEFAULT NULL COMMENT '(用户类型0学生，1老师，2管理员)',
  `user_code` varchar(30) DEFAULT NULL COMMENT '学号',
  `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `user_password` varchar(200) DEFAULT NULL COMMENT '密码',
  `user_pic` varchar(200) DEFAULT NULL COMMENT '头像',
  `major_id` varchar(200) DEFAULT NULL COMMENT '专业id',
  `user_major` varchar(40) DEFAULT NULL COMMENT '专业',
  `user_mark` varchar(200) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `time_user` DISABLE KEYS */;
REPLACE INTO `time_user` (`user_id`, `user_type`, `user_code`, `user_name`, `user_password`, `user_pic`, `major_id`, `user_major`, `user_mark`) VALUES
	('1224319047332663296', '0', '111111', 'admin', '$2a$10$wr6A482JiCEi4e3QrA5D7OjfjX2eNV9.LM8Q.fTRb7rV/CSrH/vMO', '../../../static/images/header.png', '1224935436628135936', NULL, '11111'),
	('1233434', '1', '111111', 'admin', '$2a$10$wr6A482JiCEi4e3QrA5D7OjfjX2eNV9.LM8Q.fTRb7rV/CSrH/vMO', '../../../static/images/header.png', '1224935436628135936', NULL, '11111');
/*!40000 ALTER TABLE `time_user` ENABLE KEYS */;

-- 导出  表 check_work_db.time_user_course 结构
CREATE TABLE IF NOT EXISTS `time_user_course` (
  `USER_COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '学生id',
  `COURSE_ID` int(11) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`USER_COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_user_course 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `time_user_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_user_course` ENABLE KEYS */;

-- 导出  表 check_work_db.time_user_questions 结构
CREATE TABLE IF NOT EXISTS `time_user_questions` (
  `USER_QUESTIONS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL COMMENT '学生id',
  `QUESTIONS_ID` int(11) DEFAULT NULL COMMENT '随堂提问id',
  `QUESTIONS_TITLE` varchar(30) DEFAULT NULL COMMENT '随堂提问题目标题',
  `QUESTIONS_ANSWER` int(11) DEFAULT NULL COMMENT '随堂提问回答答案',
  PRIMARY KEY (`USER_QUESTIONS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  check_work_db.time_user_questions 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `time_user_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_user_questions` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
