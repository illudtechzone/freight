/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.illud.freight.client.freight.api;

import com.illud.freight.client.freight.model.VehicleLookUpDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

@Api(value = "VehicleLookUpResource", description = "the VehicleLookUpResource API")
public interface VehicleLookUpResourceApi {

    @ApiOperation(value = "createVehicleLookUp", nickname = "createVehicleLookUpUsingPOST", notes = "", response = VehicleLookUpDTO.class, tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VehicleLookUpDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/vehicle-look-ups",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<VehicleLookUpDTO> createVehicleLookUpUsingPOST(@ApiParam(value = "vehicleLookUpDTO" ,required=true )  @Valid @RequestBody VehicleLookUpDTO vehicleLookUpDTO);


    @ApiOperation(value = "deleteVehicleLookUp", nickname = "deleteVehicleLookUpUsingDELETE", notes = "", tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/vehicle-look-ups/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteVehicleLookUpUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllVehicleLookUps", nickname = "getAllVehicleLookUpsUsingGET", notes = "", response = VehicleLookUpDTO.class, responseContainer = "List", tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VehicleLookUpDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/vehicle-look-ups",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<VehicleLookUpDTO>> getAllVehicleLookUpsUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getVehicleLookUp", nickname = "getVehicleLookUpUsingGET", notes = "", response = VehicleLookUpDTO.class, tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VehicleLookUpDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/vehicle-look-ups/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<VehicleLookUpDTO> getVehicleLookUpUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "searchVehicleLookUps", nickname = "searchVehicleLookUpsUsingGET", notes = "", response = VehicleLookUpDTO.class, responseContainer = "List", tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VehicleLookUpDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/vehicle-look-ups",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<VehicleLookUpDTO>> searchVehicleLookUpsUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateVehicleLookUp", nickname = "updateVehicleLookUpUsingPUT", notes = "", response = VehicleLookUpDTO.class, tags={ "vehicle-look-up-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = VehicleLookUpDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/vehicle-look-ups",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<VehicleLookUpDTO> updateVehicleLookUpUsingPUT(@ApiParam(value = "vehicleLookUpDTO" ,required=true )  @Valid @RequestBody VehicleLookUpDTO vehicleLookUpDTO);

}