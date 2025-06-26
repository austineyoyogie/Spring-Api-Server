package com.sj07.sj07hardwarestore.query.inventory;

public class CategoryQuery {
    public static final String COUNT_CATEGORY_NAME_QUERY = "SELECT COUNT(*) FROM Categories WHERE name = :name";
    public static final String COUNT_CATEGORY_SLUG_QUERY = "SELECT COUNT(*) FROM Categories WHERE slug = :slug";
    public static final String INSERT_CATEGORY_QUERY = "INSERT INTO Categories (user_id, supplier_id, name, descriptions, slug) VALUES (:user_id, :supplier_id, :name, :descriptions, :slug)";
    public static final String SELECT_CATEGORY_QUERY = "SELECT * FROM Categories ORDER BY id";
    public static final String SELECT_CATEGORY_BY_ID_QUERY = "SELECT * FROM Categories WHERE id = :id";
    public static final String INSERT_CATEGORY_TO_PRODUCT_QUERY = "INSERT INTO ProductCategories (product_id, category_id) VALUES (:productId, :categoryId)";
    public static final String SELECT_CATEGORY_BY_NAME_QUERY = "SELECT * FROM Categories WHERE name = :name";
    public static final String SELECT_CATEGORY_BY_GROUP_ID_QUERY = "SELECT c.id, c.name, c.descriptions FROM Categories c JOIN ProductCategories pc ON pc.category_id = c.id JOIN Products p ON p.id = pc.product_id WHERE p.id = :id";
    public static final String UPDATE_PRODUCT_CATEGORY_QUERY = "UPDATE ProductCategories SET category_id = :categoryId WHERE product_id = :productId";
}
