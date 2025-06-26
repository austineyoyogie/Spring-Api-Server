package com.sj07.sj07hardwarestore.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.sj07.sj07hardwarestore.entities.authentic.Role;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Role.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .permissions(resultSet.getString("permissions"))
            .build();
    }
}
