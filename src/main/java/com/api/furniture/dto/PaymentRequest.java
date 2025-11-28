package com.api.furniture.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Integer orderId;
    private String paymentMethod;
    private Double amount;
    private String paymentStatus;
    private String transactionId;
}