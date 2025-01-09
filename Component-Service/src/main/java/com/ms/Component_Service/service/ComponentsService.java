package com.ms.Component_Service.service;

import java.util.List;

import com.ms.Component_Service.entity.Components;

public interface ComponentsService {

    Components findById(Long id);

    List<Components> findAll();

    Components insert(Components Entity);

    Components update(Components Entity);

    void deleteById(Long id);
}