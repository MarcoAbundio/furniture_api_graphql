package com.api.furniture.dto;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Builder
@Value
public class ProductReviewResponse {
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}