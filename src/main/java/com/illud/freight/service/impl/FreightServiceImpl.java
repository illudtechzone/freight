package com.illud.freight.service.impl;

import com.illud.freight.service.FreightService;
import com.illud.freight.domain.Freight;
import com.illud.freight.repository.FreightRepository;
import com.illud.freight.repository.search.FreightSearchRepository;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.mapper.FreightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Freight.
 */
@Service
@Transactional
public class FreightServiceImpl implements FreightService {

    private final Logger log = LoggerFactory.getLogger(FreightServiceImpl.class);

    private final FreightRepository freightRepository;

    private final FreightMapper freightMapper;

    private final FreightSearchRepository freightSearchRepository;

    public FreightServiceImpl(FreightRepository freightRepository, FreightMapper freightMapper, FreightSearchRepository freightSearchRepository) {
        this.freightRepository = freightRepository;
        this.freightMapper = freightMapper;
        this.freightSearchRepository = freightSearchRepository;
    }

    /**
     * Save a freight.
     *
     * @param freightDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FreightDTO save(FreightDTO freightDTO) {
        log.debug("Request to save Freight : {}", freightDTO);
        Freight freight = freightMapper.toEntity(freightDTO);
        freight = freightRepository.save(freight);
        FreightDTO result = freightMapper.toDto(freight);
        freightSearchRepository.save(freight);
        return result;
    }

    /**
     * Get all the freights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FreightDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Freights");
        return freightRepository.findAll(pageable)
            .map(freightMapper::toDto);
    }


    /**
     * Get one freight by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FreightDTO> findOne(Long id) {
        log.debug("Request to get Freight : {}", id);
        return freightRepository.findById(id)
            .map(freightMapper::toDto);
    }

    /**
     * Delete the freight by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Freight : {}", id);
        freightRepository.deleteById(id);
        freightSearchRepository.deleteById(id);
    }

    /**
     * Search for the freight corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FreightDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Freights for query {}", query);
        return freightSearchRepository.search(queryStringQuery(query), pageable)
            .map(freightMapper::toDto);
    }
}
