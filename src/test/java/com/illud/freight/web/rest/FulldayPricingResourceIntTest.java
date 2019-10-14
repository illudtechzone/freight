package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.FulldayPricing;
import com.illud.freight.repository.FulldayPricingRepository;
import com.illud.freight.repository.search.FulldayPricingSearchRepository;
import com.illud.freight.service.FulldayPricingService;
import com.illud.freight.service.dto.FulldayPricingDTO;
import com.illud.freight.service.mapper.FulldayPricingMapper;
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
 * Test class for the FulldayPricingResource REST controller.
 *
 * @see FulldayPricingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class FulldayPricingResourceIntTest {

    private static final Double DEFAULT_RATE_PER_MINS_ABOVE_LIMIT = 1D;
    private static final Double UPDATED_RATE_PER_MINS_ABOVE_LIMIT = 2D;

    private static final Double DEFAULT_RATE_PER_KM = 1D;
    private static final Double UPDATED_RATE_PER_KM = 2D;

    private static final Double DEFAULT_START_LIMIT_IN_KMS = 1D;
    private static final Double UPDATED_START_LIMIT_IN_KMS = 2D;

    private static final Double DEFAULT_END_LIMIT_IN_KMS = 1D;
    private static final Double UPDATED_END_LIMIT_IN_KMS = 2D;

    private static final Double DEFAULT_START_LIMIT_IN_HOUR = 1D;
    private static final Double UPDATED_START_LIMIT_IN_HOUR = 2D;

    private static final Double DEFAULT_END_LIMIT_IN_HOUR = 1D;
    private static final Double UPDATED_END_LIMIT_IN_HOUR = 2D;

    private static final LimitStatus DEFAULT_LIMIT_STATUS = LimitStatus.BETWEEN;
    private static final LimitStatus UPDATED_LIMIT_STATUS = LimitStatus.ABOVE;

    @Autowired
    private FulldayPricingRepository fulldayPricingRepository;

    @Autowired
    private FulldayPricingMapper fulldayPricingMapper;

    @Autowired
    private FulldayPricingService fulldayPricingService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.FulldayPricingSearchRepositoryMockConfiguration
     */
    @Autowired
    private FulldayPricingSearchRepository mockFulldayPricingSearchRepository;

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

    private MockMvc restFulldayPricingMockMvc;

    private FulldayPricing fulldayPricing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FulldayPricingResource fulldayPricingResource = new FulldayPricingResource(fulldayPricingService);
        this.restFulldayPricingMockMvc = MockMvcBuilders.standaloneSetup(fulldayPricingResource)
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
    public static FulldayPricing createEntity(EntityManager em) {
        FulldayPricing fulldayPricing = new FulldayPricing()
            .ratePerMinsAboveLimit(DEFAULT_RATE_PER_MINS_ABOVE_LIMIT)
            .ratePerKm(DEFAULT_RATE_PER_KM)
            .startLimitInKms(DEFAULT_START_LIMIT_IN_KMS)
            .endLimitInKms(DEFAULT_END_LIMIT_IN_KMS)
            .startLimitInHour(DEFAULT_START_LIMIT_IN_HOUR)
            .endLimitInHour(DEFAULT_END_LIMIT_IN_HOUR)
            .limitStatus(DEFAULT_LIMIT_STATUS);
        return fulldayPricing;
    }

    @Before
    public void initTest() {
        fulldayPricing = createEntity(em);
    }

    @Test
    @Transactional
    public void createFulldayPricing() throws Exception {
        int databaseSizeBeforeCreate = fulldayPricingRepository.findAll().size();

        // Create the FulldayPricing
        FulldayPricingDTO fulldayPricingDTO = fulldayPricingMapper.toDto(fulldayPricing);
        restFulldayPricingMockMvc.perform(post("/api/fullday-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fulldayPricingDTO)))
            .andExpect(status().isCreated());

        // Validate the FulldayPricing in the database
        List<FulldayPricing> fulldayPricingList = fulldayPricingRepository.findAll();
        assertThat(fulldayPricingList).hasSize(databaseSizeBeforeCreate + 1);
        FulldayPricing testFulldayPricing = fulldayPricingList.get(fulldayPricingList.size() - 1);
        assertThat(testFulldayPricing.getRatePerMinsAboveLimit()).isEqualTo(DEFAULT_RATE_PER_MINS_ABOVE_LIMIT);
        assertThat(testFulldayPricing.getRatePerKm()).isEqualTo(DEFAULT_RATE_PER_KM);
        assertThat(testFulldayPricing.getStartLimitInKms()).isEqualTo(DEFAULT_START_LIMIT_IN_KMS);
        assertThat(testFulldayPricing.getEndLimitInKms()).isEqualTo(DEFAULT_END_LIMIT_IN_KMS);
        assertThat(testFulldayPricing.getStartLimitInHour()).isEqualTo(DEFAULT_START_LIMIT_IN_HOUR);
        assertThat(testFulldayPricing.getEndLimitInHour()).isEqualTo(DEFAULT_END_LIMIT_IN_HOUR);
        assertThat(testFulldayPricing.getLimitStatus()).isEqualTo(DEFAULT_LIMIT_STATUS);

        // Validate the FulldayPricing in Elasticsearch
        verify(mockFulldayPricingSearchRepository, times(1)).save(testFulldayPricing);
    }

    @Test
    @Transactional
    public void createFulldayPricingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fulldayPricingRepository.findAll().size();

        // Create the FulldayPricing with an existing ID
        fulldayPricing.setId(1L);
        FulldayPricingDTO fulldayPricingDTO = fulldayPricingMapper.toDto(fulldayPricing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFulldayPricingMockMvc.perform(post("/api/fullday-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fulldayPricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FulldayPricing in the database
        List<FulldayPricing> fulldayPricingList = fulldayPricingRepository.findAll();
        assertThat(fulldayPricingList).hasSize(databaseSizeBeforeCreate);

        // Validate the FulldayPricing in Elasticsearch
        verify(mockFulldayPricingSearchRepository, times(0)).save(fulldayPricing);
    }

    @Test
    @Transactional
    public void getAllFulldayPricings() throws Exception {
        // Initialize the database
        fulldayPricingRepository.saveAndFlush(fulldayPricing);

        // Get all the fulldayPricingList
        restFulldayPricingMockMvc.perform(get("/api/fullday-pricings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fulldayPricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePerMinsAboveLimit").value(hasItem(DEFAULT_RATE_PER_MINS_ABOVE_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].ratePerKm").value(hasItem(DEFAULT_RATE_PER_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInKms").value(hasItem(DEFAULT_START_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInKms").value(hasItem(DEFAULT_END_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInHour").value(hasItem(DEFAULT_START_LIMIT_IN_HOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInHour").value(hasItem(DEFAULT_END_LIMIT_IN_HOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].limitStatus").value(hasItem(DEFAULT_LIMIT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getFulldayPricing() throws Exception {
        // Initialize the database
        fulldayPricingRepository.saveAndFlush(fulldayPricing);

        // Get the fulldayPricing
        restFulldayPricingMockMvc.perform(get("/api/fullday-pricings/{id}", fulldayPricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fulldayPricing.getId().intValue()))
            .andExpect(jsonPath("$.ratePerMinsAboveLimit").value(DEFAULT_RATE_PER_MINS_ABOVE_LIMIT.doubleValue()))
            .andExpect(jsonPath("$.ratePerKm").value(DEFAULT_RATE_PER_KM.doubleValue()))
            .andExpect(jsonPath("$.startLimitInKms").value(DEFAULT_START_LIMIT_IN_KMS.doubleValue()))
            .andExpect(jsonPath("$.endLimitInKms").value(DEFAULT_END_LIMIT_IN_KMS.doubleValue()))
            .andExpect(jsonPath("$.startLimitInHour").value(DEFAULT_START_LIMIT_IN_HOUR.doubleValue()))
            .andExpect(jsonPath("$.endLimitInHour").value(DEFAULT_END_LIMIT_IN_HOUR.doubleValue()))
            .andExpect(jsonPath("$.limitStatus").value(DEFAULT_LIMIT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFulldayPricing() throws Exception {
        // Get the fulldayPricing
        restFulldayPricingMockMvc.perform(get("/api/fullday-pricings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFulldayPricing() throws Exception {
        // Initialize the database
        fulldayPricingRepository.saveAndFlush(fulldayPricing);

        int databaseSizeBeforeUpdate = fulldayPricingRepository.findAll().size();

        // Update the fulldayPricing
        FulldayPricing updatedFulldayPricing = fulldayPricingRepository.findById(fulldayPricing.getId()).get();
        // Disconnect from session so that the updates on updatedFulldayPricing are not directly saved in db
        em.detach(updatedFulldayPricing);
        updatedFulldayPricing
            .ratePerMinsAboveLimit(UPDATED_RATE_PER_MINS_ABOVE_LIMIT)
            .ratePerKm(UPDATED_RATE_PER_KM)
            .startLimitInKms(UPDATED_START_LIMIT_IN_KMS)
            .endLimitInKms(UPDATED_END_LIMIT_IN_KMS)
            .startLimitInHour(UPDATED_START_LIMIT_IN_HOUR)
            .endLimitInHour(UPDATED_END_LIMIT_IN_HOUR)
            .limitStatus(UPDATED_LIMIT_STATUS);
        FulldayPricingDTO fulldayPricingDTO = fulldayPricingMapper.toDto(updatedFulldayPricing);

        restFulldayPricingMockMvc.perform(put("/api/fullday-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fulldayPricingDTO)))
            .andExpect(status().isOk());

        // Validate the FulldayPricing in the database
        List<FulldayPricing> fulldayPricingList = fulldayPricingRepository.findAll();
        assertThat(fulldayPricingList).hasSize(databaseSizeBeforeUpdate);
        FulldayPricing testFulldayPricing = fulldayPricingList.get(fulldayPricingList.size() - 1);
        assertThat(testFulldayPricing.getRatePerMinsAboveLimit()).isEqualTo(UPDATED_RATE_PER_MINS_ABOVE_LIMIT);
        assertThat(testFulldayPricing.getRatePerKm()).isEqualTo(UPDATED_RATE_PER_KM);
        assertThat(testFulldayPricing.getStartLimitInKms()).isEqualTo(UPDATED_START_LIMIT_IN_KMS);
        assertThat(testFulldayPricing.getEndLimitInKms()).isEqualTo(UPDATED_END_LIMIT_IN_KMS);
        assertThat(testFulldayPricing.getStartLimitInHour()).isEqualTo(UPDATED_START_LIMIT_IN_HOUR);
        assertThat(testFulldayPricing.getEndLimitInHour()).isEqualTo(UPDATED_END_LIMIT_IN_HOUR);
        assertThat(testFulldayPricing.getLimitStatus()).isEqualTo(UPDATED_LIMIT_STATUS);

        // Validate the FulldayPricing in Elasticsearch
        verify(mockFulldayPricingSearchRepository, times(1)).save(testFulldayPricing);
    }

    @Test
    @Transactional
    public void updateNonExistingFulldayPricing() throws Exception {
        int databaseSizeBeforeUpdate = fulldayPricingRepository.findAll().size();

        // Create the FulldayPricing
        FulldayPricingDTO fulldayPricingDTO = fulldayPricingMapper.toDto(fulldayPricing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFulldayPricingMockMvc.perform(put("/api/fullday-pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fulldayPricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FulldayPricing in the database
        List<FulldayPricing> fulldayPricingList = fulldayPricingRepository.findAll();
        assertThat(fulldayPricingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FulldayPricing in Elasticsearch
        verify(mockFulldayPricingSearchRepository, times(0)).save(fulldayPricing);
    }

    @Test
    @Transactional
    public void deleteFulldayPricing() throws Exception {
        // Initialize the database
        fulldayPricingRepository.saveAndFlush(fulldayPricing);

        int databaseSizeBeforeDelete = fulldayPricingRepository.findAll().size();

        // Delete the fulldayPricing
        restFulldayPricingMockMvc.perform(delete("/api/fullday-pricings/{id}", fulldayPricing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FulldayPricing> fulldayPricingList = fulldayPricingRepository.findAll();
        assertThat(fulldayPricingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FulldayPricing in Elasticsearch
        verify(mockFulldayPricingSearchRepository, times(1)).deleteById(fulldayPricing.getId());
    }

    @Test
    @Transactional
    public void searchFulldayPricing() throws Exception {
        // Initialize the database
        fulldayPricingRepository.saveAndFlush(fulldayPricing);
        when(mockFulldayPricingSearchRepository.search(queryStringQuery("id:" + fulldayPricing.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(fulldayPricing), PageRequest.of(0, 1), 1));
        // Search the fulldayPricing
        restFulldayPricingMockMvc.perform(get("/api/_search/fullday-pricings?query=id:" + fulldayPricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fulldayPricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePerMinsAboveLimit").value(hasItem(DEFAULT_RATE_PER_MINS_ABOVE_LIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].ratePerKm").value(hasItem(DEFAULT_RATE_PER_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInKms").value(hasItem(DEFAULT_START_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInKms").value(hasItem(DEFAULT_END_LIMIT_IN_KMS.doubleValue())))
            .andExpect(jsonPath("$.[*].startLimitInHour").value(hasItem(DEFAULT_START_LIMIT_IN_HOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].endLimitInHour").value(hasItem(DEFAULT_END_LIMIT_IN_HOUR.doubleValue())))
            .andExpect(jsonPath("$.[*].limitStatus").value(hasItem(DEFAULT_LIMIT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FulldayPricing.class);
        FulldayPricing fulldayPricing1 = new FulldayPricing();
        fulldayPricing1.setId(1L);
        FulldayPricing fulldayPricing2 = new FulldayPricing();
        fulldayPricing2.setId(fulldayPricing1.getId());
        assertThat(fulldayPricing1).isEqualTo(fulldayPricing2);
        fulldayPricing2.setId(2L);
        assertThat(fulldayPricing1).isNotEqualTo(fulldayPricing2);
        fulldayPricing1.setId(null);
        assertThat(fulldayPricing1).isNotEqualTo(fulldayPricing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FulldayPricingDTO.class);
        FulldayPricingDTO fulldayPricingDTO1 = new FulldayPricingDTO();
        fulldayPricingDTO1.setId(1L);
        FulldayPricingDTO fulldayPricingDTO2 = new FulldayPricingDTO();
        assertThat(fulldayPricingDTO1).isNotEqualTo(fulldayPricingDTO2);
        fulldayPricingDTO2.setId(fulldayPricingDTO1.getId());
        assertThat(fulldayPricingDTO1).isEqualTo(fulldayPricingDTO2);
        fulldayPricingDTO2.setId(2L);
        assertThat(fulldayPricingDTO1).isNotEqualTo(fulldayPricingDTO2);
        fulldayPricingDTO1.setId(null);
        assertThat(fulldayPricingDTO1).isNotEqualTo(fulldayPricingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fulldayPricingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fulldayPricingMapper.fromId(null)).isNull();
    }
}
