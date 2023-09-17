package com.ecommerce.kgateway.config;

import com.ecommerce.kgateway.authorization.Role;
import com.ecommerce.kgateway.security.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    SecurityContext securityContext;

    @Autowired
    public SecurityConfig(SecurityContext securityContext) {
        this.securityContext = securityContext;

        securityContext
                .registerPath("/api/auth/login", "/api/auth/signup").permitAll()
                .registerPath("/api/product").withRoles(Role.USER, Role.ADMIN);
    }


}
