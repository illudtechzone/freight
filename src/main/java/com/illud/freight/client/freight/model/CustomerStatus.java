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
 * CustomerStatus
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class CustomerStatus   {
  @JsonProperty("quotationId")
  private Long quotationId = null;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("trackingId")
  private String trackingId = null;

  public CustomerStatus quotationId(Long quotationId) {
    this.quotationId = quotationId;
    return this;
  }

  /**
   * Get quotationId
   * @return quotationId
  **/
  @ApiModelProperty(value = "")


  public Long getQuotationId() {
    return quotationId;
  }

  public void setQuotationId(Long quotationId) {
    this.quotationId = quotationId;
  }

  public CustomerStatus status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CustomerStatus trackingId(String trackingId) {
    this.trackingId = trackingId;
    return this;
  }

  /**
   * Get trackingId
   * @return trackingId
  **/
  @ApiModelProperty(value = "")


  public String getTrackingId() {
    return trackingId;
  }

  public void setTrackingId(String trackingId) {
    this.trackingId = trackingId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerStatus customerStatus = (CustomerStatus) o;
    return Objects.equals(this.quotationId, customerStatus.quotationId) &&
        Objects.equals(this.status, customerStatus.status) &&
        Objects.equals(this.trackingId, customerStatus.trackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quotationId, status, trackingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerStatus {\n");
    
    sb.append("    quotationId: ").append(toIndentedString(quotationId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
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

