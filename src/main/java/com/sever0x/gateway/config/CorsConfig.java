package com.sever0x.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    private static final List<String> httpMethods = List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");

    private final String googleOriginUrl = "https://accounts.google.com";

    @Value("${frontend.url}")
    private String frontendOriginUrl;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedMethods(httpMethods);
        cors.setAllowedOrigins(List.of(frontendOriginUrl, googleOriginUrl));
        cors.setAllowedHeaders(List.of("*", "Authorization"));
        cors.setExposedHeaders(List.of("Location"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);

        return new CorsWebFilter(source);
    }
}
