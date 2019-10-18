/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (3.0.0-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.illud.freight.client.freight.api;

import java.util.List;
import com.illud.freight.client.freight.model.Quotation;
import com.illud.freight.client.freight.model.QuotationDTO;
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

@Api(value = "QuotationResource", description = "the QuotationResource API")
public interface QuotationResourceApi {

    @ApiOperation(value = "createQuotation", nickname = "createQuotationUsingPOST", notes = "", response = QuotationDTO.class, tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/quotations",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.POST)
    ResponseEntity<QuotationDTO> createQuotationUsingPOST(@ApiParam(value = "quotationDTO" ,required=true )  @Valid @RequestBody QuotationDTO quotationDTO);


    @ApiOperation(value = "createQuotationsDtoList", nickname = "createQuotationsDtoListUsingGET", notes = "", response = QuotationDTO.class, responseContainer = "List", tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/convertdtolist",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<QuotationDTO>> createQuotationsDtoListUsingGET(@ApiParam(value = "quotations" ,required=true )  @Valid @RequestBody List<Quotation> quotation);


    @ApiOperation(value = "createQuotationsDto", nickname = "createQuotationsDtoUsingGET", notes = "", response = QuotationDTO.class, tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/convertdto",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<QuotationDTO> createQuotationsDtoUsingGET(@ApiParam(value = "quotation" ,required=true )  @Valid @RequestBody Quotation quotation);


    @ApiOperation(value = "deleteQuotation", nickname = "deleteQuotationUsingDELETE", notes = "", tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden") })
    @RequestMapping(value = "/api/quotations/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteQuotationUsingDELETE(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "getAllQuotations", nickname = "getAllQuotationsUsingGET", notes = "", response = QuotationDTO.class, responseContainer = "List", tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/quotations",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<QuotationDTO>> getAllQuotationsUsingGET(@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "getQuotation", nickname = "getQuotationUsingGET", notes = "", response = QuotationDTO.class, tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/quotations/{id}",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<QuotationDTO> getQuotationUsingGET(@ApiParam(value = "id",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "searchQuotations", nickname = "searchQuotationsUsingGET", notes = "", response = QuotationDTO.class, responseContainer = "List", tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/_search/quotations",
        produces = "*/*", 
        method = RequestMethod.GET)
    ResponseEntity<List<QuotationDTO>> searchQuotationsUsingGET(@NotNull @ApiParam(value = "query", required = true) @Valid @RequestParam(value = "query", required = true) String query,@ApiParam(value = "Page number of the requested page") @Valid @RequestParam(value = "page", required = false) Integer page,@ApiParam(value = "Size of a page") @Valid @RequestParam(value = "size", required = false) Integer size,@ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.") @Valid @RequestParam(value = "sort", required = false) List<String> sort);


    @ApiOperation(value = "updateQuotation", nickname = "updateQuotationUsingPUT", notes = "", response = QuotationDTO.class, tags={ "quotation-resource", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = QuotationDTO.class),
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/api/quotations",
        produces = "*/*", 
        consumes = "application/json",
        method = RequestMethod.PUT)
    ResponseEntity<QuotationDTO> updateQuotationUsingPUT(@ApiParam(value = "quotationDTO" ,required=true )  @Valid @RequestBody QuotationDTO quotationDTO);

}
