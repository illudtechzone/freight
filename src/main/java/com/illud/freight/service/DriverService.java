package com.illud.freight.service;

import com.illud.freight.domain.Driver;
import com.illud.freight.service.dto.DriverDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Driver.
 */
public interface DriverService {

    /**
     * Save a driver.
     *
     * @param driverDTO the entity to save
     * @return the persisted entity
     */
    DriverDTO save(DriverDTO driverDTO);

    /**
     * Get all the drivers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DriverDTO> findAll(Pageable pageable);


    /**
     * Get the "id" driver.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DriverDTO> findOne(Long id);

    /**
     * Delete the "id" driver.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the driver corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DriverDTO> search(String query, Pageable pageable);

	Optional<DriverDTO> createdriverIfnotExist(DriverDTO driverDTO);

	Optional<DriverDTO> createDto(Driver driver);

	List<DriverDTO> createDtoList(List<Driver> driver);
}
