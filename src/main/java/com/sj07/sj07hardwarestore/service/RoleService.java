package com.sj07.sj07hardwarestore.service;

import java.util.Collection;

import com.sj07.sj07hardwarestore.entities.authentic.Role;


public interface RoleService {
    Role getRoleByUserId(Long id);
    Collection<Role> getRoles();
}
