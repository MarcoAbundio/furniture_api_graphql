package com.api.furniture.service;

import com.api.furniture.dto.OrderRequest;
import com.api.furniture.dto.OrderResponse;
import java.time.LocalDateTime;
import java.util.List;
//import org.springframework.data.domain.Page;

public interface OrderService {
    List<OrderResponse> findAll();
    List<OrderResponse> findAll(int page, int pageSize);
    OrderResponse findById(Integer id);
    OrderResponse create(OrderRequest request);
    OrderResponse update(Integer id, OrderRequest request);
    void delete(Integer id);
    
    // Métodos personalizados
    OrderResponse findByOrderNumber(String orderNumber);
    List<OrderResponse> findByUserId(Integer userId);
    // Page<OrderResponse> findByUserIdOrderByDateDesc(Integer userId, int page, int pageSize);
    List<OrderResponse> findByStatus(String status);
    List<OrderResponse> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderResponse> findByUserIdAndStatus(Integer userId, String status);
    Long countByStatus(String status);
    List<OrderResponse> findByTotalAmountRange(Double minAmount, Double maxAmount);
}