USE sj07hardwarestore;

DROP TABLE IF EXISTS UniqueIdentifiers;

CREATE TABLE UniqueIdentifiers (
   id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   user_id          BIGINT UNSIGNED NOT NULL,
   identifier       VARCHAR(25) NOT NULL,
   created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
   updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT UQ_UniqueIdentifiers_User_Id UNIQUE (user_id),
   CONSTRAINT UQ_UniqueIdentifiers_Id UNIQUE (identifier)
);

DROP TABLE IF EXISTS Suppliers;

CREATE TABLE Suppliers
(
    id               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id          BIGINT UNSIGNED NOT NULL,
    identifier_id    BIGINT UNSIGNED NOT NULL,
    full_name        VARCHAR(50) NOT NULL,
    address          VARCHAR(255) NOT NULL,
    phone            VARCHAR(255) NOT NULL,
    is_active        BOOLEAN DEFAULT TRUE,
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UQ_Suppliers_User_Id UNIQUE (user_id),
    CONSTRAINT UQ_Suppliers_UniqueIdentifiers_Id UNIQUE (identifier_id),
    CONSTRAINT FK_Suppliers_UniqueIdentifiers_Id FOREIGN KEY (identifier_id) REFERENCES UniqueIdentifiers (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Categories;

CREATE TABLE Categories
(
    id  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT UNSIGNED NOT NULL,
    supplier_id  BIGINT UNSIGNED NOT NULL,
    name         VARCHAR(255) NOT NULL,
    descriptions VARCHAR(255) NOT NULL,
    slug         VARCHAR(255) NOT NULL,
    is_active    BOOLEAN DEFAULT TRUE,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT   UQ_Categories_User_Id UNIQUE (user_id),
    CONSTRAINT   UQ_Categories_Suppliers_Id UNIQUE (supplier_id),
    CONSTRAINT   UQ_Categories_Name UNIQUE (name),
    CONSTRAINT   UQ_Categories_Slug UNIQUE (slug),
    CONSTRAINT   FK_Categories_Suppliers_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS Products;

CREATE TABLE Products
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id      BIGINT UNSIGNED NOT NULL,
    category_id  BIGINT UNSIGNED NOT NULL,
    supplier_id  BIGINT UNSIGNED NOT NULL,
    name         VARCHAR(50) NOT NULL,
    descriptions TEXT NOT NULL,
    slug         VARCHAR(255) NOT NULL,
    sku_id       VARCHAR(100) NOT NULL,
    image        VARCHAR(255) NOT NULL,
    quantity     INTEGER NOT NULL DEFAULT 0,
    stock        INTEGER NOT NULL DEFAULT 0,
    is_active    BOOLEAN DEFAULT TRUE,
    outdated     BOOLEAN NOT NULL DEFAULT FALSE,
    created_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT   UQ_Products_User_Id UNIQUE (user_id),
    CONSTRAINT   UQ_Products_Categories_Id UNIQUE (category_id),
    CONSTRAINT   UQ_Products_Suppliers_Id UNIQUE (supplier_id),
    CONSTRAINT   UQ_Products_Name UNIQUE (name),
    CONSTRAINT   UQ_Products_Slug UNIQUE (slug),
    CONSTRAINT   UQ_Products_Sku UNIQUE (slug),
    CONSTRAINT   FK_Products_Categories_Id FOREIGN KEY (category_id) REFERENCES Categories (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT   FK_Products_Suppliers_Id FOREIGN KEY (supplier_id) REFERENCES Suppliers (id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS ProductCategories;

CREATE TABLE ProductCategories
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id  BIGINT UNSIGNED NOT NULL,
    category_id BIGINT UNSIGNED NOT NULL,
    CONSTRAINT  UQ_ProductCategories_Product_Id UNIQUE (product_id),
    CONSTRAINT  UQ_ProductCategories_Category_Id UNIQUE (category_id),
    CONSTRAINT  FK_ProductCategories_Product_Id FOREIGN KEY (product_id) REFERENCES Products (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT  FK_ProductCategories_Category_Id FOREIGN KEY (category_id) REFERENCES Categories (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE RESTRICT
);


