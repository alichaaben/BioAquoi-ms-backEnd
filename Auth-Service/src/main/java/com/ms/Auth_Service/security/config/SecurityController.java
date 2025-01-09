package com.ms.Auth_Service.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import com.ms.Auth_Service.entity.Users;
import com.ms.Auth_Service.security.service.LoginService;
import com.ms.Auth_Service.service.UsersService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtDecoder jwtDecoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> requestForToken(
            @RequestParam("grantType") String grantType,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "withRefreshToken", defaultValue = "false") boolean withRefreshToken,
            @RequestParam(value = "refreshToken", required = false) String refreshToken
    ) {
        Map<String, String> response;
    
        try {
            switch (grantType) {
                case "password":
                    if (username == null || password == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(Map.of("error", "Username and password are required for grantType 'password'"));
                    }

                    Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                    );

                    response = loginService.generateJwtToken(
                        authentication.getName(),
                        authentication.getAuthorities(),
                        withRefreshToken
                    );
                    return ResponseEntity.ok(response);

                case "refreshToken":
                    if (refreshToken == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("error", "RefreshToken Not Present"));
                    }

                    Jwt decodedJwt = jwtDecoder.decode(refreshToken);
                    String decodedUsername = decodedJwt.getSubject();
                    Users user = usersService.findByUserName(decodedUsername);

                    if (user == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("error", "User not found"));
                    }

                    Collection<GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority(user.getRole().getRoleName())
                    );

                    response = loginService.generateJwtToken(
                        user.getUserName(),
                        authorities,
                        withRefreshToken
                    );
                    return ResponseEntity.ok(response);

                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(Map.of("error", String.format("grantType <<%s>> not supported", grantType)));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Account is disabled"));
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Account is locked"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred"));
        }
    }
}
