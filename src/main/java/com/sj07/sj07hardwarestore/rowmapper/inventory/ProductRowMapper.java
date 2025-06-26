package com.sj07.sj07hardwarestore.rowmapper.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Product.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .descri(resultSet.getString("descri"))
            .slug(resultSet.getString("slug"))
            .sku(resultSet.getString("sku"))
            .image(resultSet.getString("image"))
            .quantity(resultSet.getInt("quantity"))
            .price(resultSet.getBigDecimal("price"))
            .stock(resultSet.getInt("stock"))
            .isActive(resultSet.getBoolean("is_active"))
            .outdated(resultSet.getBoolean("outdated"))
            .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
            .build();
    }
}


