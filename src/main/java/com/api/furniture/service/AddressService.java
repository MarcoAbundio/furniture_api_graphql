package com.api.furniture.service;

import com.api.furniture.dto.AddressRequest;
import com.api.furniture.dto.AddressResponse;
import java.util.List;

public interface AddressService {
    List<AddressResponse> findAll();
    List<AddressResponse> findAll(int page, int pageSize);
    AddressResponse findById(Integer id);
    AddressResponse create(AddressRequest request);
    AddressResponse update(Integer id, AddressRequest request);
    void delete(Integer id);
    
    // Métodos personalizados
    List<AddressResponse> findByUserId(Integer userId);
    AddressResponse findDefaultAddressByUserId(Integer userId);
    List<AddressResponse> findByUserIdAndAddressType(Integer userId, String addressType);
    Long countByUserId(Integer userId);
}