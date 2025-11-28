package com.api.furniture.dto;

import lombok.Builder;
import lombok.Value;
import java.time.LocalDateTime;

@Builder
@Value
public class PaymentResponse {
    private Integer id;
    private Integer orderId;
    private String paymentMethod;
    private Double amount;
    private String paymentStatus;
    private String transactionId;
    private LocalDateTime paymentDate;
}