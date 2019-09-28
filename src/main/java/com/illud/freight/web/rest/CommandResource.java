package com.illud.freight.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.illud.freight.client.activiti_rest_api.model.freight.CustomerStatus;
import com.illud.freight.client.activiti_rest_api.model.freight.TransportOwnerResponse;
import com.illud.freight.service.CommandService;
import com.illud.freight.service.CustomerService;
import com.illud.freight.service.FreightService;
import com.illud.freight.service.QuotationService;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.QuotationDTO;

@RestController
@RequestMapping("/api")
public class CommandResource {

	private final Logger log = LoggerFactory.getLogger(CommandResource.class);

	@Autowired
	CommandService commandService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	FreightService freightService;
	
	@Autowired
	QuotationService quotationService;

	
	
	
	/**
	 * Update freight by transport owner with quatations
	 *
	 */
	
	@PostMapping("/sendQuatation/{taskId}")
	public void sendQuatation(@PathVariable String taskId, @RequestBody QuotationDTO response) {
		 quotationService.sendQuatation(taskId,response);
		
	}
	
	  @PostMapping("/customer/status/{taskId}")
	    public void status(@PathVariable String taskId,@RequestBody CustomerStatus customerStatus) {
		  freightService.customerStatus(taskId,customerStatus);
	    }
	  
	
}
