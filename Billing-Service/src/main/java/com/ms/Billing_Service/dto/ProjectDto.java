package com.ms.Billing_Service.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProjectDto {
    private Long projectId;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String status;

}
