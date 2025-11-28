package com.api.furniture.repository;

import com.api.furniture.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    Optional<Order> findByOrderNumber(String orderNumber);
    
    List<Order> findByUserId(Integer userId);
    
    // @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.orderDate DESC")
    // List<Order> findByUserIdOrderByDateDesc(@Param("userId") Integer userId);
    
    @Query("SELECT o FROM Order o WHERE o.status = :status")
    List<Order> findByStatus(@Param("status") String status);
    
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, 
                                      @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = :status")
    List<Order> findByUserIdAndStatus(@Param("userId") Integer userId, 
                                     @Param("status") String status);
    
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") String status);
    
    @Query("SELECT o FROM Order o WHERE o.totalAmount BETWEEN :minAmount AND :maxAmount")
    List<Order> findByTotalAmountRange(@Param("minAmount") Double minAmount, 
                                      @Param("maxAmount") Double maxAmount);
}