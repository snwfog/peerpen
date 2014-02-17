

INSERT INTO `peers` (`id`, `first_name`, `last_name`, `email`, `user_name`, `password`, `point`, `personal_website`, `creation_date`, `last_modified_date`, `etag`, `semaphore`, `description`, `rank_id`, `date_of_birth`, `country`, `industry`, `experience`, `gender`, `avatar_id`, `session_id`)
  VALUES
  (2,'Ron','Hagen','hello@gmail.com','qwe','qwe',9000,'peerpen.com','2014-01-11 13:04:33','2014-02-08 20:30:52',NULL,0,'this is a description',NULL,'2014-01-11','Canada','SOEN',2,'M',NULL,NULL),
  (3,'John','Doe','hello@gmail.cm','asd','asd',1000,'google.com','2014-01-13 17:55:28','2014-02-08 20:30:00',NULL,0,'this is a description',NULL,'2014-01-13','US of A','MECH',3,'M',NULL,NULL),
  (4,'Jane','Smith','info@peerpen.com','bob','bob',10,'peerpen.com','2014-01-13 17:56:48','2014-02-08 20:29:43',NULL,0,'I am newly graduate',NULL,'2014-01-13','Australia','FINANCE',0,'F',NULL,NULL),
  (5,'John ','Smith','John.Smith@google.com','jon','jon',20,'google.com','2014-01-13 17:58:22','2014-02-08 20:29:25',NULL,0,'I am a fake person',NULL,'2014-01-13','China','OIL',4,'F',NULL,NULL),
  (6,'Mark','Zane','mark@peerpen.com','mar','mar',4,'google.com','2014-02-08 19:15:39','2014-02-08 20:29:04',NULL,0,'Hi, my name is Mark',NULL,'2014-02-08','Canada','ACCO',3,'M',NULL,NULL),
  (7,'Brad','Zimmer','brad@peer.ca','bra','bra',5,'google.com','2014-02-08 19:17:45','2014-02-08 20:28:33',NULL,0,'Hi, Im Brad, pleasure to meet you',NULL,'2014-02-08','US of A','FINA',5,'M',NULL,NULL),
  (8,'Melissa','Margerat','mel@stdlife.ca','mel','mel',7,'google.com','2014-02-08 19:19:18','2014-02-08 20:28:14',NULL,0,'Hello, is it me youre looking for?',NULL,'2014-02-08','Australia','MECH',5,'F',NULL,NULL),
  (9,'Meg','Griffin','meg@facebook.com','meg','meg',6,'google.com','2014-02-08 19:20:09','2014-02-08 20:27:41',NULL,0,'Meg.',NULL,'2014-02-08','Germany','BIOL',2,'F',NULL,NULL),
  (10,'James','DeSanta','james.desenta@getalife.com','jam','jam',3,'google.com','2014-02-08 19:21:05','2014-02-08 20:27:17',NULL,0,'I am looking for a exciting positiong',NULL,'2014-02-08','France','BIOT',3,'M',NULL,NULL);



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

INSERT INTO `changesets` (`id`, `creation_date`, `last_modified_date`, `peer_id`, `content`, `hunk_id`)
  VALUES
  (5,'2014-01-15 23:24:05','2014-01-15 23:24:05',2,'Changed the format of Education\n',1),
  (6,'2014-01-15 23:24:48','2014-01-15 23:24:48',2,'Added Leasure section',2),
  (7,'2014-01-15 23:25:24','2014-01-15 23:25:24',2,'Remove High School in Education',3);


-- INSERT INTO `comments` (`id`, `message`, `document_id`, `peer_id`, `creation_date`, `last_modified_date`, `changeset_id`, `up_vote`, `down_vote`)
-- VALUES
--   (1,'My first comment',1,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,2,0),
--   (2,'Nice resume! :)',1,3,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,3,0),
--   (3,'Theres a few spelling mistakes..',1,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,4,0),
--   (4,'Thanks! fixed :D',1,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
--   (5,'There a run-on sentence in your first paragraph.',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
--   (6,'Thanks, fixed it! stayed up all night trying to fix it... :/\r\nYou a life saver! ',2,2,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,6,0),
--   (7,'No problem :)',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,3,0),
--   (8,'Nice!',2,4,'2014-01-13 00:00:00','2014-01-13 00:00:00',NULL,5,0),
--   (9,'Good Luck',1,4,'2014-01-15 23:26:41','2014-01-15 23:26:41',NULL,2,0),
--   (10,'True',1,3,'2014-01-15 23:26:35','2014-01-15 23:26:35',5,4,0),
--   (11,'Smart',1,4,'2014-01-15 23:27:01','2014-01-15 23:27:01',5,3,0),
--   (12,'Hmmm.. I see your point',1,5,'2014-01-15 23:27:48','2014-01-15 23:27:48',6,10,0);
--
--

-- INSERT INTO `feedables` (`id`, `child_id`, `user_id`,`type`, `status`,`last_modified_date`, `creation_date`)
--   VALUES
--   (1, 5, 2,'Changeset','new' ,'2014-01-13 17:59:59','2014-01-13 17:58:59'),
--   (2, 6, 2,'Changeset','new' ,'2014-01-13 17:56:59','2014-01-13 17:58:59'),
--   (3, 7, 2,'Changeset','new' ,'2014-01-13 17:54:59','2014-01-13 17:58:59'),
--   (4, 1, 2,'Comment','new' ,'2014-01-13 17:57:59','2014-01-13 17:58:59'),
--   (5, 2, 2,'Comment','new' ,'2014-01-13 17:52:59','2014-01-13 17:58:59');


INSERT INTO `groups` (`id`, `group_name`, `description`, `creation_date`, `last_modified_date`, `semaphore`, `etag`, `admin_id`)
  VALUES
  (3,'Finance','Financial economics is the branch of economics studying the interrelation of financial variables, such as prices, interest rates and shares,','2014-01-28 00:40:05','2014-02-08 20:30:07',0,NULL,3),
  (4,'Marketing','Marketing is the process of communicating the value of a product or service to customers, for selling that product or service','2014-01-28 00:40:05','2014-02-08 20:29:48',0,NULL,2),
  (5,'International Business','International business comprises all commercial transactions (private and governmental, sales, investments, logistics, and transportation) ','2014-01-28 00:54:28','2014-02-08 20:30:09',0,NULL,3),
  (6,'Arts','liberal arts education is a term that can be interpreted in different ways. It can refer to certain areas of literature, languages, philosophy, history, mathematics, psycho','2014-01-28 00:54:29','2014-02-08 20:29:29',0,NULL,2),
  (7,'Education','Education in its general sense is a form of learning in which the knowledge, skills, and habits of a group of people are transferred from one generation to the next through teaching, training, or rese','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'7a96cd60217b8e49821c5e9fe3dce9d3d9e44ae7848711f1b1da76668d0f7617',3),
  (10,'Int Business','International business comprises all commercial transactions (private and governmental, sales, investments, logistics, and transportation) ','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'19f5f4e7ca135006240fe67a9dd59cca426dd6da68eb1bd2c6d22b3f9745de76',2),
  (12,'Software Engineer','Software Engineering is the study and application of engineering to the design, development, and maintenance of software','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'2f708070ecec7d81414c16dada6df8c8768f0716cdb36b9d3f280faefab80f69',2),
  (13,'Mechanical Engineer','Mechanical engineering is a discipline of engineering that applies the principles of engineering, physics and','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'4799a334b3db606e65a56f0a26f944f736a97802c8b37731d20d0f511956a188',3),
  (14,'Computer Engineer','Computer engineering is a discipline that integrates several fields of electrical engineering and computer science required to develop computer hardware and software.','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'3f40bac4ba0722873e9ef348b34d6fc669aa53b21163bf321e1cb1b6314efb8d',3),
  (15,'Accounting','Accounting, or accountancy, is the measurement, processing and communication of financial information about economic entities.','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'6721ed662ca15e11e4159329f7c303cd5654a2457ca077ff31510f1cc2364e6d',3),
  (16,'Actuary','An actuary is a business professional who deals with the financial impact of risk and uncertainty. Actuaries provide assessments of financial security systems, with a focus on their complexity','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'1146a02634caa4b199925d7555cecf939c2c7155660fc41cfeddc4ad2feb2b51',3),
  (17,'Business Administration','The administration of a business includes the performance or management of business operations and decision making as well as the efficient organization of people ','2014-02-13 23:28:42','2014-02-13 23:28:42',0,'8e02a506c3806cf1ea55bf5dc0604f3502d91c987f2a51d9dc523579c06e44a9',3),
  (18,'The Mafia','Mafia is a blanket term used to describe a type of organized crime syndicate that primarily practices protection racketeering ? the use of violent intimidation to manipulate local economic activity, ','2014-02-13 23:31:26','2014-02-13 23:31:26',0,'fb7e3d165ab51412b4c1c53fbb9a33b06efb10e4088817efc1f4dda21c5de17a',2),
  (19,'Social Science','Social science is an academic discipline concerned with society and the relationships among individuals within a society. ','2014-02-13 23:31:26','2014-02-13 23:31:26',0,'02bfe9989e72ba7b3f99ff75989de57f673d63b537912b66ccf0c6eb085d302d',3),
  (20,'Political Science','In political economics, entrepreneurship is a process of identifying ','2014-02-13 23:31:26','2014-02-13 23:31:26',0,'5af2b873e1353216a2b9487ca92ee14834283201fb71c2dfa573c9298d9f3ad0',2),
  (21,'Communism','Communism s a classless, moneyless,[1][2] and stateless social order structured upon common ownership of the means of production.','2014-02-13 23:31:26','2014-02-13 23:31:26',0,'18dd36c530c83f5b437b06de63befd65f016e96349d52efbf223251a3fca2ffd',3),
  (22,'Architect','An architect is a person trained and licensed to plan, design, and oversee the construction of buildings. To practice architecture means to provide services in connection with the design and construct','2014-02-14 10:31:54','2014-02-14 10:31:54',0,'c646c28629c8532f123c871c04854926cb5c1d02f6d919c3fa61eef0d6f958ab',3);

INSERT INTO `tag_descriptors` (`id`, `tag_name`, `creation_date`, `last_modified_date`, `semaphore`, `etag`)
VALUES
	(1, 'concordia', '2014-01-24 16:27:11', '2014-01-24 16:27:11', 0, NULL),
	(2, 'mcgill', '2014-01-24 17:40:16', '2014-01-24 17:40:19', 0, NULL),
	(3, 'test', '2014-01-24 17:40:23', '2014-01-24 17:40:23', 0, NULL),
	(5, 'ccc', '2014-01-24 17:40:28', '2014-01-24 17:40:28', 0, NULL),
	(6, 'resume', '2014-01-24 18:46:27', '2014-01-24 18:46:27', 0, NULL),
	(7, 'montreal', '2014-01-24 18:46:37', '2014-01-24 18:46:37', 0, NULL),
	(8, 'PEER', '2014-01-24 18:46:48', '2014-01-24 18:46:48', 0, NULL),
	(9, 'Accounting', '2014-01-24 18:47:04', '2014-01-24 18:47:04', 0, NULL),
	(11, 'blabla', '2014-01-24 20:48:29', '2014-01-24 20:48:29', 0, NULL),
	(12, 'university', '2014-01-25 19:20:03', '2014-01-25 19:20:03', 0, NULL),
	(13, 'google', '2014-02-14 11:55:59', '2014-02-14 11:55:59', 0, NULL),
	(14, 'microsoft', '2014-02-14 11:56:10', '2014-02-14 11:56:10', 0, NULL),
	(15, 'engineer', '2014-02-14 11:56:28', '2014-02-14 11:56:28', 0, NULL),
	(16, 'engineering', '2014-02-14 11:56:43', '2014-02-14 11:56:43', 0, NULL),
	(17, 'java', '2014-02-14 11:56:58', '2014-02-14 11:56:58', 0, NULL),
	(18, 'programming', '2014-02-14 11:57:07', '2014-02-14 11:57:07', 0, NULL),
	(19, 'cover', '2014-02-14 11:57:14', '2014-02-14 11:57:14', 0, NULL),
	(20, 'reviewed', '2014-02-14 11:57:54', '2014-02-14 11:57:54', 0, NULL),
	(21, 'history', '2014-02-14 11:58:15', '2014-02-14 11:58:15', 0, NULL),
	(22, 'past', '2014-02-14 11:58:30', '2014-02-14 11:58:30', 0, NULL),
	(23, 'senior', '2014-02-14 11:58:51', '2014-02-14 11:58:51', 0, NULL),
	(24, 'junior', '2014-02-14 11:59:00', '2014-02-14 11:59:00', 0, NULL),
	(25, 'finance', '2014-02-14 11:59:12', '2014-02-14 11:59:12', 0, NULL),
	(26, 'computer', '2014-02-14 11:59:35', '2014-02-14 11:59:35', 0, NULL),
	(27, 'internship', '2014-02-14 11:59:59', '2014-02-14 11:59:59', 0, NULL),
	(28, 'student', '2014-02-14 12:00:07', '2014-02-14 12:00:07', 0, NULL),
	(29, 'business', '2014-02-14 12:00:21', '2014-02-14 12:00:21', 0, NULL),
	(30, 'arts', '2014-02-14 12:00:30', '2014-02-14 12:00:30', 0, NULL),
	(31, 'manager', '2014-02-14 12:00:39', '2014-02-14 12:00:39', 0, NULL),
	(32, 'management', '2014-02-14 12:00:58', '2014-02-14 12:00:58', 0, NULL),
	(33, 'jsmb', '2014-02-16 11:34:21', '2014-02-16 11:34:21', 0, NULL),
	(34, 'canada', '2014-02-16 11:34:34', '2014-02-16 11:34:34', 0, NULL),
	(35, 'teaching', '2014-02-16 11:35:00', '2014-02-16 11:35:00', 0, NULL),
	(36, 'mba', '2014-02-16 11:35:20', '2014-02-16 11:35:20', 0, NULL),
	(37, 'javascript', '2014-02-16 11:35:44', '2014-02-16 11:35:44', 0, NULL),
	(38, 'freelance', '2014-02-16 11:35:56', '2014-02-16 11:35:56', 0, NULL),
	(39, 'freelancer', '2014-02-16 11:36:13', '2014-02-16 11:36:13', 0, NULL),
	(40, 'coverletter', '2014-02-16 11:36:30', '2014-02-16 11:36:30', 0, NULL);



INSERT INTO `taggables` (`id`, `child_id`, `type`, `creation_date`, `last_modified_date`, `semaphore`, `etag`)
VALUES
	(1, 1, 'Group', '2014-01-28 00:39:15', '2014-01-28 00:39:15', 0, NULL),
	(2, 2, 'Group', '2014-01-28 00:39:15', '2014-01-28 00:39:15', 0, NULL),
	(5, 3, 'Group', '2014-01-28 00:40:05', '2014-01-28 00:40:05', 0, NULL),
	(6, 4, 'Group', '2014-01-28 00:40:05', '2014-01-28 00:40:05', 0, NULL),
	(7, 5, 'Group', '2014-01-28 00:54:29', '2014-01-28 00:54:29', 0, NULL),
	(8, 6, 'Group', '2014-01-28 00:54:29', '2014-01-28 00:54:29', 0, NULL),
	(9, 1, 'Document', '2014-01-31 20:50:47', '2014-01-31 20:50:47', 0, NULL),
	(10, 2, 'Document', '2014-01-31 20:50:56', '2014-01-31 20:50:56', 0, NULL),
	(11, 3, 'Document', '2014-01-31 20:51:10', '2014-01-31 20:51:10', 0, NULL),
	(12, 4, 'Document', '2014-01-31 20:51:27', '2014-01-31 20:51:27', 0, NULL),
	(13, 5, 'Document', '2014-01-31 20:51:38', '2014-01-31 20:51:38', 0, NULL),
	(14, 6, 'Document', '2014-01-31 20:51:48', '2014-01-31 20:51:48', 0, NULL),
	(15, 7, 'Document', '2014-01-31 20:51:59', '2014-01-31 20:51:59', 0, NULL),
	(16, 8, 'Document', '2014-01-31 20:52:13', '2014-01-31 20:52:13', 0, NULL),
	(17, 9, 'Document', '2014-01-31 20:52:23', '2014-01-31 20:52:23', 0, NULL),
	(18, 7, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(19, 8, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(20, 9, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(21, 10, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(22, 11, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(23, 12, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(24, 13, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(25, 14, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(26, 15, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(27, 16, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(28, 17, 'Group', '2014-02-13 23:28:42', '2014-02-13 23:28:42', 0, NULL),
	(29, 18, 'Group', '2014-02-13 23:31:26', '2014-02-13 23:31:26', 0, NULL),
	(30, 19, 'Group', '2014-02-13 23:31:26', '2014-02-13 23:31:26', 0, NULL),
	(31, 20, 'Group', '2014-02-13 23:31:26', '2014-02-13 23:31:26', 0, NULL),
	(32, 21, 'Group', '2014-02-13 23:31:26', '2014-02-13 23:31:26', 0, NULL),
	(33, 22, 'Group', '2014-02-14 10:31:54', '2014-02-14 10:31:54', 0, NULL);


INSERT INTO `tags` (`id`, `tag_descriptor_id`, `taggable_id`, `semaphore`, `etag`)
VALUES
	(2, 12, 5, 0, NULL),
	(3, 3, 5, 0, NULL),
	(4, 7, 8, 0, NULL),
	(5, 7, 5, 0, NULL),
	(6, 7, 6, 0, NULL),
	(7, 13, 10, 0, NULL),
	(8, 19, 10, 0, NULL),
	(9, 40, 10, 0, NULL);


INSERT INTO `peers_groups` (`peer_id`, `group_id`)
  VALUES
  (2,3),
  (2,4),
  (2,5),
  (2,6),
  (2,10),
  (2,12),
  (2,13),
  (2,18),
  (2,20),
  (3,2),
  (3,5),
  (3,6),
  (3,7),
  (3,10),
  (3,12),
  (3,13),
  (3,14),
  (3,16),
  (3,17),
  (3,19),
  (3,21),
  (3,22),
  (8,3),
  (8,4),
  (8,6),
  (8,7),
  (8,10),
  (8,13),
  (8,14),
  (8,16),
  (8,17),
  (8,20),
  (8,21),
  (9,3),
  (9,5),
  (9,6),
  (9,7),
  (9,10),
  (9,14),
  (9,16),
  (9,17),
  (9,18),
  (9,19),
  (9,21),
  (10,3),
  (10,4),
  (10,5),
  (10,6),
  (10,7),
  (10,10),
  (10,12),
  (10,14),
  (10,15),
  (10,16),
  (10,17),
  (10,18),
  (10,20),
  (10,21);

