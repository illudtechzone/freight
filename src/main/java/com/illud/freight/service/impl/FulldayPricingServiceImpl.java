package com.illud.freight.service.impl;

import com.illud.freight.service.FulldayPricingService;
import com.illud.freight.domain.FulldayPricing;
import com.illud.freight.repository.FulldayPricingRepository;
import com.illud.freight.repository.search.FulldayPricingSearchRepository;
import com.illud.freight.service.dto.FulldayPricingDTO;
import com.illud.freight.service.mapper.FulldayPricingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing FulldayPricing.
 */
@Service
@Transactional
public class FulldayPricingServiceImpl implements FulldayPricingService {

    private final Logger log = LoggerFactory.getLogger(FulldayPricingServiceImpl.class);

    private final FulldayPricingRepository fulldayPricingRepository;

    private final FulldayPricingMapper fulldayPricingMapper;

    private final FulldayPricingSearchRepository fulldayPricingSearchRepository;

    public FulldayPricingServiceImpl(FulldayPricingRepository fulldayPricingRepository, FulldayPricingMapper fulldayPricingMapper, FulldayPricingSearchRepository fulldayPricingSearchRepository) {
        this.fulldayPricingRepository = fulldayPricingRepository;
        this.fulldayPricingMapper = fulldayPricingMapper;
        this.fulldayPricingSearchRepository = fulldayPricingSearchRepository;
    }

    /**
     * Save a fulldayPricing.
     *
     * @param fulldayPricingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FulldayPricingDTO save(FulldayPricingDTO fulldayPricingDTO) {
        log.debug("Request to save FulldayPricing : {}", fulldayPricingDTO);
        FulldayPricing fulldayPricing = fulldayPricingMapper.toEntity(fulldayPricingDTO);
        fulldayPricing = fulldayPricingRepository.save(fulldayPricing);
        FulldayPricingDTO result = fulldayPricingMapper.toDto(fulldayPricing);
        fulldayPricingSearchRepository.save(fulldayPricing);
        return result;
    }

    /**
     * Get all the fulldayPricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FulldayPricingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FulldayPricings");
        return fulldayPricingRepository.findAll(pageable)
            .map(fulldayPricingMapper::toDto);
    }


    /**
     * Get one fulldayPricing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FulldayPricingDTO> findOne(Long id) {
        log.debug("Request to get FulldayPricing : {}", id);
        return fulldayPricingRepository.findById(id)
            .map(fulldayPricingMapper::toDto);
    }

    /**
     * Delete the fulldayPricing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FulldayPricing : {}", id);
        fulldayPricingRepository.deleteById(id);
        fulldayPricingSearchRepository.deleteById(id);
    }

    /**
     * Search for the fulldayPricing corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FulldayPricingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of FulldayPricings for query {}", query);
        return fulldayPricingSearchRepository.search(queryStringQuery(query), pageable)
            .map(fulldayPricingMapper::toDto);
    }
}
