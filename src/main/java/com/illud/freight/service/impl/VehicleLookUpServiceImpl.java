package com.illud.freight.service.impl;

import com.illud.freight.service.VehicleLookUpService;
import com.illud.freight.domain.VehicleLookUp;
import com.illud.freight.repository.VehicleLookUpRepository;
import com.illud.freight.repository.search.VehicleLookUpSearchRepository;
import com.illud.freight.service.dto.VehicleLookUpDTO;
import com.illud.freight.service.mapper.VehicleLookUpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing VehicleLookUp.
 */
@Service
@Transactional
public class VehicleLookUpServiceImpl implements VehicleLookUpService {

    private final Logger log = LoggerFactory.getLogger(VehicleLookUpServiceImpl.class);

    private final VehicleLookUpRepository vehicleLookUpRepository;

    private final VehicleLookUpMapper vehicleLookUpMapper;

    private final VehicleLookUpSearchRepository vehicleLookUpSearchRepository;

    public VehicleLookUpServiceImpl(VehicleLookUpRepository vehicleLookUpRepository, VehicleLookUpMapper vehicleLookUpMapper, VehicleLookUpSearchRepository vehicleLookUpSearchRepository) {
        this.vehicleLookUpRepository = vehicleLookUpRepository;
        this.vehicleLookUpMapper = vehicleLookUpMapper;
        this.vehicleLookUpSearchRepository = vehicleLookUpSearchRepository;
    }

    /**
     * Save a vehicleLookUp.
     *
     * @param vehicleLookUpDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VehicleLookUpDTO save(VehicleLookUpDTO vehicleLookUpDTO) {
        log.debug("Request to save VehicleLookUp : {}", vehicleLookUpDTO);
        VehicleLookUp vehicleLookUp = vehicleLookUpMapper.toEntity(vehicleLookUpDTO);
        vehicleLookUp = vehicleLookUpRepository.save(vehicleLookUp);
        VehicleLookUpDTO result = vehicleLookUpMapper.toDto(vehicleLookUp);
        vehicleLookUpSearchRepository.save(vehicleLookUp);
        return update(result);
    }
    public VehicleLookUpDTO update(VehicleLookUpDTO vehicleLookUpDTO) {
        log.debug("Request to save VehicleLookUp : {}", vehicleLookUpDTO);
        VehicleLookUp vehicleLookUp = vehicleLookUpMapper.toEntity(vehicleLookUpDTO);
        vehicleLookUp = vehicleLookUpRepository.save(vehicleLookUp);
        VehicleLookUpDTO result = vehicleLookUpMapper.toDto(vehicleLookUp);
        vehicleLookUpSearchRepository.save(vehicleLookUp);
        return result;
    }
    /**
     * Get all the vehicleLookUps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleLookUpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VehicleLookUps");
        return vehicleLookUpRepository.findAll(pageable)
            .map(vehicleLookUpMapper::toDto);
    }


    /**
     * Get one vehicleLookUp by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleLookUpDTO> findOne(Long id) {
        log.debug("Request to get VehicleLookUp : {}", id);
        return vehicleLookUpRepository.findById(id)
            .map(vehicleLookUpMapper::toDto);
    }

    /**
     * Delete the vehicleLookUp by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VehicleLookUp : {}", id);
        vehicleLookUpRepository.deleteById(id);
        vehicleLookUpSearchRepository.deleteById(id);
    }

    /**
     * Search for the vehicleLookUp corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleLookUpDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of VehicleLookUps for query {}", query);
        return vehicleLookUpSearchRepository.search(queryStringQuery(query), pageable)
            .map(vehicleLookUpMapper::toDto);
    }
}
