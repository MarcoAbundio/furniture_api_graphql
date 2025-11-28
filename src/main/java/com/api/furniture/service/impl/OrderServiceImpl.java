package com.api.furniture.service.impl;

import com.api.furniture.dto.OrderRequest;
import com.api.furniture.dto.OrderResponse;
import com.api.furniture.mapper.OrderMapper;
import com.api.furniture.model.Order;
import com.api.furniture.repository.OrderRepository;
import com.api.furniture.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    @Override
    @Transactional
    public List<OrderResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderResponse> findAll(int page, int pageSize) {
        Pageable pageReq = PageRequest.of(page, pageSize);
        Page<Order> orders = repository.findAll(pageReq);
        return orders.getContent().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponse findById(Integer id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
        return mapper.toResponse(order);
    }

    @Override
    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = mapper.toEntity(request);
        // Generar número de orden único
        String orderNumber = "ORD-" + System.currentTimeMillis();
        order.setOrderNumber(orderNumber);
        order.setOrderDate(LocalDateTime.now());
        
        Order saved = repository.save(order);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrderResponse update(Integer id, OrderRequest request) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
        mapper.copyToEntity(request, existing);
        Order saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Order not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = repository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with number: " + orderNumber));
        return mapper.toResponse(order);
    }

    @Override
    @Transactional
    public List<OrderResponse> findByUserId(Integer userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    // @Override
    // public List<OrderResponse> findByUserIdOrderByDateDesc(Integer userId, int page, int pageSize) {
    //     Pageable pageReq = PageRequest.of(page, pageSize);
    //     Page<Order> orders = repository.findByUserIdOrderByDateDesc(userId, pageReq);
    //     return orders.getContent().stream()
    //             .map(mapper::toResponse)
    //             .toList();
    // }

    @Override
    @Transactional
    public List<OrderResponse> findByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderResponse> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByOrderDateBetween(startDate, endDate).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderResponse> findByUserIdAndStatus(Integer userId, String status) {
        return repository.findByUserIdAndStatus(userId, status).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public Long countByStatus(String status) {
        return repository.countByStatus(status);
    }

    @Override
    @Transactional
    public List<OrderResponse> findByTotalAmountRange(Double minAmount, Double maxAmount) {
        return repository.findByTotalAmountRange(minAmount, maxAmount).stream()
                .map(mapper::toResponse)
                .toList();
    }
}