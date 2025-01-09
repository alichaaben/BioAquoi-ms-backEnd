package com.ms.Billing_Service.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.Billing_Service.entity.Billing;

@Repository
public interface BillingRepo extends JpaRepository<Billing,Long> {
    // Optional<Billing> findByBillingName(String BillingName);

}
