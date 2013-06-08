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
  `corner1` point NOT NULL,
  `corner2` point NOT NULL,
  `kingdom` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `corner1_UNIQUE` (`corner1`(25)),
  UNIQUE KEY `corner2_UNIQUE` (`corner2`(25)),
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
  `corner1` point NOT NULL,
  `corner2` point NOT NULL,
  `vassal` int(11) NOT NULL,
  PRIMARY KEY (`id`,`corner1`(25)),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `corner1_UNIQUE` (`corner1`(25)),
  UNIQUE KEY `corner2_UNIQUE` (`corner2`(25)),
  KEY `vassal_idx` (`vassal`),
  CONSTRAINT `vassal` FOREIGN KEY (`vassal`) REFERENCES `vassals` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$