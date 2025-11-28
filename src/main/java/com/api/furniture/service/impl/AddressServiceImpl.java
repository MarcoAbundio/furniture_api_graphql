package com.api.furniture.service.impl;

import com.api.furniture.dto.AddressRequest;
import com.api.furniture.dto.AddressResponse;
import com.api.furniture.mapper.AddressMapper;
import com.api.furniture.model.Address;
import com.api.furniture.repository.AddressRepository;
import com.api.furniture.service.AddressService;
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
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    private final AddressMapper mapper;

    @Override
    @Transactional
    public List<AddressResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<AddressResponse> findAll(int page, int pageSize) {
        Pageable pageReq = PageRequest.of(page, pageSize);
        Page<Address> addresses = repository.findAll(pageReq);
        return addresses.getContent().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AddressResponse findById(Integer id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + id));
        return mapper.toResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse create(AddressRequest request) {
        Address address = mapper.toEntity(request);
        Address saved = repository.save(address);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AddressResponse update(Integer id, AddressRequest request) {
        Address existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + id));
        mapper.copyToEntity(request, existing);
        Address saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Address not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<AddressResponse> findByUserId(Integer userId) {
        return repository.findByUserId(userId).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AddressResponse findDefaultAddressByUserId(Integer userId) {
        Address address = repository.findDefaultAddressByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Default address not found for user: " + userId));
        return mapper.toResponse(address);
    }

    @Override
    @Transactional
    public List<AddressResponse> findByUserIdAndAddressType(Integer userId, String addressType) {
        return repository.findByUserIdAndAddressType(userId, addressType).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public Long countByUserId(Integer userId) {
        return repository.countByUserId(userId);
    }
}