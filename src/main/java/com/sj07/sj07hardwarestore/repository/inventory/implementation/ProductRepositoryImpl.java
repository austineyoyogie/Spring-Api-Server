package com.sj07.sj07hardwarestore.repository.inventory.implementation;

import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.entities.inventory.Product;
import com.sj07.sj07hardwarestore.exception.ApiException;
import com.sj07.sj07hardwarestore.repository.inventory.CategoryRepository;
import com.sj07.sj07hardwarestore.repository.inventory.ProductRepository;
import com.sj07.sj07hardwarestore.rowmapper.inventory.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;


import static com.sj07.sj07hardwarestore.enumerator.CategoryType.CATEGORY_PRODUCT_DEFAULT;
import static com.sj07.sj07hardwarestore.query.inventory.ProductQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository<Product> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CategoryRepository<Category> categoryRepository;

    @Override
    public Product create(Product product) {
        if(getProductCount(product.getName()) > 0)
            throw new ApiException("Product already in use. Please use a different product and try again.");
        try {
            KeyHolder holder = new GeneratedKeyHolder(); // Get the id of the user in database
            SqlParameterSource parameters = getSqlParameterSource(product);
            jdbcTemplate.update(INSERT_PRODUCT_QUERY, parameters, holder, new String[] {"id"});
            product.setId(requireNonNull(holder.getKey()).longValue());
            categoryRepository.addCategoryToProduct(product.getId(), CATEGORY_PRODUCT_DEFAULT.name());
            product.setIsActive(true);
            product.setOutdated(false);
            return product;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Collection<Product> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Product get(Long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_QUERY, of("id", id), new ProductRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No Product found by id: " + id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Product update(Product data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Product getProductByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_NAME_QUERY, of("name", name), new ProductRowMapper());
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No Product found by name: " + name);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }


    private Integer getProductCount(String name) {
        return jdbcTemplate.queryForObject(COUNT_PRODUCT_NAME_QUERY, of("name", name), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(Product product) {
        return new MapSqlParameterSource()
            .addValue("name", product.getName())
            .addValue("descri", product.getDescri())
            .addValue("slug", product.getSlug())
            .addValue("sku", product.getSku())
            .addValue("image", product.getImage())
            .addValue("quantity", product.getQuantity())
            .addValue("price", product.getPrice())
            .addValue("stock", product.getStock());
    }
}
