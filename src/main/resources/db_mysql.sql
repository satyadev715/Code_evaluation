CREATE TABLE `customer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `active` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`));

INSERT INTO `customer` VALUES
(1,1,'Big News Media Corp'),
(2,1,'Online Mega Store'),
(3,0,'Nachoroo Delivery'),
(4,1,'Euro Telecom Group');

CREATE TABLE `ip_blacklist` (
  `ip` int unsigned NOT NULL,
  PRIMARY KEY (`ip`)
);

INSERT INTO `ip_blacklist` VALUES (INET_ATON("213.070.64.33"));
INSERT INTO `ip_blacklist` VALUES (INET_ATON("0"));

CREATE TABLE `ua_blacklist` (
  `ua` varchar(255) NOT NULL,
  PRIMARY KEY (`ua`)
);

INSERT INTO `ua_blacklist` VALUES ('A6-Indexer'),('Googlebot-News'),('Googlebot');

CREATE TABLE `request_data`
( `request_id` int(11)  NOT NULL AUTO_INCREMENT,
   `request_payload` varchar(1000) not null ,
  `customer_id`  int(11) not null ,
  `request_time` timestamp not null ,
   `status` char(1) not null default 'Y',
 constraint `request_data_pk` primary key(`request_id`)
);