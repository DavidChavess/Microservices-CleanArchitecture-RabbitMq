--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS supplier (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zipcode VARCHAR(50) NOT NULL
);