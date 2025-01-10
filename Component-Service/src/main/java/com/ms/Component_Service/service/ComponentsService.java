package com.ms.Component_Service.service;

import java.util.List;
import com.ms.Component_Service.dto.ComponentsDto;

public interface ComponentsService {

    ComponentsDto findById(Long id);

    List<ComponentsDto> findAll();

    ComponentsDto insert(ComponentsDto componentsDto);

    ComponentsDto update(ComponentsDto componentsDto);

    void deleteById(Long id);
}