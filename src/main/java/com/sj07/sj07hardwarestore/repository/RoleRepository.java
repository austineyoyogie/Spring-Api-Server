package com.sj07.sj07hardwarestore.repository;

import java.util.Collection;

import com.sj07.sj07hardwarestore.entities.authentic.Role;

public interface RoleRepository<T extends Role> {
 
    T create(T data);
    Collection<T> list();
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    void addRoleToUser(Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);
}
