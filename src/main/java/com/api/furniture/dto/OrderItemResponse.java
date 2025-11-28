package com.api.furniture.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderItemResponse {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}