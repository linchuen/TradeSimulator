CREATE TABLE IF NOT EXISTS `skip_date` (
  `id` bigint(20) NOT NULL,
  `reason` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;