-- Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,

  encryption INT(1) NOT NULL,
  public_key VARCHAR(255),
  private_key VARCHAR(255),

  fcm_token VARCHAR(255)
)
  ENGINE = InnoDB;

-- Insert data

INSERT INTO users VALUES (1, 'antonid',
                          '$2a$06$oq3Mc52nWjrDHxKscx4P0OCB0wj.tHDrmbdO8Moj9VTNpsJKZ2iwK', 0,
                          '123', '123', null);
