package com.blog.token.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        String secretKey = System.getProperty("JWT_SECRET");
        return new JwtTokenProvider(secretKey);
    }
}
