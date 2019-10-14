package com.illud.freight.service;

import com.illud.freight.service.dto.FulldayPricingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FulldayPricing.
 */
public interface FulldayPricingService {

    /**
     * Save a fulldayPricing.
     *
     * @param fulldayPricingDTO the entity to save
     * @return the persisted entity
     */
    FulldayPricingDTO save(FulldayPricingDTO fulldayPricingDTO);

    /**
     * Get all the fulldayPricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FulldayPricingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fulldayPricing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FulldayPricingDTO> findOne(Long id);

    /**
     * Delete the "id" fulldayPricing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the fulldayPricing corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FulldayPricingDTO> search(String query, Pageable pageable);
}
