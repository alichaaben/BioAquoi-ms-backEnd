package com.ms.Project_Service.controller;

import com.ms.Project_Service.Exceptions.ResourceNotFoundException;
import com.ms.Project_Service.dto.ProjectDto;
import com.ms.Project_Service.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtos = projectService.findAllProjects();
        return ResponseEntity.ok(projectDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto projectDto = projectService.findProjectById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<?> createProject(@Validated @RequestBody ProjectDto projectDto) {
        try {
            ProjectDto savedProjectDto = projectService.createProject(projectDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProjectDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProject(@Validated @RequestBody ProjectDto projectDto) {
        try {
            ProjectDto updatedProjectDto = projectService.updateProject(projectDto);
            return ResponseEntity.ok(updatedProjectDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteById(id);
            return ResponseEntity.ok("Project with ID " + id + " successfully deleted.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the project: " + e.getMessage());
        }
    }
}