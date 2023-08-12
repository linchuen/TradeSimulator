CREATE TABLE `user_stock_wallet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `stock_id` int(11) NOT NULL,
  `amount` decimal(20,5) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_stock_wallet_uk` (`account_id`,`stock_id`) USING BTREE
);