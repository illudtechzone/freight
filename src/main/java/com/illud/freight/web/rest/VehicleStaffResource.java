package com.illud.freight.web.rest;
import com.illud.freight.service.VehicleStaffService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.VehicleStaffDTO;
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
 * REST controller for managing VehicleStaff.
 */
@RestController
@RequestMapping("/api")
public class VehicleStaffResource {

    private final Logger log = LoggerFactory.getLogger(VehicleStaffResource.class);

    private static final String ENTITY_NAME = "freightVehicleStaff";

    private final VehicleStaffService vehicleStaffService;

    public VehicleStaffResource(VehicleStaffService vehicleStaffService) {
        this.vehicleStaffService = vehicleStaffService;
    }

    /**
     * POST  /vehicle-staffs : Create a new vehicleStaff.
     *
     * @param vehicleStaffDTO the vehicleStaffDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vehicleStaffDTO, or with status 400 (Bad Request) if the vehicleStaff has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vehicle-staffs")
    public ResponseEntity<VehicleStaffDTO> createVehicleStaff(@RequestBody VehicleStaffDTO vehicleStaffDTO) throws URISyntaxException {
        log.debug("REST request to save VehicleStaff : {}", vehicleStaffDTO);
        if (vehicleStaffDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicleStaff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleStaffDTO result = vehicleStaffService.save(vehicleStaffDTO);
        return ResponseEntity.created(new URI("/api/vehicle-staffs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vehicle-staffs : Updates an existing vehicleStaff.
     *
     * @param vehicleStaffDTO the vehicleStaffDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vehicleStaffDTO,
     * or with status 400 (Bad Request) if the vehicleStaffDTO is not valid,
     * or with status 500 (Internal Server Error) if the vehicleStaffDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vehicle-staffs")
    public ResponseEntity<VehicleStaffDTO> updateVehicleStaff(@RequestBody VehicleStaffDTO vehicleStaffDTO) throws URISyntaxException {
        log.debug("REST request to update VehicleStaff : {}", vehicleStaffDTO);
        if (vehicleStaffDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleStaffDTO result = vehicleStaffService.save(vehicleStaffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleStaffDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vehicle-staffs : get all the vehicleStaffs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of vehicleStaffs in body
     */
    @GetMapping("/vehicle-staffs")
    public ResponseEntity<List<VehicleStaffDTO>> getAllVehicleStaffs(Pageable pageable) {
        log.debug("REST request to get a page of VehicleStaffs");
        Page<VehicleStaffDTO> page = vehicleStaffService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vehicle-staffs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /vehicle-staffs/:id : get the "id" vehicleStaff.
     *
     * @param id the id of the vehicleStaffDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vehicleStaffDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vehicle-staffs/{id}")
    public ResponseEntity<VehicleStaffDTO> getVehicleStaff(@PathVariable Long id) {
        log.debug("REST request to get VehicleStaff : {}", id);
        Optional<VehicleStaffDTO> vehicleStaffDTO = vehicleStaffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicleStaffDTO);
    }

    /**
     * DELETE  /vehicle-staffs/:id : delete the "id" vehicleStaff.
     *
     * @param id the id of the vehicleStaffDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vehicle-staffs/{id}")
    public ResponseEntity<Void> deleteVehicleStaff(@PathVariable Long id) {
        log.debug("REST request to delete VehicleStaff : {}", id);
        vehicleStaffService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/vehicle-staffs?query=:query : search for the vehicleStaff corresponding
     * to the query.
     *
     * @param query the query of the vehicleStaff search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/vehicle-staffs")
    public ResponseEntity<List<VehicleStaffDTO>> searchVehicleStaffs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of VehicleStaffs for query {}", query);
        Page<VehicleStaffDTO> page = vehicleStaffService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/vehicle-staffs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PostMapping("/assignvehiclestaff/{vehicleId}/{driverId}")
    public ResponseEntity<VehicleStaffDTO> assignVehicleStaffForDriver(@PathVariable Long staffId,@PathVariable Long vehicleId){
    	log.debug("<<<<<<< assignVehicleStaffForDriver >>>>>>",staffId,vehicleId);
    	Optional<VehicleStaffDTO> opt = vehicleStaffService.AssignDriverAsVehicleStaffOfAnVehicle(staffId,vehicleId);
		return ResponseUtil.wrapOrNotFound(opt);
    	
    }


}
