package com.api.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
//import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String passwordHash;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isActive;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // private List<Integer> addresses;
    // private List<Integer> orders;
    // private List<Integer> productReviews;
    
}