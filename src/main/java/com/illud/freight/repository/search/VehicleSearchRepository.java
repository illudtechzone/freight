package com.illud.freight.repository.search;

import com.illud.freight.domain.Vehicle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Vehicle entity.
 */
public interface VehicleSearchRepository extends ElasticsearchRepository<Vehicle, Long> {
}
