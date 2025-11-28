package com.api.furniture.dto;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddressResponse {
    private Integer id;
    private Integer userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;
    private Boolean isDefault;
    private List<Integer> shipping_orders_ids;
    private List<Integer> billing_orders_ids;
}