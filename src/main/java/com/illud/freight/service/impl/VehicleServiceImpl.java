package com.illud.freight.service.impl;

import com.illud.freight.service.VehicleService;
import com.illud.freight.domain.Vehicle;
import com.illud.freight.repository.VehicleRepository;
import com.illud.freight.repository.search.VehicleSearchRepository;
import com.illud.freight.service.dto.VehicleDTO;
import com.illud.freight.service.mapper.VehicleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Vehicle.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper;

    private final VehicleSearchRepository vehicleSearchRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, VehicleSearchRepository vehicleSearchRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.vehicleSearchRepository = vehicleSearchRepository;
    }

    /**
     * Save a vehicle.
     *
     * @param vehicleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        log.debug("Request to save Vehicle : {}", vehicleDTO);
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        VehicleDTO result = vehicleMapper.toDto(vehicle);
        vehicleSearchRepository.save(vehicle);
        return result;
    }

	/*
	 * public VehicleDTO update(VehicleDTO vehicleDTO) {
	 * log.debug("Request to save Vehicle : {}", vehicleDTO); Vehicle vehicle =
	 * vehicleMapper.toEntity(vehicleDTO); vehicle =
	 * vehicleRepository.save(vehicle); VehicleDTO result =
	 * vehicleMapper.toDto(vehicle); vehicleSearchRepository.save(vehicle); return
	 * result; }
	 */
    /**
     * Get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vehicles");
        return vehicleRepository.findAll(pageable)
            .map(vehicleMapper::toDto);
    }


    /**
     * Get one vehicle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDTO> findOne(Long id) {
        log.debug("Request to get Vehicle : {}", id);
        return vehicleRepository.findById(id)
            .map(vehicleMapper::toDto);
    }

    /**
     * Delete the vehicle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.deleteById(id);
        vehicleSearchRepository.deleteById(id);
    }

    /**
     * Search for the vehicle corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VehicleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Vehicles for query {}", query);
        return vehicleSearchRepository.search(queryStringQuery(query), pageable)
            .map(vehicleMapper::toDto);
    }

	@Override
	public Optional<VehicleDTO> convertToDto(Vehicle vehicle) {
		log.debug("<<<<< convert to optional toDto >>>>>>",vehicle);
		return Optional.of(vehicle).map(vehicleMapper::toDto);
	}

	@Override
	public List<VehicleDTO> convertToDtoList(List<Vehicle> vehicle) {
		log.debug("<<<< convert to entitylist to toDto >>>>",vehicle);
		List<VehicleDTO> listDto = new ArrayList<>();
		vehicle.forEach(data->{
			listDto.add(vehicleMapper.toDto(data));});
		
		//for(int i=0;i<vehicle.size();i++) {
		//	listDto.add(vehicleMapper.toDto(vehicle.get(i)));
		//}
		return listDto;
	}

	
}
