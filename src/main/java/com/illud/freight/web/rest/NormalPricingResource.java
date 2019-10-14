package com.illud.freight.web.rest;
import com.illud.freight.service.NormalPricingService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.NormalPricingDTO;
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
 * REST controller for managing NormalPricing.
 */
@RestController
@RequestMapping("/api")
public class NormalPricingResource {

    private final Logger log = LoggerFactory.getLogger(NormalPricingResource.class);

    private static final String ENTITY_NAME = "freightNormalPricing";

    private final NormalPricingService normalPricingService;

    public NormalPricingResource(NormalPricingService normalPricingService) {
        this.normalPricingService = normalPricingService;
    }

    /**
     * POST  /normal-pricings : Create a new normalPricing.
     *
     * @param normalPricingDTO the normalPricingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new normalPricingDTO, or with status 400 (Bad Request) if the normalPricing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/normal-pricings")
    public ResponseEntity<NormalPricingDTO> createNormalPricing(@RequestBody NormalPricingDTO normalPricingDTO) throws URISyntaxException {
        log.debug("REST request to save NormalPricing : {}", normalPricingDTO);
        if (normalPricingDTO.getId() != null) {
            throw new BadRequestAlertException("A new normalPricing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NormalPricingDTO result = normalPricingService.save(normalPricingDTO);
        return ResponseEntity.created(new URI("/api/normal-pricings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /normal-pricings : Updates an existing normalPricing.
     *
     * @param normalPricingDTO the normalPricingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated normalPricingDTO,
     * or with status 400 (Bad Request) if the normalPricingDTO is not valid,
     * or with status 500 (Internal Server Error) if the normalPricingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/normal-pricings")
    public ResponseEntity<NormalPricingDTO> updateNormalPricing(@RequestBody NormalPricingDTO normalPricingDTO) throws URISyntaxException {
        log.debug("REST request to update NormalPricing : {}", normalPricingDTO);
        if (normalPricingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NormalPricingDTO result = normalPricingService.save(normalPricingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, normalPricingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /normal-pricings : get all the normalPricings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of normalPricings in body
     */
    @GetMapping("/normal-pricings")
    public ResponseEntity<List<NormalPricingDTO>> getAllNormalPricings(Pageable pageable) {
        log.debug("REST request to get a page of NormalPricings");
        Page<NormalPricingDTO> page = normalPricingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/normal-pricings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /normal-pricings/:id : get the "id" normalPricing.
     *
     * @param id the id of the normalPricingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the normalPricingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/normal-pricings/{id}")
    public ResponseEntity<NormalPricingDTO> getNormalPricing(@PathVariable Long id) {
        log.debug("REST request to get NormalPricing : {}", id);
        Optional<NormalPricingDTO> normalPricingDTO = normalPricingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(normalPricingDTO);
    }

    /**
     * DELETE  /normal-pricings/:id : delete the "id" normalPricing.
     *
     * @param id the id of the normalPricingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/normal-pricings/{id}")
    public ResponseEntity<Void> deleteNormalPricing(@PathVariable Long id) {
        log.debug("REST request to delete NormalPricing : {}", id);
        normalPricingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/normal-pricings?query=:query : search for the normalPricing corresponding
     * to the query.
     *
     * @param query the query of the normalPricing search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/normal-pricings")
    public ResponseEntity<List<NormalPricingDTO>> searchNormalPricings(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of NormalPricings for query {}", query);
        Page<NormalPricingDTO> page = normalPricingService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/normal-pricings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
