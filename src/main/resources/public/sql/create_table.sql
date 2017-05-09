DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS shoppingcart;

CREATE TABLE products
(
  id              VARCHAR(36) PRIMARY KEY,
  name            VARCHAR(200),
  default_price   FLOAT(20),
  currency_string VARCHAR(200),
  description     VARCHAR (300),
  category_id      VARCHAR (400),--FOREIGN KEY
  supplier_id      VARCHAR (200)--FOREIGN KEY
);

CREATE TABLE suppliers
(
  id          VARCHAR(36) PRIMARY KEY,
  name        VARCHAR(200),
  description VARCHAR(300)
);


CREATE TABLE categories
(
  id          VARCHAR(36) PRIMARY KEY,
  name        VARCHAR(200),
  department  VARCHAR (200),
  description VARCHAR(300)
);

CREATE TABLE shoppingcart
(
  id          VARCHAR(36) PRIMARY KEY,
  productid   VARCHAR(200),--FOREIGN KEY
  quantity    DECIMAL (20)
);