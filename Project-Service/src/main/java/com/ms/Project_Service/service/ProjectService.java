package com.ms.Project_Service.service;

import com.ms.Project_Service.dto.ProjectDto;
import com.ms.Project_Service.entity.Project;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findAllProjects();

    ProjectDto findProjectById(Long id);
    
    Project findByProjectName(String projectName);

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(ProjectDto projectDto);
    
    void deleteById(Long id);
}