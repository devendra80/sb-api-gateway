package org.dev.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()
            .authorizeExchange(authorize -> authorize
                .pathMatchers("/login", "/register").permitAll()
                .anyExchange().authenticated()
            )
            .httpBasic() // Enable Basic Authentication
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint()); // Use custom authentication entry point

        return http.build();
    }
}