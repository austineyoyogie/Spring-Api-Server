package com.sj07.sj07hardwarestore.rowmapper.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Category.builder()
            .id(resultSet.getLong("id"))
            .userId(resultSet.getLong("user_id"))
            .supplierId(resultSet.getLong("supplier_id"))
            .name(resultSet.getString("name"))
            .descriptions(resultSet.getString("descriptions"))
            .slug(resultSet.getString("slug"))
            .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
            .build();
    }
}

