package com.api.furniture.mapper;

import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.api.furniture.dto.UserLoginRequest;
import com.api.furniture.dto.UserLoginResponse;
import com.api.furniture.model.Role;
import com.api.furniture.model.User;

@Component
public class UserLoginMapper {
    public static UserLoginResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }
        return UserLoginResponse.builder()
                .email(user.getEmail())
                .role(firstElement.getAuthority())
                .build();
    }
    
    public static User toEntity(UserLoginRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .email(userRequest.getEmail())
                .passwordHash(userRequest.getPasswordHash())
                .build();
    }
    
    public static void copyToEntity(UserLoginRequest userRequest, User user) {
        if (user == null || userRequest == null) {
            return;
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPasswordHash() != null) {
            user.setPasswordHash(userRequest.getPasswordHash());
        }
    }
}
