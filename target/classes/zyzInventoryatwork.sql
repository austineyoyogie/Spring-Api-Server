USE sj07hardwarestore;

DROP TABLE IF EXISTS UniqueIdentifiers;

CREATE TABLE UniqueIdentifiers (
                                   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   identifier_id VARCHAR(25) NOT NULL,
                                   created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT UQ_UniqueIdentifiers_Id UNIQUE (identifier_id)
);

DROP TABLE IF EXISTS Suppliers;

CREATE TABLE Suppliers
(
    id    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    identifier_id    BIGINT UNSIGNED NOT NULL,
    user_id          BIGINT UNSIGNED NOT NULL,
    full_name        VARCHAR(50) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    phone            VARCHAR(255) NOT NULL,
    is_active        BOOLEAN DEFAULT TRUE,
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_ProductSuppliers_Product_Id UNIQUE (identifier_id)
--     CONSTRAINT FK_Product_Suppliers_User_Id FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
--     CONSTRAINT FK_UniqueIdentifiers_Supplier_Id FOREIGN KEY (identifier_id) REFERENCES UniqueIdentifiers (id) ON DELETE CASCADE ON UPDATE CASCADE
);



DROP TABLE IF EXISTS Categories;

CREATE TABLE Categories
(
    id  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id      INT,
    supplier_id  INT,
    name         VARCHAR(255) NOT NULL,
    descriptions VARCHAR(255) NOT NULL,
    slug         VARCHAR(255) NOT NULL,
    is_active    BOOLEAN DEFAULT TRUE,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT   UQ_Categories_Name UNIQUE (name),
    CONSTRAINT   UQ_Categories_Slug UNIQUE (slug),
--     CONSTRAINT FK_User_Id FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Suppler_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CONSTRAINT FK_Categories_User_Id FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Categories_Supplier_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id) ON DELETE CASCADE ON UPDATE CASCADE



CREATE TABLE customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE contact (
                         id INT,
                         customer_id INT,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         INDEX par_ind (customer_id),
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id)
                             REFERENCES customer(id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
) ENGINE=INNODB;
=============================
CREATE TABLE customer (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE contact (
                         id BIGINT NOT NULL,
                         customer_id BIGINT NOT NULL,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY ( customer_id ) REFERENCES customer ( id ) ON DELETE CASCADE ON UPDATE RESTRICT
);



ALTER TABLE contact ADD INDEX par_ind ( customer_id );
ALTER TABLE contact ADD



    =============================


DROP TABLE IF EXISTS Products;

CREATE TABLE Products
(
    id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT UNSIGNED NOT NULL,
    category_id      BIGINT UNSIGNED NOT NULL,
    name             VARCHAR(50) NOT NULL,
    descriptions     TEXT NOT NULL,
    slug             VARCHAR(255) NOT NULL,
    sku_id           VARCHAR(100) NOT NULL,
    image            VARCHAR(255) NOT NULL,
    quantity         INTEGER NOT NULL DEFAULT 0,
    stock            INTEGER NOT NULL DEFAULT 0,
    is_active        BOOLEAN DEFAULT TRUE,
    outdated         BOOLEAN NOT NULL DEFAULT FALSE,
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_Products_SKU_Id UNIQUE (sku_id),
    CONSTRAINT UQ_Products_Slug UNIQUE (slug),
    CONSTRAINT FK_Products_User_Id FOREIGN KEY (id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Product_Categories_Id FOREIGN KEY (category_id) REFERENCES Categories (id) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS ProductCategories;

CREATE TABLE ProductCategories
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id  BIGINT UNSIGNED NOT NULL,
    category_id BIGINT UNSIGNED NOT NULL,
    CONSTRAINT  UQ_ProductCategories_Product_Id UNIQUE (product_id),
    CONSTRAINT  FK_Product_Categories_Product_Id FOREIGN KEY (product_id) REFERENCES Products (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT  FK_Product_Categories_Category_Id FOREIGN KEY (category_id) REFERENCES Categories (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);


FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE ON UPDATE CASCADE,

--     CONSTRAINT FK_Categories_User_Id FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
--     CONSTRAINT FK_Categories_Supplier_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers (id) ON DELETE CASCADE ON UPDATE CASCADE

CONSTRAINT UQ_Products_SKU_Id UNIQUE (sku_id),
CONSTRAINT UQ_Products_Slug UNIQUE (slug),
CONSTRAINT FK_Products_User_Id FOREIGN KEY (id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_Product_Categories_Id FOREIGN KEY (category_id) REFERENCES Categories (id) ON DELETE CASCADE ON UPDATE CASCADE

CONSTRAINT FK_Categories_User_Id FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT FK_Categories_Supplier_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers(supplier_id) ON DELETE CASCADE ON UPDATE CASCADE


CREATE TABLE customer (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE contact (
                         id BIGINT NOT NULL,
                         customer_id BIGINT NOT NULL,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY ( customer_id ) REFERENCES customer ( id ) ON DELETE CASCADE ON UPDATE RESTRICT
);




CREATE TABLE customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE contact (
                         id INT,
                         customer_id INT,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         INDEX par_ind (customer_id),
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id)
                             REFERENCES customer(id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
) ENGINE=INNODB;
=============================
CREATE TABLE customer (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
);

CREATE TABLE contact (
                         id BIGINT NOT NULL,
                         customer_id BIGINT NOT NULL,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         CONSTRAINT fk_customer FOREIGN KEY ( customer_id ) REFERENCES customer ( id ) ON DELETE CASCADE ON UPDATE RESTRICT
);



ALTER TABLE contact ADD INDEX par_ind ( customer_id );
ALTER TABLE contact ADD



    =============================



DROP TABLE IF EXISTS ProductCategories;

CREATE TABLE ProductCategories
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id  BIGINT UNSIGNED NOT NULL,
    category_id BIGINT UNSIGNED NOT NULL,
    CONSTRAINT  UQ_ProductCategories_Product_Id UNIQUE (product_id),
    CONSTRAINT  FK_Product_Categories_Product_Id FOREIGN KEY (product_id) REFERENCES Products (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT  FK_Product_Categories_Category_Id FOREIGN KEY (category_id) REFERENCES Categories (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);


FOREIGN KEY (emp_no) REFERENCES employees (emp_no) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (dept_no) REFERENCES departments (dept_no) ON DELETE CASCADE ON UPDATE CASCADE,

--     CONSTRAINT FK_Categories_User_Id FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE CASCADE ON UPDATE CASCADE,
--     CONSTRAINT FK_Categories_Supplier_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers (id) ON DELETE CASCADE ON UPDATE CASCADE



user_id      INT NOT NULL,          --BIGINT UNSIGNED,  BIGINT NOT NULL,  -- categoryID INT NOT NULL,
supplier_id  INT NOT NULL,           --BIGINT UNSIGNED,  BIGINT NOT NULL,  -- categoryID INT NOT NULL,

CREATE TABLE customer (
                          id INT NOT NULL AUTO_INCREMENT,
                          firstname varchar(50) NOT NULL,
                          lastname varchar(50) NOT NULL,
                          PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE contact (
                         id INT,
                         customer_id INT,
                         info varchar(50) NOT NULL,
                         type varchar(50) NOT NULL,
                         INDEX par_ind (customer_id),
                         CONSTRAINT fk_customer FOREIGN KEY (customer_id)
                             REFERENCES customer(id)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
) ENGINE=INNODB;