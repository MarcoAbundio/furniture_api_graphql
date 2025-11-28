package com.api.furniture.service;

import com.api.furniture.dto.OrderItemRequest;
import com.api.furniture.dto.OrderItemResponse;
import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> findAll();
    List<OrderItemResponse> findAll(int page, int pageSize);
    OrderItemResponse findById(Integer id);
    OrderItemResponse create(OrderItemRequest request);
    OrderItemResponse update(Integer id, OrderItemRequest request);
    void delete(Integer id);
    
    // Métodos personalizados
    List<OrderItemResponse> findByOrderId(Integer orderId);
}