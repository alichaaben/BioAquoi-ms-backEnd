package com.ms.Billing_Service.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ms.Billing_Service.dto.UsersDto;

@FeignClient(name="Auth-service")
public interface UserRestClient {

    @GetMapping("users/{userName}")
    UsersDto findByUserName(@PathVariable String userName);

    @GetMapping("users/{id}")
    UsersDto findById(@PathVariable Long id);

}
