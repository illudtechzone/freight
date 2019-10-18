package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DriverDocumentDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class DriverDocumentDTO   {
  @JsonProperty("document")
  private byte[] document = null;

  @JsonProperty("documentContentType")
  private String documentContentType = null;

  @JsonProperty("documentType")
  private String documentType = null;

  @JsonProperty("driverId")
  private Long driverId = null;

  @JsonProperty("expiryDate")
  private LocalDate expiryDate = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("isExpired")
  private Boolean isExpired = null;

  @JsonProperty("uploadTime")
  private OffsetDateTime uploadTime = null;

  @JsonProperty("validataionStartDate")
  private LocalDate validataionStartDate = null;

  public DriverDocumentDTO document(byte[] document) {
    this.document = document;
    return this;
  }

  /**
   * Get document
   * @return document
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getDocument() {
    return document;
  }

  public void setDocument(byte[] document) {
    this.document = document;
  }

  public DriverDocumentDTO documentContentType(String documentContentType) {
    this.documentContentType = documentContentType;
    return this;
  }

  /**
   * Get documentContentType
   * @return documentContentType
  **/
  @ApiModelProperty(value = "")


  public String getDocumentContentType() {
    return documentContentType;
  }

  public void setDocumentContentType(String documentContentType) {
    this.documentContentType = documentContentType;
  }

  public DriverDocumentDTO documentType(String documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
  **/
  @ApiModelProperty(value = "")


  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  public DriverDocumentDTO driverId(Long driverId) {
    this.driverId = driverId;
    return this;
  }

  /**
   * Get driverId
   * @return driverId
  **/
  @ApiModelProperty(value = "")


  public Long getDriverId() {
    return driverId;
  }

  public void setDriverId(Long driverId) {
    this.driverId = driverId;
  }

  public DriverDocumentDTO expiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  /**
   * Get expiryDate
   * @return expiryDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
  }

  public DriverDocumentDTO id(Long id) {
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

  public DriverDocumentDTO isExpired(Boolean isExpired) {
    this.isExpired = isExpired;
    return this;
  }

  /**
   * Get isExpired
   * @return isExpired
  **/
  @ApiModelProperty(value = "")


  public Boolean isIsExpired() {
    return isExpired;
  }

  public void setIsExpired(Boolean isExpired) {
    this.isExpired = isExpired;
  }

  public DriverDocumentDTO uploadTime(OffsetDateTime uploadTime) {
    this.uploadTime = uploadTime;
    return this;
  }

  /**
   * Get uploadTime
   * @return uploadTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getUploadTime() {
    return uploadTime;
  }

  public void setUploadTime(OffsetDateTime uploadTime) {
    this.uploadTime = uploadTime;
  }

  public DriverDocumentDTO validataionStartDate(LocalDate validataionStartDate) {
    this.validataionStartDate = validataionStartDate;
    return this;
  }

  /**
   * Get validataionStartDate
   * @return validataionStartDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getValidataionStartDate() {
    return validataionStartDate;
  }

  public void setValidataionStartDate(LocalDate validataionStartDate) {
    this.validataionStartDate = validataionStartDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverDocumentDTO driverDocumentDTO = (DriverDocumentDTO) o;
    return Objects.equals(this.document, driverDocumentDTO.document) &&
        Objects.equals(this.documentContentType, driverDocumentDTO.documentContentType) &&
        Objects.equals(this.documentType, driverDocumentDTO.documentType) &&
        Objects.equals(this.driverId, driverDocumentDTO.driverId) &&
        Objects.equals(this.expiryDate, driverDocumentDTO.expiryDate) &&
        Objects.equals(this.id, driverDocumentDTO.id) &&
        Objects.equals(this.isExpired, driverDocumentDTO.isExpired) &&
        Objects.equals(this.uploadTime, driverDocumentDTO.uploadTime) &&
        Objects.equals(this.validataionStartDate, driverDocumentDTO.validataionStartDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(document, documentContentType, documentType, driverId, expiryDate, id, isExpired, uploadTime, validataionStartDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DriverDocumentDTO {\n");
    
    sb.append("    document: ").append(toIndentedString(document)).append("\n");
    sb.append("    documentContentType: ").append(toIndentedString(documentContentType)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    driverId: ").append(toIndentedString(driverId)).append("\n");
    sb.append("    expiryDate: ").append(toIndentedString(expiryDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    isExpired: ").append(toIndentedString(isExpired)).append("\n");
    sb.append("    uploadTime: ").append(toIndentedString(uploadTime)).append("\n");
    sb.append("    validataionStartDate: ").append(toIndentedString(validataionStartDate)).append("\n");
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

