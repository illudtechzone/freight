package com.illud.freight.service.impl;

import com.illud.freight.service.CompanyService;
import com.illud.freight.domain.Company;
import com.illud.freight.domain.Customer;
import com.illud.freight.repository.CompanyRepository;
import com.illud.freight.repository.search.CompanySearchRepository;
import com.illud.freight.service.dto.CompanyDTO;
import com.illud.freight.service.mapper.CompanyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Company.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final CompanySearchRepository companySearchRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, CompanySearchRepository companySearchRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companySearchRepository = companySearchRepository;
    }

    /**
     * Save a company.
     *
     * @param companyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", companyDTO);
        Company company = companyMapper.toEntity(companyDTO);
        company = companyRepository.save(company);
        CompanyDTO result = companyMapper.toDto(company);
        companySearchRepository.save(company);
        return result;
    }

    /**
     * Get all the companies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable)
            .map(companyMapper::toDto);
    }


    /**
     * Get one company by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyDTO> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id)
            .map(companyMapper::toDto);
    }

    /**
     * Delete the company by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
        companySearchRepository.deleteById(id);
    }

    /**
     * Search for the company corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Companies for query {}", query);
        return companySearchRepository.search(queryStringQuery(query), pageable)
            .map(companyMapper::toDto);
    }

	@Override
	public Optional<CompanyDTO> createcompanyIfnotexist(CompanyDTO companyDTO) {
		log.debug("<<<<<< createcompanyIfnotexist >>>>>>",companyDTO);
		Optional<Company> company = companyRepository.findByCompanyIdpCode(companyDTO.getCompanyIdpCode());
		if(company.isPresent()) {
			return company.map(companyMapper::toDto);
		}
		else {
			return Optional.of(save(companyDTO));
		}
	}

	@Override
	public Optional<CompanyDTO> createDto(Company company) {
		log.debug("<<<<<< createDto >>>>",company);
		return Optional.of(company).map(companyMapper::toDto);
	}

	@Override
	public List<CompanyDTO> createDtoList(List<Company> companies) {
		log.debug("<<<<< createDtoList >>>>>>",companies);
		
		return companyMapper.toDto(companies);
	}
	
	

	
}
