package com.illud.freight.service;

import com.illud.freight.service.dto.VehicleStaffDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VehicleStaff.
 */
public interface VehicleStaffService {

    /**
     * Save a vehicleStaff.
     *
     * @param vehicleStaffDTO the entity to save
     * @return the persisted entity
     */
    VehicleStaffDTO save(VehicleStaffDTO vehicleStaffDTO);

    /**
     * Get all the vehicleStaffs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VehicleStaffDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vehicleStaff.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VehicleStaffDTO> findOne(Long id);

    /**
     * Delete the "id" vehicleStaff.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the vehicleStaff corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VehicleStaffDTO> search(String query, Pageable pageable);

	Optional<VehicleStaffDTO> AssignDriverAsVehicleStaffOfAnVehicle(Long staffId, Long vehicleId);
}
