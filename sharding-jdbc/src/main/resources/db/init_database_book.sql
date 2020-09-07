-- 创建数据库
CREATE DATABASE `db0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `db2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- ----------------------------
-- Table structure for book_0
-- ----------------------------
DROP TABLE IF EXISTS `book_0`;
CREATE TABLE `book_0` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for book_1
-- ----------------------------
DROP TABLE IF EXISTS `book_1`;
CREATE TABLE `book_1` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
