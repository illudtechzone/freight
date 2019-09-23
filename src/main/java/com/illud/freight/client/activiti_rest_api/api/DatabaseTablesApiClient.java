package com.illud.freight.client.activiti_rest_api.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.illud.freight.client.activiti_rest_api.ClientConfiguration;

@FeignClient(name="${activitiRestApi.name:activitiRestApi}", url="${activitiRestApi.url:http://localhost:8080/activiti-rest/service}", configuration = ClientConfiguration.class)
public interface DatabaseTablesApiClient extends DatabaseTablesApi {
}