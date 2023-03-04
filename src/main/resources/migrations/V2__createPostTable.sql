CREATE TABLE IF NOT EXISTS post (
  id bigint NOT NULL AUTO_INCREMENT,
  title varchar(255) NOT NULL DEFAULT '',
  description varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);