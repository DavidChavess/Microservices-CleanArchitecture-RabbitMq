--liquibase formatted sql

--changeset david:1
ALTER TABLE purchase_order CHANGE total total DECIMAL(7,2) NOT NULL;
ALTER TABLE purchase_order_item CHANGE total total DECIMAL(7,2) NOT NULL;
ALTER TABLE purchase_order_item CHANGE quantity quantity DECIMAL(7,2) NOT NULL;