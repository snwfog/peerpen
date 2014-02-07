ALTER TABLE avatars ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE avatars ADD etag varchar(256) DEFAULT NULL;