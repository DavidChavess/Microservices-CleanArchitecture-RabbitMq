--liquibase formatted sql

--changeset david:1
ALTER TABLE product CHANGE price price DECIMAL(7,2) NOT NULL