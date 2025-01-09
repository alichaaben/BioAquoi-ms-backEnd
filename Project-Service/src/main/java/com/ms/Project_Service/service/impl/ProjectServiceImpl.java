package com.ms.Project_Service.service.impl;

import com.ms.Project_Service.dto.ProjectDto;
import com.ms.Project_Service.dto.UsersDto;
import com.ms.Project_Service.entity.Project;
import com.ms.Project_Service.mapper.ProjectMapper;
import com.ms.Project_Service.openFeign.UserRestClient;
import com.ms.Project_Service.repository.ProjectRepo;
import com.ms.Project_Service.service.ProjectService;
import com.ms.Project_Service.Exceptions.ResourceNotFoundException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;
    private final UserRestClient userRestClient;

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(projectMapper::map)
                .collect(Collectors.toList());
    }
    
    @Override
    public ProjectDto findProjectById(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));
        return projectMapper.map(project);
    }

    @Override
    public Project findByProjectName(String projectName) {
        return projectRepo.findByProjectName(projectName)
        .orElseThrow(() -> new ResourceNotFoundException("Project not found with Name: " + projectName));

    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        try {
            UsersDto user = userRestClient.findById(projectDto.getUserId());
            if (user == null) {
                throw new ResourceNotFoundException("User not found with ID: " + projectDto.getUserId());
            }

            Project project = projectMapper.unMap(projectDto);
            project.setUserId(user.getUserId());

            Project savedProject = projectRepo.save(project);

            return projectMapper.map(savedProject);

        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("User not found with ID: " + projectDto.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the project: " + e.getMessage());
        }
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        try {
            UsersDto user = userRestClient.findById(projectDto.getUserId());
            if (user == null) {
                throw new ResourceNotFoundException("User not found with ID: " + projectDto.getUserId());
            }

            Project project = projectMapper.unMap(projectDto);

            Project existingProject = projectRepo.findById(project.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + project.getProjectId()));

            existingProject.setProjectName(project.getProjectName());
            existingProject.setStatus(project.getStatus());
            existingProject.setUserId(project.getUserId());

            Project updatedProject = projectRepo.save(existingProject);

            return projectMapper.map(updatedProject);

        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("User not found with ID: " + projectDto.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the project: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!projectRepo.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with ID: " + id);
        }
        projectRepo.deleteById(id);
    }

}