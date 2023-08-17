CREATE TABLE IF NOT EXISTS `skip_date` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `skip_date_uk` (`date`) USING BTREE
);