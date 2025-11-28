package com.api.furniture.service.impl;

import com.api.furniture.dto.UserLoginRequest;
import com.api.furniture.dto.UserRequest;
import com.api.furniture.dto.UserResponse;
import com.api.furniture.mapper.UserMapper;
import com.api.furniture.model.Role;
import com.api.furniture.model.User;
import com.api.furniture.repository.UserRepository;
import com.api.furniture.repository.RoleRepository;
import com.api.furniture.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public List<UserResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = repository.findAll(pageReq);
        return users.getContent().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public UserResponse findById(Integer id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return mapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        String role = request.getRole();
        Role userRole = roleRepository.findByAuthority(role)
                .orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = mapper.toEntity(request);
        user.setAuthorities(authorities);
        // user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setCreatedAt(java.time.LocalDateTime.now());
        user.setUpdatedAt(java.time.LocalDateTime.now());
        User saved = repository.save(user);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public UserResponse update(Integer id, UserRequest request) {
        User existing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        existing.setUpdatedAt(java.time.LocalDateTime.now());
        mapper.copyToEntity(request, existing);
        User saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse findByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return mapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse findByUsername(String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
        return mapper.toResponse(user);
    }

    @Override
    @Transactional
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    @Transactional
    public List<UserResponse> findAllActiveUsers() {
        return repository.findAllActiveUsers().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public UserResponse findActiveUserByEmail(String email) {
        User user = repository.findActiveUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Active user not found with email: " + email));
        return mapper.toResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // Autenticacion
    public User authenticate(UserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPasswordHash()
                )
        );
        return repository.findByEmail(input.getEmail())
                .orElseThrow();
    }

    public User authenticate(UserLoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPasswordHash()
                )
        );
        return repository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}