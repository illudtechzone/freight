package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.illud.freight.client.freight.model.Driver;
import com.illud.freight.client.freight.model.Staff;
import com.illud.freight.client.freight.model.Vehicle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Company
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class Company   {
  @JsonProperty("address")
  private String address = null;

  @JsonProperty("companyIdpCode")
  private String companyIdpCode = null;

  @JsonProperty("drivers")
  @Valid
  private List<Driver> drivers = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("locationAddress")
  private String locationAddress = null;

  @JsonProperty("locationGeopoint")
  private String locationGeopoint = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phoneNumber")
  private Long phoneNumber = null;

  @JsonProperty("staffs")
  @Valid
  private List<Staff> staffs = null;

  @JsonProperty("vehicles")
  @Valid
  private List<Vehicle> vehicles = null;

  public Company address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Company companyIdpCode(String companyIdpCode) {
    this.companyIdpCode = companyIdpCode;
    return this;
  }

  /**
   * Get companyIdpCode
   * @return companyIdpCode
  **/
  @ApiModelProperty(value = "")


  public String getCompanyIdpCode() {
    return companyIdpCode;
  }

  public void setCompanyIdpCode(String companyIdpCode) {
    this.companyIdpCode = companyIdpCode;
  }

  public Company drivers(List<Driver> drivers) {
    this.drivers = drivers;
    return this;
  }

  public Company addDriversItem(Driver driversItem) {
    if (this.drivers == null) {
      this.drivers = new ArrayList<Driver>();
    }
    this.drivers.add(driversItem);
    return this;
  }

  /**
   * Get drivers
   * @return drivers
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(List<Driver> drivers) {
    this.drivers = drivers;
  }

  public Company email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(value = "")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Company id(Long id) {
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

  public Company locationAddress(String locationAddress) {
    this.locationAddress = locationAddress;
    return this;
  }

  /**
   * Get locationAddress
   * @return locationAddress
  **/
  @ApiModelProperty(value = "")


  public String getLocationAddress() {
    return locationAddress;
  }

  public void setLocationAddress(String locationAddress) {
    this.locationAddress = locationAddress;
  }

  public Company locationGeopoint(String locationGeopoint) {
    this.locationGeopoint = locationGeopoint;
    return this;
  }

  /**
   * Get locationGeopoint
   * @return locationGeopoint
  **/
  @ApiModelProperty(value = "")


  public String getLocationGeopoint() {
    return locationGeopoint;
  }

  public void setLocationGeopoint(String locationGeopoint) {
    this.locationGeopoint = locationGeopoint;
  }

  public Company name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Company phoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
  **/
  @ApiModelProperty(value = "")


  public Long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(Long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Company staffs(List<Staff> staffs) {
    this.staffs = staffs;
    return this;
  }

  public Company addStaffsItem(Staff staffsItem) {
    if (this.staffs == null) {
      this.staffs = new ArrayList<Staff>();
    }
    this.staffs.add(staffsItem);
    return this;
  }

  /**
   * Get staffs
   * @return staffs
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Staff> getStaffs() {
    return staffs;
  }

  public void setStaffs(List<Staff> staffs) {
    this.staffs = staffs;
  }

  public Company vehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
    return this;
  }

  public Company addVehiclesItem(Vehicle vehiclesItem) {
    if (this.vehicles == null) {
      this.vehicles = new ArrayList<Vehicle>();
    }
    this.vehicles.add(vehiclesItem);
    return this;
  }

  /**
   * Get vehicles
   * @return vehicles
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Company company = (Company) o;
    return Objects.equals(this.address, company.address) &&
        Objects.equals(this.companyIdpCode, company.companyIdpCode) &&
        Objects.equals(this.drivers, company.drivers) &&
        Objects.equals(this.email, company.email) &&
        Objects.equals(this.id, company.id) &&
        Objects.equals(this.locationAddress, company.locationAddress) &&
        Objects.equals(this.locationGeopoint, company.locationGeopoint) &&
        Objects.equals(this.name, company.name) &&
        Objects.equals(this.phoneNumber, company.phoneNumber) &&
        Objects.equals(this.staffs, company.staffs) &&
        Objects.equals(this.vehicles, company.vehicles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, companyIdpCode, drivers, email, id, locationAddress, locationGeopoint, name, phoneNumber, staffs, vehicles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Company {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    companyIdpCode: ").append(toIndentedString(companyIdpCode)).append("\n");
    sb.append("    drivers: ").append(toIndentedString(drivers)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locationAddress: ").append(toIndentedString(locationAddress)).append("\n");
    sb.append("    locationGeopoint: ").append(toIndentedString(locationGeopoint)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    staffs: ").append(toIndentedString(staffs)).append("\n");
    sb.append("    vehicles: ").append(toIndentedString(vehicles)).append("\n");
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

