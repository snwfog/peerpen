ALTER TABLE changesets DROP FOREIGN KEY changesets_ibfk_1;
ALTER TABLE changesets DROP FOREIGN KEY changesets_ibfk_2;
ALTER TABLE changesets DROP COLUMN document_id;
ALTER TABLE changesets DROP COLUMN peer_id;