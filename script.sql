delimiter $$

USE `fuedalism`$$

CREATE TABLE `kingdoms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `kingdomclaims` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` polygon NOT NULL,
  `kingdom` int(11) NOT NULL,
  `world` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `corner1_UNIQUE` (`region`(25)),
  KEY `kingdom_idx` (`kingdom`),
  CONSTRAINT `kingdoms` FOREIGN KEY (`kingdom`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `vassals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `kingdom` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `kingdom_idx` (`kingdom`),
  CONSTRAINT `kingdom` FOREIGN KEY (`kingdom`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

CREATE TABLE `fiefs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `region` polygon DEFAULT NULL,
  `vassal` int(11) NOT NULL,
  `world` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `corner1_UNIQUE` (`region`(25)),
  KEY `vassal_idx` (`vassal`),
  CONSTRAINT `vassal` FOREIGN KEY (`vassal`) REFERENCES `vassals` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8$$