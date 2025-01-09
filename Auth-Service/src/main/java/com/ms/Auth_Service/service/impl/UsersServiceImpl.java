package com.ms.Auth_Service.service.impl;

import com.ms.Auth_Service.entity.Users;
import com.ms.Auth_Service.repository.UsersRepo;
import com.ms.Auth_Service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepo;

    @Override
    public Users findById(Long id) {
        return usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public List<Users> findAll() {
        return usersRepo.findAll();
    }

    @Override
    public Users findByUserName(String userName) {
        return usersRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userName));
    }

    @Override
    public Users insert(Users user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new RuntimeException("Username cannot be empty.");
        }
        return usersRepo.save(user);
    }

    @Override
    public Users update(Users user) {
        if (!usersRepo.existsById(user.getUserId())) {
            throw new RuntimeException("User not found with ID: " + user.getUserId());
        }
        return usersRepo.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!usersRepo.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        usersRepo.deleteById(id);
    }
}