package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.Freight;
import com.illud.freight.repository.FreightRepository;
import com.illud.freight.repository.search.FreightSearchRepository;
import com.illud.freight.service.FreightService;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.mapper.FreightMapper;
import com.illud.freight.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;


import static com.illud.freight.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.illud.freight.domain.enumeration.RequestStatus;
import com.illud.freight.domain.enumeration.FreightStatus;
/**
 * Test class for the FreightResource REST controller.
 *
 * @see FreightResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class FreightResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_DISTANCE = 1L;
    private static final Long UPDATED_DISTANCE = 2L;

    private static final String DEFAULT_PICKUP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_PLACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_PLACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_PLACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_PLACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PICKUP_GEOPOINT = "AAAAAAAAAA";
    private static final String UPDATED_PICKUP_GEOPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_GEOPOINT = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_GEOPOINT = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ID = "BBBBBBBBBB";

    private static final RequestStatus DEFAULT_REQUESTED_STATUS = RequestStatus.REQUEST;
    private static final RequestStatus UPDATED_REQUESTED_STATUS = RequestStatus.CONFIRM;

    private static final FreightStatus DEFAULT_ACCEPTED_STATUS = FreightStatus.START;
    private static final FreightStatus UPDATED_ACCEPTED_STATUS = FreightStatus.COMPLETE;

    private static final String DEFAULT_VEHICLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final Instant DEFAULT_CREATED_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_START_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DESTIONATION_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DESTIONATION_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private FreightMapper freightMapper;

    @Autowired
    private FreightService freightService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.FreightSearchRepositoryMockConfiguration
     */
    @Autowired
    private FreightSearchRepository mockFreightSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFreightMockMvc;

    private Freight freight;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreightResource freightResource = new FreightResource(freightService);
        this.restFreightMockMvc = MockMvcBuilders.standaloneSetup(freightResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Freight createEntity(EntityManager em) {
        Freight freight = new Freight()
            .type(DEFAULT_TYPE)
            .distance(DEFAULT_DISTANCE)
            .pickupAddress(DEFAULT_PICKUP_ADDRESS)
            .pickupPlaceId(DEFAULT_PICKUP_PLACE_ID)
            .destinationPlaceId(DEFAULT_DESTINATION_PLACE_ID)
            .destinationAddress(DEFAULT_DESTINATION_ADDRESS)
            .pickupGeopoint(DEFAULT_PICKUP_GEOPOINT)
            .destinationGeopoint(DEFAULT_DESTINATION_GEOPOINT)
            .customerId(DEFAULT_CUSTOMER_ID)
            .requestedStatus(DEFAULT_REQUESTED_STATUS)
            .acceptedStatus(DEFAULT_ACCEPTED_STATUS)
            .vehicleId(DEFAULT_VEHICLE_ID)
            .companyId(DEFAULT_COMPANY_ID)
            .amount(DEFAULT_AMOUNT)
            .createdTime(DEFAULT_CREATED_TIME)
            .startTime(DEFAULT_START_TIME)
            .destionationTime(DEFAULT_DESTIONATION_TIME);
        return freight;
    }

    @Before
    public void initTest() {
        freight = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreight() throws Exception {
        int databaseSizeBeforeCreate = freightRepository.findAll().size();

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);
        restFreightMockMvc.perform(post("/api/freights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isCreated());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeCreate + 1);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFreight.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testFreight.getPickupAddress()).isEqualTo(DEFAULT_PICKUP_ADDRESS);
        assertThat(testFreight.getPickupPlaceId()).isEqualTo(DEFAULT_PICKUP_PLACE_ID);
        assertThat(testFreight.getDestinationPlaceId()).isEqualTo(DEFAULT_DESTINATION_PLACE_ID);
        assertThat(testFreight.getDestinationAddress()).isEqualTo(DEFAULT_DESTINATION_ADDRESS);
        assertThat(testFreight.getPickupGeopoint()).isEqualTo(DEFAULT_PICKUP_GEOPOINT);
        assertThat(testFreight.getDestinationGeopoint()).isEqualTo(DEFAULT_DESTINATION_GEOPOINT);
        assertThat(testFreight.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testFreight.getRequestedStatus()).isEqualTo(DEFAULT_REQUESTED_STATUS);
        assertThat(testFreight.getAcceptedStatus()).isEqualTo(DEFAULT_ACCEPTED_STATUS);
        assertThat(testFreight.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testFreight.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testFreight.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFreight.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testFreight.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testFreight.getDestionationTime()).isEqualTo(DEFAULT_DESTIONATION_TIME);

        // Validate the Freight in Elasticsearch
        verify(mockFreightSearchRepository, times(1)).save(testFreight);
    }

    @Test
    @Transactional
    public void createFreightWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freightRepository.findAll().size();

        // Create the Freight with an existing ID
        freight.setId(1L);
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreightMockMvc.perform(post("/api/freights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeCreate);

        // Validate the Freight in Elasticsearch
        verify(mockFreightSearchRepository, times(0)).save(freight);
    }

    @Test
    @Transactional
    public void getAllFreights() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        // Get all the freightList
        restFreightMockMvc.perform(get("/api/freights?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freight.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].pickupAddress").value(hasItem(DEFAULT_PICKUP_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].pickupPlaceId").value(hasItem(DEFAULT_PICKUP_PLACE_ID.toString())))
            .andExpect(jsonPath("$.[*].destinationPlaceId").value(hasItem(DEFAULT_DESTINATION_PLACE_ID.toString())))
            .andExpect(jsonPath("$.[*].destinationAddress").value(hasItem(DEFAULT_DESTINATION_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].pickupGeopoint").value(hasItem(DEFAULT_PICKUP_GEOPOINT.toString())))
            .andExpect(jsonPath("$.[*].destinationGeopoint").value(hasItem(DEFAULT_DESTINATION_GEOPOINT.toString())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.toString())))
            .andExpect(jsonPath("$.[*].requestedStatus").value(hasItem(DEFAULT_REQUESTED_STATUS.toString())))
            .andExpect(jsonPath("$.[*].acceptedStatus").value(hasItem(DEFAULT_ACCEPTED_STATUS.toString())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.toString())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].destionationTime").value(hasItem(DEFAULT_DESTIONATION_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        // Get the freight
        restFreightMockMvc.perform(get("/api/freights/{id}", freight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freight.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.intValue()))
            .andExpect(jsonPath("$.pickupAddress").value(DEFAULT_PICKUP_ADDRESS.toString()))
            .andExpect(jsonPath("$.pickupPlaceId").value(DEFAULT_PICKUP_PLACE_ID.toString()))
            .andExpect(jsonPath("$.destinationPlaceId").value(DEFAULT_DESTINATION_PLACE_ID.toString()))
            .andExpect(jsonPath("$.destinationAddress").value(DEFAULT_DESTINATION_ADDRESS.toString()))
            .andExpect(jsonPath("$.pickupGeopoint").value(DEFAULT_PICKUP_GEOPOINT.toString()))
            .andExpect(jsonPath("$.destinationGeopoint").value(DEFAULT_DESTINATION_GEOPOINT.toString()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.toString()))
            .andExpect(jsonPath("$.requestedStatus").value(DEFAULT_REQUESTED_STATUS.toString()))
            .andExpect(jsonPath("$.acceptedStatus").value(DEFAULT_ACCEPTED_STATUS.toString()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.toString()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.createdTime").value(DEFAULT_CREATED_TIME.toString()))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.destionationTime").value(DEFAULT_DESTIONATION_TIME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFreight() throws Exception {
        // Get the freight
        restFreightMockMvc.perform(get("/api/freights/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeUpdate = freightRepository.findAll().size();

        // Update the freight
        Freight updatedFreight = freightRepository.findById(freight.getId()).get();
        // Disconnect from session so that the updates on updatedFreight are not directly saved in db
        em.detach(updatedFreight);
        updatedFreight
            .type(UPDATED_TYPE)
            .distance(UPDATED_DISTANCE)
            .pickupAddress(UPDATED_PICKUP_ADDRESS)
            .pickupPlaceId(UPDATED_PICKUP_PLACE_ID)
            .destinationPlaceId(UPDATED_DESTINATION_PLACE_ID)
            .destinationAddress(UPDATED_DESTINATION_ADDRESS)
            .pickupGeopoint(UPDATED_PICKUP_GEOPOINT)
            .destinationGeopoint(UPDATED_DESTINATION_GEOPOINT)
            .customerId(UPDATED_CUSTOMER_ID)
            .requestedStatus(UPDATED_REQUESTED_STATUS)
            .acceptedStatus(UPDATED_ACCEPTED_STATUS)
            .vehicleId(UPDATED_VEHICLE_ID)
            .companyId(UPDATED_COMPANY_ID)
            .amount(UPDATED_AMOUNT)
            .createdTime(UPDATED_CREATED_TIME)
            .startTime(UPDATED_START_TIME)
            .destionationTime(UPDATED_DESTIONATION_TIME);
        FreightDTO freightDTO = freightMapper.toDto(updatedFreight);

        restFreightMockMvc.perform(put("/api/freights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isOk());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFreight.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testFreight.getPickupAddress()).isEqualTo(UPDATED_PICKUP_ADDRESS);
        assertThat(testFreight.getPickupPlaceId()).isEqualTo(UPDATED_PICKUP_PLACE_ID);
        assertThat(testFreight.getDestinationPlaceId()).isEqualTo(UPDATED_DESTINATION_PLACE_ID);
        assertThat(testFreight.getDestinationAddress()).isEqualTo(UPDATED_DESTINATION_ADDRESS);
        assertThat(testFreight.getPickupGeopoint()).isEqualTo(UPDATED_PICKUP_GEOPOINT);
        assertThat(testFreight.getDestinationGeopoint()).isEqualTo(UPDATED_DESTINATION_GEOPOINT);
        assertThat(testFreight.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testFreight.getRequestedStatus()).isEqualTo(UPDATED_REQUESTED_STATUS);
        assertThat(testFreight.getAcceptedStatus()).isEqualTo(UPDATED_ACCEPTED_STATUS);
        assertThat(testFreight.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testFreight.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testFreight.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFreight.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testFreight.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testFreight.getDestionationTime()).isEqualTo(UPDATED_DESTIONATION_TIME);

        // Validate the Freight in Elasticsearch
        verify(mockFreightSearchRepository, times(1)).save(testFreight);
    }

    @Test
    @Transactional
    public void updateNonExistingFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreightMockMvc.perform(put("/api/freights")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Freight in Elasticsearch
        verify(mockFreightSearchRepository, times(0)).save(freight);
    }

    @Test
    @Transactional
    public void deleteFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeDelete = freightRepository.findAll().size();

        // Delete the freight
        restFreightMockMvc.perform(delete("/api/freights/{id}", freight.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Freight in Elasticsearch
        verify(mockFreightSearchRepository, times(1)).deleteById(freight.getId());
    }

    @Test
    @Transactional
    public void searchFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);
        when(mockFreightSearchRepository.search(queryStringQuery("id:" + freight.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(freight), PageRequest.of(0, 1), 1));
        // Search the freight
        restFreightMockMvc.perform(get("/api/_search/freights?query=id:" + freight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freight.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].pickupAddress").value(hasItem(DEFAULT_PICKUP_ADDRESS)))
            .andExpect(jsonPath("$.[*].pickupPlaceId").value(hasItem(DEFAULT_PICKUP_PLACE_ID)))
            .andExpect(jsonPath("$.[*].destinationPlaceId").value(hasItem(DEFAULT_DESTINATION_PLACE_ID)))
            .andExpect(jsonPath("$.[*].destinationAddress").value(hasItem(DEFAULT_DESTINATION_ADDRESS)))
            .andExpect(jsonPath("$.[*].pickupGeopoint").value(hasItem(DEFAULT_PICKUP_GEOPOINT)))
            .andExpect(jsonPath("$.[*].destinationGeopoint").value(hasItem(DEFAULT_DESTINATION_GEOPOINT)))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].requestedStatus").value(hasItem(DEFAULT_REQUESTED_STATUS.toString())))
            .andExpect(jsonPath("$.[*].acceptedStatus").value(hasItem(DEFAULT_ACCEPTED_STATUS.toString())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID)))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(DEFAULT_CREATED_TIME.toString())))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].destionationTime").value(hasItem(DEFAULT_DESTIONATION_TIME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Freight.class);
        Freight freight1 = new Freight();
        freight1.setId(1L);
        Freight freight2 = new Freight();
        freight2.setId(freight1.getId());
        assertThat(freight1).isEqualTo(freight2);
        freight2.setId(2L);
        assertThat(freight1).isNotEqualTo(freight2);
        freight1.setId(null);
        assertThat(freight1).isNotEqualTo(freight2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreightDTO.class);
        FreightDTO freightDTO1 = new FreightDTO();
        freightDTO1.setId(1L);
        FreightDTO freightDTO2 = new FreightDTO();
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
        freightDTO2.setId(freightDTO1.getId());
        assertThat(freightDTO1).isEqualTo(freightDTO2);
        freightDTO2.setId(2L);
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
        freightDTO1.setId(null);
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(freightMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(freightMapper.fromId(null)).isNull();
    }
}
