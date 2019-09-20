package com.illud.freight.repository.search;

import com.illud.freight.domain.VehicleLookUp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the VehicleLookUp entity.
 */
public interface VehicleLookUpSearchRepository extends ElasticsearchRepository<VehicleLookUp, Long> {
}
