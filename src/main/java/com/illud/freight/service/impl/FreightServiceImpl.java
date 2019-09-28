package com.illud.freight.service.impl;

import com.illud.freight.service.FreightService;

import com.illud.freight.client.activiti_rest_api.api.FormsApi;
import com.illud.freight.client.activiti_rest_api.api.HistoryApi;
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

import net.bytebuddy.asm.Advice.Return;

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
	private HistoryApi historyApi;

    
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
        freightInfo.setBuget(freightDTO.getEstimatedAmount());
        freightInfo.setCustomerId(freightDTO.getCustomerId());
        String pid=initiate(freightInfo);
        result.setTrackingId(pid);

        return result;
    }

    
	
	
	public String initiate(DefaultInfo freightInfo) {

		ProcessInstanceCreateRequest processInstanceCreateRequest=new ProcessInstanceCreateRequest();
   		List<RestVariable> variables=new ArrayList<RestVariable>();
   		
   		processInstanceCreateRequest.setProcessDefinitionId("freight:3:41902");
   		
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
		
   		processInstanceApi.createProcessInstance(processInstanceCreateRequest);
   		
   	 ResponseEntity<DataResponse> taskResponse = tasksApi.getTasks(null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, processInstanceId, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, "0", null, "desc", "30");
	 
	// @SuppressWarnings("unchecked")
		String taskId = ((List<LinkedHashMap<String, String>>) taskResponse.getBody().getData()).get(0)
				.get("id");
		log.info("Initiate task is************************************** " + taskId);
		
		collectInfo(taskId, freightInfo);
		
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
   		String budget = ""+freightInfo.getBuget();
   		bugetFormProperty.setValue(budget);
   		formProperties.add(bugetFormProperty);
   		
   		RestFormProperty customerIdFormProperty = new RestFormProperty();
   		customerIdFormProperty.setId("customerId");
   		customerIdFormProperty.setName("customerId");
   		customerIdFormProperty.setType("String");
   		customerIdFormProperty.setReadable(true);
   		String customerId = ""+freightInfo.getCustomerId();
   		customerIdFormProperty.setValue(customerId);
   		formProperties.add(customerIdFormProperty);
		
   		submitFormRequest.setProperties(formProperties);
   		formsApi.submitForm(submitFormRequest);
		
	}
	
	/**
     * Get all the open freights through activiti.
     */
	@Override
	public List<FreightDTO>getPendingFreights( String name,
			 String nameLike,
			 String assignee,
			 String assigneeLike,
			 String candidateUser,
			 String candidateGroup,
			 String candidateGroups,
			 String processInstanceId,
			 String processDefinitionId,
			 String processDefinitionKey,
			 String createdOn,
			String createdBefore,
			 String createdAfter/*Pageable pageable*/) {

		
		ResponseEntity<DataResponse> response = tasksApi.getTasks(name, nameLike, null, null, null, null, assignee,
				assigneeLike, null, null, null, null, candidateUser, candidateGroup, candidateGroups, null, null, null,
				processInstanceId, null, null, "freight:3:41902", null, null, null, null, null, createdOn, createdBefore, createdAfter, null,
				null, null, null, null, null, null, null, null, null, null, null, null, /*pageable.getPageNumber()+""*/"0",null, "desc",/* pageable.getPageSize()+""*/"1500");
		List<LinkedHashMap<String, String>> myTasks = (List<LinkedHashMap<String, String>>) response.getBody()
				.getData();
		
		log.info("*****************************////////////////////////////////////freight size**********"+myTasks.size());
		List<FreightDTO> freights = new ArrayList<FreightDTO>();
		
		myTasks.forEach(task -> {
			FreightDTO openTask = new FreightDTO();
			String taskProcessInstanceId = task.get("processInstanceId");
			//log.info("***************************************************Process Instance id to delete  is  "+taskProcessInstanceId);
			String taskName = task.get("name");
			String taskId = task.get("id");
			log.info("*****************************////////////////////////////////////**********"+taskName);
			log.info("*****************************////////////////////////////////////**********"+taskId);
			openTask.setPickupPlaceId(getBookingDetails(taskProcessInstanceId).getPickupPlaceId());
			openTask.setDestinationPlaceId(getBookingDetails(taskProcessInstanceId).getDestinationPlaceId());
			openTask.setEstimatedAmount(getBookingDetails(taskProcessInstanceId).getEstimatedAmount());
			//openTask.setCustomerId(getBookingDetails(taskProcessInstanceId).getCustomerId());
			freights.add(openTask);
		});
		return freights;
		}
	
	
@Override	
public FreightDTO getBookingDetails(String processInstanceId) {
		
	FreightDTO defaultInfoRequest = new FreightDTO();
		List<LinkedHashMap<String, String>> taskResponseCollectInfo = (List<LinkedHashMap<String, String>>) getHistoricTaskusingProcessInstanceIdAndName(
				processInstanceId, "collect details").getBody().getData();
		String taskId = taskResponseCollectInfo.get(0).get("id");
		log.info("Collect Informations TaskID is ********************"+taskId);
		log.info("///////////////////////////////////********************"+taskResponseCollectInfo.size());
		
		ResponseEntity<DataResponse> requestDetails = historyApi.getHistoricDetailInfo(null, processInstanceId, null, null,
				taskId.toString(), true, false);
		
		List<LinkedHashMap<String, String>> requestFormProperties = (List<LinkedHashMap<String, String>>) requestDetails
				.getBody().getData();
		
		log.info("Number of items in the collection ********************************************"+requestFormProperties.size());
		log.info("Task Id of the item is "+taskId);
		
		for (LinkedHashMap<String, String> requestMap : requestFormProperties) {
			String source = null;
			String destination = null;
			String budget = null;
			String customerId = null;
			
			String propertyId = requestMap.get("propertyId");
			
			log.info("++++++++++++++++++++++++++"+propertyId);
			if (propertyId.equals("source")) {
				source = requestMap.get("propertyValue");
				log.info("+++++++++++++++++++++++++*"+source);
				defaultInfoRequest.setPickupPlaceId(source);
			}
			else if (propertyId.equals("destination")) {
				destination = requestMap.get("propertyValue");
				log.info("++++++++++++++++++++++++++**"+destination);
				defaultInfoRequest.setDestinationPlaceId(destination);
			}
			else if (propertyId.equals("customerId")) {
				customerId = requestMap.get("propertyValue");
				log.info("++++++++++++++++++++++++++****"+customerId);
				defaultInfoRequest.setCustomerId(Long.parseLong(customerId));
			
			
				}
			else if (propertyId.equals("buget")) {
				budget = requestMap.get("propertyValue");
				log.info("++++++++++++++++++++++++++***"+budget);
				defaultInfoRequest.setEstimatedAmount(Double.parseDouble(budget));
				}
		}
		
		return defaultInfoRequest;
}

public ResponseEntity<DataResponse> getHistoricTaskusingProcessInstanceIdAndName(String processInstanceId,
		String name) {

	return historyApi.listHistoricTaskInstances(null, processInstanceId, null, null, null, null, null, null, null,
			null, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

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

	@Override
	public Optional<FreightDTO> convertToDto(Freight freight) {
		return  Optional.of(freight).map(freightMapper::toDto);
		
	}

	@Override
	public List<FreightDTO> convertToDtoList(List<Freight> page) {
		List<FreightDTO> dtos = new ArrayList<>();
		page.forEach(data->{
			dtos.add(freightMapper.toDto(data));
		});
		return dtos; 
	}
}
