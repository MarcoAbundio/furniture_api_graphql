package com.api.furniture.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer userId;
    private Double totalAmount;
    private String status;
    private Integer shippingAddressId;
    private Integer billingAddressId;
}