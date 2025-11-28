package com.api.furniture.repository;

import com.api.furniture.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AddressRepository extends JpaRepository<Address, Integer> {
    
    List<Address> findByUserId(Integer userId);
    
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId AND a.isDefault = true")
    Optional<Address> findDefaultAddressByUserId(@Param("userId") Integer userId);
    
    @Query("SELECT a FROM Address a WHERE a.user.id = :userId AND a.addressType = :addressType")
    List<Address> findByUserIdAndAddressType(@Param("userId") Integer userId, 
                                           @Param("addressType") String addressType);
    
    @Query("SELECT COUNT(a) FROM Address a WHERE a.user.id = :userId")
    Long countByUserId(@Param("userId") Integer userId);
}