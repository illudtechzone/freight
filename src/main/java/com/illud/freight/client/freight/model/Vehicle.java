package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.illud.freight.client.freight.model.Company;
import com.illud.freight.client.freight.model.VehicleDocument;
import com.illud.freight.client.freight.model.VehicleStaff;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Vehicle
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class Vehicle   {
  @JsonProperty("company")
  private Company company = null;

  @JsonProperty("currentLocationGeopoint")
  private String currentLocationGeopoint = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("occupied")
  private Boolean occupied = null;

  @JsonProperty("registerNo")
  private String registerNo = null;

  @JsonProperty("vehicleDocuments")
  @Valid
  private List<VehicleDocument> vehicleDocuments = null;

  @JsonProperty("vehicleLookupId")
  private Long vehicleLookupId = null;

  @JsonProperty("vehicleStaffs")
  @Valid
  private List<VehicleStaff> vehicleStaffs = null;

  public Vehicle company(Company company) {
    this.company = company;
    return this;
  }

  /**
   * Get company
   * @return company
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Vehicle currentLocationGeopoint(String currentLocationGeopoint) {
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

  public Vehicle id(Long id) {
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

  public Vehicle occupied(Boolean occupied) {
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

  public Vehicle registerNo(String registerNo) {
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

  public Vehicle vehicleDocuments(List<VehicleDocument> vehicleDocuments) {
    this.vehicleDocuments = vehicleDocuments;
    return this;
  }

  public Vehicle addVehicleDocumentsItem(VehicleDocument vehicleDocumentsItem) {
    if (this.vehicleDocuments == null) {
      this.vehicleDocuments = new ArrayList<VehicleDocument>();
    }
    this.vehicleDocuments.add(vehicleDocumentsItem);
    return this;
  }

  /**
   * Get vehicleDocuments
   * @return vehicleDocuments
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<VehicleDocument> getVehicleDocuments() {
    return vehicleDocuments;
  }

  public void setVehicleDocuments(List<VehicleDocument> vehicleDocuments) {
    this.vehicleDocuments = vehicleDocuments;
  }

  public Vehicle vehicleLookupId(Long vehicleLookupId) {
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

  public Vehicle vehicleStaffs(List<VehicleStaff> vehicleStaffs) {
    this.vehicleStaffs = vehicleStaffs;
    return this;
  }

  public Vehicle addVehicleStaffsItem(VehicleStaff vehicleStaffsItem) {
    if (this.vehicleStaffs == null) {
      this.vehicleStaffs = new ArrayList<VehicleStaff>();
    }
    this.vehicleStaffs.add(vehicleStaffsItem);
    return this;
  }

  /**
   * Get vehicleStaffs
   * @return vehicleStaffs
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<VehicleStaff> getVehicleStaffs() {
    return vehicleStaffs;
  }

  public void setVehicleStaffs(List<VehicleStaff> vehicleStaffs) {
    this.vehicleStaffs = vehicleStaffs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(this.company, vehicle.company) &&
        Objects.equals(this.currentLocationGeopoint, vehicle.currentLocationGeopoint) &&
        Objects.equals(this.id, vehicle.id) &&
        Objects.equals(this.occupied, vehicle.occupied) &&
        Objects.equals(this.registerNo, vehicle.registerNo) &&
        Objects.equals(this.vehicleDocuments, vehicle.vehicleDocuments) &&
        Objects.equals(this.vehicleLookupId, vehicle.vehicleLookupId) &&
        Objects.equals(this.vehicleStaffs, vehicle.vehicleStaffs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(company, currentLocationGeopoint, id, occupied, registerNo, vehicleDocuments, vehicleLookupId, vehicleStaffs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    currentLocationGeopoint: ").append(toIndentedString(currentLocationGeopoint)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    occupied: ").append(toIndentedString(occupied)).append("\n");
    sb.append("    registerNo: ").append(toIndentedString(registerNo)).append("\n");
    sb.append("    vehicleDocuments: ").append(toIndentedString(vehicleDocuments)).append("\n");
    sb.append("    vehicleLookupId: ").append(toIndentedString(vehicleLookupId)).append("\n");
    sb.append("    vehicleStaffs: ").append(toIndentedString(vehicleStaffs)).append("\n");
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

