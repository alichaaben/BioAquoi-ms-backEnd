package com.ms.Project_Service.dto;

import lombok.Data;

@Data
public class ProjectDto {

    private Long projectId;
    private String projectName;
    private String status;
    private Long userId;

}