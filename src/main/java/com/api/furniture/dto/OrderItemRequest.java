package com.api.furniture.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double unitPrice;
}