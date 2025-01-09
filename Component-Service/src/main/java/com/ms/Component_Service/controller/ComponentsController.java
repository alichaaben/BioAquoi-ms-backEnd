package com.ms.Component_Service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.Component_Service.Exceptions.ResourceNotFoundException;
import com.ms.Component_Service.dto.ComponentsDto;
import com.ms.Component_Service.entity.Components;
import com.ms.Component_Service.mapper.ComponentsMapper;
import com.ms.Component_Service.service.ComponentsService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/components")
@RequiredArgsConstructor
public class ComponentsController {

    private final ComponentsService componentsService;
    private final ComponentsMapper componentsMapper;
//    private final ProjectRepo projectRepo;

    @GetMapping
    public ResponseEntity<List<ComponentsDto>> getAllComponents() {
        List<Components> components = componentsService.findAll();
        List<ComponentsDto> componentsDtos = componentsMapper.map(components);
        return ResponseEntity.ok(componentsDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentsDto> getComponentsById(@PathVariable Long id) {
        Components components = componentsService.findById(id);
        ComponentsDto componentsDto = componentsMapper.map(components);
        return ResponseEntity.ok(componentsDto);
    }

    @PostMapping()
    public ResponseEntity<ComponentsDto> createComponents(@RequestBody ComponentsDto componentsDto) {

//        Project project = projectRepo.findByProjectName(componentsDto.getProjectName());
//        if (project == null) {
//            throw new ResourceNotFoundException("project not found with username: " + componentsDto.getProjectName());
//        }
       
        Components components = componentsMapper.unMap(componentsDto);
//        components.setProject(project);

        Components savedComponents = componentsService.insert(components);
        ComponentsDto savedComponentsDto = componentsMapper.map(savedComponents);
        return ResponseEntity.ok(savedComponentsDto);
    }


    @PutMapping()
    public ResponseEntity<ComponentsDto> updateComponents(@RequestBody ComponentsDto componentsDto) {
        Components existingComponents = componentsService.findById(componentsDto.getMaterialId());
        componentsMapper.updateEntityFromDto(existingComponents, componentsDto);
        Components updatedComponents = componentsService.update(existingComponents);
        ComponentsDto updatedComponentsDto = componentsMapper.map(updatedComponents);
        return ResponseEntity.ok(updatedComponentsDto);
    }


    @DeleteMapping("/{id}")
    public void deleteComponents(@PathVariable Long id) {
        Components components = componentsService.findById(id);
        if (components == null) {
            throw new ResourceNotFoundException("Components not found with ID: " + id);
        }
        componentsService.deleteById(id);
    }

}