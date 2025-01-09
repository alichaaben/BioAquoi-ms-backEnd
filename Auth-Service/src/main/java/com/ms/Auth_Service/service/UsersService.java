package com.ms.Auth_Service.service;

import com.ms.Auth_Service.entity.Users;

import java.util.List;

public interface UsersService {
    Users findById(Long id);

    List<Users> findAll();

    Users findByUserName(String userName);

    Users insert(Users Entity);

    Users update(Users Entity);

    void deleteById(Long id);

}
