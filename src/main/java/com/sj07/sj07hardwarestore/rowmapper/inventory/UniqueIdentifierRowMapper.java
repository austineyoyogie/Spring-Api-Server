package com.sj07.sj07hardwarestore.rowmapper.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.UniqueIdentifier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniqueIdentifierRowMapper implements RowMapper<UniqueIdentifier> {
    @Override
    public UniqueIdentifier mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return UniqueIdentifier.builder()
            .id(resultSet.getLong("id"))
            .userId(resultSet.getLong("user_id"))
            .identifier(resultSet.getString("identifier"))
            .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
            .build();
    }
}
