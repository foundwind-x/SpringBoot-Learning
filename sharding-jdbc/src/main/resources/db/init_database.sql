-- 创建数据库
CREATE DATABASE `db0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE `db2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 订单表
CREATE TABLE IF NOT EXISTS t_order_0 (
  order_id BIGINT NOT NULL,
  user_id INT NOT NULL,
  address_id BIGINT NOT NULL,
  status VARCHAR(50),
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_1 (
  order_id BIGINT NOT NULL,
  user_id INT NOT NULL,
  address_id BIGINT NOT NULL,
  status VARCHAR(50),
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 订单项
CREATE TABLE IF NOT EXISTS t_order_item_0 (
  order_item_id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  user_id INT NOT NULL,
  price DOUBLE(10,1) DEFAULT NULL,
  status VARCHAR(50),
  PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_item_1 (
  order_item_id BIGINT NOT NULL AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  user_id INT NOT NULL,
  price DOUBLE(10,1) DEFAULT NULL,
  status VARCHAR(50),
  PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;