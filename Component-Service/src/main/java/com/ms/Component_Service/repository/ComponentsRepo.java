package com.ms.Component_Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.Component_Service.entity.Components;

public interface ComponentsRepo extends JpaRepository<Components,Long>{

}
