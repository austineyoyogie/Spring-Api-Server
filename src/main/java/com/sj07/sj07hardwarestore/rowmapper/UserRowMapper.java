package com.sj07.sj07hardwarestore.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import com.sj07.sj07hardwarestore.entities.authentic.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return User.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .email(resultSet.getString("email"))
            .password(resultSet.getString("password"))
            .telephone(resultSet.getString("telephone"))
            .title(resultSet.getString("title"))
            .imageUrl(resultSet.getString("image_url"))
            .loginAttempts(resultSet.getInt("login_attempts"))
            .isUsingMfa(resultSet.getBoolean("is_using_mfa"))
            .isEnabled(resultSet.getBoolean("is_enabled"))
            .isNotLocked(resultSet.getBoolean("is_not_locked"))
            .isExpired(resultSet.getBoolean("is_expired"))
            .lastLogin(resultSet.getTimestamp("last_login").toLocalDateTime())
            .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
            .updatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime())
            .build();
    }
}
