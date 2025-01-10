package com.ms.Component_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "components")
public class Components {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long composentId;

    @Column(nullable = false)
    private String componentName;

    @Column(nullable = false)
    private int usageQuantity;

    @Column(nullable = false)
    private int quantityInStock;

    @ElementCollection
    @CollectionTable(name = "component_project", joinColumns = @JoinColumn(name = "component_id"))
    @Column(name = "projet_id")
    private Set<Long> projetIds = new HashSet<>();
}