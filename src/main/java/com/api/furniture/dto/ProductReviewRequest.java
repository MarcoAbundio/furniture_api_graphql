package com.api.furniture.dto;

import lombok.Data;

@Data
public class ProductReviewRequest {
    private Integer productId;
    private Integer userId;
    private Integer rating;
    private String comment;
}