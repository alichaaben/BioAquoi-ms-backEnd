package com.ms.Component_Service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ms.Component_Service.dto.ComponentsDto;
import com.ms.Component_Service.entity.Components;


@Mapper(componentModel = "spring")
public interface ComponentsMapper {

    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    // @Mapping(source = "project.projectName", target = "projectName")
    ComponentsDto map(Components entity);

    List<ComponentsDto> map(List<Components> entities);

    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    // @Mapping(source = "projectName", target = "project.projectName")
    Components unMap(ComponentsDto dto);

    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    // @Mapping(source = "projectName", target = "project.projectName")
    void updateEntityFromDto(@MappingTarget Components entity, ComponentsDto dto);
}