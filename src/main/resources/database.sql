-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  encryption INT(1) NOT NULL
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO users VALUES (1, 'antonid', '12345', 0);
