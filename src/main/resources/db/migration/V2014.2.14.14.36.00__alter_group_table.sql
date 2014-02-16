ALTER TABLE groups ADD admin_id int;
ALTER TABLE groups ADD FOREIGN KEY (admin_id) REFERENCES peers (id) on delete set null;