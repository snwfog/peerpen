# Dump of table ranks
# ------------------------------------------------------------

CREATE TABLE `ranks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_name` varchar(255) DEFAULT NULL,
  `min_point` int(11) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table peers
# ------------------------------------------------------------

CREATE TABLE `peers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `personal_website` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `etag` varchar(256) DEFAULT NULL,
  `semaphore` int(1) NOT NULL DEFAULT '0',
  `description` varchar(256) DEFAULT NULL,
  `rank_id` int(11) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `country` varchar(256) DEFAULT NULL,
  `industry` varchar(256) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `gender` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rank_id` (`rank_id`),
  CONSTRAINT `peers_ibfk_1` FOREIGN KEY (`rank_id`) REFERENCES `ranks` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table peers_groups
# ------------------------------------------------------------

CREATE TABLE `peers_groups` (
  `peer_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`peer_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table avatars
# ------------------------------------------------------------
CREATE TABLE `avatars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_data` blob,
  `img_title` varchar(256) DEFAULT NULL,
  `peer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `peer_id` (`peer_id`),
  CONSTRAINT `avatars_ibfk_1` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table categories
# ------------------------------------------------------------

CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `semaphore` int(1) NOT NULL DEFAULT '0',
  `etag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table documents
# ------------------------------------------------------------

CREATE TABLE `documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_name` varchar(255) DEFAULT NULL,
  `thumbnail_path` varchar(255) DEFAULT NULL,
  `peer_id` int(11) DEFAULT NULL,
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `semaphore` int(1) NOT NULL DEFAULT '0',
  `etag` varchar(256) DEFAULT NULL,
  `doc_type` enum('resume','cover_letter') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `peer_id` (`peer_id`),
  CONSTRAINT `documents_ibfk_1` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table documents_taggables
# ------------------------------------------------------------

CREATE TABLE `documents_taggables` (
  `document_id` int(11) NOT NULL,
  `taggable_id` int(11) NOT NULL,
  PRIMARY KEY (`taggable_id`,`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table comments
# ------------------------------------------------------------

CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `peer_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `document_id` (`document_id`),
  KEY `peer_id` (`peer_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table feedables
# ------------------------------------------------------------

CREATE TABLE `feedables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table groups
# ------------------------------------------------------------

CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `semaphore` int(1) NOT NULL DEFAULT '0',
  `etag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table groups_categories
# ------------------------------------------------------------

CREATE TABLE `groups_categories` (
  `category_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`group_id`,`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table hunks
# ------------------------------------------------------------

CREATE TABLE `hunks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_view` varchar(256) DEFAULT NULL,
  `document_id` int(11) DEFAULT NULL,
  `content` text,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `document_id` (`document_id`),
  CONSTRAINT `hunks_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table likes
# ------------------------------------------------------------

CREATE TABLE `likes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `peer_id` int(11) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `peer_id` (`peer_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table logs
# ------------------------------------------------------------

CREATE TABLE `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `etag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table changesets
# ------------------------------------------------------------

CREATE TABLE `changesets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `peer_id` int(11) DEFAULT NULL,
  `content` text,
  `hunk_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `document_id` (`document_id`),
  KEY `peer_id` (`peer_id`),
  KEY `hunk_id` (`hunk_id`),
  CONSTRAINT `changesets_ibfk_3` FOREIGN KEY (`hunk_id`) REFERENCES `hunks` (`id`) ON DELETE SET NULL,
  CONSTRAINT `changesets_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`) ON DELETE SET NULL,
  CONSTRAINT `changesets_ibfk_2` FOREIGN KEY (`peer_id`) REFERENCES `peers` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table snapshots
# ------------------------------------------------------------

CREATE TABLE `snapshots` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `document_id` (`document_id`),
  CONSTRAINT `snapshots_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table tag_descriptors
# ------------------------------------------------------------

CREATE TABLE `tag_descriptors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table taggables
# ------------------------------------------------------------

CREATE TABLE `taggables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# Dump of table tags
# ------------------------------------------------------------

CREATE TABLE `tags` (
  `tag_descriptor_id` int(11) NOT NULL,
  `taggable_id` int(11) NOT NULL,
  PRIMARY KEY (`tag_descriptor_id`,`taggable_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
