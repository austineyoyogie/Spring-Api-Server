package com.sj07.sj07hardwarestore.repository.inventory.implementation;

import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.exception.ApiException;
import com.sj07.sj07hardwarestore.repository.inventory.CategoryRepository;
import com.sj07.sj07hardwarestore.rowmapper.inventory.CategoryRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.sj07.sj07hardwarestore.enumerator.CategoryType.*;
import static com.sj07.sj07hardwarestore.query.inventory.CategoryQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository<Category> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Category create(Category category) {
        if (getCategoryCount(category.getName()) > 0)
            throw new ApiException("Category already in use. Please use a different category name and try again.");
        if (getCategorySlugCount(category.getSlug().trim().toLowerCase()) > 0)
            throw new ApiException("Category already in use. Please use a different category name and try again.");
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(category);
            jdbcTemplate.update(INSERT_CATEGORY_QUERY, parameters, holder, new String[] {"id"});
            category.setId(requireNonNull(holder.getKey()).longValue());
            return category;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Collection<Category> list() {
        log.info("Fetching all categories");
        try {
           return jdbcTemplate.query(SELECT_CATEGORY_QUERY, new CategoryRowMapper());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Category get(Long id) {
        log.info("Fetching all categories");
        try {
            return jdbcTemplate.queryForObject(SELECT_CATEGORY_BY_ID_QUERY, of("id", id), new CategoryRowMapper());
        }  catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No category found by id:" + id);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Category update(Category data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }


    @Override
    public void addCategoryToProduct(Long productId, String categoryName) {
        log.info("Adding Category {} to product id: {}", categoryName, productId);
        try {
            Category category = jdbcTemplate.queryForObject(SELECT_CATEGORY_BY_NAME_QUERY, of("name", categoryName), new CategoryRowMapper());
            jdbcTemplate.update(INSERT_CATEGORY_TO_PRODUCT_QUERY, of("productId", productId, "categoryId", requireNonNull(category).getId()));
        } catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No category found by that name." + CATEGORY_PRODUCT_DEFAULT.name());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Category getCategoryByProductId(Long productId) {
        return null;
    }

    @Override
    public Category getCategoryByProductName(String name) {
        return null;
    }

    @Override
    public void updateProductCategory(Long productId, String categoryName) {

    }

    private Integer getCategoryCount(String name) {
        return jdbcTemplate.queryForObject(COUNT_CATEGORY_NAME_QUERY, of("name", name), Integer.class);
    }
    private Integer getCategorySlugCount(String slug) {
        return jdbcTemplate.queryForObject(COUNT_CATEGORY_SLUG_QUERY, of("slug", slug), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(Category category) {
        return new MapSqlParameterSource()
            .addValue("user_id", category.getUserId())
            .addValue("supplier_id", category.getSupplierId())
            .addValue("name", category.getName())
            .addValue("descriptions", category.getDescriptions())
            .addValue("slug", category.getSlug());
    }
}
