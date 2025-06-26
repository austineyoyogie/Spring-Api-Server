package com.sj07.sj07hardwarestore.repository.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.UniqueIdentifier;

import java.util.Collection;

public interface UniqueIdentifierRepository<T extends UniqueIdentifier> {
    T create(T data);
    Collection<T> list();
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    void addUniqueIdentifier(Long userId, String identifier);
    UniqueIdentifier createUniqueIdentifier(UniqueIdentifier identifier);
    UniqueIdentifier getUniqueIdentifierById(Long identifierId);
    UniqueIdentifier getUniqueIdentifierName(String identifier);
    void updateIdentifier(Long identifierId, String identifier);
}
