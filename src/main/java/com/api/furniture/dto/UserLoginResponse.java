package com.api.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private String email;
    private String role;
    private String token;
    private long expiresIn;
}
