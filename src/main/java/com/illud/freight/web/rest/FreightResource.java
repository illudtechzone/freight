package com.illud.freight.web.rest;
import com.illud.freight.domain.Freight;
import com.illud.freight.service.FreightService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.FreightDTO;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

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

import javax.validation.Valid;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Freight.
 */
@RestController
@RequestMapping("/api")
public class FreightResource {

    private final Logger log = LoggerFactory.getLogger(FreightResource.class);

    private static final String ENTITY_NAME = "freightFreight";

    private final FreightService freightService;

    public FreightResource(FreightService freightService) {
        this.freightService = freightService;
    }

    /**
     * POST  /freights : Create a new freight.
     *
     * @param freightDTO the freightDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new freightDTO, or with status 400 (Bad Request) if the freight has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/freights")
    public ResponseEntity<FreightDTO> createFreight(@RequestBody FreightDTO freightDTO) throws URISyntaxException {
        log.debug("REST request to save Freight : {}", freightDTO);
        if (freightDTO.getId() != null) {
            throw new BadRequestAlertException("A new freight cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FreightDTO result = freightService.save(freightDTO);
        return ResponseEntity.created(new URI("/api/freights/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /freights : Updates an existing freight.
     *
     * @param freightDTO the freightDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated freightDTO,
     * or with status 400 (Bad Request) if the freightDTO is not valid,
     * or with status 500 (Internal Server Error) if the freightDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/freights")
    public ResponseEntity<FreightDTO> updateFreight(@RequestBody FreightDTO freightDTO) throws URISyntaxException {
        log.debug("REST request to update Freight : {}", freightDTO);
        if (freightDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FreightDTO result = freightService.update(freightDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, freightDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /freights : get all the freights.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of freights in body
     */
    @GetMapping("/freights")
    public ResponseEntity<List<FreightDTO>> getAllFreights(Pageable pageable) {
        log.debug("REST request to get a page of Freights");
        Page<FreightDTO> page = freightService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/freights");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /freights/:id : get the "id" freight.
     *
     * @param id the id of the freightDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the freightDTO, or with status 404 (Not Found)
     */
    @GetMapping("/freights/{id}")
    public ResponseEntity<FreightDTO> getFreight(@PathVariable Long id) {
        log.debug("REST request to get Freight : {}", id);
        Optional<FreightDTO> freightDTO = freightService.findOne(id);
        return ResponseUtil.wrapOrNotFound(freightDTO);
    }

    /**
     * DELETE  /freights/:id : delete the "id" freight.
     *
     * @param id the id of the freightDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/freights/{id}")
    public ResponseEntity<Void> deleteFreight(@PathVariable Long id) {
        log.debug("REST request to delete Freight : {}", id);
        freightService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/freights?query=:query : search for the freight corresponding
     * to the query.
     *
     * @param query the query of the freight search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/freights")
    public ResponseEntity<List<FreightDTO>> searchFreights(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Freights for query {}", query);
        Page<FreightDTO> page = freightService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/freights");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @PostMapping("/convertToDto")
    public ResponseEntity<FreightDTO> createFreightDTO(@RequestBody Freight freight) {
        Optional<FreightDTO> freightDto=freightService.convertToDto(freight);
        return ResponseUtil.wrapOrNotFound(freightDto);
    }
    @PostMapping("/convertToList")
    public ResponseEntity<List<FreightDTO>> createFreightDtoList(@RequestBody List<Freight> page){
    	List<FreightDTO> pageDto = freightService.convertToDtoList(page);
		return ResponseEntity.ok().body(pageDto);
    	
    }

    
    
}
