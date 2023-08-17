CREATE TABLE IF NOT EXISTS `stock_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stockcode` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `ISIN_code` varchar(20) DEFAULT NULL,
  `publish_date` date DEFAULT NULL,
  `market_type` varchar(20) DEFAULT NULL,
  `industry_type` varchar(20) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stock_info_uk` (`stockcode`,`market_type`,`industry_type`) USING BTREE
);