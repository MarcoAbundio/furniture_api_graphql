package com.api.furniture.service.impl;

import com.api.furniture.dto.OrderItemRequest;
import com.api.furniture.dto.OrderItemResponse;
import com.api.furniture.mapper.OrderItemMapper;
import com.api.furniture.model.OrderItem;
import com.api.furniture.repository.OrderItemRepository;
import com.api.furniture.service.OrderItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    @Override
    @Transactional
    public List<OrderItemResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderItemResponse> findAll(int page, int pageSize) {
        Pageable pageReq = PageRequest.of(page, pageSize);
        Page<OrderItem> orderItems = repository.findAll(pageReq);
        return orderItems.getContent().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public OrderItemResponse findById(Integer id) {
        OrderItem orderItem = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found: " + id));
        return mapper.toResponse(orderItem);
    }

    @Override
    @Transactional
    public OrderItemResponse create(OrderItemRequest request) {
        OrderItem orderItem = mapper.toEntity(request);
        // Calcular subtotal si no viene calculado
        if (orderItem.getSubtotal() == null) {
            orderItem.setSubtotal(orderItem.getQuantity() * orderItem.getUnitPrice());
        }
        OrderItem saved = repository.save(orderItem);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OrderItemResponse update(Integer id, OrderItemRequest request) {
        OrderItem existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order item not found: " + id));
        mapper.copyToEntity(request, existing);
        // Recalcular subtotal si cambió cantidad o precio
        if (request.getQuantity() != null || request.getUnitPrice() != null) {
            existing.setSubtotal(existing.getQuantity() * existing.getUnitPrice());
        }
        OrderItem saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Order item not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<OrderItemResponse> findByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId).stream()
                .map(mapper::toResponse)
                .toList();
    }
}