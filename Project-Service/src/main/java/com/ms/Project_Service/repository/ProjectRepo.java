package com.ms.Project_Service.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.Project_Service.entity.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project,Long>{
    Optional<Project> findByProjectName(String ProjectName);
}
