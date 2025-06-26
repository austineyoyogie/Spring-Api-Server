package com.sj07.sj07hardwarestore.repository.inventory.implementation;

import com.sj07.sj07hardwarestore.entities.inventory.UniqueIdentifier;
import com.sj07.sj07hardwarestore.exception.ApiException;
import com.sj07.sj07hardwarestore.repository.inventory.UniqueIdentifierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static com.sj07.sj07hardwarestore.query.inventory.UniqueIdentifierQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UniqueIdentifierRepositoryImpl implements UniqueIdentifierRepository<UniqueIdentifier> {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UniqueIdentifier create(UniqueIdentifier identifier) {
        if (getUniqueIdentifierCount(identifier.getIdentifier()) > 0)
            throw new ApiException("Unique identifier already in use. Please use a different unique name and try again.");
        try{
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(identifier);
            jdbcTemplate.update(INSERT_UNIQUE_IDENTIFIER_QUERY, parameters, holder, new String[] {"id"});
            identifier.setId(requireNonNull(holder.getKey()).longValue());
            return identifier;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public Collection<UniqueIdentifier> list() {
        return null;
    }

    @Override
    public UniqueIdentifier get(Long id) {
        return null;
    }

    @Override
    public UniqueIdentifier update(UniqueIdentifier data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addUniqueIdentifier(Long userId, String identifier) {

    }

    @Override
    public UniqueIdentifier createUniqueIdentifier(UniqueIdentifier identifier) {
        return null;
    }

    @Override
    public UniqueIdentifier getUniqueIdentifierById(Long identifierId) {
        return null;
    }

    @Override
    public UniqueIdentifier getUniqueIdentifierName(String identifier) {
        return null;
    }

    @Override
    public void updateIdentifier(Long identifierId, String identifier) {

    }

    private Integer getUniqueIdentifierCount(String identifier) {
        return jdbcTemplate.queryForObject(COUNT_UNIQUE_IDENTIFIER_NAME_QUERY, of("identifier", identifier), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(UniqueIdentifier uniqueIdentifier) {
        return new MapSqlParameterSource()
                .addValue("user_id", uniqueIdentifier.getUserId())
                .addValue("identifier", uniqueIdentifier.getIdentifier());
    }
}
