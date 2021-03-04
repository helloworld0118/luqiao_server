CREATE TABLE `luqiao`.`module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `remark` varchar(100) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `luqiao`.`enterprise`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(50) NULL,
  `password` varchar(50) NULL,
  `name` varchar(50) NULL,
  `legal_person_idcard` varchar(20) NULL,
  `mobile` varchar(20) NULL,
  `create_date` varchar(20) NULL,
  `tip_date` int(5) NULL,
  `code` varchar(20) NULL,
  `web_name` varchar(20) NULL,
  `database_name` varchar(20) NULL,
  `remark` varchar(100) NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `luqiao`.`module_meal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) NOT NULL,
  `project_max_num` int(11) NULL,
  `staff_max_num` int(11) NULL,
  `price` int(11) NULL,
  `remark` varchar(100) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id` FOREIGN KEY (`module`) REFERENCES `luqiao`.`module` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `luqiao`.`recharge`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` int(11) NULL,
  `meal` int(11) NULL,
  `account` int(11) NULL,
  `date` varchar(20) NULL,
  `remark` varchar(100) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pk_enterprise` FOREIGN KEY (`enterprise`) REFERENCES `luqiao`.`enterprise` (`id`),
  CONSTRAINT `pk_meal` FOREIGN KEY (`meal`) REFERENCES `luqiao`.`module_meal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `luqiao`.`enterprise_meal`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise` int(11) NULL,
  `meal` int(11) NULL,
  `expiry_date` int(11) NULL,
  `state` int(1) NULL COMMENT '0 正常 1提醒 2欠费',
  PRIMARY KEY (`id`),
  CONSTRAINT `pk_e_enterprise` FOREIGN KEY (`enterprise`) REFERENCES `luqiao`.`enterprise` (`id`),
  CONSTRAINT `pk_m_meal` FOREIGN KEY (`meal`) REFERENCES `luqiao`.`module_meal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;