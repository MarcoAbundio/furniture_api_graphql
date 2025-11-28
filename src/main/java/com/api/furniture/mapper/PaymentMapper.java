package com.api.furniture.mapper;

import com.api.furniture.model.Order;
import com.api.furniture.model.Payment;
import com.api.furniture.dto.PaymentRequest;
import com.api.furniture.dto.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    
    public PaymentResponse toResponse(Payment payment) {
        Integer orderId = payment.getOrder() != null ? payment.getOrder().getId() : null;
        
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(orderId)
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .transactionId(payment.getTransactionId())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
    
    public Payment toEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        if (paymentRequest.getOrderId() != null) {
            payment.setOrder(Order.builder().id(paymentRequest.getOrderId()).build());
        }
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        payment.setTransactionId(paymentRequest.getTransactionId());
        return payment;
    }
    
    public void copyToEntity(PaymentRequest paymentRequest, Payment payment) {
        if (paymentRequest.getOrderId() != null) {
            payment.setOrder(Order.builder().id(paymentRequest.getOrderId()).build());
        }
        if (paymentRequest.getPaymentMethod() != null) {
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        }
        if (paymentRequest.getAmount() != null) {
            payment.setAmount(paymentRequest.getAmount());
        }
        if (paymentRequest.getPaymentStatus() != null) {
            payment.setPaymentStatus(paymentRequest.getPaymentStatus());
        }
        if (paymentRequest.getTransactionId() != null) {
            payment.setTransactionId(paymentRequest.getTransactionId());
        }
    }
}