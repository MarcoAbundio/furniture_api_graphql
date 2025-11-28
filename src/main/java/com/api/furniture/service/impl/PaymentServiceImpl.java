package com.api.furniture.service.impl;

import com.api.furniture.dto.PaymentRequest;
import com.api.furniture.dto.PaymentResponse;
import com.api.furniture.mapper.PaymentMapper;
import com.api.furniture.model.Payment;
import com.api.furniture.repository.PaymentRepository;
import com.api.furniture.service.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    @Override
    @Transactional
    public List<PaymentResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<PaymentResponse> findAll(int page, int pageSize) {
        Pageable pageReq = PageRequest.of(page, pageSize);
        Page<Payment> payments = repository.findAll(pageReq);
        return payments.getContent().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PaymentResponse findById(Integer id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found: " + id));
        return mapper.toResponse(payment);
    }

    @Override
    @Transactional
    public PaymentResponse create(PaymentRequest request) {
        Payment payment = mapper.toEntity(request);
        payment.setPaymentDate(LocalDateTime.now());
        Payment saved = repository.save(payment);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PaymentResponse update(Integer id, PaymentRequest request) {
        Payment existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found: " + id));
        mapper.copyToEntity(request, existing);
        Payment saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Payment not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<PaymentResponse> findByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<PaymentResponse> findByPaymentStatus(String status) {
        return repository.findByPaymentStatus(status).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<PaymentResponse> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByPaymentDateBetween(startDate, endDate).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PaymentResponse findByTransactionId(String transactionId) {
        Payment payment = repository.findByTransactionId(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with transaction ID: " + transactionId));
        return mapper.toResponse(payment);
    }

    @Override
    @Transactional
    public List<PaymentResponse> findCompletedPaymentsByOrderId(Integer orderId) {
        return repository.findCompletedPaymentsByOrderId(orderId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public Double getTotalRevenueByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.getTotalRevenueByDateRange(startDate, endDate);
    }
}