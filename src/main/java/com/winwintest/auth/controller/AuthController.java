package com.winwintest.auth.controller;

import com.winwintest.auth.model.dto.LoginRequest;
import com.winwintest.auth.model.dto.RegisterRequest;
import com.winwintest.auth.model.dto.TokenResponse;
import com.winwintest.auth.security.JwtService;
import com.winwintest.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request.email(), request.password());
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        var user = authService.authenticate(request.email(), request.password());
        return new TokenResponse(jwtService.generateToken(user));
    }
}
