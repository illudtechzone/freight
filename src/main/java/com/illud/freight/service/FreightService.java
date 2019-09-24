package com.illud.freight.service;

import com.illud.freight.service.dto.FreightDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * Service Interface for managing Freight.
 */
public interface FreightService {

    /**
     * Save a freight.
     *
     * @param freightDTO the entity to save
     * @return the persisted entity
     */
    FreightDTO save(FreightDTO freightDTO);

    /**
     * Get all the freights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FreightDTO> findAll(Pageable pageable);


    /**
     * Get the "id" freight.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FreightDTO> findOne(Long id);

    /**
     * Delete the "id" freight.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the freight corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FreightDTO> search(String query, Pageable pageable);

	List<FreightDTO> getPendingFreights(String name, String nameLike, String assignee, String assigneeLike,
			String candidateUser, String candidateGroup, String candidateGroups, String processInstanceId,
			@Valid String processDefinitionId, @Valid String processDefinitionKey, @Valid String createdOn,
			@Valid String createdBefore, @Valid String createdAfter);

	FreightDTO getBookingDetails(String processInstanceId);
}
