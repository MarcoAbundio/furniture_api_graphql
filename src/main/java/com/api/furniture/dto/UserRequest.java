package com.api.furniture.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isActive;
    private String role;
}