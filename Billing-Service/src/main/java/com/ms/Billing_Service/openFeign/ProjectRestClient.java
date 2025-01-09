package com.ms.Billing_Service.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ms.Billing_Service.dto.ProjectDto;

@FeignClient(name="Project-service")
public interface ProjectRestClient {
    
    @GetMapping("/projects/{userName}")
    ProjectDto findByUserName(@PathVariable String userName);

    @GetMapping("/projects/{id}")
    ProjectDto findById(@PathVariable Long id);
}
