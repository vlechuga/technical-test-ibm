

DROP TABLE IF EXISTS client;

CREATE TABLE client (
  client_id bigint PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  address VARCHAR(100) NOT NULL,
  city VARCHAR(30) DEFAULT NULL,
  phone VARCHAR(20) DEFAULT NULL
);

DROP TABLE IF EXISTS adviser;

CREATE TABLE adviser (
  id bigint PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  specialty VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS card;

CREATE TABLE card (
  card_id bigint  PRIMARY KEY,
  number VARCHAR(50) NOT NULL UNIQUE,
  type VARCHAR(50) NOT NULL,
  client_id bigint REFERENCES client(client_id) ON DELETE CASCADE ON UPDATE CASCADE,
);

DROP TABLE IF EXISTS audit;

CREATE TABLE audit (
  id bigint  PRIMARY KEY,
  creation_date timestamp without time zone NOT NULL,
  description VARCHAR(100) NOT NULL,
  amount DECIMAL(10, 2) NOT NULL,
  card_id bigint REFERENCES card(card_id) ON DELETE CASCADE ON UPDATE CASCADE,
);