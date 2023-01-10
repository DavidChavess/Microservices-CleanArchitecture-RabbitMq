--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS purchase_order (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    uuid VARCHAR(255) NOT NULL UNIQUE,
    total DECIMAL NOT NULL,
    created_on DATETIME NOT NULL DEFAULT NOW()
);