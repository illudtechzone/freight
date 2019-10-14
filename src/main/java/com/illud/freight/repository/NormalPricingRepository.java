package com.illud.freight.repository;

import com.illud.freight.domain.NormalPricing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NormalPricing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NormalPricingRepository extends JpaRepository<NormalPricing, Long> {

}
