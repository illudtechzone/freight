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
import com.illud.freight.client.activiti_rest_api.model.freight.CustomerStatus;
import com.illud.freight.client.activiti_rest_api.model.freight.DefaultInfo;
import com.illud.freight.domain.Freight;
import com.illud.freight.domain.Quotation;
import com.illud.freight.domain.Vehicle;
import com.illud.freight.domain.enumeration.FreightStatus;
import com.illud.freight.domain.enumeration.RequestStatus;
import com.illud.freight.repository.FreightRepository;
import com.illud.freight.repository.QuotationRepository;
import com.illud.freight.repository.VehicleRepository;
import com.illud.freight.repository.search.FreightSearchRepository;
import com.illud.freight.service.dto.FreightDTO;
import com.illud.freight.service.dto.QuotationDTO;
import com.illud.freight.service.dto.VehicleDTO;
import com.illud.freight.service.mapper.FreightMapper;
import com.illud.freight.service.mapper.QuotationMapper;
import com.illud.freight.web.rest.errors.BadRequestAlertException;

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
    
    private static final String ENTITY_NAME = "freightFreight";

    private final FreightRepository freightRepository;

    private final FreightMapper freightMapper;

    private final FreightSearchRepository freightSearchRepository;
    
    private final QuotationMapper quotationMapper;
    
    @Autowired
    private FormsApi formsApi;

	@Autowired
	private TasksApi tasksApi;
	
	@Autowired
	private ProcessInstancesApi processInstancesApi;
	
	@Autowired
	private HistoryApi historyApi;
	
	@Autowired
	private QuotationRepository quotationRepository;

    
    @Autowired
    private ProcessInstancesApi processInstanceApi;
    
    @Autowired
    VehicleRepository vehicleRepository;
    
    @Autowired
    VehicleDTO vehicleDTO;
    
    @Autowired
    VehicleServiceImpl vehicleServiceImpl;

    public FreightServiceImpl(FreightRepository freightRepository, FreightMapper freightMapper, FreightSearchRepository freightSearchRepository, QuotationMapper quotationMapper) {
        this.freightRepository = freightRepository;
        this.freightMapper = freightMapper;
        this.freightSearchRepository = freightSearchRepository;
        this.quotationMapper = quotationMapper;
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
        result.setRequestedStatus(RequestStatus.REQUEST);
        
        Freight fre = freightMapper.toEntity(result);
        freightRepository.save(fre);
        freightSearchRepository.save(fre);
        
        

        return result;
    }

    
	
	
	public String initiate(DefaultInfo freightInfo) {

		ProcessInstanceCreateRequest processInstanceCreateRequest=new ProcessInstanceCreateRequest();
   		List<RestVariable> variables=new ArrayList<RestVariable>();
   		
   		//processInstanceCreateRequest.setProcessDefinitionId("freight:4:45140");
   		processInstanceCreateRequest.setProcessDefinitionId("freight:5:53215");
   		
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

		
		ResponseEntity<DataResponse> response = tasksApi.getTasks("get requests and send quotations", nameLike, null, null, null, null, assignee,
				assigneeLike, null, null, null, null, candidateUser, candidateGroup, candidateGroups, null, null, null,
				processInstanceId, null, null, "freight:5:53215", null, null, null, null, null, createdOn, createdBefore, createdAfter, null,
				null, null, null, null, null, null, null, null, null, null, null, null, /*pageable.getPageNumber()+""*/"0",null, "desc",/* pageable.getPageSize()+""*/"1500");
		List<LinkedHashMap<String, String>> myTasks = (List<LinkedHashMap<String, String>>) response.getBody()
				.getData();
		
		log.info("*****************************////////////////////////////////////freight size**********"+myTasks.size());
		List<FreightDTO> freights = new ArrayList<FreightDTO>();
		
		myTasks.forEach(task -> {
			//FreightDTO openTask = new FreightDTO();
			String taskProcessInstanceId = task.get("processInstanceId");
			//log.info("***************************************************Process Instance id to delete  is  "+taskProcessInstanceId);
			String taskName = task.get("name");
			String taskId = task.get("id");
			log.info("*****************************////////////////////////////////////**********"+taskName);
			log.info("*****************************////////////////////////////////////**********"+taskId);
			log.info("*****************************////////////////////////////////////**********"+taskProcessInstanceId);
			
			FreightDTO freight = getBookingDetails(taskProcessInstanceId);
			freight.setTrackingId(taskProcessInstanceId);
			
			//openTask.setPickupPlaceId(getBookingDetails(taskProcessInstanceId).getPickupPlaceId());
			//openTask.setDestinationPlaceId(getBookingDetails(taskProcessInstanceId).getDestinationPlaceId());
			//openTask.setEstimatedAmount(getBookingDetails(taskProcessInstanceId).getEstimatedAmount());
			//openTask.setCustomerId(getBookingDetails(taskProcessInstanceId).getCustomerId());
			//openTask.setTrackingId(taskProcessInstanceId);
			freights.add(freight);
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

	return historyApi.listHistoricTaskInstances(null, processInstanceId, null, null, "freight:4:45140", null, null, null, null,
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
	public void customerStatus(String taskId, CustomerStatus customerStatus) {
		
		log .info("into ====================customerStatus()");
   		List<RestFormProperty>formProperties=new ArrayList<RestFormProperty>();
   		SubmitFormRequest submitFormRequest = new SubmitFormRequest();
   		submitFormRequest.setAction("completed");
   		submitFormRequest.setTaskId(taskId);
		
   		RestFormProperty statusFormProperty = new RestFormProperty();
   		statusFormProperty.setId("status");
   		statusFormProperty.setName("status");
   		statusFormProperty.setType("String");
   		statusFormProperty.setReadable(true);
   		
   		statusFormProperty.setValue(customerStatus.getStatus());
   		formProperties.add(statusFormProperty);
   		
   		submitFormRequest.setProperties(formProperties);
   		
   		formsApi.submitForm(submitFormRequest);
   		
   		String freightTrackingId = customerStatus.getTrackingId();
   		
   		log.info("---------------------------------------------"+freightTrackingId);
   		Optional<FreightDTO> freight = findByTrackingId(freightTrackingId);
   		log.info("---------------------------------------------"+freight);
   		FreightDTO f=freight.get();
   		log.info("******************************************************"+f);
   		
   		log.info("---------------------------------------------"+freight);
   		
   		log.info("---------------------------------------------"+customerStatus.getStatus());
   		
   		
   		Long quotationId = customerStatus.getQuotationId();
   		log.info("---------------------------------------------"+quotationId);
   		Optional<QuotationDTO> quotation = quotationRepository.findById(quotationId).map(quotationMapper::toDto);;
   		QuotationDTO quotationDto=quotation.get();
   		
   		log.info("******************************************************"+quotationDto);
   		
   		
   		if (customerStatus.getStatus().equals("accept")) {
   			log.info("*********success1");
   		f.setRequestedStatus(RequestStatus.CONFIRM);
   		f.setCompanyId(quotationDto.getCompanyId());
   		f.setOriginalAmount(quotationDto.getAmount());
   		Freight fre = freightMapper.toEntity(f);
   		freightRepository.save(fre);
   		freightSearchRepository.save(fre);
   	
   		
   		
   		}
   		else if (customerStatus.getStatus().equals("return")){
   			log.info("*********success2");
   			f.setRequestedStatus(RequestStatus.REQUEST);
   			Freight fre = freightMapper.toEntity(f);
   	   		freightRepository.save(fre);
   	   		freightSearchRepository.save(fre);
   		}
   		else {
   			log.info("*********success3");
   		}
   		
	}

	@Override
	public Optional<FreightDTO> findByTrackingId(String trackingId) {
		
		Optional<FreightDTO> freight = freightRepository.findByTrackingId(trackingId).map(freightMapper::toDto);
		return freight;
	}
    


	@Override
	public Optional<FreightDTO> convertToDto(Freight freight) {
		return  Optional.of(freight).map(freightMapper::toDto);
		
	}

	@Override
	public List<FreightDTO> convertToDtoList(List<Freight> list) {
		List<FreightDTO> dtos = new ArrayList<>();
		list.forEach(data->{
			dtos.add(freightMapper.toDto(data));
		});
		 
		return dtos;
		
	}

	@Override
	public FreightDTO update(FreightDTO freightDTO) {
		log.debug("<<<<<<<<<< update freight>>>>>>>>",freightDTO);
		if(freightDTO.getId()==null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		  Freight freight = freightMapper.toEntity(freightDTO);
		  freight = freightRepository.save(freight);
		  freightSearchRepository.save(freight);
		  FreightDTO result = freightMapper.toDto(freight);
		return  result;
	}

	@Override
	public Optional<FreightDTO> assumeFreightAndVehicle(FreightDTO freightDTO, Long vehicleId) {
		log.debug("<<<<<<<< assumeFreightAndVehicle >>>>>>>",freightDTO,vehicleId);
		freightDTO.setVehicleId(vehicleId);
		Optional<FreightDTO> opt = Optional.of(update(freightDTO));
		VehicleDTO vehicleDTO=vehicleServiceImpl.findOne(vehicleId).get();
		//vehicleRepository.getOne(vehicleId);
		vehicleDTO.setOccupied(true);
		vehicleServiceImpl.save(vehicleDTO);
		return opt;
	}
	

}
