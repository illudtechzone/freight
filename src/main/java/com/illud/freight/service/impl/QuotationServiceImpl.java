package com.illud.freight.service.impl;

import com.illud.freight.service.QuotationService;
import com.illud.freight.client.activiti_rest_api.api.FormsApi;
import com.illud.freight.client.activiti_rest_api.model.RestFormProperty;
import com.illud.freight.client.activiti_rest_api.model.SubmitFormRequest;
import com.illud.freight.client.activiti_rest_api.model.freight.TransportOwnerResponse;
import com.illud.freight.domain.Quotation;
import com.illud.freight.repository.QuotationRepository;
import com.illud.freight.repository.search.QuotationSearchRepository;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.QuotationDTO;
import com.illud.freight.service.mapper.QuotationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Quotation.
 */
@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

    private final Logger log = LoggerFactory.getLogger(QuotationServiceImpl.class);

    private final QuotationRepository quotationRepository;

    private final QuotationMapper quotationMapper;

    private final QuotationSearchRepository quotationSearchRepository;
    
    @Autowired
    FormsApi formsApi;

    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper, QuotationSearchRepository quotationSearchRepository) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
        this.quotationSearchRepository = quotationSearchRepository;
    }

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public QuotationDTO save(QuotationDTO quotationDTO) {
        log.debug("Request to save Quotation : {}", quotationDTO);
        Quotation quotation = quotationMapper.toEntity(quotationDTO);
        quotation = quotationRepository.save(quotation);
        QuotationDTO result = quotationMapper.toDto(quotation);
        quotationSearchRepository.save(quotation);
        return result;
    }

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quotations");
        return quotationRepository.findAll(pageable)
            .map(quotationMapper::toDto);
    }


    /**
     * Get one quotation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<QuotationDTO> findOne(Long id) {
        log.debug("Request to get Quotation : {}", id);
        return quotationRepository.findById(id)
            .map(quotationMapper::toDto);
    }

    /**
     * Delete the quotation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Quotation : {}", id);
        quotationRepository.deleteById(id);
        quotationSearchRepository.deleteById(id);
    }

    /**
     * Search for the quotation corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<QuotationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Quotations for query {}", query);
        return quotationSearchRepository.search(queryStringQuery(query), pageable)
            .map(quotationMapper::toDto);
    }
    

	/*
	 * @Override public void sendQuatation(String taskId,QuotationDTO response) {
	 * 
	 * log .info("into ====================sendQuatation()");
	 * List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
	 * SubmitFormRequest submitFormRequest = new SubmitFormRequest();
	 * submitFormRequest.setAction("completed");
	 * submitFormRequest.setTaskId(taskId);
	 * 
	 * RestFormProperty finalizedBudgetFormProperty = new RestFormProperty();
	 * finalizedBudgetFormProperty.setId("finalizedBudget");
	 * finalizedBudgetFormProperty.setName("finalizedBudget");
	 * finalizedBudgetFormProperty.setType("String");
	 * finalizedBudgetFormProperty.setReadable(true); String amount =
	 * ""+response.getAmount(); finalizedBudgetFormProperty.setValue(amount);
	 * formProperties.add(finalizedBudgetFormProperty);
	 * 
	 * RestFormProperty truckTypeFormProperty = new RestFormProperty();
	 * truckTypeFormProperty.setId("truckType");
	 * truckTypeFormProperty.setName("truckType");
	 * truckTypeFormProperty.setType("String");
	 * truckTypeFormProperty.setReadable(true); String vehicleType =
	 * ""+response.getVehicleId(); truckTypeFormProperty.setValue(vehicleType);
	 * formProperties.add(truckTypeFormProperty);
	 * 
	 * 
	 * RestFormProperty freightIdFormProperty = new RestFormProperty();
	 * freightIdFormProperty.setId("freightId");
	 * freightIdFormProperty.setName("freightId");
	 * freightIdFormProperty.setType("String");
	 * freightIdFormProperty.setReadable(true); String customerId =
	 * ""+response.getFreightId(); freightIdFormProperty.setValue(customerId);
	 * formProperties.add(freightIdFormProperty);
	 * 
	 * submitFormRequest.setProperties(formProperties);
	 * formsApi.submitForm(submitFormRequest); save(response);
	 * 
	 * }
	 */
    
    
}
