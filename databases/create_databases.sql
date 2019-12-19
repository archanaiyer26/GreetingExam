DROP DATABASE IF EXISTS tracker_dev;

CREATE DATABASE tracker_dev;

CREATE USER IF NOT EXISTS 'tracker'@'localhost'
IDENTIFIED BY '';

GRANT ALL PRIVILEGES ON tracker_dev.* TO 'tracker' @'localhost';