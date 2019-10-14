package com.illud.freight.service;

import com.illud.freight.service.dto.PricingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Pricing.
 */
public interface PricingService {

    /**
     * Save a pricing.
     *
     * @param pricingDTO the entity to save
     * @return the persisted entity
     */
    PricingDTO save(PricingDTO pricingDTO);

    /**
     * Get all the pricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PricingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pricing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PricingDTO> findOne(Long id);

    /**
     * Delete the "id" pricing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the pricing corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PricingDTO> search(String query, Pageable pageable);
}
