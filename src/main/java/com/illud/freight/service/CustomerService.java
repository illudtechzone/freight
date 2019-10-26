package com.illud.freight.service;

import com.illud.freight.client.activiti_rest_api.model.freight.CustomerStatus;
import com.illud.freight.domain.Customer;
import com.illud.freight.service.dto.CustomerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    CustomerDTO save(CustomerDTO customerDTO);

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CustomerDTO> findOne(Long id);

    /**
     * Delete the "id" customer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the customer corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerDTO> search(String query, Pageable pageable);

	Optional<CustomerDTO> createcustomerIfnotExist(CustomerDTO customerDTO);

	Optional<CustomerDTO> createCustomerDto(Customer customer);

	List<CustomerDTO> createDtoList(List<Customer> customers);

	

	
}
