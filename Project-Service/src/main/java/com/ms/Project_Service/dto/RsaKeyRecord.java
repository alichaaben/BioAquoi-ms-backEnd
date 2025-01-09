package com.ms.Project_Service.dto;

import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyRecord(RSAPublicKey publicKey) {
}