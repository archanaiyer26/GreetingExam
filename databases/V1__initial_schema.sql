CREATE TABLE greeting_generator (
  id         BIGINT(20) NOT NULL AUTO_INCREMENT,
  message    VARCHAR(20),

  PRIMARY KEY (id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8;