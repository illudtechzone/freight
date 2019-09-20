package com.illud.freight.repository.search;

import com.illud.freight.domain.Freight;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Freight entity.
 */
public interface FreightSearchRepository extends ElasticsearchRepository<Freight, Long> {
}
