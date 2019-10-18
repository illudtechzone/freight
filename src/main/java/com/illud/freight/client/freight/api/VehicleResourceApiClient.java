package com.illud.freight.client.freight.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.illud.freight.client.freight.ClientConfiguration;

@FeignClient(name="${freight.name:freight}", url="${freight.url:35.232.29.128:8085/}", configuration = ClientConfiguration.class)
public interface VehicleResourceApiClient extends VehicleResourceApi {
}