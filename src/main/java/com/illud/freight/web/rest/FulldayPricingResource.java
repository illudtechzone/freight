package com.illud.freight.web.rest;
import com.illud.freight.service.FulldayPricingService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.FulldayPricingDTO;
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
 * REST controller for managing FulldayPricing.
 */
@RestController
@RequestMapping("/api")
public class FulldayPricingResource {

    private final Logger log = LoggerFactory.getLogger(FulldayPricingResource.class);

    private static final String ENTITY_NAME = "freightFulldayPricing";

    private final FulldayPricingService fulldayPricingService;

    public FulldayPricingResource(FulldayPricingService fulldayPricingService) {
        this.fulldayPricingService = fulldayPricingService;
    }

    /**
     * POST  /fullday-pricings : Create a new fulldayPricing.
     *
     * @param fulldayPricingDTO the fulldayPricingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fulldayPricingDTO, or with status 400 (Bad Request) if the fulldayPricing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fullday-pricings")
    public ResponseEntity<FulldayPricingDTO> createFulldayPricing(@RequestBody FulldayPricingDTO fulldayPricingDTO) throws URISyntaxException {
        log.debug("REST request to save FulldayPricing : {}", fulldayPricingDTO);
        if (fulldayPricingDTO.getId() != null) {
            throw new BadRequestAlertException("A new fulldayPricing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FulldayPricingDTO result = fulldayPricingService.save(fulldayPricingDTO);
        return ResponseEntity.created(new URI("/api/fullday-pricings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fullday-pricings : Updates an existing fulldayPricing.
     *
     * @param fulldayPricingDTO the fulldayPricingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fulldayPricingDTO,
     * or with status 400 (Bad Request) if the fulldayPricingDTO is not valid,
     * or with status 500 (Internal Server Error) if the fulldayPricingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fullday-pricings")
    public ResponseEntity<FulldayPricingDTO> updateFulldayPricing(@RequestBody FulldayPricingDTO fulldayPricingDTO) throws URISyntaxException {
        log.debug("REST request to update FulldayPricing : {}", fulldayPricingDTO);
        if (fulldayPricingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FulldayPricingDTO result = fulldayPricingService.save(fulldayPricingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fulldayPricingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fullday-pricings : get all the fulldayPricings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fulldayPricings in body
     */
    @GetMapping("/fullday-pricings")
    public ResponseEntity<List<FulldayPricingDTO>> getAllFulldayPricings(Pageable pageable) {
        log.debug("REST request to get a page of FulldayPricings");
        Page<FulldayPricingDTO> page = fulldayPricingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fullday-pricings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fullday-pricings/:id : get the "id" fulldayPricing.
     *
     * @param id the id of the fulldayPricingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fulldayPricingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fullday-pricings/{id}")
    public ResponseEntity<FulldayPricingDTO> getFulldayPricing(@PathVariable Long id) {
        log.debug("REST request to get FulldayPricing : {}", id);
        Optional<FulldayPricingDTO> fulldayPricingDTO = fulldayPricingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fulldayPricingDTO);
    }

    /**
     * DELETE  /fullday-pricings/:id : delete the "id" fulldayPricing.
     *
     * @param id the id of the fulldayPricingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fullday-pricings/{id}")
    public ResponseEntity<Void> deleteFulldayPricing(@PathVariable Long id) {
        log.debug("REST request to delete FulldayPricing : {}", id);
        fulldayPricingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/fullday-pricings?query=:query : search for the fulldayPricing corresponding
     * to the query.
     *
     * @param query the query of the fulldayPricing search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/fullday-pricings")
    public ResponseEntity<List<FulldayPricingDTO>> searchFulldayPricings(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FulldayPricings for query {}", query);
        Page<FulldayPricingDTO> page = fulldayPricingService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/fullday-pricings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
