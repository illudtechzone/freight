package com.illud.freight.service.impl;

import com.illud.freight.service.FreightService;

import com.illud.freight.client.activiti_rest_api.api.FormsApi;
import com.illud.freight.client.activiti_rest_api.api.ProcessInstancesApi;
import com.illud.freight.client.activiti_rest_api.api.TasksApi;
import com.illud.freight.client.activiti_rest_api.model.DataResponse;
import com.illud.freight.client.activiti_rest_api.model.ProcessInstanceCreateRequest;
import com.illud.freight.client.activiti_rest_api.model.ProcessInstanceResponse;
import com.illud.freight.client.activiti_rest_api.model.RestFormProperty;
import com.illud.freight.client.activiti_rest_api.model.RestVariable;
import com.illud.freight.client.activiti_rest_api.model.SubmitFormRequest;
import com.illud.freight.client.activiti_rest_api.model.freight.DefaultInfo;
import com.illud.freight.domain.Freight;
import com.illud.freight.repository.FreightRepository;
import com.illud.freight.repository.search.FreightSearchRepository;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.mapper.FreightMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Freight.
 */
@Service
@Transactional
public class FreightServiceImpl implements FreightService {

    private final Logger log = LoggerFactory.getLogger(FreightServiceImpl.class);

    private final FreightRepository freightRepository;

    private final FreightMapper freightMapper;

    private final FreightSearchRepository freightSearchRepository;
    
    @Autowired
    private FormsApi formsApi;

	@Autowired
	private TasksApi tasksApi;
	
	@Autowired
	private ProcessInstancesApi processInstancesApi;

    
    @Autowired
    private ProcessInstancesApi processInstanceApi;

    public FreightServiceImpl(FreightRepository freightRepository, FreightMapper freightMapper, FreightSearchRepository freightSearchRepository) {
        this.freightRepository = freightRepository;
        this.freightMapper = freightMapper;
        this.freightSearchRepository = freightSearchRepository;
    }

    /**
     * Save a freight.
     *
     * @param freightDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FreightDTO save(FreightDTO freightDTO) {
        log.debug("Request to save Freight : {}", freightDTO);
        Freight freight = freightMapper.toEntity(freightDTO);
        freight = freightRepository.save(freight);
        FreightDTO result = freightMapper.toDto(freight);
        freightSearchRepository.save(freight);
        
        DefaultInfo freightInfo = new DefaultInfo();
        freightInfo.setSource(freightDTO.getPickupPlaceId());
        freightInfo.setDestination(freightDTO.getDestinationPlaceId());
        freightInfo.setBuget(freightDTO.getAmount());
        freightInfo.setCustomerId(freightDTO.getCustomerId());
        
      
        
        initiate(freightInfo);
        return result;
    }

    
	
	
	public String initiate(DefaultInfo freightInfo) {

		ProcessInstanceCreateRequest processInstanceCreateRequest=new ProcessInstanceCreateRequest();
   		List<RestVariable> variables=new ArrayList<RestVariable>();
   		
   		processInstanceCreateRequest.setProcessDefinitionId("freight:1:40056");
   		
   		RestVariable riderRestVariable=new RestVariable();
   		riderRestVariable.setName("customer");
   		riderRestVariable.setType("string");
   		riderRestVariable.setValue("customer");
   		variables.add(riderRestVariable);
   		
   		RestVariable driverRestVariable=new RestVariable();
   		driverRestVariable.setName("manager");
   		driverRestVariable.setType("string");
   		driverRestVariable.setValue("manager");
   		
   		variables.add(driverRestVariable);
   	
   	  
   	        
   	       
   		
   		log.info("*****************************************************"+variables.size());
   		processInstanceCreateRequest.setVariables(variables);
   		log.info("*****************************************************"+processInstanceCreateRequest.getVariables());
   		
   		ResponseEntity<ProcessInstanceResponse> processInstanceResponse = processInstanceApi
				.createProcessInstance(processInstanceCreateRequest);
		String processInstanceId = processInstanceResponse.getBody().getId();
		String processDefinitionId = processInstanceCreateRequest.getProcessDefinitionId();
		log.info("++++++++++++++++processDefinitionId++++++++++++++++++"+ processDefinitionId);
		log.info("++++++++++++++++ProcessInstanceId is+++++++++++++ " + processInstanceId);
		
		 ResponseEntity<DataResponse> taskResponse = tasksApi.getTasks(null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, processInstanceId, null,
					null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, "0", null, "desc", "30");
		 
		 @SuppressWarnings("unchecked")
			String taskId = ((List<LinkedHashMap<String, String>>) taskResponse.getBody().getData()).get(0)
					.get("id");
			log.info("Initiate task is " + taskId);
			collectInfo(taskId, freightInfo);
		
   		processInstanceApi.createProcessInstance(processInstanceCreateRequest);
   		
		
		return processInstanceId;
	}
	

	public void collectInfo(String taskId, DefaultInfo freightInfo) {
		
		log .info("into ====================collectInfo()");
   		List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
   		SubmitFormRequest submitFormRequest = new SubmitFormRequest();
   		submitFormRequest.setAction("completed");
   		submitFormRequest.setTaskId(taskId);
		
   		RestFormProperty sourceFormProperty = new RestFormProperty();
   		sourceFormProperty.setId("source");
   		sourceFormProperty.setName("source");
   		sourceFormProperty.setType("String");
   		sourceFormProperty.setReadable(true);
   		sourceFormProperty.setValue(freightInfo.getSource());
   		formProperties.add(sourceFormProperty);
   		
   		RestFormProperty destinationFormProperty = new RestFormProperty();
   		destinationFormProperty.setId("destination");
   		destinationFormProperty.setName("destination");
   		destinationFormProperty.setType("String");
   		destinationFormProperty.setReadable(true);
   		destinationFormProperty.setValue(freightInfo.getDestination());
   		formProperties.add(destinationFormProperty);
   		
   		RestFormProperty bugetFormProperty = new RestFormProperty();
   		bugetFormProperty.setId("buget");
   		bugetFormProperty.setName("buget");
   		bugetFormProperty.setType("String");
   		bugetFormProperty.setReadable(true);
   		bugetFormProperty.setValue(Long.toString(freightInfo.getBuget()));
   		formProperties.add(bugetFormProperty);
		
   		submitFormRequest.setProperties(formProperties);
   		formsApi.submitForm(submitFormRequest);
		
	}
	
	
    /**
     * Get all the freights.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FreightDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Freights");
        return freightRepository.findAll(pageable)
            .map(freightMapper::toDto);
    }


    /**
     * Get one freight by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FreightDTO> findOne(Long id) {
        log.debug("Request to get Freight : {}", id);
        return freightRepository.findById(id)
            .map(freightMapper::toDto);
    }

    /**
     * Delete the freight by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Freight : {}", id);
        freightRepository.deleteById(id);
        freightSearchRepository.deleteById(id);
    }

    /**
     * Search for the freight corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FreightDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Freights for query {}", query);
        return freightSearchRepository.search(queryStringQuery(query), pageable)
            .map(freightMapper::toDto);
    }
}
