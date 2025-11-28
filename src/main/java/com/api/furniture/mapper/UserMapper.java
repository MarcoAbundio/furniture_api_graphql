package com.api.furniture.mapper;

import com.api.furniture.model.Role;
import com.api.furniture.model.User;
import com.api.furniture.dto.UserRequest;
import com.api.furniture.dto.UserResponse;

import java.util.Iterator;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .passwordHash(user.getPasswordHash())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .isActive(user.getIsActive())
                .role(firstElement.getAuthority())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    
    public User toEntity(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .passwordHash(userRequest.getPasswordHash())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phone(userRequest.getPhone())
                .isActive(userRequest.getIsActive())
                .build();
    }
    
    public void copyToEntity(UserRequest userRequest, User user) {
        if (user == null || userRequest == null) {
            return;
        }
        if (userRequest.getUsername() != null) {
            user.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPasswordHash() != null) {
            user.setPasswordHash(userRequest.getPasswordHash());
        }
        if (userRequest.getFirstName() != null) {
            user.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName() != null) {
            user.setLastName(userRequest.getLastName());
        }
        if (userRequest.getPhone() != null) {
            user.setPhone(userRequest.getPhone());
        }
        if (userRequest.getIsActive() != null) {
            user.setIsActive(userRequest.getIsActive());
        }
    }
}