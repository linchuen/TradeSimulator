CREATE TABLE IF NOT EXISTS `stock_trade_record` (
  `id` bigint(20) NOT NULL,
  `stockcode` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `trading_volume` varchar(20) NOT NULL,
  `transaction` decimal(10,5) NOT NULL,
  `opening_price` decimal(10,5) NOT NULL,
  `highest_price` decimal(10,5) NOT NULL,
  `lowest_price` decimal(10,5) NOT NULL,
  `closing_price` decimal(10,5) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`stockcode`,`date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;