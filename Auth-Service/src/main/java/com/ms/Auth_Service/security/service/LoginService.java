package com.ms.Auth_Service.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private JwtEncoder jwtEncoder;

    public Map<String, String> generateJwtToken(String username, Collection<? extends GrantedAuthority> authorities, boolean withRefreshToken) {
        Map<String, String> idToken = new HashMap<>();
        String scope = getScope(authorities);
    
        // generez l'accessToken
        String accessToken = generateAccessToken(username, scope, withRefreshToken);
        idToken.put("accessToken", accessToken);
    
        // generez le refreshToken
        if (withRefreshToken) {
            String refreshToken = generateRefreshToken(username);
            idToken.put("refreshToken", refreshToken);
        }
    
        return idToken;
    }
    
    private String getScope(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }
    
    private String generateAccessToken(String username, String scope, boolean withRefreshToken) {
        Instant now = Instant.now();
        JwtClaimsSet accessTokenClaims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(withRefreshToken ? 5 : 30, ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope", scope)
                .build();
    
        return jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();
    }
    
    private String generateRefreshToken(String username) {
        Instant now = Instant.now();
        JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .build();
    
        return jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();
    }
    
}
