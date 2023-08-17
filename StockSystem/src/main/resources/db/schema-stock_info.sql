CREATE TABLE IF NOT EXISTS `stock_info` (
  `id` bigint(20) NOT NULL,
  `stockcode` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `ISIN_code` varchar(20) DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `market_type` varchar(20) DEFAULT NULL,
  `industry_type` varchar(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stock_info_stockcode_IDX` (`stockcode`,`market_type`,`industry_type`) USING BTREE
);