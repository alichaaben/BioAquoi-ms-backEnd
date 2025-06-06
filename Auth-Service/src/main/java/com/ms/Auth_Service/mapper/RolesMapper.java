package com.ms.Auth_Service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ms.Auth_Service.dto.RolesDto;
import com.ms.Auth_Service.entity.Roles;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    
    @Mapping(source = "roleName", target = "roleName")
    RolesDto map(Roles entity);


    
    List<RolesDto> map(List<Roles> entities);

    
    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    Roles unMap(RolesDto dto);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(target = "users", ignore = true)
    void updateEntityFromDto(@MappingTarget Roles entity, RolesDto dto);

}
