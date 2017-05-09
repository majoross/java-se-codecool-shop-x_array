DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS shoppingcart;

CREATE TABLE suppliers
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(200),
  description VARCHAR(300)
);


CREATE TABLE categories
(
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(200),
  department  VARCHAR (200),
  description VARCHAR(300)
);

CREATE TABLE products
(
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(200),
  default_price   FLOAT,
  currency_string VARCHAR(200),
  description     VARCHAR (300),
  category_id      INT REFERENCES categories (id),
  supplier_id      INT REFERENCES suppliers (id)
);

CREATE TABLE shoppingcart
(
  id          SERIAL PRIMARY KEY,
  product_id   INT REFERENCES products (id),
  quantity    INT
);