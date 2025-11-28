package com.api.furniture.mapper;

import com.api.furniture.model.Address;
import com.api.furniture.model.User;
import com.api.furniture.dto.AddressRequest;
import com.api.furniture.dto.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressResponse toResponse(Address address) {
        if (address == null) {
            return null;
        }
        return AddressResponse.builder()
                .id(address.getId())
                .userId(address.getUser().getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .addressType(address.getAddressType())
                .isDefault(address.getIsDefault())
                .shipping_orders_ids(address.getShippingOrders().stream().map(o -> o.getId()).toList())
                .billing_orders_ids(address.getBillingOrders().stream().map(o -> o.getId()).toList())
                .build();
    }

    public Address toEntity(AddressRequest addressRequest) {
        if (addressRequest == null) {
            return null;
        } else {
            return Address.builder()
                    .user(User.builder().id(addressRequest.getUserId()).build())
                    .addressLine1(addressRequest.getAddressLine1())
                    .addressLine2(addressRequest.getAddressLine2())
                    .city(addressRequest.getCity())
                    .state(addressRequest.getState())
                    .postalCode(addressRequest.getPostalCode())
                    .country(addressRequest.getCountry())
                    .addressType(addressRequest.getAddressType())
                    .isDefault(addressRequest.getIsDefault())
                    .build();
        }
    }

    public void copyToEntity(AddressRequest addressRequest, Address address) {
        if (addressRequest.getUserId() != null) {
            address.setUser(User.builder().id(addressRequest.getUserId()).build());
        }
        if (addressRequest.getAddressLine1() != null) {
            address.setAddressLine1(addressRequest.getAddressLine1());
        }
        if (addressRequest.getAddressLine2() != null) {
            address.setAddressLine2(addressRequest.getAddressLine2());
        }
        if (addressRequest.getCity() != null) {
            address.setCity(addressRequest.getCity());
        }
        if (addressRequest.getState() != null) {
            address.setState(addressRequest.getState());
        }
        if (addressRequest.getPostalCode() != null) {
            address.setPostalCode(addressRequest.getPostalCode());
        }
        if (addressRequest.getCountry() != null) {
            address.setCountry(addressRequest.getCountry());
        }
        if (addressRequest.getAddressType() != null) {
            address.setAddressType(addressRequest.getAddressType());
        }
        if (addressRequest.getIsDefault() != null) {
            address.setIsDefault(addressRequest.getIsDefault());
        }
    }
}