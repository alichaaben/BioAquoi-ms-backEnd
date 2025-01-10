package com.ms.Component_Service.dto;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class ComponentsDto {
    
    private Long composentId;
    private String componentName;
    private int usageQuantity;
    private int quantityInStock;
    private Set<Long> projetIds = new HashSet<>();
    private Set<String> projectNames = new HashSet<>();
}