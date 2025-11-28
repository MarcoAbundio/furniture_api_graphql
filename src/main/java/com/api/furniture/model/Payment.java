package com.api.furniture.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod;

    @Column(name = "amount")
    @JsonProperty("amount")
    private Double amount;

    @Column(name = "payment_status")
    @JsonProperty("payment_status")
    private String paymentStatus;

    @Column(name = "transaction_id")
    @JsonProperty("transaction_id")
    private String transactionId;

    @Column(name = "payment_date")
    @JsonProperty("payment_date")
    private LocalDateTime paymentDate;
}