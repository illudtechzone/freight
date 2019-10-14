package com.illud.freight.service.impl;

import com.illud.freight.service.NormalPricingService;
import com.illud.freight.domain.NormalPricing;
import com.illud.freight.repository.NormalPricingRepository;
import com.illud.freight.repository.search.NormalPricingSearchRepository;
import com.illud.freight.service.dto.NormalPricingDTO;
import com.illud.freight.service.mapper.NormalPricingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing NormalPricing.
 */
@Service
@Transactional
public class NormalPricingServiceImpl implements NormalPricingService {

    private final Logger log = LoggerFactory.getLogger(NormalPricingServiceImpl.class);

    private final NormalPricingRepository normalPricingRepository;

    private final NormalPricingMapper normalPricingMapper;

    private final NormalPricingSearchRepository normalPricingSearchRepository;

    public NormalPricingServiceImpl(NormalPricingRepository normalPricingRepository, NormalPricingMapper normalPricingMapper, NormalPricingSearchRepository normalPricingSearchRepository) {
        this.normalPricingRepository = normalPricingRepository;
        this.normalPricingMapper = normalPricingMapper;
        this.normalPricingSearchRepository = normalPricingSearchRepository;
    }

    /**
     * Save a normalPricing.
     *
     * @param normalPricingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NormalPricingDTO save(NormalPricingDTO normalPricingDTO) {
        log.debug("Request to save NormalPricing : {}", normalPricingDTO);
        NormalPricing normalPricing = normalPricingMapper.toEntity(normalPricingDTO);
        normalPricing = normalPricingRepository.save(normalPricing);
        NormalPricingDTO result = normalPricingMapper.toDto(normalPricing);
        normalPricingSearchRepository.save(normalPricing);
        return result;
    }

    /**
     * Get all the normalPricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NormalPricingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NormalPricings");
        return normalPricingRepository.findAll(pageable)
            .map(normalPricingMapper::toDto);
    }


    /**
     * Get one normalPricing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NormalPricingDTO> findOne(Long id) {
        log.debug("Request to get NormalPricing : {}", id);
        return normalPricingRepository.findById(id)
            .map(normalPricingMapper::toDto);
    }

    /**
     * Delete the normalPricing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NormalPricing : {}", id);
        normalPricingRepository.deleteById(id);
        normalPricingSearchRepository.deleteById(id);
    }

    /**
     * Search for the normalPricing corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NormalPricingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of NormalPricings for query {}", query);
        return normalPricingSearchRepository.search(queryStringQuery(query), pageable)
            .map(normalPricingMapper::toDto);
    }
}
