package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VehicleDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class VehicleDTO   {
  @JsonProperty("companyId")
  private Long companyId = null;

  @JsonProperty("currentLocationGeopoint")
  private String currentLocationGeopoint = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("occupied")
  private Boolean occupied = null;

  @JsonProperty("registerNo")
  private String registerNo = null;

  @JsonProperty("vehicleLookupId")
  private Long vehicleLookupId = null;

  public VehicleDTO companyId(Long companyId) {
    this.companyId = companyId;
    return this;
  }

  /**
   * Get companyId
   * @return companyId
  **/
  @ApiModelProperty(value = "")


  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public VehicleDTO currentLocationGeopoint(String currentLocationGeopoint) {
    this.currentLocationGeopoint = currentLocationGeopoint;
    return this;
  }

  /**
   * Get currentLocationGeopoint
   * @return currentLocationGeopoint
  **/
  @ApiModelProperty(value = "")


  public String getCurrentLocationGeopoint() {
    return currentLocationGeopoint;
  }

  public void setCurrentLocationGeopoint(String currentLocationGeopoint) {
    this.currentLocationGeopoint = currentLocationGeopoint;
  }

  public VehicleDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public VehicleDTO occupied(Boolean occupied) {
    this.occupied = occupied;
    return this;
  }

  /**
   * Get occupied
   * @return occupied
  **/
  @ApiModelProperty(value = "")


  public Boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(Boolean occupied) {
    this.occupied = occupied;
  }

  public VehicleDTO registerNo(String registerNo) {
    this.registerNo = registerNo;
    return this;
  }

  /**
   * Get registerNo
   * @return registerNo
  **/
  @ApiModelProperty(value = "")


  public String getRegisterNo() {
    return registerNo;
  }

  public void setRegisterNo(String registerNo) {
    this.registerNo = registerNo;
  }

  public VehicleDTO vehicleLookupId(Long vehicleLookupId) {
    this.vehicleLookupId = vehicleLookupId;
    return this;
  }

  /**
   * Get vehicleLookupId
   * @return vehicleLookupId
  **/
  @ApiModelProperty(value = "")


  public Long getVehicleLookupId() {
    return vehicleLookupId;
  }

  public void setVehicleLookupId(Long vehicleLookupId) {
    this.vehicleLookupId = vehicleLookupId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleDTO vehicleDTO = (VehicleDTO) o;
    return Objects.equals(this.companyId, vehicleDTO.companyId) &&
        Objects.equals(this.currentLocationGeopoint, vehicleDTO.currentLocationGeopoint) &&
        Objects.equals(this.id, vehicleDTO.id) &&
        Objects.equals(this.occupied, vehicleDTO.occupied) &&
        Objects.equals(this.registerNo, vehicleDTO.registerNo) &&
        Objects.equals(this.vehicleLookupId, vehicleDTO.vehicleLookupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, currentLocationGeopoint, id, occupied, registerNo, vehicleLookupId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VehicleDTO {\n");
    
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    currentLocationGeopoint: ").append(toIndentedString(currentLocationGeopoint)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    occupied: ").append(toIndentedString(occupied)).append("\n");
    sb.append("    registerNo: ").append(toIndentedString(registerNo)).append("\n");
    sb.append("    vehicleLookupId: ").append(toIndentedString(vehicleLookupId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

