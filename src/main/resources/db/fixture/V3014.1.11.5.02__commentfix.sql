


INSERT INTO `peers` (`id`, `first_name`, `last_name`, `email`, `user_name`, `password`, `point`, `personal_website`, `creation_date`, `last_modified_date`, `etag`, `semaphore`, `description`, `rank_id`, `date_of_birth`, `country`, `industry`, `experience`, `gender`, `avatar_id`)
VALUES
	(2,'Bobby','Yit','hello@gmail.com','qwe','qwe',9000,'peerpen.com','2014-01-11 13:04:33','2014-01-11 13:04:33',NULL,0,'this is a description',NULL,'2014-01-11','Canada','SOEN',2,'M',NULL),
	(3,'John','Doe','hello@gmail.cm','asd','asd',1000,'google.com','2014-01-13 17:55:28','2014-01-13 17:55:28',NULL,0,'this is a description',NULL,'2014-01-13','US of A','MECH',3,'M',NULL),
	(4,'Jane','Smith','info@peerpen.com','bob','bob',10,'peerpen.com','2014-01-13 17:56:48','2014-01-13 17:56:48',NULL,0,'I am newly graduate',NULL,'2014-01-13','Australia','FINANCE',0,'F',NULL),
	(5,'John ','Smith','John.Smith@google.com','jon','jon',20,'google.com','2014-01-13 17:58:22','2014-01-13 17:58:22',NULL,0,'I am a fake person',NULL,'2014-01-13','China','OIL',4,'F',NULL);



INSERT INTO `documents` (`id`, `doc_name`, `thumbnail_path`, `peer_id`, `last_modified_date`, `creation_date`, `semaphore`, `etag`, `doc_type`)
  VALUES
  (1,'My First Resume','none',2,'2014-01-13 17:58:59','2014-01-13 17:58:59',0,NULL,NULL),
  (2,'Google Cover Letter','none',2,'2014-01-13 17:59:18','2014-01-13 17:59:18',0,NULL,NULL),
  (3,'Apple Cover Letter','none',2,'2014-01-13 17:59:43','2014-01-13 17:59:43',0,NULL,NULL),
  (4,'Standard Life Resume','none',2,'2014-01-13 18:00:36','2014-01-13 18:00:36',0,NULL,NULL),
  (5,'Burger King CV','none',3,'2014-01-13 18:00:55','2014-01-13 18:00:55',0,NULL,NULL),
  (6,'McDonald CV','none',3,'2014-01-13 18:01:10','2014-01-13 18:01:10',0,NULL,NULL),
  (7,'Concordia Resume','none',4,'2014-01-13 18:01:33','2014-01-13 18:01:33',0,NULL,NULL),
  (8,'McGill Masters Cover Letter','none',5,'2014-01-24 21:50:24','2014-01-24 21:50:24',0,NULL,NULL),
  (9,'Exxcon Resume','none',5,'2014-01-13 18:02:31','2014-01-13 18:02:31',0,NULL,NULL);


INSERT INTO `hunks` (`id`, `id_view`, `document_id`, `content`, `creation_date`, `last_modified_date`)
  VALUES
  (1, NUll,'2','something about something about something','2014-01-13 17:58:59','2014-01-13 17:58:59'),
  (2, NUll,'2','bloooood','2014-01-13 17:58:59','2014-01-13 17:58:59'),
  (3, NUll,'2','power','2014-01-13 17:58:59','2014-01-13 17:58:59');

INSERT INTO `changesets` (`id`, `document_id`, `creation_date`, `last_modified_date`, `peer_id`, `content`, `hunk_id`)
  VALUES
  (5,1,'2014-01-15 23:24:05','2014-01-15 23:24:05',2,'Changed the format of Education\n',1),
  (6,1,'2014-01-15 23:24:48','2014-01-15 23:24:48',2,'Added Leasure section',2),
  (7,1,'2014-01-15 23:25:24','2014-01-15 23:25:24',2,'Remove High School in Education',3);


INSERT INTO `comments` (`id`, `message`, `document_id`, `peer_id`, `creation_date`, `last_modified_date`, `changeset_id`, `up_vote`, `down_vote`)
VALUES
  (1,'My first comment',1,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,2,0),
  (2,'Nice resume! :)',1,3,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,3,0),
  (3,'Theres a few spelling mistakes..',1,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,4,0),
  (4,'Thanks! fixed :D',1,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
  (5,'There a run-on sentence in your first paragraph.',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
  (6,'Thanks, fixed it! stayed up all night trying to fix it... :/\r\nYou a life saver! ',2,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,6,0),
  (7,'No problem :)',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,3,0),
  (8,'Nice!',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
  (9,'Good Luck',1,4,'2014-01-15 23:26:41','2014-01-15 23:26:41',NULL,2,0),
  (10,'True',1,3,'2014-01-15 23:26:35','2014-01-15 23:26:35',5,4,0),
  (11,'Smart',1,4,'2014-01-15 23:27:01','2014-01-15 23:27:01',5,3,0),
  (12,'Hmmm.. I see your point',1,5,'2014-01-15 23:27:48','2014-01-15 23:27:48',6,10,0);



INSERT INTO `feedables` (`id`, `child_id`, `user_id`,`type`, `status`,`last_modified_date`, `creation_date`)
  VALUES
  (1, 5, 2,'Changeset','new' ,'2014-01-13 17:59:59','2014-01-13 17:58:59'),
  (2, 6, 2,'Changeset','new' ,'2014-01-13 17:56:59','2014-01-13 17:58:59'),
  (3, 7, 2,'Changeset','new' ,'2014-01-13 17:54:59','2014-01-13 17:58:59'),
  (4, 1, 2,'Comment','new' ,'2014-01-13 17:57:59','2014-01-13 17:58:59'),
  (5, 2, 2,'Comment','new' ,'2014-01-13 17:52:59','2014-01-13 17:58:59');



INSERT INTO `groups` (`id`, `group_name`, `description`, `creation_date`, `last_modified_date`, `semaphore`, `etag`)
VALUES
	(3, 'concordia', 'this is description of concordia', '2014-01-28 00:40:05', '2014-01-28 00:40:05', 0, 'f8ec4549f8e70824d6c52ce5f548c6e11fc49a67c34fe4036095e48482230cd8'),
	(4, 'mcgill', 'this is description of mcgill', '2014-01-28 00:40:05', '2014-01-28 00:40:05', 0, '293f83bdd305bcc57a245b5b2b52932f10e4b6db47139b7815e21b72461a4c88'),
	(5, 'uqam', 'this is description of uqam', '2014-01-28 00:54:28', '2014-01-28 00:54:28', 0, '08d96c81daad331146aa90e468e8a16bfb65f4823ff3e2b269c299083982de3f'),
	(6, 'montreal', 'this is description of montreal', '2014-01-28 00:54:29', '2014-01-28 00:54:29', 0, 'aa11940a47b156bd4e42adc7444f73bc5a531594005c00d8c96155171a7c4269');

INSERT INTO `tag_descriptors` (`id`, `tag_name`, `creation_date`, `last_modified_date`)
VALUES
	(1, 'concordia', '2014-01-24 16:27:11', '2014-01-24 16:27:11'),
	(2, 'mcgill', '2014-01-24 17:40:16', '2014-01-24 17:40:19'),
	(3, 'test', '2014-01-24 17:40:23', '2014-01-24 17:40:23'),
	(5, 'ccc', '2014-01-24 17:40:28', '2014-01-24 17:40:28'),
	(6, 'resume', '2014-01-24 18:46:27', '2014-01-24 18:46:27'),
	(7, 'montreal', '2014-01-24 18:46:37', '2014-01-24 18:46:37'),
	(8, 'PEER', '2014-01-24 18:46:48', '2014-01-24 18:46:48'),
	(9, 'Accounting', '2014-01-24 18:47:04', '2014-01-24 18:47:04'),
	(11, 'blabla', '2014-01-24 20:48:29', '2014-01-24 20:48:29'),
	(12, 'university', '2014-01-25 19:20:03', '2014-01-25 19:20:03');

INSERT INTO `taggables` (`id`, `child_id`, `type`, `creation_date`, `last_modified_date`)
VALUES
	(1, 1, 'Group', '2014-01-28 00:39:15', '2014-01-28 00:39:15'),
	(2, 2, 'Group', '2014-01-28 00:39:15', '2014-01-28 00:39:15'),
	(5, 3, 'Group', '2014-01-28 00:40:05', '2014-01-28 00:40:05'),
	(6, 4, 'Group', '2014-01-28 00:40:05', '2014-01-28 00:40:05'),
	(7, 5, 'Group', '2014-01-28 00:54:29', '2014-01-28 00:54:29'),
	(8, 6, 'Group', '2014-01-28 00:54:29', '2014-01-28 00:54:29');

INSERT INTO `tags` (`id`, `tag_descriptor_id`, `taggable_id`)
VALUES
	(2, 12, 5),
	(3, 3, 5),
	(4, 7, 8),
	(5, 7, 5),
	(6, 7, 6);


