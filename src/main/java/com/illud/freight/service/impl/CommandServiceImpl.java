package com.illud.freight.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.illud.freight.client.activiti_rest_api.api.FormsApi;
import com.illud.freight.client.activiti_rest_api.api.ProcessInstancesApi;
import com.illud.freight.client.activiti_rest_api.model.ProcessInstanceCreateRequest;
import com.illud.freight.client.activiti_rest_api.model.ProcessInstanceResponse;
import com.illud.freight.client.activiti_rest_api.model.RestVariable;
import com.illud.freight.client.activiti_rest_api.model.freight.TransportOwnerResponse;
import com.illud.freight.service.CommandService;
import com.illud.freight.service.dto.FreightDTO;


@Service
@SuppressWarnings("unchecked")
public class CommandServiceImpl implements CommandService {

	private final Logger log = LoggerFactory.getLogger(CommandServiceImpl.class);
			
	
	@Autowired
    private FormsApi formsApi;

    
    @Autowired
    private ProcessInstancesApi processInstanceApi;


	
	

}
