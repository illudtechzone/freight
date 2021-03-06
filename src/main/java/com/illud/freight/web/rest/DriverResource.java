package com.illud.freight.web.rest;

import com.illud.freight.domain.Driver;
import com.illud.freight.service.DriverService;
import com.illud.freight.web.rest.errors.BadRequestAlertException;
import com.illud.freight.web.rest.util.HeaderUtil;
import com.illud.freight.web.rest.util.PaginationUtil;
import com.illud.freight.service.dto.DriverDTO;
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
 * REST controller for managing Driver.
 */
@RestController
@RequestMapping("/api")
public class DriverResource {

	private final Logger log = LoggerFactory.getLogger(DriverResource.class);

	private static final String ENTITY_NAME = "freightDriver";

	private final DriverService driverService;

	public DriverResource(DriverService driverService) {
		this.driverService = driverService;
	}

	/**
	 * POST /drivers : Create a new driver.
	 *
	 * @param driverDTO the driverDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         driverDTO, or with status 400 (Bad Request) if the driver has already
	 *         an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/drivers")
	public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) throws URISyntaxException {
		log.debug("REST request to save Driver : {}", driverDTO);
		if (driverDTO.getId() != null) {
			throw new BadRequestAlertException("A new driver cannot already have an ID", ENTITY_NAME, "idexists");
		}
		DriverDTO result = driverService.save(driverDTO);
		return ResponseEntity.created(new URI("/api/drivers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /drivers : Updates an existing driver.
	 *
	 * @param driverDTO the driverDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         driverDTO, or with status 400 (Bad Request) if the driverDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the driverDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/drivers")
	public ResponseEntity<DriverDTO> updateDriver(@RequestBody DriverDTO driverDTO) throws URISyntaxException {
		log.debug("REST request to update Driver : {}", driverDTO);
		if (driverDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		DriverDTO result = driverService.save(driverDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, driverDTO.getId().toString())).body(result);
	}

	/**
	 * GET /drivers : get all the drivers.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of drivers in
	 *         body
	 */
	@GetMapping("/drivers")
	public ResponseEntity<List<DriverDTO>> getAllDrivers(Pageable pageable) {
		log.debug("REST request to get a page of Drivers");
		Page<DriverDTO> page = driverService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/drivers");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /drivers/:id : get the "id" driver.
	 *
	 * @param id the id of the driverDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the driverDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/drivers/{id}")
	public ResponseEntity<DriverDTO> getDriver(@PathVariable Long id) {
		log.debug("REST request to get Driver : {}", id);
		Optional<DriverDTO> driverDTO = driverService.findOne(id);
		return ResponseUtil.wrapOrNotFound(driverDTO);
	}

	/**
	 * DELETE /drivers/:id : delete the "id" driver.
	 *
	 * @param id the id of the driverDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/drivers/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
		log.debug("REST request to delete Driver : {}", id);
		driverService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * SEARCH /_search/drivers?query=:query : search for the driver corresponding to
	 * the query.
	 *
	 * @param query    the query of the driver search
	 * @param pageable the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/drivers")
	public ResponseEntity<List<DriverDTO>> searchDrivers(@RequestParam String query, Pageable pageable) {
		log.debug("REST request to search for a page of Drivers for query {}", query);
		Page<DriverDTO> page = driverService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/drivers");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@PostMapping("/create/driver")
	public ResponseEntity<DriverDTO> createdriverIfnotExist(@RequestBody DriverDTO driverDTO) {
		log.debug("<<<<<<<<< create driver if not exist>>>>>>>", driverDTO);
		Optional<DriverDTO> result = driverService.createdriverIfnotExist(driverDTO);
		return ResponseUtil.wrapOrNotFound(result);

	}

	
	@PostMapping("/createDto/driver")
	public ResponseEntity<DriverDTO> createDto(@RequestBody Driver driver) {
		log.debug("<<<< create dto >>>>>", driver);
		Optional<DriverDTO> opt = driverService.createDto(driver);
		return ResponseUtil.wrapOrNotFound(opt);

	}
	@PostMapping("/createDtoList/driver")
	public ResponseEntity<List<DriverDTO>> createDtoList(@RequestBody List<Driver> driver){
		log.debug("<<<<< createDtoList in rest >>>>>",driver);
		List<DriverDTO> driverdtolist = driverService.createDtoList(driver);
		return ResponseEntity.ok().body(driverdtolist);
		
	}
	 

}
