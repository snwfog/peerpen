DROP TABLE `feedables`;
CREATE TABLE `feedables` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `notify_status` varchar(255) DEFAULT 'UNSEND',
  `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE feedables ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE feedables ADD etag varchar(256) DEFAULT NULL;