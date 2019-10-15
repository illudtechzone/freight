package com.illud.freight.service;

import com.illud.freight.client.activiti_rest_api.model.freight.TransportOwnerResponse;
import com.illud.freight.domain.Quotation;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.QuotationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Quotation.
 */
public interface QuotationService {

    /**
     * Save a quotation.
     *
     * @param quotationDTO the entity to save
     * @return the persisted entity
     */
    QuotationDTO save(QuotationDTO quotationDTO);

    /**
     * Get all the quotations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" quotation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<QuotationDTO> findOne(Long id);

    /**
     * Delete the "id" quotation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the quotation corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<QuotationDTO> search(String query, Pageable pageable);

	Optional<QuotationDTO> createQuotationDto(Quotation quotation);

	List<QuotationDTO> createQuotationsDtoList(List<Quotation> quotations);
    
    //void sendQuatation(String taskId,QuotationDTO response);

}
