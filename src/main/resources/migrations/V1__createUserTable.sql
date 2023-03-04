CREATE TABLE IF NOT EXISTS user (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL DEFAULT '',
  email varchar(255) NOT NULL DEFAULT '',
  password varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);