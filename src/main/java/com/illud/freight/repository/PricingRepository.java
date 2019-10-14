package com.illud.freight.repository;

import com.illud.freight.domain.Pricing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pricing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

}
