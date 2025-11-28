package com.api.furniture.dto;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class OrderResponse {
    private Integer id;
    private String orderNumber;
    private Integer userId;
    private LocalDateTime orderDate;
    private Double totalAmount;
    private String status;
    private Integer shippingAddressId;
    private Integer billingAddressId;
    private List<Integer> orderItemIds;
    private List<Integer> paymentIds;
}