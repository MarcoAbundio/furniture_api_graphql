package com.api.furniture.dto;

import lombok.Data;

@Data
public class AddressRequest {
    private Integer userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;
    private Boolean isDefault;
}