
/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL COMMENT '0 男 1女',
  `education` varchar(20) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `hiredate` varchar(20) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 内部 1外聘',
  `state` int(1) DEFAULT NULL COMMENT '0 在职 1离职',
  `department` int(11) DEFAULT NULL,
  `leader` int(11) DEFAULT NULL COMMENT '默认为0 如果是工人，则为工头id',
  `create_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `start_node` int(11) DEFAULT NULL,
  `end_node` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `state` int(1) DEFAULT NULL COMMENT '0  可用 1禁用',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 PC 1APP',
  `url` varchar(50) DEFAULT NULL,
  `icon` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;


/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `menu` int(11) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_r_m_role` (`role`),
  KEY `pk_r_m_menu` (`menu`),
  CONSTRAINT `pk_r_m_role` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `pk_r_m_menu` FOREIGN KEY (`menu`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;


/*Table structure for table `base_price_type` */

DROP TABLE IF EXISTS `base_price_type`;

CREATE TABLE `base_price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '0 人 1机 2小车 3拉料车',
  `order_num` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Table structure for table `base_type` */

DROP TABLE IF EXISTS `base_type`;

CREATE TABLE `base_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;



/*Table structure for table `price_fluctuation` */

DROP TABLE IF EXISTS `price_fluctuation`;

CREATE TABLE `price_fluctuation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 人 1料 2机',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8;


/*Table structure for table `unit_type_combination` */

DROP TABLE IF EXISTS `unit_type_combination`;

CREATE TABLE `unit_type_combination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_type` int(11) DEFAULT NULL,
  `material_spec` int(11) DEFAULT NULL,
  `base_price_type` int(11) DEFAULT NULL,
  `material_type` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=282 DEFAULT CHARSET=utf8;

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


/*Table structure for table `day_book` */

DROP TABLE IF EXISTS `day_book`;

CREATE TABLE `day_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `voucherno` varchar(20) DEFAULT NULL,
  `income` int(11) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `income_type` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `pay` int(11) DEFAULT NULL,
  `pay_for` varchar(100) DEFAULT NULL,
  `presenter` varchar(20) DEFAULT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `bill_type` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `expense` */

DROP TABLE IF EXISTS `expense`;

CREATE TABLE `expense` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `person` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `bill_type` int(11) DEFAULT NULL,
  `price_type` int(11) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `presenter_name` varchar(20) DEFAULT NULL,
  `presenter` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `foreman` */

DROP TABLE IF EXISTS `foreman`;

CREATE TABLE `foreman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0 工头 1小车 2机械  车主',
  `password` varchar(50) DEFAULT NULL,
  `idcard` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Table structure for table `gaibanhan` */

DROP TABLE IF EXISTS `gaibanhan`;

CREATE TABLE `gaibanhan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `node` int(11) DEFAULT NULL,
  `childNode` int(11) DEFAULT NULL,
  `col2` varchar(20) DEFAULT NULL,
  `col3` varchar(20) DEFAULT NULL,
  `col4` varchar(20) DEFAULT NULL,
  `col5` varchar(100) DEFAULT NULL,
  `col6` varchar(20) DEFAULT NULL,
  `col7` varchar(20) DEFAULT NULL,
  `col8` varchar(20) DEFAULT NULL,
  `col9` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `labor_cost` */

DROP TABLE IF EXISTS `labor_cost`;

CREATE TABLE `labor_cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `program` varchar(20) DEFAULT NULL,
  `node` varchar(20) DEFAULT NULL,
  `iprocedure` varchar(20) DEFAULT NULL,
  `foreman` int(11) DEFAULT NULL,
  `foreman_name` varchar(20) DEFAULT NULL,
  `worker_type` varchar(20) DEFAULT NULL,
  `worker` int(11) DEFAULT NULL,
  `icount` int(11) DEFAULT NULL,
  `base_type` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_price_type` varchar(20) DEFAULT NULL,
  `worker_nums` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `staff` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `loan_info` */

DROP TABLE IF EXISTS `loan_info`;

CREATE TABLE `loan_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `person` varchar(20) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `department` int(11) DEFAULT NULL,
  `presenter` varchar(20) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_price_type` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `update_date` varchar(20) DEFAULT NULL,
  `m_address` varchar(50) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 单规格 1多规格',
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_m_p_project` (`project`),
  KEY `pk_m_p_price` (`unit_price`),
  KEY `pk_m_p_ptype` (`unit_price_type`),
  CONSTRAINT `pk_m_p_project` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Table structure for table `material_received` */

DROP TABLE IF EXISTS `material_received`;

CREATE TABLE `material_received` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `node` varchar(20) DEFAULT NULL,
  `program` varchar(20) DEFAULT NULL,
  `iprocedure` varchar(20) DEFAULT NULL,
  `mechainc` int(11) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `supplier_name` varchar(20) DEFAULT NULL,
  `material` int(11) DEFAULT NULL,
  `plate_number` varchar(20) DEFAULT NULL,
  `icount` int(11) DEFAULT NULL,
  `base_type` varchar(20) DEFAULT NULL,
  `material_spec` varchar(20) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `material_price` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `material_name` varchar(20) DEFAULT NULL,
  `unit_freight_price` int(11) DEFAULT NULL,
  `freight_price` int(11) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `staff` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


/*Table structure for table `material_type` */

DROP TABLE IF EXISTS `material_type`;

CREATE TABLE `material_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_name` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `mt_order_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;


/*Table structure for table `material_spec` */

DROP TABLE IF EXISTS `material_spec`;

CREATE TABLE `material_spec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ms_name` varchar(20) DEFAULT NULL,
  `ms_order_num` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `material_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_m_m_type` (`material_type`),
  CONSTRAINT `pk_m_m_type` FOREIGN KEY (`material_type`) REFERENCES `material_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;


/*Table structure for table `mechanics` */

DROP TABLE IF EXISTS `mechanics`;

CREATE TABLE `mechanics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `m_name` varchar(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `plate_number` varchar(20) DEFAULT NULL,
  `model` varchar(20) DEFAULT NULL,
  `capacity` varchar(20) DEFAULT NULL,
  `driver_idcard` varchar(20) DEFAULT NULL,
  `driver_name` varchar(20) DEFAULT NULL,
  `driver_mobile` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `m_type` int(1) DEFAULT NULL COMMENT '0 小车 1机械 2拉料车',
  `update_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `mechanics_price` */

DROP TABLE IF EXISTS `mechanics_price`;

CREATE TABLE `mechanics_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `program` varchar(20) DEFAULT NULL,
  `node` varchar(20) DEFAULT NULL,
  `iprocedure` varchar(20) DEFAULT NULL,
  `mechanic` int(11) DEFAULT NULL,
  `mechanic_name` varchar(20) DEFAULT NULL,
  `driver_name` varchar(20) DEFAULT NULL,
  `plate_number` varchar(20) DEFAULT NULL,
  `base_type` varchar(20) DEFAULT NULL,
  `icount` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_price_type` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `staff` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `mechanics_project_owner` */

DROP TABLE IF EXISTS `mechanics_project_owner`;

CREATE TABLE `mechanics_project_owner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `mechanics` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 小车 1机械',
  `owner` int(11) DEFAULT NULL,
  `unit_price_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_p_f_owner` (`owner`),
  KEY `pk_m_p_up` (`unit_price`),
  KEY `pk_m_p_mechanics` (`mechanics`),
  KEY `pk_m_p_upt` (`unit_price_type`),
  CONSTRAINT `pk_m_p_up` FOREIGN KEY (`unit_price`) REFERENCES `price_fluctuation` (`id`),
  CONSTRAINT `pk_m_p_upt` FOREIGN KEY (`unit_price_type`) REFERENCES `unit_type_combination` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


/*Table structure for table `oil` */

DROP TABLE IF EXISTS `oil`;

CREATE TABLE `oil` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `mechanic_name` varchar(20) DEFAULT NULL,
  `mechanic` int(11) DEFAULT NULL,
  `icount` int(11) DEFAULT NULL,
  `plate_number` varchar(20) DEFAULT NULL,
  `driver_name` varchar(20) DEFAULT NULL,
  `oil_type` varchar(20) DEFAULT NULL,
  `supplier` int(11) DEFAULT NULL,
  `supplier_name` varchar(20) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `staff` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `person_account` */

DROP TABLE IF EXISTS `person_account`;

CREATE TABLE `person_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `type` int(1) DEFAULT NULL COMMENT '0 员工 1车主or工头',
  `loan` int(11) DEFAULT NULL,
  `income` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*Table structure for table `project_foreman` */

DROP TABLE IF EXISTS `project_foreman`;

CREATE TABLE `project_foreman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foreman` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_p_f_staff` (`foreman`),
  KEY `pk_p_f_project` (`project`),
  CONSTRAINT `pk_p_f_staff` FOREIGN KEY (`foreman`) REFERENCES `foreman` (`id`),
  CONSTRAINT `pk_p_f_project` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `project_staff_price` */

DROP TABLE IF EXISTS `project_staff_price`;

CREATE TABLE `project_staff_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_price_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_p_s_up` (`unit_price`),
  KEY `pk_p_s_upt` (`unit_price_type`),
  KEY `pk_p_s_staff` (`staff`),
  KEY `pk_p_s_project` (`project`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Table structure for table `project_staff_role` */

DROP TABLE IF EXISTS `project_staff_role`;

CREATE TABLE `project_staff_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_s_r_project` (`project`),
  KEY `pk_s_r_staff` (`staff`),
  KEY `pk_s_r_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;





/*Table structure for table `project_supplier` */

DROP TABLE IF EXISTS `project_supplier`;

CREATE TABLE `project_supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supplier` int(11) DEFAULT NULL,
  `project` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_p_s_supplier` (`supplier`),
  KEY `pk_project_supplier_project` (`project`),
  CONSTRAINT `pk_p_s_supplier` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`id`),
  CONSTRAINT `pk_project_supplier_project` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


/*Table structure for table `skip_material_price` */

DROP TABLE IF EXISTS `skip_material_price`;

CREATE TABLE `skip_material_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `foreman` int(11) DEFAULT NULL,
  `mechainc` int(11) DEFAULT NULL,
  `material_type` int(11) DEFAULT NULL,
  `base_price_type` int(11) DEFAULT NULL,
  `create_date`` VARCHAR(20) NULL,
  `unit_price` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;



/*Table structure for table `token_info` */

DROP TABLE IF EXISTS `token_info`;

CREATE TABLE `token_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staff` int(11) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


/*Table structure for table `unit_type_project` */

DROP TABLE IF EXISTS `unit_type_project`;

CREATE TABLE `unit_type_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `base_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0 base_price_type 1 material_spec 2 base_type',
  PRIMARY KEY (`id`),
  KEY `pk_utp_s_project` (`project`),
  CONSTRAINT `pk_utp_s_project` FOREIGN KEY (`project`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `work_charge_info` */

DROP TABLE IF EXISTS `work_charge_info`;

CREATE TABLE `work_charge_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `foreman` int(11) DEFAULT NULL,
  `worker_type` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `unit_price_type` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `update_date` varchar(20) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_wci_project` (`project`),
  KEY `pk_wci_p` (`unit_price`),
  KEY `pk_wci_p_t` (`unit_price_type`),
  KEY `pk_wci_p_o` (`foreman`),
  CONSTRAINT `pk_wci_p` FOREIGN KEY (`unit_price`) REFERENCES `price_fluctuation` (`id`),
  CONSTRAINT `pk_wci_p_t` FOREIGN KEY (`unit_price_type`) REFERENCES `unit_type_combination` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Table structure for table `xianghan` */

DROP TABLE IF EXISTS `xianghan`;

CREATE TABLE `xianghan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `node` int(11) DEFAULT NULL,
  `childNode` int(11) DEFAULT NULL,
  `col2` varchar(20) DEFAULT NULL,
  `col3` varchar(20) DEFAULT NULL,
  `col4` varchar(20) DEFAULT NULL,
  `col5` varchar(20) DEFAULT NULL,
  `col6` varchar(20) DEFAULT NULL,
  `col7` varchar(20) DEFAULT NULL,
  `col8` varchar(20) DEFAULT NULL,
  `col9` varchar(20) DEFAULT NULL,
  `col10` varchar(20) DEFAULT NULL,
  `col11` varchar(20) DEFAULT NULL,
  `col12` varchar(20) DEFAULT NULL,
  `col13` varchar(20) DEFAULT NULL,
  `col14` varchar(20) DEFAULT NULL,
  `col15` varchar(20) DEFAULT NULL,
  `col16` varchar(20) DEFAULT NULL,
  `col17` varchar(20) DEFAULT NULL,
  `col18` varchar(20) DEFAULT NULL,
  `col19` varchar(20) DEFAULT NULL,
  `col20` varchar(20) DEFAULT NULL,
  `col21` varchar(20) DEFAULT NULL,
  `col22` varchar(20) DEFAULT NULL,
  `col23` varchar(20) DEFAULT NULL,
  `col24` varchar(20) DEFAULT NULL,
  `col25` varchar(20) DEFAULT NULL,
  `col26` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `yuanguanhan` */

DROP TABLE IF EXISTS `yuanguanhan`;

CREATE TABLE `yuanguanhan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project` int(11) DEFAULT NULL,
  `node` int(11) DEFAULT NULL,
  `childNode` int(11) DEFAULT NULL,
  `col2` varchar(20) DEFAULT NULL,
  `col3` varchar(20) DEFAULT NULL,
  `col4` varchar(20) DEFAULT NULL,
  `col5` varchar(20) DEFAULT NULL,
  `col6` varchar(20) DEFAULT NULL,
  `col7` varchar(20) DEFAULT NULL,
  `col8` varchar(20) DEFAULT NULL,
  `col9` varchar(20) DEFAULT NULL,
  `col10` varchar(20) DEFAULT NULL,
  `col11` varchar(20) DEFAULT NULL,
  `col12` varchar(20) DEFAULT NULL,
  `col13` varchar(20) DEFAULT NULL,
  `col14` varchar(20) DEFAULT NULL,
  `col15` varchar(20) DEFAULT NULL,
  `col16` varchar(20) DEFAULT NULL,
  `col17` varchar(20) DEFAULT NULL,
  `col18` varchar(20) DEFAULT NULL,
  `col19` varchar(20) DEFAULT NULL,
  `col20` varchar(20) DEFAULT NULL,
  `col21` varchar(20) DEFAULT NULL,
  `col22` varchar(20) DEFAULT NULL,
  `col23` varchar(20) DEFAULT NULL,
  `col24` varchar(20) DEFAULT NULL,
  `col25` varchar(20) DEFAULT NULL,
  `col26` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


insert  into `base_type`(`id`,`name`,`type`,`remark`,`order_num`) values (1,'普种',0,NULL,0),(2,'技工',0,NULL,1),(3,'钢筋工',0,NULL,2),(4,'木工',0,NULL,3),(5,'电工',0,NULL,4),(6,'电焊工',0,NULL,5),(7,'架子工',0,NULL,6),(8,'修理工',0,NULL,7),(9,'砼振捣工',0,NULL,8),(10,'立方(m³)',1,'料单位',1),(11,'吨(t)',1,'料单位',2),(12,'车',1,'料单位',3),(16,'无',1,'料单位',4),(17,'挖掘机',2,'机械类型',1),(18,'推土机',2,NULL,2),(19,'压路机',2,NULL,3),(20,'平地机',2,NULL,4),(21,'冲击夯',2,NULL,5),(22,'水车',2,NULL,6),(23,'装载机',2,NULL,7),(24,'摊铺机',2,NULL,8),(25,'钢轮压路机',2,NULL,9),(26,'胶轮压路机',2,NULL,10),(27,'吊车',2,NULL,11),(28,'罐车',2,NULL,12),(29,'刨壕机',2,NULL,13),(30,'滑膜摊铺机',2,NULL,14),(31,'滑膜摊铺机',2,NULL,15),(32,'业主拨款',3,'收入来源',1),(33,'公司拨款',3,NULL,2),(34,'营业外收',3,NULL,3),(35,'融资借款',3,NULL,4),(36,'变卖资产',3,NULL,5),(37,'投标收入',3,NULL,6),(38,'普票',4,'票据类型',1),(39,'收据',4,NULL,2),(40,'无票',4,NULL,3),(41,'平方(㎡)',5,'机械计费单位/件',1),(42,'立方(m³)',5,'机械计费单位/件',2),(43,'吨(t)',5,'机械计费单位/件',3),(44,'米(m)',5,'机械计费单位/件',4),(45,'台班',5,'机械计费单位/日',5),(46,'平方(㎡)',7,'人/件',1),(47,'立方(m³)',7,'人/件',2),(48,'米(m)',7,'人/件',3),(49,'日',7,'人/日',4),(50,'无',7,'人/自定义',NULL),(51,'无',5,'机械计费单位/自定义',NULL),(52,'升',1,'料单位',7);

insert  into `base_price_type`(`id`,`name`,`type`,`order_num`,`remark`) values (1,'月工资',-1,0,NULL),(2,'计件',0,1,NULL),(3,'计日',0,2,NULL),(4,'日租金',2,1,NULL),(5,'月租金',2,2,NULL),(6,'吨(t)/公里',3,1,NULL),(7,'立方(m³)/公里',3,2,NULL),(8,'工资',4,0,'费用类别'),(9,'借款',4,1,NULL),(10,'报销',4,2,NULL),(14,'不计费',12,3,NULL),(15,'计件',1,1,NULL),(16,'计日',1,2,NULL),(17,'车/公里',3,5,NULL),(18,'招待费',4,3,NULL),(19,'交通燃油费',4,4,NULL),(20,'过路费',4,5,NULL),(21,'办公费',4,6,NULL),(22,'伙食费',4,7,NULL),(23,'差旅费',4,8,NULL),(24,'房租费',4,9,NULL),(25,'项目部用小材料',4,10,NULL),(26,'煤炭费',4,11,NULL),(27,'水电费',4,12,NULL),(28,'小车使用费',4,13,NULL),(29,'小车维修费',4,14,NULL),(30,'保险费',4,15,NULL),(31,'实地检测费',4,16,NULL),(32,'自定义',1,6,NULL),(33,'自定义',0,7,NULL),(34,'征地费',4,17,NULL),(35,'场地租用费',4,18,NULL),(36,'驻地建设费',4,19,NULL),(37,'调遣费',4,20,NULL),(38,'路用小材料',4,21,NULL),(39,'桥用小材料',4,22,NULL),(40,'小件预制用小材料',4,23,NULL),(41,'梁板预制小材料',4,24,NULL),(42,'拌合站用小材料',4,25,NULL),(43,'税金',4,26,NULL),(44,'其它',4,27,NULL),(45,'升/公里',3,28,NULL);

insert  into `department`(`id`,`name`,`description`,`remark`) values (1,'外部机构',NULL,NULL),(2,'总经办',NULL,NULL),(3,'拌合站',NULL,NULL),(4,'人力资源部',NULL,NULL),(5,'网络部',NULL,NULL),(6,'账务部',NULL,NULL);

insert  into `menu`(`id`,`name`,`parent`,`type`,`url`,`icon`,`remark`) values (1,'首页',0,0,'allmanager','icon-dashboard',NULL),(2,'工作管理',0,1,NULL,'',NULL),(3,'出纳管理',0,0,'financemanager.html','icon-book ',NULL),(4,'工程规划',0,0,'programmanager.html','icon-list-alt',NULL),(5,'离线管理',0,1,NULL,' ',NULL),(6,'数据报表',0,0,NULL,'icon-list',NULL),(7,'人工费汇总表',6,0,'labor.html',' ',NULL),(8,'材料费汇总表',6,0,'materials.html',' ',NULL),(9,'机械费汇总表',6,0,'mechanics.html',' ',NULL),(10,'项目设置',0,0,NULL,'icon-desktop',NULL),(11,'基础类型信息',10,0,NULL,' ',NULL),(12,'员工管理',10,0,'staffmanager.html',' ',NULL),(13,'项目管理',10,0,'projectmanager.html',' ',NULL),(14,'工头登记',10,0,'foremanmanager.html',' ',NULL),(15,'车辆管理',10,0,'carmanager.html',' ',NULL),(16,'材料登记',10,0,'materialmanager.html',' ',NULL),(17,'机械登记',10,0,'mechanicsmanager.html',' ',NULL),(18,'拉料车管理',10,0,'skipmanager.html','',NULL),(19,'结算管理',0,0,NULL,'icon-beaker ',NULL),(20,'人工费结算',19,0,NULL,NULL,NULL),(21,'材料费结算',19,0,NULL,NULL,NULL),(22,'机械费结算',19,0,NULL,NULL,NULL),(24,'加油汇总表',6,0,'oil.html',NULL,NULL);

insert  into `role`(`id`,`name`,`state`,`remark`) values (1,'公司系统管理员',0,NULL),(2,'项目系统管理员',0,NULL),(3,'项目经理',0,NULL),(4,'总工',0,NULL),(5,'会计 ',0,NULL),(6,'出纳',0,NULL),(7,'总经理等',0,NULL),(8,'收料员',0,NULL),(9,'技术员',0,NULL),(10,'加油员',0,NULL);

insert  into `role_menu`(`id`,`role`,`menu`,`order_num`) values (1,1,1,0),(2,1,10,1),(3,1,13,2),(4,1,12,3),(5,1,14,4),(6,1,15,5),(7,1,16,6),(8,1,17,7),(9,1,18,8),(10,1,6,8),(11,1,7,10),(12,1,8,11),(13,1,9,12),(14,1,19,13),(15,1,20,14),(16,1,21,15),(17,1,22,16),(18,1,2,17),(19,1,3,18),(20,1,4,19),(21,1,5,20),(22,2,1,0),(23,2,10,1),(24,2,13,2),(25,2,12,3),(26,2,14,4),(27,2,15,5),(28,2,16,6),(29,2,17,7),(30,2,18,8),(31,2,6,9),(32,2,7,10),(33,2,8,11),(34,2,9,12),(35,2,4,13),(36,2,3,14),(37,2,19,15),(38,2,20,16),(39,2,21,17),(40,2,22,18),(41,3,1,0),(42,3,6,1),(43,3,7,2),(44,3,8,3),(45,3,9,4),(46,3,19,5),(47,3,20,6),(48,3,21,7),(49,3,22,8),(50,3,4,9),(51,3,3,10),(52,4,1,0),(53,4,4,1),(54,5,1,0),(55,5,3,1),(56,5,19,2),(57,5,20,3),(58,5,21,4),(59,5,22,5),(60,6,1,0),(61,6,3,1),(62,7,1,0),(63,7,6,1),(64,7,7,2),(65,7,8,3),(66,7,9,4),(67,1,24,100),(68,2,24,100),(69,3,24,100),(70,4,24,100),(71,5,24,100),(72,6,24,100),(73,7,24,100),(74,8,10,1),(75,8,18,2);

insert  into `material_type`(`id`,`mt_name`,`remark`,`mt_order_num`) values (1,'土',NULL,0),(2,'风积沙',NULL,1),(3,'石渣',NULL,2),(4,'天然砂砾',NULL,3),(5,'过筛砂砾',NULL,4),(6,'水洗砂',NULL,5),(7,'碎石',NULL,6),(8,'石屑',NULL,7),(9,'普通水泥',NULL,8),(10,'缓凝水泥',NULL,9),(11,'沥青',NULL,10),(12,'改性沥青',NULL,11),(13,'柴油',NULL,12),(14,'汽油',NULL,13),(15,'钢筋',NULL,14),(16,'钢绞线',NULL,15),(17,'钢管',NULL,16);

insert  into `material_spec`(`id`,`ms_name`,`ms_order_num`,`remark`,`material_type`) values (1,'0-4.75',0,NULL,7),(2,'4.75-9.5',1,NULL,7),(3,'9.5-19',2,NULL,7),(4,'9.5-16',3,NULL,7),(5,'9.5-16',4,NULL,7),(6,'26.5-31.5',5,NULL,7),(7,'19-40',6,NULL,7),(8,'19-31.5',7,NULL,7),(9,'32.5',1,NULL,9),(10,'42.5',2,NULL,9),(11,'52.5',3,NULL,9),(12,'62.5',4,NULL,9),(13,'32.5',1,NULL,10),(14,'42.5',2,NULL,10),(15,'0#',1,NULL,13),(16,'-10#',2,NULL,13),(17,'-20#',3,NULL,13),(18,'-30#',4,NULL,13),(19,'92#',1,NULL,14),(20,'95#',2,NULL,14),(21,'98#',3,NULL,14),(22,'I',1,NULL,15),(23,'III',2,NULL,15);
