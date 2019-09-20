package com.illud.freight.web.rest;
import com.illud.freight.service.VehicleLookUpService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.VehicleLookUpDTO;
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
 * REST controller for managing VehicleLookUp.
 */
@RestController
@RequestMapping("/api")
public class VehicleLookUpResource {

    private final Logger log = LoggerFactory.getLogger(VehicleLookUpResource.class);

    private static final String ENTITY_NAME = "freightVehicleLookUp";

    private final VehicleLookUpService vehicleLookUpService;

    public VehicleLookUpResource(VehicleLookUpService vehicleLookUpService) {
        this.vehicleLookUpService = vehicleLookUpService;
    }

    /**
     * POST  /vehicle-look-ups : Create a new vehicleLookUp.
     *
     * @param vehicleLookUpDTO the vehicleLookUpDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleLookUpDTO, or with status 400 (Bad Request) if the vehicleLookUp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-look-ups")
    public ResponseEntity<VehicleLookUpDTO> createVehicleLookUp(@RequestBody VehicleLookUpDTO vehicleLookUpDTO) throws URISyntaxException {
        log.debug("REST request to save VehicleLookUp : {}", vehicleLookUpDTO);
        if (vehicleLookUpDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicleLookUp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleLookUpDTO result = vehicleLookUpService.save(vehicleLookUpDTO);
        return ResponseEntity.created(new URI("/api/vehicle-look-ups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicle-look-ups : Updates an existing vehicleLookUp.
     *
     * @param vehicleLookUpDTO the vehicleLookUpDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleLookUpDTO,
     * or with status 400 (Bad Request) if the vehicleLookUpDTO is not valid,
     * or with status 500 (Internal Server Error) if the vehicleLookUpDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-look-ups")
    public ResponseEntity<VehicleLookUpDTO> updateVehicleLookUp(@RequestBody VehicleLookUpDTO vehicleLookUpDTO) throws URISyntaxException {
        log.debug("REST request to update VehicleLookUp : {}", vehicleLookUpDTO);
        if (vehicleLookUpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleLookUpDTO result = vehicleLookUpService.save(vehicleLookUpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleLookUpDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-look-ups : get all the vehicleLookUps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleLookUps in body
     */
    @GetMapping("/vehicle-look-ups")
    public ResponseEntity<List<VehicleLookUpDTO>> getAllVehicleLookUps(Pageable pageable) {
        log.debug("REST request to get a page of VehicleLookUps");
        Page<VehicleLookUpDTO> page = vehicleLookUpService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicle-look-ups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vehicle-look-ups/:id : get the "id" vehicleLookUp.
     *
     * @param id the id of the vehicleLookUpDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleLookUpDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vehicle-look-ups/{id}")
    public ResponseEntity<VehicleLookUpDTO> getVehicleLookUp(@PathVariable Long id) {
        log.debug("REST request to get VehicleLookUp : {}", id);
        Optional<VehicleLookUpDTO> vehicleLookUpDTO = vehicleLookUpService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicleLookUpDTO);
    }

    /**
     * DELETE  /vehicle-look-ups/:id : delete the "id" vehicleLookUp.
     *
     * @param id the id of the vehicleLookUpDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicle-look-ups/{id}")
    public ResponseEntity<Void> deleteVehicleLookUp(@PathVariable Long id) {
        log.debug("REST request to delete VehicleLookUp : {}", id);
        vehicleLookUpService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/vehicle-look-ups?query=:query : search for the vehicleLookUp corresponding
     * to the query.
     *
     * @param query the query of the vehicleLookUp search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/vehicle-look-ups")
    public ResponseEntity<List<VehicleLookUpDTO>> searchVehicleLookUps(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of VehicleLookUps for query {}", query);
        Page<VehicleLookUpDTO> page = vehicleLookUpService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/vehicle-look-ups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
