package com.illud.freight.service;

import com.illud.freight.service.dto.FreightDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Freight.
 */
public interface FreightService {

    /**
     * Save a freight.
     *
     * @param freightDTO the entity to save
     * @return the persisted entity
     */
    FreightDTO save(FreightDTO freightDTO);

    /**
     * Get all the freights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FreightDTO> findAll(Pageable pageable);


    /**
     * Get the "id" freight.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FreightDTO> findOne(Long id);

    /**
     * Delete the "id" freight.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the freight corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FreightDTO> search(String query, Pageable pageable);
}
