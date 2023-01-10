--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS product (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL NOT NULL,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    state VARCHAR(2) NOT NULL
);