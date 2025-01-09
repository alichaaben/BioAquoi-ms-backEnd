package com.ms.Auth_Service.service;

import com.ms.Auth_Service.entity.Roles;

import java.util.List;

public interface RolesService {
    Roles findById(Long id);

    List<Roles> findAll();

    Roles findByRoleName(String roleName);

    Roles insert(Roles Entity);

    Roles update(Roles Entity);

    void deleteById(Long id);
}
