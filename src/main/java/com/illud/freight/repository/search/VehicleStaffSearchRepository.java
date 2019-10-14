package com.illud.freight.repository.search;

import com.illud.freight.domain.VehicleStaff;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the VehicleStaff entity.
 */
public interface VehicleStaffSearchRepository extends ElasticsearchRepository<VehicleStaff, Long> {
}
