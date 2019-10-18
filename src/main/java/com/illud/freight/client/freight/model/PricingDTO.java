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
 * PricingDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class PricingDTO   {
  @JsonProperty("additionalDescription")
  private String additionalDescription = null;

  @JsonProperty("basicRate")
  private Double basicRate = null;

  @JsonProperty("freeWaitingInMins")
  private Long freeWaitingInMins = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("nightSurchargeInMins")
  private Double nightSurchargeInMins = null;

  /**
   * Gets or Sets ratePlan
   */
  public enum RatePlanEnum {
    NORMAL("NORMAL"),
    
    FULL_DAY("FULL_DAY");

    private String value;

    RatePlanEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RatePlanEnum fromValue(String text) {
      for (RatePlanEnum b : RatePlanEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("ratePlan")
  private RatePlanEnum ratePlan = null;

  @JsonProperty("waitingChargeInMins")
  private Double waitingChargeInMins = null;

  public PricingDTO additionalDescription(String additionalDescription) {
    this.additionalDescription = additionalDescription;
    return this;
  }

  /**
   * Get additionalDescription
   * @return additionalDescription
  **/
  @ApiModelProperty(value = "")


  public String getAdditionalDescription() {
    return additionalDescription;
  }

  public void setAdditionalDescription(String additionalDescription) {
    this.additionalDescription = additionalDescription;
  }

  public PricingDTO basicRate(Double basicRate) {
    this.basicRate = basicRate;
    return this;
  }

  /**
   * Get basicRate
   * @return basicRate
  **/
  @ApiModelProperty(value = "")


  public Double getBasicRate() {
    return basicRate;
  }

  public void setBasicRate(Double basicRate) {
    this.basicRate = basicRate;
  }

  public PricingDTO freeWaitingInMins(Long freeWaitingInMins) {
    this.freeWaitingInMins = freeWaitingInMins;
    return this;
  }

  /**
   * Get freeWaitingInMins
   * @return freeWaitingInMins
  **/
  @ApiModelProperty(value = "")


  public Long getFreeWaitingInMins() {
    return freeWaitingInMins;
  }

  public void setFreeWaitingInMins(Long freeWaitingInMins) {
    this.freeWaitingInMins = freeWaitingInMins;
  }

  public PricingDTO id(Long id) {
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

  public PricingDTO nightSurchargeInMins(Double nightSurchargeInMins) {
    this.nightSurchargeInMins = nightSurchargeInMins;
    return this;
  }

  /**
   * Get nightSurchargeInMins
   * @return nightSurchargeInMins
  **/
  @ApiModelProperty(value = "")


  public Double getNightSurchargeInMins() {
    return nightSurchargeInMins;
  }

  public void setNightSurchargeInMins(Double nightSurchargeInMins) {
    this.nightSurchargeInMins = nightSurchargeInMins;
  }

  public PricingDTO ratePlan(RatePlanEnum ratePlan) {
    this.ratePlan = ratePlan;
    return this;
  }

  /**
   * Get ratePlan
   * @return ratePlan
  **/
  @ApiModelProperty(value = "")


  public RatePlanEnum getRatePlan() {
    return ratePlan;
  }

  public void setRatePlan(RatePlanEnum ratePlan) {
    this.ratePlan = ratePlan;
  }

  public PricingDTO waitingChargeInMins(Double waitingChargeInMins) {
    this.waitingChargeInMins = waitingChargeInMins;
    return this;
  }

  /**
   * Get waitingChargeInMins
   * @return waitingChargeInMins
  **/
  @ApiModelProperty(value = "")


  public Double getWaitingChargeInMins() {
    return waitingChargeInMins;
  }

  public void setWaitingChargeInMins(Double waitingChargeInMins) {
    this.waitingChargeInMins = waitingChargeInMins;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingDTO pricingDTO = (PricingDTO) o;
    return Objects.equals(this.additionalDescription, pricingDTO.additionalDescription) &&
        Objects.equals(this.basicRate, pricingDTO.basicRate) &&
        Objects.equals(this.freeWaitingInMins, pricingDTO.freeWaitingInMins) &&
        Objects.equals(this.id, pricingDTO.id) &&
        Objects.equals(this.nightSurchargeInMins, pricingDTO.nightSurchargeInMins) &&
        Objects.equals(this.ratePlan, pricingDTO.ratePlan) &&
        Objects.equals(this.waitingChargeInMins, pricingDTO.waitingChargeInMins);
  }

  @Override
  public int hashCode() {
    return Objects.hash(additionalDescription, basicRate, freeWaitingInMins, id, nightSurchargeInMins, ratePlan, waitingChargeInMins);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingDTO {\n");
    
    sb.append("    additionalDescription: ").append(toIndentedString(additionalDescription)).append("\n");
    sb.append("    basicRate: ").append(toIndentedString(basicRate)).append("\n");
    sb.append("    freeWaitingInMins: ").append(toIndentedString(freeWaitingInMins)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nightSurchargeInMins: ").append(toIndentedString(nightSurchargeInMins)).append("\n");
    sb.append("    ratePlan: ").append(toIndentedString(ratePlan)).append("\n");
    sb.append("    waitingChargeInMins: ").append(toIndentedString(waitingChargeInMins)).append("\n");
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

