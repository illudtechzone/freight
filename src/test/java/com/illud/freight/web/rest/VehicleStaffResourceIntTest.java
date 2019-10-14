package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.VehicleStaff;
import com.illud.freight.repository.VehicleStaffRepository;
import com.illud.freight.repository.search.VehicleStaffSearchRepository;
import com.illud.freight.service.VehicleStaffService;
import com.illud.freight.service.dto.VehicleStaffDTO;
import com.illud.freight.service.mapper.VehicleStaffMapper;
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
import java.util.Collections;
import java.util.List;


import static com.illud.freight.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.illud.freight.domain.enumeration.StaffType;
/**
 * Test class for the VehicleStaffResource REST controller.
 *
 * @see VehicleStaffResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class VehicleStaffResourceIntTest {

    private static final StaffType DEFAULT_TYPE = StaffType.DRIVER;
    private static final StaffType UPDATED_TYPE = StaffType.STAFF;

    private static final Long DEFAULT_STAFF_ID = 1L;
    private static final Long UPDATED_STAFF_ID = 2L;

    private static final Long DEFAULT_PRIORITY = 1L;
    private static final Long UPDATED_PRIORITY = 2L;

    @Autowired
    private VehicleStaffRepository vehicleStaffRepository;

    @Autowired
    private VehicleStaffMapper vehicleStaffMapper;

    @Autowired
    private VehicleStaffService vehicleStaffService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.VehicleStaffSearchRepositoryMockConfiguration
     */
    @Autowired
    private VehicleStaffSearchRepository mockVehicleStaffSearchRepository;

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

    private MockMvc restVehicleStaffMockMvc;

    private VehicleStaff vehicleStaff;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleStaffResource vehicleStaffResource = new VehicleStaffResource(vehicleStaffService);
        this.restVehicleStaffMockMvc = MockMvcBuilders.standaloneSetup(vehicleStaffResource)
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
    public static VehicleStaff createEntity(EntityManager em) {
        VehicleStaff vehicleStaff = new VehicleStaff()
            .type(DEFAULT_TYPE)
            .staffId(DEFAULT_STAFF_ID)
            .priority(DEFAULT_PRIORITY);
        return vehicleStaff;
    }

    @Before
    public void initTest() {
        vehicleStaff = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicleStaff() throws Exception {
        int databaseSizeBeforeCreate = vehicleStaffRepository.findAll().size();

        // Create the VehicleStaff
        VehicleStaffDTO vehicleStaffDTO = vehicleStaffMapper.toDto(vehicleStaff);
        restVehicleStaffMockMvc.perform(post("/api/vehicle-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleStaffDTO)))
            .andExpect(status().isCreated());

        // Validate the VehicleStaff in the database
        List<VehicleStaff> vehicleStaffList = vehicleStaffRepository.findAll();
        assertThat(vehicleStaffList).hasSize(databaseSizeBeforeCreate + 1);
        VehicleStaff testVehicleStaff = vehicleStaffList.get(vehicleStaffList.size() - 1);
        assertThat(testVehicleStaff.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testVehicleStaff.getStaffId()).isEqualTo(DEFAULT_STAFF_ID);
        assertThat(testVehicleStaff.getPriority()).isEqualTo(DEFAULT_PRIORITY);

        // Validate the VehicleStaff in Elasticsearch
        verify(mockVehicleStaffSearchRepository, times(1)).save(testVehicleStaff);
    }

    @Test
    @Transactional
    public void createVehicleStaffWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleStaffRepository.findAll().size();

        // Create the VehicleStaff with an existing ID
        vehicleStaff.setId(1L);
        VehicleStaffDTO vehicleStaffDTO = vehicleStaffMapper.toDto(vehicleStaff);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleStaffMockMvc.perform(post("/api/vehicle-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleStaff in the database
        List<VehicleStaff> vehicleStaffList = vehicleStaffRepository.findAll();
        assertThat(vehicleStaffList).hasSize(databaseSizeBeforeCreate);

        // Validate the VehicleStaff in Elasticsearch
        verify(mockVehicleStaffSearchRepository, times(0)).save(vehicleStaff);
    }

    @Test
    @Transactional
    public void getAllVehicleStaffs() throws Exception {
        // Initialize the database
        vehicleStaffRepository.saveAndFlush(vehicleStaff);

        // Get all the vehicleStaffList
        restVehicleStaffMockMvc.perform(get("/api/vehicle-staffs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleStaff.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].staffId").value(hasItem(DEFAULT_STAFF_ID.intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.intValue())));
    }
    
    @Test
    @Transactional
    public void getVehicleStaff() throws Exception {
        // Initialize the database
        vehicleStaffRepository.saveAndFlush(vehicleStaff);

        // Get the vehicleStaff
        restVehicleStaffMockMvc.perform(get("/api/vehicle-staffs/{id}", vehicleStaff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vehicleStaff.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.staffId").value(DEFAULT_STAFF_ID.intValue()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicleStaff() throws Exception {
        // Get the vehicleStaff
        restVehicleStaffMockMvc.perform(get("/api/vehicle-staffs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicleStaff() throws Exception {
        // Initialize the database
        vehicleStaffRepository.saveAndFlush(vehicleStaff);

        int databaseSizeBeforeUpdate = vehicleStaffRepository.findAll().size();

        // Update the vehicleStaff
        VehicleStaff updatedVehicleStaff = vehicleStaffRepository.findById(vehicleStaff.getId()).get();
        // Disconnect from session so that the updates on updatedVehicleStaff are not directly saved in db
        em.detach(updatedVehicleStaff);
        updatedVehicleStaff
            .type(UPDATED_TYPE)
            .staffId(UPDATED_STAFF_ID)
            .priority(UPDATED_PRIORITY);
        VehicleStaffDTO vehicleStaffDTO = vehicleStaffMapper.toDto(updatedVehicleStaff);

        restVehicleStaffMockMvc.perform(put("/api/vehicle-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleStaffDTO)))
            .andExpect(status().isOk());

        // Validate the VehicleStaff in the database
        List<VehicleStaff> vehicleStaffList = vehicleStaffRepository.findAll();
        assertThat(vehicleStaffList).hasSize(databaseSizeBeforeUpdate);
        VehicleStaff testVehicleStaff = vehicleStaffList.get(vehicleStaffList.size() - 1);
        assertThat(testVehicleStaff.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testVehicleStaff.getStaffId()).isEqualTo(UPDATED_STAFF_ID);
        assertThat(testVehicleStaff.getPriority()).isEqualTo(UPDATED_PRIORITY);

        // Validate the VehicleStaff in Elasticsearch
        verify(mockVehicleStaffSearchRepository, times(1)).save(testVehicleStaff);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicleStaff() throws Exception {
        int databaseSizeBeforeUpdate = vehicleStaffRepository.findAll().size();

        // Create the VehicleStaff
        VehicleStaffDTO vehicleStaffDTO = vehicleStaffMapper.toDto(vehicleStaff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleStaffMockMvc.perform(put("/api/vehicle-staffs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleStaffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleStaff in the database
        List<VehicleStaff> vehicleStaffList = vehicleStaffRepository.findAll();
        assertThat(vehicleStaffList).hasSize(databaseSizeBeforeUpdate);

        // Validate the VehicleStaff in Elasticsearch
        verify(mockVehicleStaffSearchRepository, times(0)).save(vehicleStaff);
    }

    @Test
    @Transactional
    public void deleteVehicleStaff() throws Exception {
        // Initialize the database
        vehicleStaffRepository.saveAndFlush(vehicleStaff);

        int databaseSizeBeforeDelete = vehicleStaffRepository.findAll().size();

        // Delete the vehicleStaff
        restVehicleStaffMockMvc.perform(delete("/api/vehicle-staffs/{id}", vehicleStaff.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VehicleStaff> vehicleStaffList = vehicleStaffRepository.findAll();
        assertThat(vehicleStaffList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the VehicleStaff in Elasticsearch
        verify(mockVehicleStaffSearchRepository, times(1)).deleteById(vehicleStaff.getId());
    }

    @Test
    @Transactional
    public void searchVehicleStaff() throws Exception {
        // Initialize the database
        vehicleStaffRepository.saveAndFlush(vehicleStaff);
        when(mockVehicleStaffSearchRepository.search(queryStringQuery("id:" + vehicleStaff.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(vehicleStaff), PageRequest.of(0, 1), 1));
        // Search the vehicleStaff
        restVehicleStaffMockMvc.perform(get("/api/_search/vehicle-staffs?query=id:" + vehicleStaff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleStaff.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].staffId").value(hasItem(DEFAULT_STAFF_ID.intValue())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleStaff.class);
        VehicleStaff vehicleStaff1 = new VehicleStaff();
        vehicleStaff1.setId(1L);
        VehicleStaff vehicleStaff2 = new VehicleStaff();
        vehicleStaff2.setId(vehicleStaff1.getId());
        assertThat(vehicleStaff1).isEqualTo(vehicleStaff2);
        vehicleStaff2.setId(2L);
        assertThat(vehicleStaff1).isNotEqualTo(vehicleStaff2);
        vehicleStaff1.setId(null);
        assertThat(vehicleStaff1).isNotEqualTo(vehicleStaff2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleStaffDTO.class);
        VehicleStaffDTO vehicleStaffDTO1 = new VehicleStaffDTO();
        vehicleStaffDTO1.setId(1L);
        VehicleStaffDTO vehicleStaffDTO2 = new VehicleStaffDTO();
        assertThat(vehicleStaffDTO1).isNotEqualTo(vehicleStaffDTO2);
        vehicleStaffDTO2.setId(vehicleStaffDTO1.getId());
        assertThat(vehicleStaffDTO1).isEqualTo(vehicleStaffDTO2);
        vehicleStaffDTO2.setId(2L);
        assertThat(vehicleStaffDTO1).isNotEqualTo(vehicleStaffDTO2);
        vehicleStaffDTO1.setId(null);
        assertThat(vehicleStaffDTO1).isNotEqualTo(vehicleStaffDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vehicleStaffMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vehicleStaffMapper.fromId(null)).isNull();
    }
}
