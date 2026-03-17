package com.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth_service.dto.LoginRequest;
import com.auth_service.dto.LogoutRequest;
import com.auth_service.dto.RefreshTokenRequest;
import com.auth_service.dto.TokenResponse; 
import com.auth_service.service.Authservice;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final Authservice authService;

    public AuthController(Authservice authService){
        this.authService = authService;
    }
    //1. API dang nhap
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        TokenResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
    //2. API lam moi token
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        TokenResponse response= authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }
    //3. API Đăng xuất
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request){
        authService.logout(request);
        return ResponseEntity.ok("Out");
    }
}
