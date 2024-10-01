DROP
  TABLE IF EXISTS customer_order;
DROP
  TABLE IF EXISTS customers;
DROP
  TABLE IF EXISTS products;

CREATE TABLE customers (
  customer_id SERIAL primary key,
  name VARCHAR(100),
  email VARCHAR(100)
);

CREATE TABLE products (
  product_id SERIAL primary key,
  description VARCHAR(100),
  price NUMERIC(10, 2)
);

CREATE TABLE customer_order (
  order_id uuid default gen_random_uuid() primary key,
  customer_id int,
  product_id int,
  amount NUMERIC(10, 2),
  address VARCHAR(500),
  order_date TIMESTAMP WITH TIME ZONE default CURRENT_TIMESTAMP,
  foreign key (customer_id) references customers(customer_id) on delete cascade,
  foreign key (product_id) references products(product_id)
);

CREATE SEQUENCE customer_sequence
START WITH 1
INCREMENT BY 100;

CREATE SEQUENCE product_sequence
START WITH 1
INCREMENT BY 200;

TRUNCATE customer_order;
DELETE FROM customers;
DELETE FROM products;