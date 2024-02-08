-- language=PgSQL

START TRANSACTION;

CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(320) UNIQUE NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE stores (
    id_store SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE stores_users (
    id_store INT,
    id_user INT,
    is_admin BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (id_store) REFERENCES stores (id_store),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE products (
    id_product SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price REAL NOT NULL CHECK (price >= 0),
    quantity INT NOT NULL CHECK (quantity >= 0),
    description TEXT NOT NULL
);

CREATE TABLE stores_products (
    id_store INT,
    id_product INT,
    FOREIGN KEY (id_store) REFERENCES stores (id_store),
    FOREIGN KEY (id_product) REFERENCES products (id_product)
);

CREATE TABLE categories (
    id_category SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE categories_products (
    id_category INT,
    id_product INT,
    FOREIGN KEY (id_category) REFERENCES categories (id_category),
    FOREIGN KEY (id_product) REFERENCES products (id_product)
);

CREATE TABLE stores_categories (
    id_store INT,
    id_category INT,
    FOREIGN KEY (id_store) REFERENCES stores (id_store),
    FOREIGN KEY (id_category) REFERENCES categories (id_category)
);

CREATE TABLE transactions (
    id_transaction SERIAL PRIMARY KEY,
    type CHAR(3)  NOT NULL CHECK (type IN ('IN', 'OUT')),
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total REAL NOT NULL CHECK (total >= 0) DEFAULT 0
);  

CREATE TABLE transactions_products (
    id_transaction INT,
    id_product INT,
    quantity INT NOT NULL CHECK (quantity > 0),
    FOREIGN KEY (id_transaction) REFERENCES transactions (id_transaction),
    FOREIGN KEY (id_product) REFERENCES products (id_product)
);

CREATE OR REPLACE FUNCTION check_quantity()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.quantity > (SELECT quantity FROM products WHERE id_product = NEW.id_product) THEN
        RAISE EXCEPTION 'Invalid quantity for the products';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_quantity_trigger
BEFORE INSERT OR UPDATE ON transactions_products
FOR EACH ROW EXECUTE FUNCTION check_quantity();

CREATE TABLE stores_transactions (
    id_store INT,
    id_transaction INT,
    FOREIGN KEY (id_store) REFERENCES stores (id_store),
    FOREIGN KEY (id_transaction) REFERENCES transactions (id_transaction)
);

COMMIT;