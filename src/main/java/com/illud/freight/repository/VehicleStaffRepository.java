package com.illud.freight.repository;

import com.illud.freight.domain.VehicleStaff;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VehicleStaff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleStaffRepository extends JpaRepository<VehicleStaff, Long> {

}
