package com.ms.Billing_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ms.Billing_Service.dto.RsaKeyRecord;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyRecord.class)
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

}
