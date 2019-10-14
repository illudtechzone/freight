package com.illud.freight.repository.search;

import com.illud.freight.domain.Pricing;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Pricing entity.
 */
public interface PricingSearchRepository extends ElasticsearchRepository<Pricing, Long> {
}
