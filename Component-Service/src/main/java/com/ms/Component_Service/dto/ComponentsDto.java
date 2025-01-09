package com.ms.Component_Service.dto;

import lombok.Data;


@Data
public class ComponentsDto {
    
    private Long materialId;

    // private String projectName;

    private int usageQuantity;

    private int quantityInStock;

}