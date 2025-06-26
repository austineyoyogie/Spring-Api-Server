package com.sj07.sj07hardwarestore.query.inventory;

public class UniqueIdentifierQuery {
    public static final String COUNT_UNIQUE_IDENTIFIER_NAME_QUERY = "SELECT COUNT(*) FROM UniqueIdentifiers WHERE identifier = :identifier";
    public static final String INSERT_UNIQUE_IDENTIFIER_QUERY = "INSERT INTO UniqueIdentifiers (user_id, identifier) VALUES (:user_id, :identifier)";
}
