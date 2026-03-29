package com.auth_service.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auth_service.dto.TokenResponse;

@FeignClient(name="KeycloakClient", url="${keycloak.url}")
public interface KeycloakFeignClient {
    @PostMapping(value="/realms/${keycloak.realm}/protocol/openid-connect/token",
                consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenResponse getToken(@RequestBody Map<String, ?> formData);

    @PostMapping(value="/realms/${keycloak.realm}/protocol/openid-connect/logout",
                consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void logout(@RequestBody Map<String, ?> formData);
}
