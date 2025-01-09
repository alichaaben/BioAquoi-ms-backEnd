package com.ms.Billing_Service.dto;

import lombok.Data;

@Data
public class BillingDto {

    private Long billingId;
    private double totalAmount;
    private Long projectId;
    private Long userId;

}
