package com.illud.freight.repository.search;

import com.illud.freight.domain.Quotation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Quotation entity.
 */
public interface QuotationSearchRepository extends ElasticsearchRepository<Quotation, Long> {
}
