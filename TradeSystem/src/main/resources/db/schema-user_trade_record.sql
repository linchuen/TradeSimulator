CREATE TABLE if not exists `user_trade_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_id` varchar(100) NOT NULL,
  `account_id` int(11) NOT NULL,
  `stock_id` int(11) DEFAULT NULL,
  `price` decimal(10,5) DEFAULT NULL,
  `stock_date` date DEFAULT NULL,
  `amount` decimal(20,5) DEFAULT NULL,
  `currency_id` int(5) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT 0,
  `err_msg` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_trade_record_uk` (`account_id`,`bill_id`) USING BTREE
);