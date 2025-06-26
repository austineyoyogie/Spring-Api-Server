package com.sj07.sj07hardwarestore.repository.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Product;

import java.util.Collection;

public interface ProductRepository<T extends Product> {
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    Product getProductByName(String name);
}
