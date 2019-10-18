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
 * StaffDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class StaffDTO   {
  @JsonProperty("companyId")
  private Long companyId = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("phoneNumber")
  private Long phoneNumber = null;

  @JsonProperty("staffIdpCode")
  private String staffIdpCode = null;

  @JsonProperty("type")
  private String type = null;

  public StaffDTO companyId(Long companyId) {
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

  public StaffDTO email(String email) {
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

  public StaffDTO id(Long id) {
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

  public StaffDTO name(String name) {
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

  public StaffDTO phoneNumber(Long phoneNumber) {
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

  public StaffDTO staffIdpCode(String staffIdpCode) {
    this.staffIdpCode = staffIdpCode;
    return this;
  }

  /**
   * Get staffIdpCode
   * @return staffIdpCode
  **/
  @ApiModelProperty(value = "")


  public String getStaffIdpCode() {
    return staffIdpCode;
  }

  public void setStaffIdpCode(String staffIdpCode) {
    this.staffIdpCode = staffIdpCode;
  }

  public StaffDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StaffDTO staffDTO = (StaffDTO) o;
    return Objects.equals(this.companyId, staffDTO.companyId) &&
        Objects.equals(this.email, staffDTO.email) &&
        Objects.equals(this.id, staffDTO.id) &&
        Objects.equals(this.name, staffDTO.name) &&
        Objects.equals(this.phoneNumber, staffDTO.phoneNumber) &&
        Objects.equals(this.staffIdpCode, staffDTO.staffIdpCode) &&
        Objects.equals(this.type, staffDTO.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyId, email, id, name, phoneNumber, staffIdpCode, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StaffDTO {\n");
    
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    staffIdpCode: ").append(toIndentedString(staffIdpCode)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

