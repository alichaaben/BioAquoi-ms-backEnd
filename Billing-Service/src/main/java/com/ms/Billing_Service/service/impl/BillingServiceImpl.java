package com.ms.Billing_Service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ms.Billing_Service.Exceptions.ResourceNotFoundException;
import com.ms.Billing_Service.dto.BillingDto;
import com.ms.Billing_Service.dto.ProjectDto;
import com.ms.Billing_Service.dto.UsersDto;
import com.ms.Billing_Service.entity.Billing;
import com.ms.Billing_Service.mapper.BillingMapper;
import com.ms.Billing_Service.openFeign.ProjectRestClient;
import com.ms.Billing_Service.openFeign.UserRestClient;
import com.ms.Billing_Service.repository.BillingRepo;
import com.ms.Billing_Service.service.BillingService;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {
    
    private final BillingRepo billingRepo;
    private final BillingMapper billingMapper;
    private final UserRestClient userRestClient;
    private final ProjectRestClient projectRestClient;

    @Override
    public List<BillingDto> findAllBillings() {
        List<Billing> Billings = billingRepo.findAll();
        return Billings.stream()
                .map(billingMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public BillingDto findBillingById(Long id) {
        Billing billing = billingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Billing not found with ID: " + id));
        return billingMapper.map(billing);
    }

    // @Override
    // public Billing findByBillingName(String billingName) {
    //     return billingRepo.findByBillingName(billingName)
    //     .orElseThrow(() -> new ResourceNotFoundException("Billing not found with Name: " + billingName));

    // }

    @Override
public BillingDto createBilling(BillingDto billingDto) {
    try {
        UsersDto user = userRestClient.findById(billingDto.getUserId());
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + billingDto.getUserId());
        }

        ProjectDto project = projectRestClient.findById(billingDto.getProjectId());
        if (project == null) {
            throw new ResourceNotFoundException("Project not found with ID: " + billingDto.getProjectId());
        }

        Billing billing = billingMapper.unMap(billingDto);
        billing.setUserId(user.getUserId());
        billing.setProjectId(project.getProjectId());

        Billing savedBilling = billingRepo.save(billing);

        return billingMapper.map(savedBilling);

    } catch (FeignException.NotFound e) {
        if (e.getMessage().contains("User")) {
            throw new ResourceNotFoundException("User not found with ID: " + billingDto.getUserId());
        } else if (e.getMessage().contains("Project")) {
            throw new ResourceNotFoundException("Project not found with ID: " + billingDto.getProjectId());
        } else {
            throw new ResourceNotFoundException("Resource not found: " + e.getMessage());
        }
    } catch (Exception e) {
        throw new RuntimeException("An error occurred while creating the billing: " + e.getMessage());
    }
}

    @Override
    public BillingDto updateBilling(BillingDto billingDto) {
        try {
            UsersDto user = userRestClient.findById(billingDto.getUserId());
            if (user == null) {
                throw new ResourceNotFoundException("User not found with ID: " + billingDto.getUserId());
            }

            ProjectDto project = projectRestClient.findById(billingDto.getProjectId());
        if (project == null) {
            throw new ResourceNotFoundException("Project not found with ID: " + billingDto.getProjectId());
        }

            Billing billing = billingMapper.unMap(billingDto);

            Billing existingBilling = billingRepo.findById(billing.getBillingId())
                    .orElseThrow(() -> new ResourceNotFoundException("Billing not found with ID: " + billing.getProjectId()));

            existingBilling.setTotalAmount(billing.getTotalAmount());
            existingBilling.setProjectId(billing.getProjectId());
            existingBilling.setUserId(billing.getUserId());

            Billing updatedBilling = billingRepo.save(existingBilling);

            return billingMapper.map(updatedBilling);

        } catch (FeignException.NotFound e) {
            if (e.getMessage().contains("User")) {
                throw new ResourceNotFoundException("User not found with ID: " + billingDto.getUserId());
            } else if (e.getMessage().contains("Project")) {
                throw new ResourceNotFoundException("Project not found with ID: " + billingDto.getProjectId());
            } else {
                throw new ResourceNotFoundException("Resource not found: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the billing: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!billingRepo.existsById(id)) {
            throw new ResourceNotFoundException("Billing not found with ID: " + id);
        }
        billingRepo.deleteById(id);
    }


}
