package com.ms.Auth_Service.controller;

import com.ms.Auth_Service.dto.RolesDto;
import com.ms.Auth_Service.entity.Roles;
import com.ms.Auth_Service.mapper.RolesMapper;
import com.ms.Auth_Service.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin("*")
public class RolesController {

    private final RolesMapper rolesMapper;
    private final RolesService rolesService;

    public RolesController(RolesMapper rolesMapper, RolesService rolesService) {
        this.rolesMapper = rolesMapper;
        this.rolesService = rolesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDto> findById(@PathVariable Long id) {
        Roles role = rolesService.findById(id);
        return ResponseEntity.ok(rolesMapper.map(role));
    }

    @GetMapping
    public ResponseEntity<List<RolesDto>> findAll() {
        List<Roles> roles = rolesService.findAll();
        return ResponseEntity.ok(rolesMapper.map(roles));
    }

    @PostMapping
    public ResponseEntity<RolesDto> insert(@RequestBody RolesDto rolesDto) {
        Roles newRole = rolesMapper.unMap(rolesDto);
        Roles savedRole = rolesService.insert(newRole);
        return ResponseEntity.ok(rolesMapper.map(savedRole));
    }

    @PutMapping
    public ResponseEntity<RolesDto> update(@RequestBody RolesDto rolesDto) {
        Roles updatedRole = rolesService.update(rolesMapper.unMap(rolesDto));
        return ResponseEntity.ok(rolesMapper.map(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        rolesService.deleteById(id);
        return ResponseEntity.ok("Role with ID " + id + " successfully deleted.");
    }
}
