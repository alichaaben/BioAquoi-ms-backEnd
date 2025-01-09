package com.ms.Billing_Service.service;

import java.util.List;

import com.ms.Billing_Service.dto.BillingDto;
// import com.ms.Billing_Service.entity.Billing;

public interface BillingService {

    List<BillingDto> findAllBillings();

    BillingDto findBillingById(Long id);
    
    // Billing findByBillingName(String BillingName);

    BillingDto createBilling(BillingDto BillingDto);

    BillingDto updateBilling(BillingDto BillingDto);
    
    void deleteById(Long id);

}
