package com.api.furniture.mapper;

import com.api.furniture.model.Order;
import com.api.furniture.model.OrderItem;
import com.api.furniture.dto.OrderItemRequest;
import com.api.furniture.dto.OrderItemResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    
    public OrderItemResponse toResponse(OrderItem orderItem) {
        Integer orderId = orderItem.getOrder() != null ? orderItem.getOrder().getId() : null;
        
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .orderId(orderId)
                .productId(orderItem.getProduct_id())
                .quantity(orderItem.getQuantity())
                .unitPrice(orderItem.getUnitPrice())
                .subtotal(orderItem.getSubtotal())
                .build();
    }
    
    public OrderItem toEntity(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        if (orderItemRequest.getOrderId() != null) {
            orderItem.setOrder(Order.builder().id(orderItemRequest.getOrderId()).build());
        }
        if (orderItemRequest.getProductId() != null) {
            orderItem.setProduct_id(orderItemRequest.getProductId());
        }
        if (orderItemRequest.getQuantity() != null) {
            orderItem.setQuantity(orderItemRequest.getQuantity());
        }
        if (orderItemRequest.getUnitPrice() != null) {
            orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
        }
        return orderItem;
    }
    
    public void copyToEntity(OrderItemRequest orderItemRequest, OrderItem orderItem) {
        if (orderItemRequest.getOrderId() != null) {
            orderItem.setOrder(Order.builder().id(orderItemRequest.getOrderId()).build());
        }
        if (orderItemRequest.getProductId() != null) {
            orderItem.setProduct_id(orderItemRequest.getProductId());
        }
        if (orderItemRequest.getQuantity() != null) {
            orderItem.setQuantity(orderItemRequest.getQuantity());
        }
        if (orderItemRequest.getUnitPrice() != null) {
            orderItem.setUnitPrice(orderItemRequest.getUnitPrice());
        }
    }
}