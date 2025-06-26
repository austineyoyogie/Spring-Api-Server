package com.sj07.sj07hardwarestore.service.inventory;

import com.sj07.sj07hardwarestore.dto.inventory.ProductDTO;
import com.sj07.sj07hardwarestore.entities.inventory.Product;


public interface ProductService {
    ProductDTO createProduct(Product product);
    ProductDTO getProductByName(String name);
    ProductDTO getProductById(Long productId);
}
