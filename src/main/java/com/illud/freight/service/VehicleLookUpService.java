package com.illud.freight.service;

import com.illud.freight.service.dto.VehicleLookUpDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VehicleLookUp.
 */
public interface VehicleLookUpService {

    /**
     * Save a vehicleLookUp.
     *
     * @param vehicleLookUpDTO the entity to save
     * @return the persisted entity
     */
    VehicleLookUpDTO save(VehicleLookUpDTO vehicleLookUpDTO);

    /**
     * Get all the vehicleLookUps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VehicleLookUpDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vehicleLookUp.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VehicleLookUpDTO> findOne(Long id);

    /**
     * Delete the "id" vehicleLookUp.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the vehicleLookUp corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VehicleLookUpDTO> search(String query, Pageable pageable);
}
