package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.VehicleLookUp;
import com.illud.freight.repository.VehicleLookUpRepository;
import com.illud.freight.repository.search.VehicleLookUpSearchRepository;
import com.illud.freight.service.VehicleLookUpService;
import com.illud.freight.service.dto.VehicleLookUpDTO;
import com.illud.freight.service.mapper.VehicleLookUpMapper;
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
import org.springframework.util.Base64Utils;
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

/**
 * Test class for the VehicleLookUpResource REST controller.
 *
 * @see VehicleLookUpResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class VehicleLookUpResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_MAX_WEIGHT = 1L;
    private static final Long UPDATED_MAX_WEIGHT = 2L;

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Double DEFAULT_LENGTH = 1D;
    private static final Double UPDATED_LENGTH = 2D;

    private static final Double DEFAULT_WIDTH = 1D;
    private static final Double UPDATED_WIDTH = 2D;

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;

    @Autowired
    private VehicleLookUpRepository vehicleLookUpRepository;

    @Autowired
    private VehicleLookUpMapper vehicleLookUpMapper;

    @Autowired
    private VehicleLookUpService vehicleLookUpService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.VehicleLookUpSearchRepositoryMockConfiguration
     */
    @Autowired
    private VehicleLookUpSearchRepository mockVehicleLookUpSearchRepository;

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

    private MockMvc restVehicleLookUpMockMvc;

    private VehicleLookUp vehicleLookUp;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleLookUpResource vehicleLookUpResource = new VehicleLookUpResource(vehicleLookUpService);
        this.restVehicleLookUpMockMvc = MockMvcBuilders.standaloneSetup(vehicleLookUpResource)
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
    public static VehicleLookUp createEntity(EntityManager em) {
        VehicleLookUp vehicleLookUp = new VehicleLookUp()
            .name(DEFAULT_NAME)
            .maxWeight(DEFAULT_MAX_WEIGHT)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .length(DEFAULT_LENGTH)
            .width(DEFAULT_WIDTH)
            .height(DEFAULT_HEIGHT);
        return vehicleLookUp;
    }

    @Before
    public void initTest() {
        vehicleLookUp = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicleLookUp() throws Exception {
        int databaseSizeBeforeCreate = vehicleLookUpRepository.findAll().size();

        // Create the VehicleLookUp
        VehicleLookUpDTO vehicleLookUpDTO = vehicleLookUpMapper.toDto(vehicleLookUp);
        restVehicleLookUpMockMvc.perform(post("/api/vehicle-look-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleLookUpDTO)))
            .andExpect(status().isCreated());

        // Validate the VehicleLookUp in the database
        List<VehicleLookUp> vehicleLookUpList = vehicleLookUpRepository.findAll();
        assertThat(vehicleLookUpList).hasSize(databaseSizeBeforeCreate + 1);
        VehicleLookUp testVehicleLookUp = vehicleLookUpList.get(vehicleLookUpList.size() - 1);
        assertThat(testVehicleLookUp.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVehicleLookUp.getMaxWeight()).isEqualTo(DEFAULT_MAX_WEIGHT);
        assertThat(testVehicleLookUp.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testVehicleLookUp.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testVehicleLookUp.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testVehicleLookUp.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testVehicleLookUp.getHeight()).isEqualTo(DEFAULT_HEIGHT);

        // Validate the VehicleLookUp in Elasticsearch
        verify(mockVehicleLookUpSearchRepository, times(1)).save(testVehicleLookUp);
    }

    @Test
    @Transactional
    public void createVehicleLookUpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleLookUpRepository.findAll().size();

        // Create the VehicleLookUp with an existing ID
        vehicleLookUp.setId(1L);
        VehicleLookUpDTO vehicleLookUpDTO = vehicleLookUpMapper.toDto(vehicleLookUp);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleLookUpMockMvc.perform(post("/api/vehicle-look-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleLookUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleLookUp in the database
        List<VehicleLookUp> vehicleLookUpList = vehicleLookUpRepository.findAll();
        assertThat(vehicleLookUpList).hasSize(databaseSizeBeforeCreate);

        // Validate the VehicleLookUp in Elasticsearch
        verify(mockVehicleLookUpSearchRepository, times(0)).save(vehicleLookUp);
    }

    @Test
    @Transactional
    public void getAllVehicleLookUps() throws Exception {
        // Initialize the database
        vehicleLookUpRepository.saveAndFlush(vehicleLookUp);

        // Get all the vehicleLookUpList
        restVehicleLookUpMockMvc.perform(get("/api/vehicle-look-ups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleLookUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].maxWeight").value(hasItem(DEFAULT_MAX_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getVehicleLookUp() throws Exception {
        // Initialize the database
        vehicleLookUpRepository.saveAndFlush(vehicleLookUp);

        // Get the vehicleLookUp
        restVehicleLookUpMockMvc.perform(get("/api/vehicle-look-ups/{id}", vehicleLookUp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vehicleLookUp.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.maxWeight").value(DEFAULT_MAX_WEIGHT.intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicleLookUp() throws Exception {
        // Get the vehicleLookUp
        restVehicleLookUpMockMvc.perform(get("/api/vehicle-look-ups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicleLookUp() throws Exception {
        // Initialize the database
        vehicleLookUpRepository.saveAndFlush(vehicleLookUp);

        int databaseSizeBeforeUpdate = vehicleLookUpRepository.findAll().size();

        // Update the vehicleLookUp
        VehicleLookUp updatedVehicleLookUp = vehicleLookUpRepository.findById(vehicleLookUp.getId()).get();
        // Disconnect from session so that the updates on updatedVehicleLookUp are not directly saved in db
        em.detach(updatedVehicleLookUp);
        updatedVehicleLookUp
            .name(UPDATED_NAME)
            .maxWeight(UPDATED_MAX_WEIGHT)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .length(UPDATED_LENGTH)
            .width(UPDATED_WIDTH)
            .height(UPDATED_HEIGHT);
        VehicleLookUpDTO vehicleLookUpDTO = vehicleLookUpMapper.toDto(updatedVehicleLookUp);

        restVehicleLookUpMockMvc.perform(put("/api/vehicle-look-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleLookUpDTO)))
            .andExpect(status().isOk());

        // Validate the VehicleLookUp in the database
        List<VehicleLookUp> vehicleLookUpList = vehicleLookUpRepository.findAll();
        assertThat(vehicleLookUpList).hasSize(databaseSizeBeforeUpdate);
        VehicleLookUp testVehicleLookUp = vehicleLookUpList.get(vehicleLookUpList.size() - 1);
        assertThat(testVehicleLookUp.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVehicleLookUp.getMaxWeight()).isEqualTo(UPDATED_MAX_WEIGHT);
        assertThat(testVehicleLookUp.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testVehicleLookUp.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testVehicleLookUp.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testVehicleLookUp.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testVehicleLookUp.getHeight()).isEqualTo(UPDATED_HEIGHT);

        // Validate the VehicleLookUp in Elasticsearch
        verify(mockVehicleLookUpSearchRepository, times(1)).save(testVehicleLookUp);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicleLookUp() throws Exception {
        int databaseSizeBeforeUpdate = vehicleLookUpRepository.findAll().size();

        // Create the VehicleLookUp
        VehicleLookUpDTO vehicleLookUpDTO = vehicleLookUpMapper.toDto(vehicleLookUp);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleLookUpMockMvc.perform(put("/api/vehicle-look-ups")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vehicleLookUpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleLookUp in the database
        List<VehicleLookUp> vehicleLookUpList = vehicleLookUpRepository.findAll();
        assertThat(vehicleLookUpList).hasSize(databaseSizeBeforeUpdate);

        // Validate the VehicleLookUp in Elasticsearch
        verify(mockVehicleLookUpSearchRepository, times(0)).save(vehicleLookUp);
    }

    @Test
    @Transactional
    public void deleteVehicleLookUp() throws Exception {
        // Initialize the database
        vehicleLookUpRepository.saveAndFlush(vehicleLookUp);

        int databaseSizeBeforeDelete = vehicleLookUpRepository.findAll().size();

        // Delete the vehicleLookUp
        restVehicleLookUpMockMvc.perform(delete("/api/vehicle-look-ups/{id}", vehicleLookUp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VehicleLookUp> vehicleLookUpList = vehicleLookUpRepository.findAll();
        assertThat(vehicleLookUpList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the VehicleLookUp in Elasticsearch
        verify(mockVehicleLookUpSearchRepository, times(1)).deleteById(vehicleLookUp.getId());
    }

    @Test
    @Transactional
    public void searchVehicleLookUp() throws Exception {
        // Initialize the database
        vehicleLookUpRepository.saveAndFlush(vehicleLookUp);
        when(mockVehicleLookUpSearchRepository.search(queryStringQuery("id:" + vehicleLookUp.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(vehicleLookUp), PageRequest.of(0, 1), 1));
        // Search the vehicleLookUp
        restVehicleLookUpMockMvc.perform(get("/api/_search/vehicle-look-ups?query=id:" + vehicleLookUp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleLookUp.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].maxWeight").value(hasItem(DEFAULT_MAX_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleLookUp.class);
        VehicleLookUp vehicleLookUp1 = new VehicleLookUp();
        vehicleLookUp1.setId(1L);
        VehicleLookUp vehicleLookUp2 = new VehicleLookUp();
        vehicleLookUp2.setId(vehicleLookUp1.getId());
        assertThat(vehicleLookUp1).isEqualTo(vehicleLookUp2);
        vehicleLookUp2.setId(2L);
        assertThat(vehicleLookUp1).isNotEqualTo(vehicleLookUp2);
        vehicleLookUp1.setId(null);
        assertThat(vehicleLookUp1).isNotEqualTo(vehicleLookUp2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VehicleLookUpDTO.class);
        VehicleLookUpDTO vehicleLookUpDTO1 = new VehicleLookUpDTO();
        vehicleLookUpDTO1.setId(1L);
        VehicleLookUpDTO vehicleLookUpDTO2 = new VehicleLookUpDTO();
        assertThat(vehicleLookUpDTO1).isNotEqualTo(vehicleLookUpDTO2);
        vehicleLookUpDTO2.setId(vehicleLookUpDTO1.getId());
        assertThat(vehicleLookUpDTO1).isEqualTo(vehicleLookUpDTO2);
        vehicleLookUpDTO2.setId(2L);
        assertThat(vehicleLookUpDTO1).isNotEqualTo(vehicleLookUpDTO2);
        vehicleLookUpDTO1.setId(null);
        assertThat(vehicleLookUpDTO1).isNotEqualTo(vehicleLookUpDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vehicleLookUpMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vehicleLookUpMapper.fromId(null)).isNull();
    }
}
