package com.sj07.sj07hardwarestore.service.inventory.implementation;

import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.repository.inventory.CategoryRepository;
import com.sj07.sj07hardwarestore.service.inventory.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository<Category> categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Category getCategoryByProductId(Long id) {
        return categoryRepository.getCategoryByProductId(id);
    }

    @Override
    public Collection<Category> getCategories() {
        return categoryRepository.list();
    }
}
