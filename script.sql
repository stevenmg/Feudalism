CREATE TABLE `kingdoms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,`name` varchar(12) NOT NULL,
  `owner` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `owner_UNIQUE` (`owner`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
CREATE TABLE `vassals` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `kingdom` int(11) DEFAULT NULL,
  `leader` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `kingdom_idx` (`kingdom`),
  CONSTRAINT `kingdom` FOREIGN KEY (`kingdom`) REFERENCES `kingdoms` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
#
CREATE TABLE `relations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kingdom1` int(11) NOT NULL,
  `kingdom2` int(11) NOT NULL,
  `relation` enum('1','2') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `k1_idx` (`kingdom1`),
  KEY `k2_idx` (`kingdom2`),
  CONSTRAINT `kingdom1` FOREIGN KEY (`kingdom1`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kingdom2` FOREIGN KEY (`kingdom2`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
CREATE TABLE `alliancerequests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kingdom_sender` int(11) NOT NULL,
  `kingdom_receiver` int(11) NOT NULL,
  `expiration` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `k1_idx` (`kingdom_sender`),
  KEY `k2_idx` (`kingdom_receiver`),
  CONSTRAINT `k1` FOREIGN KEY (`kingdom_sender`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `k2` FOREIGN KEY (`kingdom_receiver`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
CREATE TABLE `invitations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kingdom` int(11) NOT NULL,
  `vassal` int(11) NOT NULL,
  `expiration` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `kingdom_idx` (`kingdom`),
  KEY `vassal_idx` (`vassal`),
  CONSTRAINT `kingdo` FOREIGN KEY (`kingdom`) REFERENCES `kingdoms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vass` FOREIGN KEY (`vassal`) REFERENCES `vassals` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
ALTER TABLE `kingdoms` ADD CONSTRAINT `kOwner` FOREIGN KEY (`owner`) REFERENCES `vassals` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION