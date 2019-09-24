package com.illud.freight.repository;

import com.illud.freight.domain.Customer;
import com.illud.freight.service.dto.CustomerDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerIdpCode(String customerIdpCode);



}
