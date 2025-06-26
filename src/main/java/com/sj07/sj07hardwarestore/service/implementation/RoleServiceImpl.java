package com.sj07.sj07hardwarestore.service.implementation;

import com.sj07.sj07hardwarestore.entities.authentic.Role;
import com.sj07.sj07hardwarestore.repository.RoleRepository;
import com.sj07.sj07hardwarestore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRoleRepository;

    @Override
    public Role getRoleByUserId(Long id) {
        return roleRoleRepository.getRoleByUserId(id);
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRoleRepository.list();
    }
}
