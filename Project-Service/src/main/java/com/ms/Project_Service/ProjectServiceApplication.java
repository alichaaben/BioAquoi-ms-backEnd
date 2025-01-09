package com.ms.Project_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.ms.Project_Service.dto.RsaKeyRecord;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
@EnableConfigurationProperties(RsaKeyRecord.class)
public class ProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

}
