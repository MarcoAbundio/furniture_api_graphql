package com.api.furniture.mapper;

import com.api.furniture.model.Address;
import com.api.furniture.model.Order;
import com.api.furniture.model.User;
import com.api.furniture.dto.OrderRequest;
import com.api.furniture.dto.OrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    
    public OrderResponse toResponse(Order order) {
        Integer userId = order.getUser() != null ? order.getUser().getId() : null;
        Integer shippingAddressId = order.getShippingAddress() != null ? order.getShippingAddress().getId() : null;
        Integer billingAddressId = order.getBillingAddress() != null ? order.getBillingAddress().getId() : null;
        
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .userId(userId)
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .shippingAddressId(shippingAddressId)
                .billingAddressId(billingAddressId)
                .orderItemIds(order.getOrderItems().stream().map(oi -> oi.getId()).toList())
                .paymentIds(order.getPayments().stream().map(p -> p.getId()).toList())
                .build();
    }
    
    public Order toEntity(OrderRequest orderRequest) {
        Order order = new Order();
        if (orderRequest.getUserId() != null) {
            order.setUser(User.builder().id(orderRequest.getUserId()).build());
        }
        if (orderRequest.getShippingAddressId() != null) {
            order.setShippingAddress(Address.builder().id(orderRequest.getShippingAddressId()).build());
        }
        if (orderRequest.getBillingAddressId() != null) {
            order.setBillingAddress(Address.builder().id(orderRequest.getBillingAddressId()).build());
        }
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setStatus(orderRequest.getStatus());
        return order;
    }
    
    public void copyToEntity(OrderRequest orderRequest, Order order) {
        if (orderRequest.getUserId() != null) {
            order.setUser(User.builder().id(orderRequest.getUserId()).build());
        }
        if (orderRequest.getShippingAddressId() != null) {
            order.setShippingAddress(Address.builder().id(orderRequest.getShippingAddressId()).build());
        }
        if (orderRequest.getBillingAddressId() != null) {
            order.setBillingAddress(Address.builder().id(orderRequest.getBillingAddressId()).build());
        }
        if (orderRequest.getTotalAmount() != null) {
            order.setTotalAmount(orderRequest.getTotalAmount());
        }
        if (orderRequest.getStatus() != null) {
            order.setStatus(orderRequest.getStatus());
        }
    }
}