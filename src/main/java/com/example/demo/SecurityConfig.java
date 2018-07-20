package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.WebFilter;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange().anyExchange().permitAll()
                   .and()
                   .csrf().disable()
                   .build();
    }

    @Profile("!usingHttp")
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    WebFilter forwardedHeaderFilter() {
        return new ProxyForwardedHeaderFilter();
    }
}
