CREATE TABLE `broadcasts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  `peer_id` int(11) DEFAULT NULL,
  `semaphore` int(1) NOT NULL DEFAULT '0',
  `etag` varchar(256) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `peer_id` (`peer_id`),
  CONSTRAINT `broadcasts_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `broadcasts_ibfk_2` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;