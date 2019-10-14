package com.illud.freight.service.impl;

import com.illud.freight.service.PricingService;
import com.illud.freight.domain.Pricing;
import com.illud.freight.repository.PricingRepository;
import com.illud.freight.repository.search.PricingSearchRepository;
import com.illud.freight.service.dto.PricingDTO;
import com.illud.freight.service.mapper.PricingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Pricing.
 */
@Service
@Transactional
public class PricingServiceImpl implements PricingService {

    private final Logger log = LoggerFactory.getLogger(PricingServiceImpl.class);

    private final PricingRepository pricingRepository;

    private final PricingMapper pricingMapper;

    private final PricingSearchRepository pricingSearchRepository;

    public PricingServiceImpl(PricingRepository pricingRepository, PricingMapper pricingMapper, PricingSearchRepository pricingSearchRepository) {
        this.pricingRepository = pricingRepository;
        this.pricingMapper = pricingMapper;
        this.pricingSearchRepository = pricingSearchRepository;
    }

    /**
     * Save a pricing.
     *
     * @param pricingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PricingDTO save(PricingDTO pricingDTO) {
        log.debug("Request to save Pricing : {}", pricingDTO);
        Pricing pricing = pricingMapper.toEntity(pricingDTO);
        pricing = pricingRepository.save(pricing);
        PricingDTO result = pricingMapper.toDto(pricing);
        pricingSearchRepository.save(pricing);
        return result;
    }

    /**
     * Get all the pricings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PricingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pricings");
        return pricingRepository.findAll(pageable)
            .map(pricingMapper::toDto);
    }


    /**
     * Get one pricing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PricingDTO> findOne(Long id) {
        log.debug("Request to get Pricing : {}", id);
        return pricingRepository.findById(id)
            .map(pricingMapper::toDto);
    }

    /**
     * Delete the pricing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pricing : {}", id);
        pricingRepository.deleteById(id);
        pricingSearchRepository.deleteById(id);
    }

    /**
     * Search for the pricing corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PricingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Pricings for query {}", query);
        return pricingSearchRepository.search(queryStringQuery(query), pageable)
            .map(pricingMapper::toDto);
    }
}
