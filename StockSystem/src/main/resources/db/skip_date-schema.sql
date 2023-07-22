CREATE TABLE IF NOT EXISTS `skip_date` (
  `id` bigint(20) NOT NULL,
  `reason` varchar(20) NOT NULL,
  `skip_date` date DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`skip_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;