package com.illud.freight.web.rest;
import com.illud.freight.client.activiti_rest_api.model.freight.TransportOwnerResponse;
import com.illud.freight.service.QuotationService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.QuotationDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Quotation.
 */
@RestController
@RequestMapping("/api")
public class QuotationResource {

    private final Logger log = LoggerFactory.getLogger(QuotationResource.class);

    private static final String ENTITY_NAME = "freightQuotation";

    private final QuotationService quotationService;

    public QuotationResource(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    /**
     * POST  /quotations : Create a new quotation.
     *
     * @param quotationDTO the quotationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotationDTO, or with status 400 (Bad Request) if the quotation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quotations")
    public ResponseEntity<QuotationDTO> createQuotation(@RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to save Quotation : {}", quotationDTO);
        if (quotationDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.created(new URI("/api/quotations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quotations : Updates an existing quotation.
     *
     * @param quotationDTO the quotationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotationDTO,
     * or with status 400 (Bad Request) if the quotationDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quotations")
    public ResponseEntity<QuotationDTO> updateQuotation(@RequestBody QuotationDTO quotationDTO) throws URISyntaxException {
        log.debug("REST request to update Quotation : {}", quotationDTO);
        if (quotationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuotationDTO result = quotationService.save(quotationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quotations : get all the quotations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quotations in body
     */
    @GetMapping("/quotations")
    public ResponseEntity<List<QuotationDTO>> getAllQuotations(Pageable pageable) {
        log.debug("REST request to get a page of Quotations");
        Page<QuotationDTO> page = quotationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quotations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quotations/:id : get the "id" quotation.
     *
     * @param id the id of the quotationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quotations/{id}")
    public ResponseEntity<QuotationDTO> getQuotation(@PathVariable Long id) {
        log.debug("REST request to get Quotation : {}", id);
        Optional<QuotationDTO> quotationDTO = quotationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quotationDTO);
    }

    /**
     * DELETE  /quotations/:id : delete the "id" quotation.
     *
     * @param id the id of the quotationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quotations/{id}")
    public ResponseEntity<Void> deleteQuotation(@PathVariable Long id) {
        log.debug("REST request to delete Quotation : {}", id);
        quotationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quotations?query=:query : search for the quotation corresponding
     * to the query.
     *
     * @param query the query of the quotation search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quotations")
    public ResponseEntity<List<QuotationDTO>> searchQuotations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Quotations for query {}", query);
        Page<QuotationDTO> page = quotationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quotations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    

    
	
    
}
