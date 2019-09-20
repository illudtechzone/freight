package com.illud.freight.repository;

import com.illud.freight.domain.VehicleLookUp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VehicleLookUp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleLookUpRepository extends JpaRepository<VehicleLookUp, Long> {

}
