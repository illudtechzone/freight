package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.Pricing;
import com.illud.freight.repository.PricingRepository;
import com.illud.freight.repository.search.PricingSearchRepository;
import com.illud.freight.service.PricingService;
import com.illud.freight.service.dto.PricingDTO;
import com.illud.freight.service.mapper.PricingMapper;
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

import com.illud.freight.domain.enumeration.RatePlan;
/**
 * Test class for the PricingResource REST controller.
 *
 * @see PricingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class PricingResourceIntTest {

    private static final RatePlan DEFAULT_RATE_PLAN = RatePlan.NORMAL;
    private static final RatePlan UPDATED_RATE_PLAN = RatePlan.FULL_DAY;

    private static final String DEFAULT_ADDITIONAL_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_FREE_WAITING_IN_MINS = 1L;
    private static final Long UPDATED_FREE_WAITING_IN_MINS = 2L;

    private static final Double DEFAULT_WAITING_CHARGE_IN_MINS = 1D;
    private static final Double UPDATED_WAITING_CHARGE_IN_MINS = 2D;

    private static final Double DEFAULT_NIGHT_SURCHARGE_IN_MINS = 1D;
    private static final Double UPDATED_NIGHT_SURCHARGE_IN_MINS = 2D;

    private static final Double DEFAULT_BASIC_RATE = 1D;
    private static final Double UPDATED_BASIC_RATE = 2D;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private PricingMapper pricingMapper;

    @Autowired
    private PricingService pricingService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.PricingSearchRepositoryMockConfiguration
     */
    @Autowired
    private PricingSearchRepository mockPricingSearchRepository;

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

    private MockMvc restPricingMockMvc;

    private Pricing pricing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PricingResource pricingResource = new PricingResource(pricingService);
        this.restPricingMockMvc = MockMvcBuilders.standaloneSetup(pricingResource)
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
    public static Pricing createEntity(EntityManager em) {
        Pricing pricing = new Pricing()
            .ratePlan(DEFAULT_RATE_PLAN)
            .additionalDescription(DEFAULT_ADDITIONAL_DESCRIPTION)
            .freeWaitingInMins(DEFAULT_FREE_WAITING_IN_MINS)
            .waitingChargeInMins(DEFAULT_WAITING_CHARGE_IN_MINS)
            .nightSurchargeInMins(DEFAULT_NIGHT_SURCHARGE_IN_MINS)
            .basicRate(DEFAULT_BASIC_RATE);
        return pricing;
    }

    @Before
    public void initTest() {
        pricing = createEntity(em);
    }

    @Test
    @Transactional
    public void createPricing() throws Exception {
        int databaseSizeBeforeCreate = pricingRepository.findAll().size();

        // Create the Pricing
        PricingDTO pricingDTO = pricingMapper.toDto(pricing);
        restPricingMockMvc.perform(post("/api/pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pricingDTO)))
            .andExpect(status().isCreated());

        // Validate the Pricing in the database
        List<Pricing> pricingList = pricingRepository.findAll();
        assertThat(pricingList).hasSize(databaseSizeBeforeCreate + 1);
        Pricing testPricing = pricingList.get(pricingList.size() - 1);
        assertThat(testPricing.getRatePlan()).isEqualTo(DEFAULT_RATE_PLAN);
        assertThat(testPricing.getAdditionalDescription()).isEqualTo(DEFAULT_ADDITIONAL_DESCRIPTION);
        assertThat(testPricing.getFreeWaitingInMins()).isEqualTo(DEFAULT_FREE_WAITING_IN_MINS);
        assertThat(testPricing.getWaitingChargeInMins()).isEqualTo(DEFAULT_WAITING_CHARGE_IN_MINS);
        assertThat(testPricing.getNightSurchargeInMins()).isEqualTo(DEFAULT_NIGHT_SURCHARGE_IN_MINS);
        assertThat(testPricing.getBasicRate()).isEqualTo(DEFAULT_BASIC_RATE);

        // Validate the Pricing in Elasticsearch
        verify(mockPricingSearchRepository, times(1)).save(testPricing);
    }

    @Test
    @Transactional
    public void createPricingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pricingRepository.findAll().size();

        // Create the Pricing with an existing ID
        pricing.setId(1L);
        PricingDTO pricingDTO = pricingMapper.toDto(pricing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPricingMockMvc.perform(post("/api/pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pricing in the database
        List<Pricing> pricingList = pricingRepository.findAll();
        assertThat(pricingList).hasSize(databaseSizeBeforeCreate);

        // Validate the Pricing in Elasticsearch
        verify(mockPricingSearchRepository, times(0)).save(pricing);
    }

    @Test
    @Transactional
    public void getAllPricings() throws Exception {
        // Initialize the database
        pricingRepository.saveAndFlush(pricing);

        // Get all the pricingList
        restPricingMockMvc.perform(get("/api/pricings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePlan").value(hasItem(DEFAULT_RATE_PLAN.toString())))
            .andExpect(jsonPath("$.[*].additionalDescription").value(hasItem(DEFAULT_ADDITIONAL_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].freeWaitingInMins").value(hasItem(DEFAULT_FREE_WAITING_IN_MINS.intValue())))
            .andExpect(jsonPath("$.[*].waitingChargeInMins").value(hasItem(DEFAULT_WAITING_CHARGE_IN_MINS.doubleValue())))
            .andExpect(jsonPath("$.[*].nightSurchargeInMins").value(hasItem(DEFAULT_NIGHT_SURCHARGE_IN_MINS.doubleValue())))
            .andExpect(jsonPath("$.[*].basicRate").value(hasItem(DEFAULT_BASIC_RATE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPricing() throws Exception {
        // Initialize the database
        pricingRepository.saveAndFlush(pricing);

        // Get the pricing
        restPricingMockMvc.perform(get("/api/pricings/{id}", pricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pricing.getId().intValue()))
            .andExpect(jsonPath("$.ratePlan").value(DEFAULT_RATE_PLAN.toString()))
            .andExpect(jsonPath("$.additionalDescription").value(DEFAULT_ADDITIONAL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.freeWaitingInMins").value(DEFAULT_FREE_WAITING_IN_MINS.intValue()))
            .andExpect(jsonPath("$.waitingChargeInMins").value(DEFAULT_WAITING_CHARGE_IN_MINS.doubleValue()))
            .andExpect(jsonPath("$.nightSurchargeInMins").value(DEFAULT_NIGHT_SURCHARGE_IN_MINS.doubleValue()))
            .andExpect(jsonPath("$.basicRate").value(DEFAULT_BASIC_RATE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPricing() throws Exception {
        // Get the pricing
        restPricingMockMvc.perform(get("/api/pricings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePricing() throws Exception {
        // Initialize the database
        pricingRepository.saveAndFlush(pricing);

        int databaseSizeBeforeUpdate = pricingRepository.findAll().size();

        // Update the pricing
        Pricing updatedPricing = pricingRepository.findById(pricing.getId()).get();
        // Disconnect from session so that the updates on updatedPricing are not directly saved in db
        em.detach(updatedPricing);
        updatedPricing
            .ratePlan(UPDATED_RATE_PLAN)
            .additionalDescription(UPDATED_ADDITIONAL_DESCRIPTION)
            .freeWaitingInMins(UPDATED_FREE_WAITING_IN_MINS)
            .waitingChargeInMins(UPDATED_WAITING_CHARGE_IN_MINS)
            .nightSurchargeInMins(UPDATED_NIGHT_SURCHARGE_IN_MINS)
            .basicRate(UPDATED_BASIC_RATE);
        PricingDTO pricingDTO = pricingMapper.toDto(updatedPricing);

        restPricingMockMvc.perform(put("/api/pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pricingDTO)))
            .andExpect(status().isOk());

        // Validate the Pricing in the database
        List<Pricing> pricingList = pricingRepository.findAll();
        assertThat(pricingList).hasSize(databaseSizeBeforeUpdate);
        Pricing testPricing = pricingList.get(pricingList.size() - 1);
        assertThat(testPricing.getRatePlan()).isEqualTo(UPDATED_RATE_PLAN);
        assertThat(testPricing.getAdditionalDescription()).isEqualTo(UPDATED_ADDITIONAL_DESCRIPTION);
        assertThat(testPricing.getFreeWaitingInMins()).isEqualTo(UPDATED_FREE_WAITING_IN_MINS);
        assertThat(testPricing.getWaitingChargeInMins()).isEqualTo(UPDATED_WAITING_CHARGE_IN_MINS);
        assertThat(testPricing.getNightSurchargeInMins()).isEqualTo(UPDATED_NIGHT_SURCHARGE_IN_MINS);
        assertThat(testPricing.getBasicRate()).isEqualTo(UPDATED_BASIC_RATE);

        // Validate the Pricing in Elasticsearch
        verify(mockPricingSearchRepository, times(1)).save(testPricing);
    }

    @Test
    @Transactional
    public void updateNonExistingPricing() throws Exception {
        int databaseSizeBeforeUpdate = pricingRepository.findAll().size();

        // Create the Pricing
        PricingDTO pricingDTO = pricingMapper.toDto(pricing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPricingMockMvc.perform(put("/api/pricings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pricingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pricing in the database
        List<Pricing> pricingList = pricingRepository.findAll();
        assertThat(pricingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Pricing in Elasticsearch
        verify(mockPricingSearchRepository, times(0)).save(pricing);
    }

    @Test
    @Transactional
    public void deletePricing() throws Exception {
        // Initialize the database
        pricingRepository.saveAndFlush(pricing);

        int databaseSizeBeforeDelete = pricingRepository.findAll().size();

        // Delete the pricing
        restPricingMockMvc.perform(delete("/api/pricings/{id}", pricing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pricing> pricingList = pricingRepository.findAll();
        assertThat(pricingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Pricing in Elasticsearch
        verify(mockPricingSearchRepository, times(1)).deleteById(pricing.getId());
    }

    @Test
    @Transactional
    public void searchPricing() throws Exception {
        // Initialize the database
        pricingRepository.saveAndFlush(pricing);
        when(mockPricingSearchRepository.search(queryStringQuery("id:" + pricing.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(pricing), PageRequest.of(0, 1), 1));
        // Search the pricing
        restPricingMockMvc.perform(get("/api/_search/pricings?query=id:" + pricing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pricing.getId().intValue())))
            .andExpect(jsonPath("$.[*].ratePlan").value(hasItem(DEFAULT_RATE_PLAN.toString())))
            .andExpect(jsonPath("$.[*].additionalDescription").value(hasItem(DEFAULT_ADDITIONAL_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].freeWaitingInMins").value(hasItem(DEFAULT_FREE_WAITING_IN_MINS.intValue())))
            .andExpect(jsonPath("$.[*].waitingChargeInMins").value(hasItem(DEFAULT_WAITING_CHARGE_IN_MINS.doubleValue())))
            .andExpect(jsonPath("$.[*].nightSurchargeInMins").value(hasItem(DEFAULT_NIGHT_SURCHARGE_IN_MINS.doubleValue())))
            .andExpect(jsonPath("$.[*].basicRate").value(hasItem(DEFAULT_BASIC_RATE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pricing.class);
        Pricing pricing1 = new Pricing();
        pricing1.setId(1L);
        Pricing pricing2 = new Pricing();
        pricing2.setId(pricing1.getId());
        assertThat(pricing1).isEqualTo(pricing2);
        pricing2.setId(2L);
        assertThat(pricing1).isNotEqualTo(pricing2);
        pricing1.setId(null);
        assertThat(pricing1).isNotEqualTo(pricing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PricingDTO.class);
        PricingDTO pricingDTO1 = new PricingDTO();
        pricingDTO1.setId(1L);
        PricingDTO pricingDTO2 = new PricingDTO();
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
        pricingDTO2.setId(pricingDTO1.getId());
        assertThat(pricingDTO1).isEqualTo(pricingDTO2);
        pricingDTO2.setId(2L);
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
        pricingDTO1.setId(null);
        assertThat(pricingDTO1).isNotEqualTo(pricingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pricingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pricingMapper.fromId(null)).isNull();
    }
}
