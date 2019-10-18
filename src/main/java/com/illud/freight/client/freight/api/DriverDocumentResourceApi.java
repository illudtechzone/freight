/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.illud.freight.client.freight.api;

import com.illud.freight.client.freight.model.DriverDocumentDTO;
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

@Api(value = "DriverDocumentResource", description = "the DriverDocumentResource API")
public interface DriverDocumentResourceApi {

    @ApiOperation(value = "createDriverDocument", nickname = "createDriverDocumentUsingPOST", notes = "", response = DriverDocumentDTO.class, tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DriverDocumentDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/driver-documents",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<DriverDocumentDTO> createDriverDocumentUsingPOST(@ApiParam(value = "driverDocumentDTO" ,required=true )  @Valid @RequestBody DriverDocumentDTO driverDocumentDTO);


    @ApiOperation(value = "deleteDriverDocument", nickname = "deleteDriverDocumentUsingDELETE", notes = "", tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/driver-documents/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteDriverDocumentUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllDriverDocuments", nickname = "getAllDriverDocumentsUsingGET", notes = "", response = DriverDocumentDTO.class, responseContainer = "List", tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DriverDocumentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/driver-documents",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<DriverDocumentDTO>> getAllDriverDocumentsUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getDriverDocument", nickname = "getDriverDocumentUsingGET", notes = "", response = DriverDocumentDTO.class, tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DriverDocumentDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/driver-documents/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<DriverDocumentDTO> getDriverDocumentUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "searchDriverDocuments", nickname = "searchDriverDocumentsUsingGET", notes = "", response = DriverDocumentDTO.class, responseContainer = "List", tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DriverDocumentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/driver-documents",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<DriverDocumentDTO>> searchDriverDocumentsUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateDriverDocument", nickname = "updateDriverDocumentUsingPUT", notes = "", response = DriverDocumentDTO.class, tags={ "driver-document-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = DriverDocumentDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/driver-documents",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<DriverDocumentDTO> updateDriverDocumentUsingPUT(@ApiParam(value = "driverDocumentDTO" ,required=true )  @Valid @RequestBody DriverDocumentDTO driverDocumentDTO);

}
