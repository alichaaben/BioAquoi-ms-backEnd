package com.ms.Auth_Service.dto;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyRecord(RSAPublicKey publicKey,RSAPrivateKey privateKey) {
}