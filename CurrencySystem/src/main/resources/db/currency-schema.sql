CREATE TABLE IF NOT EXISTS `currency` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `rate` decimal(10,5) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;