package com.ms.Component_Service.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.Component_Service.dto.ComponentsDto;
import com.ms.Component_Service.dto.ProjectDto;
import com.ms.Component_Service.entity.Components;
import com.ms.Component_Service.mapper.ComponentsMapper;
import com.ms.Component_Service.openFeign.ProjectRestClient;
import com.ms.Component_Service.repository.ComponentsRepo;
import com.ms.Component_Service.service.ComponentsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ComponentsServiceImpl implements ComponentsService {

    @Autowired
    private ComponentsRepo componentsRepo;

    @Autowired
    private ComponentsMapper componentsMapper;

    @Autowired
    private ProjectRestClient projectRestClient;

    @Override
    public ComponentsDto findById(Long id) {
        Components component = componentsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Component not found with ID: " + id));
        return mapComponentWithProjectDetails(component);
    }

    @Override
    public List<ComponentsDto> findAll() {
        List<Components> components = componentsRepo.findAll();
        return components.stream()
                .map(this::mapComponentWithProjectDetails)
                .collect(Collectors.toList());
    }

    @Override
    public ComponentsDto insert(ComponentsDto componentsDto) {
        for (Long projetId : componentsDto.getProjetIds()) {
            try {
                ProjectDto projectDto = projectRestClient.findById(projetId);
                if (projectDto == null) {
                    throw new IllegalArgumentException("Project not found with ID: " + projetId);
                }
            } catch (HttpClientErrorException.NotFound e) {
                throw new IllegalArgumentException("Project not found with ID: " + projetId);
            }
        }

        Components component = componentsMapper.unMap(componentsDto);
        Components savedComponent = componentsRepo.save(component);
        return mapComponentWithProjectDetails(savedComponent);
    }

    @Override
    public ComponentsDto update(ComponentsDto componentsDto) {
        Long id = componentsDto.getComposentId();
        if (id == null) {
            throw new IllegalArgumentException("Component ID is required for update");
        }

        Components existingComponent = componentsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Component not found with ID: " + id));

        for (Long projetId : componentsDto.getProjetIds()) {
            try {
                ProjectDto projectDto = projectRestClient.findById(projetId);
                if (projectDto == null) {
                    throw new IllegalArgumentException("Project not found with ID: " + projetId);
                }
            } catch (HttpClientErrorException.NotFound e) {
                throw new IllegalArgumentException("Project not found with ID: " + projetId);
            }
        }

        componentsMapper.updateEntityFromDto(existingComponent, componentsDto);
        Components updatedComponent = componentsRepo.save(existingComponent);
        return mapComponentWithProjectDetails(updatedComponent);
    }

    @Override
    public void deleteById(Long id) {
        Components component = componentsRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Component not found with ID: " + id));
        componentsRepo.delete(component);
    }

    private ComponentsDto mapComponentWithProjectDetails(Components component) {
        ComponentsDto dto = componentsMapper.map(component);
        Set<String> projectNames = new HashSet<>();

        for (Long projetId : component.getProjetIds()) {
            ProjectDto projectDto = projectRestClient.findById(projetId);
            if (projectDto != null) {
                projectNames.add(projectDto.getProjectName());
            }
        }

        dto.setProjectNames(projectNames);
        return dto;
    }
}