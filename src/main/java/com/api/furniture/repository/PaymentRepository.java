package com.api.furniture.repository;

import com.api.furniture.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
    List<Payment> findByOrderId(Integer orderId);
    
    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = :status")
    List<Payment> findByPaymentStatus(@Param("status") String status);
    
    @Query("SELECT p FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    List<Payment> findByPaymentDateBetween(@Param("startDate") LocalDateTime startDate, 
                                          @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT p FROM Payment p WHERE p.transactionId = :transactionId")
    Optional<Payment> findByTransactionId(@Param("transactionId") String transactionId);
    
    @Query("SELECT p FROM Payment p WHERE p.order.id = :orderId AND p.paymentStatus = 'completed'")
    List<Payment> findCompletedPaymentsByOrderId(@Param("orderId") Integer orderId);
    
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentStatus = 'completed' " +
           "AND p.paymentDate BETWEEN :startDate AND :endDate")
    Double getTotalRevenueByDateRange(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);
}