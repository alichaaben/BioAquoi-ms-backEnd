package com.ms.Auth_Service.controller;

import com.ms.Auth_Service.dto.UsersDto;
import com.ms.Auth_Service.entity.Roles;
import com.ms.Auth_Service.entity.Users;
import com.ms.Auth_Service.mapper.UsersMapper;
import com.ms.Auth_Service.repository.RolesRepo;
import com.ms.Auth_Service.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.ms.Auth_Service.Exceptions.ResourceNotFoundException;


import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;
    private final RolesRepo rolesRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> findById(@PathVariable Long id) {
        Users user = usersService.findById(id);
        return ResponseEntity.ok(usersMapper.map(user));
    }

    @GetMapping
    public ResponseEntity<List<UsersDto>> findAll() {
        List<Users> users = usersService.findAll();
        return ResponseEntity.ok(usersMapper.map(users));
    }



    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {

        Roles role = rolesRepo.findByRoleName("ROLE_"+usersDto.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + usersDto.getRoleName()));

        Users user = usersMapper.unMap(usersDto);
        user.setRole(role);

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        Users savedUser = usersService.insert(user);
        UsersDto savedUsersDto = usersMapper.map(savedUser);

        return ResponseEntity.ok(savedUsersDto);
    }

    @PutMapping
    public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto usersDto) {
        Users existingUser = usersService.findById(usersDto.getUserId());
                // .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + usersDto.getUserId()));

        existingUser.setUserName(usersDto.getUserName());
        existingUser.setEmail(usersDto.getEmail());

        if (usersDto.getRoleName() != null) {
            Roles role = rolesRepo.findByRoleName("ROLE_" + usersDto.getRoleName())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with name: " + usersDto.getRoleName()));
            existingUser.setRole(role);
        }

        if (usersDto.getPassword() != null && !usersDto.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(usersDto.getPassword());
            existingUser.setPassword(hashedPassword);
        }

        Users updatedUser = usersService.update(existingUser);

        UsersDto updatedUsersDto = usersMapper.map(updatedUser);
        return ResponseEntity.ok(updatedUsersDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}