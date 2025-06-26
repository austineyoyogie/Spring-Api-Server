/*
package com.sj07.sj07hardwarestore.service.inventory.implementation;
*/
/*
import com.sj07.sj07hardwarestore.dto.inventory.ProductDTO;
import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.entities.inventory.Product;
import com.sj07.sj07hardwarestore.repository.inventory.CategoryRepository;
import com.sj07.sj07hardwarestore.repository.inventory.ProductRepository;
import com.sj07.sj07hardwarestore.service.inventory.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sj07.sj07hardwarestore.dtomapper.ProductDTOMapper.fromProduct;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository<Product> productRepository;
    private final CategoryRepository<Category> categoryRepository;

    @Override
    public ProductDTO createProduct(Product product) {
        return mapToProductDTO(productRepository.create(product));
    }

    @Override
    public ProductDTO getProductByName(String name) {
        return mapToProductDTO(productRepository.getProductByName(name));
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        return mapToProductDTO(productRepository.get(productId));
    }

    private ProductDTO mapToProductDTO(Product product) {
        return fromProduct(product, categoryRepository.getCategoryByProductId(product.getId()));
    }
}
*/