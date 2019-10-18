package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QuotationDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class QuotationDTO   {
  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("companyId")
  private Long companyId = null;

  @JsonProperty("deliveryDate")
  private LocalDate deliveryDate = null;

  @JsonProperty("freightId")
  private Long freightId = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("vehicleId")
  private Long vehicleId = null;

  public QuotationDTO amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(value = "")


  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public QuotationDTO companyId(Long companyId) {
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

  public QuotationDTO deliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
    return this;
  }

  /**
   * Get deliveryDate
   * @return deliveryDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public QuotationDTO freightId(Long freightId) {
    this.freightId = freightId;
    return this;
  }

  /**
   * Get freightId
   * @return freightId
  **/
  @ApiModelProperty(value = "")


  public Long getFreightId() {
    return freightId;
  }

  public void setFreightId(Long freightId) {
    this.freightId = freightId;
  }

  public QuotationDTO id(Long id) {
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

  public QuotationDTO vehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
    return this;
  }

  /**
   * Get vehicleId
   * @return vehicleId
  **/
  @ApiModelProperty(value = "")


  public Long getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QuotationDTO quotationDTO = (QuotationDTO) o;
    return Objects.equals(this.amount, quotationDTO.amount) &&
        Objects.equals(this.companyId, quotationDTO.companyId) &&
        Objects.equals(this.deliveryDate, quotationDTO.deliveryDate) &&
        Objects.equals(this.freightId, quotationDTO.freightId) &&
        Objects.equals(this.id, quotationDTO.id) &&
        Objects.equals(this.vehicleId, quotationDTO.vehicleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, companyId, deliveryDate, freightId, id, vehicleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QuotationDTO {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
    sb.append("    freightId: ").append(toIndentedString(freightId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
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

