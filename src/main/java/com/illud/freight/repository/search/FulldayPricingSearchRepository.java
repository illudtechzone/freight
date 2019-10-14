package com.illud.freight.repository.search;

import com.illud.freight.domain.FulldayPricing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FulldayPricing entity.
 */
public interface FulldayPricingSearchRepository extends ElasticsearchRepository<FulldayPricing, Long> {
}
