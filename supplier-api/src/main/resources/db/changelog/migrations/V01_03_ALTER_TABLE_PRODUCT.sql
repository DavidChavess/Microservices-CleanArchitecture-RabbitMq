--liquibase formatted sql

--changeset david:1
ALTER TABLE product ADD (
    description VARCHAR(255),
    state VARCHAR(2) NOT NULL
);