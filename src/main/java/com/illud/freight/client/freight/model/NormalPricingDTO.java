package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NormalPricingDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class NormalPricingDTO   {
  @JsonProperty("endLimitInKms")
  private Double endLimitInKms = null;

  @JsonProperty("id")
  private Long id = null;

  /**
   * Gets or Sets limitStatus
   */
  public enum LimitStatusEnum {
    BETWEEN("BETWEEN"),
    
    ABOVE("ABOVE");

    private String value;

    LimitStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LimitStatusEnum fromValue(String text) {
      for (LimitStatusEnum b : LimitStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("limitStatus")
  private LimitStatusEnum limitStatus = null;

  @JsonProperty("pricingId")
  private Long pricingId = null;

  @JsonProperty("ratePerKm")
  private Double ratePerKm = null;

  @JsonProperty("startLimitInKms")
  private Double startLimitInKms = null;

  public NormalPricingDTO endLimitInKms(Double endLimitInKms) {
    this.endLimitInKms = endLimitInKms;
    return this;
  }

  /**
   * Get endLimitInKms
   * @return endLimitInKms
  **/
  @ApiModelProperty(value = "")


  public Double getEndLimitInKms() {
    return endLimitInKms;
  }

  public void setEndLimitInKms(Double endLimitInKms) {
    this.endLimitInKms = endLimitInKms;
  }

  public NormalPricingDTO id(Long id) {
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

  public NormalPricingDTO limitStatus(LimitStatusEnum limitStatus) {
    this.limitStatus = limitStatus;
    return this;
  }

  /**
   * Get limitStatus
   * @return limitStatus
  **/
  @ApiModelProperty(value = "")


  public LimitStatusEnum getLimitStatus() {
    return limitStatus;
  }

  public void setLimitStatus(LimitStatusEnum limitStatus) {
    this.limitStatus = limitStatus;
  }

  public NormalPricingDTO pricingId(Long pricingId) {
    this.pricingId = pricingId;
    return this;
  }

  /**
   * Get pricingId
   * @return pricingId
  **/
  @ApiModelProperty(value = "")


  public Long getPricingId() {
    return pricingId;
  }

  public void setPricingId(Long pricingId) {
    this.pricingId = pricingId;
  }

  public NormalPricingDTO ratePerKm(Double ratePerKm) {
    this.ratePerKm = ratePerKm;
    return this;
  }

  /**
   * Get ratePerKm
   * @return ratePerKm
  **/
  @ApiModelProperty(value = "")


  public Double getRatePerKm() {
    return ratePerKm;
  }

  public void setRatePerKm(Double ratePerKm) {
    this.ratePerKm = ratePerKm;
  }

  public NormalPricingDTO startLimitInKms(Double startLimitInKms) {
    this.startLimitInKms = startLimitInKms;
    return this;
  }

  /**
   * Get startLimitInKms
   * @return startLimitInKms
  **/
  @ApiModelProperty(value = "")


  public Double getStartLimitInKms() {
    return startLimitInKms;
  }

  public void setStartLimitInKms(Double startLimitInKms) {
    this.startLimitInKms = startLimitInKms;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NormalPricingDTO normalPricingDTO = (NormalPricingDTO) o;
    return Objects.equals(this.endLimitInKms, normalPricingDTO.endLimitInKms) &&
        Objects.equals(this.id, normalPricingDTO.id) &&
        Objects.equals(this.limitStatus, normalPricingDTO.limitStatus) &&
        Objects.equals(this.pricingId, normalPricingDTO.pricingId) &&
        Objects.equals(this.ratePerKm, normalPricingDTO.ratePerKm) &&
        Objects.equals(this.startLimitInKms, normalPricingDTO.startLimitInKms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endLimitInKms, id, limitStatus, pricingId, ratePerKm, startLimitInKms);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NormalPricingDTO {\n");
    
    sb.append("    endLimitInKms: ").append(toIndentedString(endLimitInKms)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    limitStatus: ").append(toIndentedString(limitStatus)).append("\n");
    sb.append("    pricingId: ").append(toIndentedString(pricingId)).append("\n");
    sb.append("    ratePerKm: ").append(toIndentedString(ratePerKm)).append("\n");
    sb.append("    startLimitInKms: ").append(toIndentedString(startLimitInKms)).append("\n");
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

