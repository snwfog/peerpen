ALTER TABLE `avatars` ADD COLUMN `creation_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00';
ALTER TABLE `avatars` ADD COLUMN `last_modified_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00';
