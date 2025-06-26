
/*
package com.sj07.sj07hardwarestore.dtomapper;
import com.sj07.sj07hardwarestore.dto.inventory.ProductDTO;
import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.entities.inventory.Product;
import org.springframework.beans.BeanUtils;

public class ProductDTOMapper {
    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static ProductDTO fromProduct(Product product, Category category) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setCategoryName(category.getName());
        productDTO.setDescriptions(category.getDescriptions());
        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
*/