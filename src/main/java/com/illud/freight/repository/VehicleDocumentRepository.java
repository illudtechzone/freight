package com.illud.freight.repository;

import com.illud.freight.domain.VehicleDocument;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VehicleDocument entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleDocumentRepository extends JpaRepository<VehicleDocument, Long> {

}
