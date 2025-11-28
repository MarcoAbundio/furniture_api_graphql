package com.api.furniture.service;

import com.api.furniture.dto.UserLoginRequest;
import com.api.furniture.dto.UserRequest;
import com.api.furniture.dto.UserResponse;
import com.api.furniture.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();

    List<UserResponse> findAll(int page, int pageSize);

    UserResponse findById(Integer id);

    UserResponse create(UserRequest request);

    UserResponse update(Integer id, UserRequest request);

    void delete(Integer id);

    // Métodos personalizados
    UserResponse findByEmail(String email);

    UserResponse findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    List<UserResponse> findAllActiveUsers();

    UserResponse findActiveUserByEmail(String email);

    // Autenticacion
    User authenticate(UserRequest input);

    User authenticate(UserLoginRequest input);
}