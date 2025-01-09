package com.ms.Auth_Service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.Auth_Service.entity.Users;

public interface UsersRepo extends JpaRepository<Users,Long>{
    Optional<Users> findByUserName(String userName);
}
