package com.api.furniture.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@JsonBackReference
    private User user;

    @Column(name = "address_line1")
    @JsonProperty("address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    @JsonProperty("address_line2")
    private String addressLine2;

    @Column(name = "city")
    @JsonProperty("city")
    private String city;

    @Column(name = "state")
    @JsonProperty("state")
    private String state;

    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @Column(name = "country")
    @JsonProperty("country")
    private String country;

    @Column(name = "address_type")
    @JsonProperty("address_type")
    private String addressType;

    @Column(name = "is_default")
    @JsonProperty("is_default")
    private Boolean isDefault;

    @OneToMany(mappedBy = "shippingAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("shipping_orders_ids")
    private List<Order> shippingOrders;

    @OneToMany(mappedBy = "billingAddress", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("billing_orders_ids")
    private List<Order> billingOrders;
}