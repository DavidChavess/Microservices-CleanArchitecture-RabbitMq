--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS product (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL NOT NULL
);