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
 * CompanyDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class CompanyDTO   {
  @JsonProperty("address")
  private String address = null;

  @JsonProperty("companyIdpCode")
  private String companyIdpCode = null;

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

  public CompanyDTO address(String address) {
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

  public CompanyDTO companyIdpCode(String companyIdpCode) {
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

  public CompanyDTO email(String email) {
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

  public CompanyDTO id(Long id) {
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

  public CompanyDTO locationAddress(String locationAddress) {
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

  public CompanyDTO locationGeopoint(String locationGeopoint) {
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

  public CompanyDTO name(String name) {
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

  public CompanyDTO phoneNumber(Long phoneNumber) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompanyDTO companyDTO = (CompanyDTO) o;
    return Objects.equals(this.address, companyDTO.address) &&
        Objects.equals(this.companyIdpCode, companyDTO.companyIdpCode) &&
        Objects.equals(this.email, companyDTO.email) &&
        Objects.equals(this.id, companyDTO.id) &&
        Objects.equals(this.locationAddress, companyDTO.locationAddress) &&
        Objects.equals(this.locationGeopoint, companyDTO.locationGeopoint) &&
        Objects.equals(this.name, companyDTO.name) &&
        Objects.equals(this.phoneNumber, companyDTO.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, companyIdpCode, email, id, locationAddress, locationGeopoint, name, phoneNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompanyDTO {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    companyIdpCode: ").append(toIndentedString(companyIdpCode)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    locationAddress: ").append(toIndentedString(locationAddress)).append("\n");
    sb.append("    locationGeopoint: ").append(toIndentedString(locationGeopoint)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
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

