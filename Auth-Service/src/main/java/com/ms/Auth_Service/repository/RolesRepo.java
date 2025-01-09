package com.ms.Auth_Service.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.Auth_Service.entity.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Long> {
    Optional<Roles> findByRoleName(String roleName);
}
