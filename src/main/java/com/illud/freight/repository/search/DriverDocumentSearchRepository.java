package com.illud.freight.repository.search;

import com.illud.freight.domain.DriverDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DriverDocument entity.
 */
public interface DriverDocumentSearchRepository extends ElasticsearchRepository<DriverDocument, Long> {
}
