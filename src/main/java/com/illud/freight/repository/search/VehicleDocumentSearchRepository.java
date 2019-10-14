package com.illud.freight.repository.search;

import com.illud.freight.domain.VehicleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the VehicleDocument entity.
 */
public interface VehicleDocumentSearchRepository extends ElasticsearchRepository<VehicleDocument, Long> {
}
