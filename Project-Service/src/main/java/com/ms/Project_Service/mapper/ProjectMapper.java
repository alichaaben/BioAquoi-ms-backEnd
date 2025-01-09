package com.ms.Project_Service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ms.Project_Service.dto.ProjectDto;
import com.ms.Project_Service.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userId", target = "userId")
    ProjectDto map(Project entity);

    List<ProjectDto> map(List<Project> entities);

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userId", target = "userId")
    Project unMap(ProjectDto dto);

    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "userId", target = "userId")
    void updateEntityFromDto(@MappingTarget Project entity, ProjectDto dto);

}
