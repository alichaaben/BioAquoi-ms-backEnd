package com.ms.Component_Service.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.ms.Component_Service.dto.ComponentsDto;
import com.ms.Component_Service.entity.Components;

@Mapper(componentModel = "spring")
public interface ComponentsMapper {

    // Mapping Components -> ComponentsDto
    @Mapping(source = "componentName", target = "componentName")
    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    @Mapping(source = "projetIds", target = "projetIds")
    @Mapping(target = "projectNames", ignore = true)
    ComponentsDto map(Components entity);

    // Mapping List<Components> -> List<ComponentsDto>
    List<ComponentsDto> map(List<Components> entities);

    // Mapping ComponentsDto -> Components
    @Mapping(source = "componentName", target = "componentName")
    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    @Mapping(source = "projetIds", target = "projetIds")
    Components unMap(ComponentsDto dto);

    // Mapping ComponentsDto -> Components pour mise à jour
    @Mapping(source = "componentName", target = "componentName")
    @Mapping(source = "usageQuantity", target = "usageQuantity")
    @Mapping(source = "quantityInStock", target = "quantityInStock")
    @Mapping(source = "projetIds", target = "projetIds")
    void updateEntityFromDto(@MappingTarget Components entity, ComponentsDto dto);
}