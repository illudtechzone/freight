package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.NormalPricing;
import com.illud.freight.repository.NormalPricingRepository;
import com.illud.freight.repository.search.NormalPricingSearchRepository;
import com.illud.freight.service.NormalPricingService;
import com.illud.freight.service.dto.NormalPricingDTO;
import com.illud.freight.service.mapper.NormalPricingMapper;
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

import com.illud.freight.domain.enumeration.LimitStatus;
/**
 * Test class for the NormalPricingResource REST controller.
 *
 * @see NormalPricingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class NormalPricingResourceIntTest {

    private static final Double DEFAULT_RATE_PER_KM = 1D;
    private static final Double UPDATED_RATE_PER_KM = 2D;

    private static final Double DEFAULT_START_LIMIT_IN_KMS = 1D;
    private static final Double UPDATED_START_LIMIT_IN_KMS = 2D;

    private static final Double DEFAULT_END_LIMIT_IN_KMS = 1D;
    private static final Double UPDATED_END_LIMIT_IN_KMS = 2D;

    private static final LimitStatus DEFAULT_LIMIT_STATUS = LimitStatus.BETWEEN;
    private static final LimitStatus UPDATED_LIMIT_STATUS = LimitStatus.ABOVE;

    @Autowired
    private NormalPricingRepository normalPricingRepository;

    @Autowired
    private NormalPricingMapper normalPricingMapper;

    @Autowired
    private NormalPricingService normalPricingService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.NormalPricingSearchRepositoryMockConfiguration
     */
    @Autowired
    private NormalPricingSearchRepository mockNormalPricingSearchRepository;

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

    private MockMvc restNormalPricingMockMvc;

    private NormalPricing normalPricing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NormalPricingResource normalPricingResource = new NormalPricingResource(normalPricingService);
        this.restNormalPricingMockMvc = MockMvcBuilders.standaloneSetup(normalPricingResource)
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
    public static NormalPricing createEntity(EntityManager em) {
        NormalPricing normalPricing = new NormalPricing()
            .ratePerKm(DEFAULT_RATE_PER_KM)
            .startLimitInKms(DEFAULT_START_LIMIT_IN_KMS)
            .endLimitInKms(DEFAULT_END_LIMIT_IN_KMS)
            .limitStatus(DEFAULT_LIMIT_STATUS);
        return normalPricing;
    }

    @Before
    public void initTest() {
        normalPricing = createEntity(em);
    }

    @Test
    @Transactional
    public void createNormalPricing() throws Exception {
        int databaseSizeBeforeCreate = normalPricingRepository.findAll().size();

        // Create the NormalPricing
        NormalPricingDTO normalPricingDTO = normalPricingMapper.toDto(normalPricing);
        restNormalPricingMockMvc.perform(post("/api/normal-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(normalPricingDTO)))
            .andExpect(status().isCreated());

        // Validate the NormalPricing in the database
        List<NormalPricing> normalPricingList = normalPricingRepository.findAll();
        assertThat(normalPricingList).hasSize(databaseSizeBeforeCreate + 1);
        NormalPricing testNormalPricing = normalPricingList.get(normalPricingList.size() - 1);
        assertThat(testNormalPricing.getRatePerKm()).isEqualTo(DEFAULT_RATE_PER_KM);
        assertThat(testNormalPricing.getStartLimitInKms()).isEqualTo(DEFAULT_START_LIMIT_IN_KMS);
        assertThat(testNormalPricing.getEndLimitInKms()).isEqualTo(DEFAULT_END_LIMIT_IN_KMS);
        assertThat(testNormalPricing.getLimitStatus()).isEqualTo(DEFAULT_LIMIT_STATUS);

        // Validate the NormalPricing in Elasticsearch
        verify(mockNormalPricingSearchRepository, times(1)).save(testNormalPricing);
    }

    @Test
    @Transactional
    public void createNormalPricingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = normalPricingRepository.findAll().size();

        // Create the NormalPricing with an existing ID
        normalPricing.setId(1L);
        NormalPricingDTO normalPricingDTO = normalPricingMapper.toDto(normalPricing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNormalPricingMockMvc.perform(post("/api/normal-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(normalPricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NormalPricing in the database
        List<NormalPricing> normalPricingList = normalPricingRepository.findAll();
        assertThat(normalPricingList).hasSize(databaseSizeBeforeCreate);

        // Validate the NormalPricing in Elasticsearch
        verify(mockNormalPricingSearchRepository, times(0)).save(normalPricing);
    }

    @Test
    @Transactional
    public void getAllNormalPricings() throws Exception {
        // Initialize the database
        normalPricingRepository.saveAndFlush(normalPricing);

        // Get all the normalPricingList
        restNormalPricingMockMvc.perform(get("/api/normal-pricings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(normalPricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePerKm").value(hasItem(DEFAULT_RATE_PER_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInKms").value(hasItem(DEFAULT_START_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInKms").value(hasItem(DEFAULT_END_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].limitStatus").value(hasItem(DEFAULT_LIMIT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getNormalPricing() throws Exception {
        // Initialize the database
        normalPricingRepository.saveAndFlush(normalPricing);

        // Get the normalPricing
        restNormalPricingMockMvc.perform(get("/api/normal-pricings/{id}", normalPricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(normalPricing.getId().intValue()))
            .andExpect(jsonPath("$.ratePerKm").value(DEFAULT_RATE_PER_KM.doubleValue()))
            .andExpect(jsonPath("$.startLimitInKms").value(DEFAULT_START_LIMIT_IN_KMS.doubleValue()))
            .andExpect(jsonPath("$.endLimitInKms").value(DEFAULT_END_LIMIT_IN_KMS.doubleValue()))
            .andExpect(jsonPath("$.limitStatus").value(DEFAULT_LIMIT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNormalPricing() throws Exception {
        // Get the normalPricing
        restNormalPricingMockMvc.perform(get("/api/normal-pricings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNormalPricing() throws Exception {
        // Initialize the database
        normalPricingRepository.saveAndFlush(normalPricing);

        int databaseSizeBeforeUpdate = normalPricingRepository.findAll().size();

        // Update the normalPricing
        NormalPricing updatedNormalPricing = normalPricingRepository.findById(normalPricing.getId()).get();
        // Disconnect from session so that the updates on updatedNormalPricing are not directly saved in db
        em.detach(updatedNormalPricing);
        updatedNormalPricing
            .ratePerKm(UPDATED_RATE_PER_KM)
            .startLimitInKms(UPDATED_START_LIMIT_IN_KMS)
            .endLimitInKms(UPDATED_END_LIMIT_IN_KMS)
            .limitStatus(UPDATED_LIMIT_STATUS);
        NormalPricingDTO normalPricingDTO = normalPricingMapper.toDto(updatedNormalPricing);

        restNormalPricingMockMvc.perform(put("/api/normal-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(normalPricingDTO)))
            .andExpect(status().isOk());

        // Validate the NormalPricing in the database
        List<NormalPricing> normalPricingList = normalPricingRepository.findAll();
        assertThat(normalPricingList).hasSize(databaseSizeBeforeUpdate);
        NormalPricing testNormalPricing = normalPricingList.get(normalPricingList.size() - 1);
        assertThat(testNormalPricing.getRatePerKm()).isEqualTo(UPDATED_RATE_PER_KM);
        assertThat(testNormalPricing.getStartLimitInKms()).isEqualTo(UPDATED_START_LIMIT_IN_KMS);
        assertThat(testNormalPricing.getEndLimitInKms()).isEqualTo(UPDATED_END_LIMIT_IN_KMS);
        assertThat(testNormalPricing.getLimitStatus()).isEqualTo(UPDATED_LIMIT_STATUS);

        // Validate the NormalPricing in Elasticsearch
        verify(mockNormalPricingSearchRepository, times(1)).save(testNormalPricing);
    }

    @Test
    @Transactional
    public void updateNonExistingNormalPricing() throws Exception {
        int databaseSizeBeforeUpdate = normalPricingRepository.findAll().size();

        // Create the NormalPricing
        NormalPricingDTO normalPricingDTO = normalPricingMapper.toDto(normalPricing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNormalPricingMockMvc.perform(put("/api/normal-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(normalPricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NormalPricing in the database
        List<NormalPricing> normalPricingList = normalPricingRepository.findAll();
        assertThat(normalPricingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the NormalPricing in Elasticsearch
        verify(mockNormalPricingSearchRepository, times(0)).save(normalPricing);
    }

    @Test
    @Transactional
    public void deleteNormalPricing() throws Exception {
        // Initialize the database
        normalPricingRepository.saveAndFlush(normalPricing);

        int databaseSizeBeforeDelete = normalPricingRepository.findAll().size();

        // Delete the normalPricing
        restNormalPricingMockMvc.perform(delete("/api/normal-pricings/{id}", normalPricing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NormalPricing> normalPricingList = normalPricingRepository.findAll();
        assertThat(normalPricingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the NormalPricing in Elasticsearch
        verify(mockNormalPricingSearchRepository, times(1)).deleteById(normalPricing.getId());
    }

    @Test
    @Transactional
    public void searchNormalPricing() throws Exception {
        // Initialize the database
        normalPricingRepository.saveAndFlush(normalPricing);
        when(mockNormalPricingSearchRepository.search(queryStringQuery("id:" + normalPricing.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(normalPricing), PageRequest.of(0, 1), 1));
        // Search the normalPricing
        restNormalPricingMockMvc.perform(get("/api/_search/normal-pricings?query=id:" + normalPricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(normalPricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePerKm").value(hasItem(DEFAULT_RATE_PER_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInKms").value(hasItem(DEFAULT_START_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInKms").value(hasItem(DEFAULT_END_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].limitStatus").value(hasItem(DEFAULT_LIMIT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NormalPricing.class);
        NormalPricing normalPricing1 = new NormalPricing();
        normalPricing1.setId(1L);
        NormalPricing normalPricing2 = new NormalPricing();
        normalPricing2.setId(normalPricing1.getId());
        assertThat(normalPricing1).isEqualTo(normalPricing2);
        normalPricing2.setId(2L);
        assertThat(normalPricing1).isNotEqualTo(normalPricing2);
        normalPricing1.setId(null);
        assertThat(normalPricing1).isNotEqualTo(normalPricing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NormalPricingDTO.class);
        NormalPricingDTO normalPricingDTO1 = new NormalPricingDTO();
        normalPricingDTO1.setId(1L);
        NormalPricingDTO normalPricingDTO2 = new NormalPricingDTO();
        assertThat(normalPricingDTO1).isNotEqualTo(normalPricingDTO2);
        normalPricingDTO2.setId(normalPricingDTO1.getId());
        assertThat(normalPricingDTO1).isEqualTo(normalPricingDTO2);
        normalPricingDTO2.setId(2L);
        assertThat(normalPricingDTO1).isNotEqualTo(normalPricingDTO2);
        normalPricingDTO1.setId(null);
        assertThat(normalPricingDTO1).isNotEqualTo(normalPricingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(normalPricingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(normalPricingMapper.fromId(null)).isNull();
    }
}
