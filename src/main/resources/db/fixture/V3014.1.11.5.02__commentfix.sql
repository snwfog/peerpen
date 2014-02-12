


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
(3,'Finance','Financial economics is the branch of economics studying the interrelation of financial variables, such as prices, interest rates and shares,','2014-01-28 00:40:05','2014-02-08 20:30:07',0,NULL),
(4,'Marketing','Marketing is the process of communicating the value of a product or service to customers, for selling that product or service','2014-01-28 00:40:05','2014-02-08 20:29:48',0,NULL),
(5,'International Business','International business comprises all commercial transactions (private and governmental, sales, investments, logistics, and transportation) ','2014-01-28 00:54:28','2014-02-08 20:30:09',0,NULL),
(6,'Arts','liberal arts education is a term that can be interpreted in different ways. It can refer to certain areas of literature, languages, philosophy, history, mathematics, psycho','2014-01-28 00:54:29','2014-02-08 20:29:29',0,NULL),
(7,'Software Engineer','Software Engineering is the study and application of engineering to the design, development, and maintenance of software','2014-02-08 20:10:02','2014-02-08 20:30:07',0,NULL),
(8,'Mechanical Engineer','Mechanical engineering is a discipline of engineering that applies the principles of engineering, physics and','2014-02-08 20:11:01','2014-02-08 20:29:31',0,NULL),
(9,'Computer Engineer','Computer engineering is a discipline that integrates several fields of electrical engineering and computer science required to develop computer hardware and software.','2014-02-08 20:11:27','2014-02-08 20:30:08',0,NULL),
(10,'Accounting','Accounting, or accountancy, is the measurement, processing and communication of financial information about economic entities.','2014-02-08 20:12:04','2014-02-08 20:29:50',0,NULL),
(11,'Actuary','An actuary is a business professional who deals with the financial impact of risk and uncertainty. Actuaries provide assessments of financial security systems, with a focus on their complexity,','2014-02-08 20:12:32','2014-02-08 20:30:06',0,NULL),
(12,'Business Administration','The administration of a business includes the performance or management of business operations and decision making as well as the efficient organization of people ','2014-02-08 20:13:09','2014-02-08 20:28:45',0,NULL),
(13,'Education','Education in its general sense is a form of learning in which the knowledge, skills, and habits of a group of people are transferred from one generation to the next through teaching, training, or research','2014-02-08 20:18:16','2014-02-08 20:29:50',0,NULL),
(14,'Social Science','Social science is an academic discipline concerned with society and the relationships among individuals within a society. ','2014-02-08 20:18:16','2014-02-08 20:30:13',0,NULL),
(15,'Entrepreneurship','In political economics, entrepreneurship is a process of identifying ','2014-02-08 20:18:16','2014-02-08 20:30:11',0,NULL),
(16,'Communism','Communism s a classless, moneyless,[1][2] and stateless social order structured upon common ownership of the means of production.','2014-02-08 20:18:16','2014-02-08 20:28:53',0,NULL),
(17,'Mafia','Mafia is a blanket term used to describe a type of organized crime syndicate that primarily practices protection racketeering ? the use of violent intimidation to manipulate local economic activity, ','2014-02-08 20:18:59','2014-02-08 20:28:51',0,NULL);

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
	(8, 6, 'Group', '2014-01-28 00:54:29', '2014-01-28 00:54:29'),
	(9, 1, 'Document', '2014-01-31 20:50:47', '2014-01-31 20:50:47'),
	(10, 2, 'Document', '2014-01-31 20:50:56', '2014-01-31 20:50:56'),
	(11, 3, 'Document', '2014-01-31 20:51:10', '2014-01-31 20:51:10'),
	(12, 4, 'Document', '2014-01-31 20:51:27', '2014-01-31 20:51:27'),
	(13, 5, 'Document', '2014-01-31 20:51:38', '2014-01-31 20:51:38'),
	(14, 6, 'Document', '2014-01-31 20:51:48', '2014-01-31 20:51:48'),
	(15, 7, 'Document', '2014-01-31 20:51:59', '2014-01-31 20:51:59'),
	(16, 8, 'Document', '2014-01-31 20:52:13', '2014-01-31 20:52:13'),
	(17, 9, 'Document', '2014-01-31 20:52:23', '2014-01-31 20:52:23');


INSERT INTO `tags` (`id`, `tag_descriptor_id`, `taggable_id`)
VALUES
	(2, 12, 5),
	(3, 3, 5),
	(4, 7, 8),
	(5, 7, 5),
	(6, 7, 6);


