package com.illud.freight.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of FreightSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class FreightSearchRepositoryMockConfiguration {

    @MockBean
    private FreightSearchRepository mockFreightSearchRepository;

}
