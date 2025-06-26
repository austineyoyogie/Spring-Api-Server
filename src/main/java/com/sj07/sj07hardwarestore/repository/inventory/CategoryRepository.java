package com.sj07.sj07hardwarestore.repository.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Category;

import java.util.Collection;

public interface CategoryRepository<T extends Category> {
    T create(T data);
    Collection<T> list();
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    void addCategoryToProduct(Long productId, String categoryName);
    Category createCategory(Category category);
    Category getCategoryByProductId(Long productId);
    Category getCategoryByProductName(String name);
    void updateProductCategory(Long productId, String categoryName);

}

