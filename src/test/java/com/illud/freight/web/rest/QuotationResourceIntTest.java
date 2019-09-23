package com.illud.freight.web.rest;

import com.illud.freight.FreightApp;

import com.illud.freight.domain.Quotation;
import com.illud.freight.repository.QuotationRepository;
import com.illud.freight.repository.search.QuotationSearchRepository;
import com.illud.freight.service.QuotationService;
import com.illud.freight.service.dto.QuotationDTO;
import com.illud.freight.service.mapper.QuotationMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Test class for the QuotationResource REST controller.
 *
 * @see QuotationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreightApp.class)
public class QuotationResourceIntTest {

    private static final Long DEFAULT_FREIGHT_ID = 1L;
    private static final Long UPDATED_FREIGHT_ID = 2L;

    private static final Long DEFAULT_COMPANY_ID = 1L;
    private static final Long UPDATED_COMPANY_ID = 2L;

    private static final Long DEFAULT_VEHICLE_ID = 1L;
    private static final Long UPDATED_VEHICLE_ID = 2L;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final LocalDate DEFAULT_DELIVERY_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DELIVERY_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationMapper quotationMapper;

    @Autowired
    private QuotationService quotationService;

    /**
     * This repository is mocked in the com.illud.freight.repository.search test package.
     *
     * @see com.illud.freight.repository.search.QuotationSearchRepositoryMockConfiguration
     */
    @Autowired
    private QuotationSearchRepository mockQuotationSearchRepository;

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

    private MockMvc restQuotationMockMvc;

    private Quotation quotation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuotationResource quotationResource = new QuotationResource(quotationService);
        this.restQuotationMockMvc = MockMvcBuilders.standaloneSetup(quotationResource)
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
    public static Quotation createEntity(EntityManager em) {
        Quotation quotation = new Quotation()
            .freightId(DEFAULT_FREIGHT_ID)
            .companyId(DEFAULT_COMPANY_ID)
            .vehicleId(DEFAULT_VEHICLE_ID)
            .amount(DEFAULT_AMOUNT)
            .deliveryDate(DEFAULT_DELIVERY_DATE);
        return quotation;
    }

    @Before
    public void initTest() {
        quotation = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuotation() throws Exception {
        int databaseSizeBeforeCreate = quotationRepository.findAll().size();

        // Create the Quotation
        QuotationDTO quotationDTO = quotationMapper.toDto(quotation);
        restQuotationMockMvc.perform(post("/api/quotations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationDTO)))
            .andExpect(status().isCreated());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeCreate + 1);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getFreightId()).isEqualTo(DEFAULT_FREIGHT_ID);
        assertThat(testQuotation.getCompanyId()).isEqualTo(DEFAULT_COMPANY_ID);
        assertThat(testQuotation.getVehicleId()).isEqualTo(DEFAULT_VEHICLE_ID);
        assertThat(testQuotation.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testQuotation.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);

        // Validate the Quotation in Elasticsearch
        verify(mockQuotationSearchRepository, times(1)).save(testQuotation);
    }

    @Test
    @Transactional
    public void createQuotationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quotationRepository.findAll().size();

        // Create the Quotation with an existing ID
        quotation.setId(1L);
        QuotationDTO quotationDTO = quotationMapper.toDto(quotation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotationMockMvc.perform(post("/api/quotations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeCreate);

        // Validate the Quotation in Elasticsearch
        verify(mockQuotationSearchRepository, times(0)).save(quotation);
    }

    @Test
    @Transactional
    public void getAllQuotations() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        // Get all the quotationList
        restQuotationMockMvc.perform(get("/api/quotations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotation.getId().intValue())))
            .andExpect(jsonPath("$.[*].freightId").value(hasItem(DEFAULT_FREIGHT_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        // Get the quotation
        restQuotationMockMvc.perform(get("/api/quotations/{id}", quotation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quotation.getId().intValue()))
            .andExpect(jsonPath("$.freightId").value(DEFAULT_FREIGHT_ID.intValue()))
            .andExpect(jsonPath("$.companyId").value(DEFAULT_COMPANY_ID.intValue()))
            .andExpect(jsonPath("$.vehicleId").value(DEFAULT_VEHICLE_ID.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuotation() throws Exception {
        // Get the quotation
        restQuotationMockMvc.perform(get("/api/quotations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();

        // Update the quotation
        Quotation updatedQuotation = quotationRepository.findById(quotation.getId()).get();
        // Disconnect from session so that the updates on updatedQuotation are not directly saved in db
        em.detach(updatedQuotation);
        updatedQuotation
            .freightId(UPDATED_FREIGHT_ID)
            .companyId(UPDATED_COMPANY_ID)
            .vehicleId(UPDATED_VEHICLE_ID)
            .amount(UPDATED_AMOUNT)
            .deliveryDate(UPDATED_DELIVERY_DATE);
        QuotationDTO quotationDTO = quotationMapper.toDto(updatedQuotation);

        restQuotationMockMvc.perform(put("/api/quotations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationDTO)))
            .andExpect(status().isOk());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);
        Quotation testQuotation = quotationList.get(quotationList.size() - 1);
        assertThat(testQuotation.getFreightId()).isEqualTo(UPDATED_FREIGHT_ID);
        assertThat(testQuotation.getCompanyId()).isEqualTo(UPDATED_COMPANY_ID);
        assertThat(testQuotation.getVehicleId()).isEqualTo(UPDATED_VEHICLE_ID);
        assertThat(testQuotation.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testQuotation.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);

        // Validate the Quotation in Elasticsearch
        verify(mockQuotationSearchRepository, times(1)).save(testQuotation);
    }

    @Test
    @Transactional
    public void updateNonExistingQuotation() throws Exception {
        int databaseSizeBeforeUpdate = quotationRepository.findAll().size();

        // Create the Quotation
        QuotationDTO quotationDTO = quotationMapper.toDto(quotation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuotationMockMvc.perform(put("/api/quotations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Quotation in the database
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Quotation in Elasticsearch
        verify(mockQuotationSearchRepository, times(0)).save(quotation);
    }

    @Test
    @Transactional
    public void deleteQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);

        int databaseSizeBeforeDelete = quotationRepository.findAll().size();

        // Delete the quotation
        restQuotationMockMvc.perform(delete("/api/quotations/{id}", quotation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Quotation> quotationList = quotationRepository.findAll();
        assertThat(quotationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Quotation in Elasticsearch
        verify(mockQuotationSearchRepository, times(1)).deleteById(quotation.getId());
    }

    @Test
    @Transactional
    public void searchQuotation() throws Exception {
        // Initialize the database
        quotationRepository.saveAndFlush(quotation);
        when(mockQuotationSearchRepository.search(queryStringQuery("id:" + quotation.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(quotation), PageRequest.of(0, 1), 1));
        // Search the quotation
        restQuotationMockMvc.perform(get("/api/_search/quotations?query=id:" + quotation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotation.getId().intValue())))
            .andExpect(jsonPath("$.[*].freightId").value(hasItem(DEFAULT_FREIGHT_ID.intValue())))
            .andExpect(jsonPath("$.[*].companyId").value(hasItem(DEFAULT_COMPANY_ID.intValue())))
            .andExpect(jsonPath("$.[*].vehicleId").value(hasItem(DEFAULT_VEHICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quotation.class);
        Quotation quotation1 = new Quotation();
        quotation1.setId(1L);
        Quotation quotation2 = new Quotation();
        quotation2.setId(quotation1.getId());
        assertThat(quotation1).isEqualTo(quotation2);
        quotation2.setId(2L);
        assertThat(quotation1).isNotEqualTo(quotation2);
        quotation1.setId(null);
        assertThat(quotation1).isNotEqualTo(quotation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotationDTO.class);
        QuotationDTO quotationDTO1 = new QuotationDTO();
        quotationDTO1.setId(1L);
        QuotationDTO quotationDTO2 = new QuotationDTO();
        assertThat(quotationDTO1).isNotEqualTo(quotationDTO2);
        quotationDTO2.setId(quotationDTO1.getId());
        assertThat(quotationDTO1).isEqualTo(quotationDTO2);
        quotationDTO2.setId(2L);
        assertThat(quotationDTO1).isNotEqualTo(quotationDTO2);
        quotationDTO1.setId(null);
        assertThat(quotationDTO1).isNotEqualTo(quotationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quotationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quotationMapper.fromId(null)).isNull();
    }
}
