package com.illud.freight.service;

import com.illud.freight.service.dto.NormalPricingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NormalPricing.
 */
public interface NormalPricingService {

    /**
     * Save a normalPricing.
     *
     * @param normalPricingDTO the entity to save
     * @return the persisted entity
     */
    NormalPricingDTO save(NormalPricingDTO normalPricingDTO);

    /**
     * Get all the normalPricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NormalPricingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" normalPricing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NormalPricingDTO> findOne(Long id);

    /**
     * Delete the "id" normalPricing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the normalPricing corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NormalPricingDTO> search(String query, Pageable pageable);
}
