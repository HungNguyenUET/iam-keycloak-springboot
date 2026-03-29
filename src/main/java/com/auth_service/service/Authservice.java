package com.auth_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth_service.dto.LoginRequest;
import com.auth_service.dto.LogoutRequest;
import com.auth_service.dto.RefreshTokenRequest;
import com.auth_service.dto.TokenResponse;

@Service
public class Authservice {
    private final KeycloakFeignClient keycloakClient;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    public Authservice(KeycloakFeignClient keycloakClient){
        this.keycloakClient=keycloakClient;
    }

    //1.Ham dang nhap
    public TokenResponse authenticate(LoginRequest request){
        Map<String, String> formData= new HashMap<>();
        formData.put("client_id",clientId);
        formData.put("client_secret", clientSecret);
        formData.put("grant_type","password");
        formData.put("username",request.getUsername());
        formData.put("password",request.getPassword());

        return keycloakClient.getToken(formData);
    }
    //2. RefreshToken
    public TokenResponse refreshToken(RefreshTokenRequest request){
        Map<String,String> formData = new HashMap<>();
        formData.put("client_id", clientId);
        formData.put("client_secret", clientSecret);
        formData.put("grand_type","refresh_token");
        formData.put("refresh_token",request.getRefreshToken());
        return keycloakClient.getToken(formData);
    }
    //3. Dang xuat
    public void logout(LogoutRequest request){
        Map<String,String> formData = new HashMap<>();
        formData.put("client_id", clientId);
        formData.put("client_secret", clientSecret);
        formData.put("refresh_token",request.getRefreshToken());

        keycloakClient.logout(formData);
    }

}