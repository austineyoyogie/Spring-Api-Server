package com.sj07.sj07hardwarestore.service.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Category;

import java.util.Collection;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryByProductId(Long id);
    Collection<Category> getCategories();
}
