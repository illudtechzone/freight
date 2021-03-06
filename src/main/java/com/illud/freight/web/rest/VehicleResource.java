package com.illud.freight.web.rest;
import com.illud.freight.domain.Vehicle;
import com.illud.freight.service.VehicleService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.VehicleDTO;
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
 * REST controller for managing Vehicle.
 */
@RestController
@RequestMapping("/api")
public class VehicleResource {

    private final Logger log = LoggerFactory.getLogger(VehicleResource.class);

    private static final String ENTITY_NAME = "freightVehicle";

    private final VehicleService vehicleService;

    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /**
     * POST  /vehicles : Create a new vehicle.
     *
     * @param vehicleDTO the vehicleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleDTO, or with status 400 (Bad Request) if the vehicle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) throws URISyntaxException {
        log.debug("REST request to save Vehicle : {}", vehicleDTO);
        if (vehicleDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleDTO result = vehicleService.save(vehicleDTO);
        if (result.getId()== null) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idnull");
        }
        VehicleDTO result1 = vehicleService.save(result);
        return ResponseEntity.created(new URI("/api/vehicles/" + result1.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result1.getId().toString()))
            .body(result1);
    }

    /**
     * PUT  /vehicles : Updates an existing vehicle.
     *
     * @param vehicleDTO the vehicleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleDTO,
     * or with status 400 (Bad Request) if the vehicleDTO is not valid,
     * or with status 500 (Internal Server Error) if the vehicleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicles")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicleDTO) throws URISyntaxException {
        log.debug("REST request to update Vehicle : {}", vehicleDTO);
        if (vehicleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleDTO result = vehicleService.save(vehicleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicles : get all the vehicles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vehicles in body
     */
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(Pageable pageable) {
        log.debug("REST request to get a page of Vehicles");
        Page<VehicleDTO> page = vehicleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vehicles/:id : get the "id" vehicle.
     *
     * @param id the id of the vehicleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable Long id) {
        log.debug("REST request to get Vehicle : {}", id);
        Optional<VehicleDTO> vehicleDTO = vehicleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicleDTO);
    }

    /**
     * DELETE  /vehicles/:id : delete the "id" vehicle.
     *
     * @param id the id of the vehicleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        log.debug("REST request to delete Vehicle : {}", id);
        vehicleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/vehicles?query=:query : search for the vehicle corresponding
     * to the query.
     *
     * @param query the query of the vehicle search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/vehicles")
    public ResponseEntity<List<VehicleDTO>> searchVehicles(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Vehicles for query {}", query);
        Page<VehicleDTO> page = vehicleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/vehicles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/convertToVehicleDto")
    public ResponseEntity<VehicleDTO> createVehicleDto(@RequestBody Vehicle vehicle){
    	log.debug("<<<<< convert to dto in rest >>>>>>",vehicle);
    	Optional<VehicleDTO> result = vehicleService.convertToDto(vehicle);
		return ResponseUtil.wrapOrNotFound(result);
    	
    }
    @PostMapping("/converToVehicleDtoList")
    public ResponseEntity<List<VehicleDTO>> createVehicleDtoList(@RequestBody List<Vehicle> list){
    	log.debug("<<<<<< convert to dto list in rest >>>>>>",list);
    	List<VehicleDTO> pageDto = vehicleService.convertToDtoList(list);
		return ResponseEntity.ok().body(pageDto);
    	
    }

}
