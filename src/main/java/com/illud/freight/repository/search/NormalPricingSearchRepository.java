package com.illud.freight.repository.search;

import com.illud.freight.domain.NormalPricing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NormalPricing entity.
 */
public interface NormalPricingSearchRepository extends ElasticsearchRepository<NormalPricing, Long> {
}
