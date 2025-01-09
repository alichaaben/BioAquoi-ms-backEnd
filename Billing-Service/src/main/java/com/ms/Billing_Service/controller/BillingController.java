package com.ms.Billing_Service.controller;

import com.ms.Billing_Service.Exceptions.ResourceNotFoundException;
import com.ms.Billing_Service.dto.BillingDto;
import com.ms.Billing_Service.service.BillingService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billings")
@RequiredArgsConstructor
public class BillingController {


    private final BillingService billingService;

    @GetMapping
    public ResponseEntity<List<BillingDto>> getAllBillings() {
        List<BillingDto> billingDto = billingService.findAllBillings();
        return ResponseEntity.ok(billingDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillingDto> getBillingById(@PathVariable Long id) {
        BillingDto billingDto = billingService.findBillingById(id);
        return ResponseEntity.ok(billingDto);
    }

    @PostMapping
    public ResponseEntity<?> createBilling(@Validated @RequestBody BillingDto BillingDto) {
        try {
            BillingDto savedBillingDto = billingService.createBilling(BillingDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBillingDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBilling(@Validated @RequestBody BillingDto billingDto) {
        try {
            BillingDto updatedBillingDto = billingService.updateBilling(billingDto);
            return ResponseEntity.ok(updatedBillingDto);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBilling(@PathVariable Long id) {
        try {
            billingService.deleteById(id);
            return ResponseEntity.ok("Billing with ID " + id + " successfully deleted.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the Billing: " + e.getMessage());
        }
    }

    }
