package com.api.furniture.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.api.furniture.dto.UserRequest;
import com.api.furniture.dto.UserResponse;

import com.api.furniture.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@Controller
public class UserGraphql {
    @Autowired
    private UserServiceImpl service;

    @QueryMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @QueryMapping
    public UserResponse findById(@Argument Integer id) {
        return service.findById(id);
   }

    @MutationMapping
    public UserResponse create(@Valid @Argument UserRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public UserResponse update(@Argument Integer id, @Valid @Argument UserRequest req) {
        return service.update(id, req);
    }

    @MutationMapping
    public void delete(@Argument Integer id) {
        service.delete(id);
    }
    
}