DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;

CREATE TABLE products
(
  id varchar(36) PRIMARY KEY,
  name varchar(200),
  default_price varchar(200),
  currency_string varchar(200),
  description varchar (300),
  category varchar (400),
  supplier varchar (200)
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
  department varchar (200),
  description VARCHAR(300)
);