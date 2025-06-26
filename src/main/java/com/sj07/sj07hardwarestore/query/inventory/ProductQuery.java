package com.sj07.sj07hardwarestore.query.inventory;

public class ProductQuery {
    public static final String COUNT_PRODUCT_NAME_QUERY = "SELECT COUNT(*) FROM Products WHERE name = :name";
    public static final String INSERT_PRODUCT_QUERY = "INSERT INTO Products (name, descri, slug, sku, image, quantity, price, stock) VALUES (:name, :descri, :slug, :sku, :image, :quantity, :price, :stock)";
    public static final String SELECT_PRODUCT_BY_ID_QUERY = "SELECT * FROM Products WHERE id = :id";
    public static final String SELECT_PRODUCT_BY_NAME_QUERY = "SELECT * FROM Products WHERE name = :name";
}
