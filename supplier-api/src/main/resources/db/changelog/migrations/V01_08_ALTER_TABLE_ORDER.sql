--liquibase formatted sql

--changeset david:1
ALTER TABLE purchase_order ADD preparation_time_in_minutes INTEGER NOT NULL;
ALTER TABLE purchase_order RENAME COLUMN uuid TO purchase_id;