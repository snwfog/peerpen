ALTER TABLE feedables ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE feedables ADD etag varchar(256) DEFAULT NULL;