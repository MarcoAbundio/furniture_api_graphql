package com.api.furniture.service;

import com.api.furniture.dto.PaymentRequest;
import com.api.furniture.dto.PaymentResponse;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    List<PaymentResponse> findAll();
    List<PaymentResponse> findAll(int page, int pageSize);
    PaymentResponse findById(Integer id);
    PaymentResponse create(PaymentRequest request);
    PaymentResponse update(Integer id, PaymentRequest request);
    void delete(Integer id);
    
    // Métodos personalizados
    List<PaymentResponse> findByOrderId(Integer orderId);
    List<PaymentResponse> findByPaymentStatus(String status);
    List<PaymentResponse> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    PaymentResponse findByTransactionId(String transactionId);
    List<PaymentResponse> findCompletedPaymentsByOrderId(Integer orderId);
    Double getTotalRevenueByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}