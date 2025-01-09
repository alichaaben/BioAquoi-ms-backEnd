package com.ms.Billing_Service.entity;

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
@Table(name = "billings")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long projectId;

    //    @ManyToOne
//    @JoinColumn(name = "project_id", nullable = false)
//    private Project project;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Users user;
}
