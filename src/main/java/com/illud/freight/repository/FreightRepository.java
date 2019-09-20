package com.illud.freight.repository;

import com.illud.freight.domain.Freight;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Freight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {

}
