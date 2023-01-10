--liquibase formatted sql

--changeset david:1
CREATE TABLE IF NOT EXISTS purchase_order_item (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    order_id INT NOT NULL,
    total DECIMAL NOT NULL,
    quantity DECIMAL NOT NULL,
    CONSTRAINT product_id_fk FOREIGN KEY(product_id) REFERENCES product(id),
    CONSTRAINT order_id_fk FOREIGN KEY(order_id) REFERENCES purchase_order(id)
);