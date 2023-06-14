CREATE DATABASE lottoDB;

USE lottoDB;

-- lottoDB.`change` definition

CREATE TABLE `change` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `denomination` int NOT NULL,
  `quantity` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK93agc3cju4sdip5uj5evf6nld` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lottoDB.lotto definition

CREATE TABLE `lotto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `max_ball_number` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num_balls_to_draw` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lottoDB.lotto_ticket definition

CREATE TABLE `lotto_ticket` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `lotto_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK681x10h59r3ap6gvjsl4pyrga` (`lotto_id`),
  KEY `FKi3dsmspbtw1wlsv7ijgbftcv6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lottoDB.lotto_ticket_result definition

CREATE TABLE `lotto_ticket_result` (
  `lotto_ticket_id` bigint NOT NULL,
  `result` int DEFAULT NULL,
  KEY `FKfhxd8rf9urjureu6q05esjss1` (`lotto_ticket_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lottoDB.lotto_ticket_selections definition

CREATE TABLE `lotto_ticket_selections` (
  `lotto_ticket_id` bigint NOT NULL,
  `selections` int DEFAULT NULL,
  KEY `FK19jxlx2swr1hwutghronv0fu7` (`lotto_ticket_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- lottoDB.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

