ALTER TABLE tag_descriptors ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE tag_descriptors ADD etag varchar(256) DEFAULT NULL;

ALTER TABLE taggables ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE taggables ADD etag varchar(256) DEFAULT NULL;

ALTER TABLE tags ADD semaphore int(1) NOT NULL DEFAULT '0';
ALTER TABLE tags ADD etag varchar(256) DEFAULT NULL;