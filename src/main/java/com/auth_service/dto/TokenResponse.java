package com.auth_service.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class TokenResponse {
    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_token")
    private String attribute;

    @JsonProperty("expires_in")
    private Long expiresIn;
    
    @JsonProperty("token_type")
    private String tokenType;
}
