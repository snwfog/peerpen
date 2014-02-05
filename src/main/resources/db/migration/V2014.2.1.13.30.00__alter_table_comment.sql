ALTER TABLE comments ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE comments ADD etag varchar(256) DEFAULT NULL;