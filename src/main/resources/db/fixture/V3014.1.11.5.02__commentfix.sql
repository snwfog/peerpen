INSERT INTO `comments` (`id`, `message`, `document_id`, `peer_id`, `creation_date`, `last_modified_date`, `changeset_id`)
VALUES
	(5,'gro\r\n',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(6,'test\r\n',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(7,'test',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(8,'test',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(10,'HELLO',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(11,'sadf',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(12,'hello',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(13,'sdafds',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(14,'HIIIII',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(16,'haaaa',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL),
	(18,'dsafd',3,2,'2014-01-11 00:00:00','2014-01-11 00:00:00',NULL);

INSERT INTO `documents` (`id`, `doc_name`, `thumbnail_path`, `peer_id`, `last_modified_date`, `creation_date`, `semaphore`, `etag`, `doc_type`)
VALUES
	(3,'testesttest','none',2,'2014-01-11 13:15:06','2014-01-11 13:15:06',0,NULL,NULL);

INSERT INTO `peers` (`id`, `first_name`, `last_name`, `email`, `user_name`, `password`, `point`, `personal_website`, `creation_date`, `last_modified_date`, `etag`, `semaphore`, `description`, `rank_id`, `date_of_birth`, `country`, `industry`, `experience`, `gender`)
VALUES
	(2,'bob','yit','asn.brotha@gmail.com','qwe','qwe',23,'google.com','2014-01-11 13:04:33','2014-01-11 13:04:33',NULL,0,'dsfasdf',NULL,'2014-01-11','here','porn',24,'M');