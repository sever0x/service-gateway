package com.sever0x.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.Mono;

@Configuration
public class WebConfig {

    @Bean
    public WebSessionManager webSessionManager() {
        return exchange -> Mono.empty();
    }
}
