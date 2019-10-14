package com.illud.freight.repository;

import com.illud.freight.domain.FulldayPricing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FulldayPricing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FulldayPricingRepository extends JpaRepository<FulldayPricing, Long> {

}
