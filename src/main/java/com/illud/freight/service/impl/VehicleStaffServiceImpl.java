package com.illud.freight.service.impl;

import com.illud.freight.service.VehicleStaffService;
import com.illud.freight.domain.VehicleStaff;
import com.illud.freight.repository.VehicleStaffRepository;
import com.illud.freight.repository.search.VehicleStaffSearchRepository;
import com.illud.freight.service.dto.VehicleStaffDTO;
import com.illud.freight.service.mapper.VehicleStaffMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing VehicleStaff.
 */
@Service
@Transactional
public class VehicleStaffServiceImpl implements VehicleStaffService {

    private final Logger log = LoggerFactory.getLogger(VehicleStaffServiceImpl.class);

    private final VehicleStaffRepository vehicleStaffRepository;

    private final VehicleStaffMapper vehicleStaffMapper;

    private final VehicleStaffSearchRepository vehicleStaffSearchRepository;

    public VehicleStaffServiceImpl(VehicleStaffRepository vehicleStaffRepository, VehicleStaffMapper vehicleStaffMapper, VehicleStaffSearchRepository vehicleStaffSearchRepository) {
        this.vehicleStaffRepository = vehicleStaffRepository;
        this.vehicleStaffMapper = vehicleStaffMapper;
        this.vehicleStaffSearchRepository = vehicleStaffSearchRepository;
    }

    /**
     * Save a vehicleStaff.
     *
     * @param vehicleStaffDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VehicleStaffDTO save(VehicleStaffDTO vehicleStaffDTO) {
        log.debug("Request to save VehicleStaff : {}", vehicleStaffDTO);
        VehicleStaff vehicleStaff = vehicleStaffMapper.toEntity(vehicleStaffDTO);
        vehicleStaff = vehicleStaffRepository.save(vehicleStaff);
        VehicleStaffDTO result = vehicleStaffMapper.toDto(vehicleStaff);
        vehicleStaffSearchRepository.save(vehicleStaff);
        return result;
    }

    /**
     * Get all the vehicleStaffs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleStaffDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VehicleStaffs");
        return vehicleStaffRepository.findAll(pageable)
            .map(vehicleStaffMapper::toDto);
    }


    /**
     * Get one vehicleStaff by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleStaffDTO> findOne(Long id) {
        log.debug("Request to get VehicleStaff : {}", id);
        return vehicleStaffRepository.findById(id)
            .map(vehicleStaffMapper::toDto);
    }

    /**
     * Delete the vehicleStaff by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VehicleStaff : {}", id);
        vehicleStaffRepository.deleteById(id);
        vehicleStaffSearchRepository.deleteById(id);
    }

    /**
     * Search for the vehicleStaff corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleStaffDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of VehicleStaffs for query {}", query);
        return vehicleStaffSearchRepository.search(queryStringQuery(query), pageable)
            .map(vehicleStaffMapper::toDto);
    }
}
