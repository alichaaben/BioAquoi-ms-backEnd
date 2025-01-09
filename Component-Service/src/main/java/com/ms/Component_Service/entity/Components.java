package com.ms.Component_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "components")
public class Components {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

//    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
//    private Project project;

    @Column(nullable = false)
    private int usageQuantity;

    @Column(nullable = false)
    private int quantityInStock;
}