package com.illud.freight.repository;

import com.illud.freight.domain.Company;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyIdpCode(String companyIdpCode);

}
