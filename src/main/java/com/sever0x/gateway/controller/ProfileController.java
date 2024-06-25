package com.sever0x.gateway.controller;

import com.sever0x.gateway.auth.dto.UserInfo;
import com.sever0x.gateway.data.UserSession;
import com.sever0x.gateway.service.SessionService;
import com.sever0x.gateway.service.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {

    private final SessionService sessionService;

    @GetMapping("/profile")
    public Mono<UserInfo> getProfile(ServerWebExchange exchange) {
        return sessionService.checkSession(exchange)
                .flatMap(this::toUserInfo)
                .onErrorResume(UnauthorizedException.class, e -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
                });
    }

    private Mono<UserInfo> toUserInfo(UserSession session) {
        return Mono.just(UserInfo.builder()
                .email(session.getEmail())
                .name(session.getName())
                .build());
    }
}
