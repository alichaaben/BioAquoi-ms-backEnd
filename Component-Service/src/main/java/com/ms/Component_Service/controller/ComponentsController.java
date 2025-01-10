package com.ms.Component_Service.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ms.Component_Service.dto.ComponentsDto;
import com.ms.Component_Service.service.ComponentsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/components")
public class ComponentsController {

    private final ComponentsService componentsService;

    public ComponentsController(ComponentsService componentsService) {
        this.componentsService = componentsService;
    }

    @GetMapping
    public ResponseEntity<List<ComponentsDto>> getAllComponents() {
        List<ComponentsDto> componentsDtos = componentsService.findAll();
        return ResponseEntity.ok(componentsDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentsDto> getComponentsById(@PathVariable Long id) {
        ComponentsDto componentsDto = componentsService.findById(id);
        return ResponseEntity.ok(componentsDto);
    }

    @PostMapping
    public ResponseEntity<?> createComponents(@RequestBody ComponentsDto componentsDto) {
        try {
            ComponentsDto savedComponentsDto = componentsService.insert(componentsDto);
            return ResponseEntity.ok(savedComponentsDto);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateComponents(@RequestBody ComponentsDto componentsDto) {
        try {
            ComponentsDto updatedComponentsDto = componentsService.update(componentsDto);
            return ResponseEntity.ok(updatedComponentsDto);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComponents(@PathVariable Long id) {
        componentsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}