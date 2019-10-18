package com.illud.freight.client.freight.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FreightDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class FreightDTO   {
  /**
   * Gets or Sets acceptedStatus
   */
  public enum AcceptedStatusEnum {
    START("START"),
    
    COMPLETE("COMPLETE"),
    
    NOT_STARTED("NOT_STARTED");

    private String value;

    AcceptedStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AcceptedStatusEnum fromValue(String text) {
      for (AcceptedStatusEnum b : AcceptedStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("acceptedStatus")
  private AcceptedStatusEnum acceptedStatus = null;

  @JsonProperty("companyId")
  private Long companyId = null;

  @JsonProperty("createdTime")
  private OffsetDateTime createdTime = null;

  @JsonProperty("customerId")
  private Long customerId = null;

  @JsonProperty("deliveryDate")
  private LocalDate deliveryDate = null;

  @JsonProperty("destinationAddress")
  private String destinationAddress = null;

  @JsonProperty("destinationGeopoint")
  private String destinationGeopoint = null;

  @JsonProperty("destinationPlaceId")
  private String destinationPlaceId = null;

  @JsonProperty("destionationTime")
  private OffsetDateTime destionationTime = null;

  @JsonProperty("distance")
  private Long distance = null;

  @JsonProperty("estimatedAmount")
  private Double estimatedAmount = null;

  @JsonProperty("fragile")
  private Boolean fragile = null;

  @JsonProperty("height")
  private Double height = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("length")
  private Double length = null;

  @JsonProperty("originalAmount")
  private Double originalAmount = null;

  @JsonProperty("pickupAddress")
  private String pickupAddress = null;

  @JsonProperty("pickupGeopoint")
  private String pickupGeopoint = null;

  @JsonProperty("pickupPlaceId")
  private String pickupPlaceId = null;

  /**
   * Gets or Sets requestedStatus
   */
  public enum RequestedStatusEnum {
    REQUEST("REQUEST"),
    
    CONFIRM("CONFIRM"),
    
    REJECT("REJECT");

    private String value;

    RequestedStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RequestedStatusEnum fromValue(String text) {
      for (RequestedStatusEnum b : RequestedStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("requestedStatus")
  private RequestedStatusEnum requestedStatus = null;

  @JsonProperty("startTime")
  private OffsetDateTime startTime = null;

  @JsonProperty("trackingId")
  private String trackingId = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("vehicleId")
  private Long vehicleId = null;

  @JsonProperty("weight")
  private Double weight = null;

  @JsonProperty("width")
  private Double width = null;

  public FreightDTO acceptedStatus(AcceptedStatusEnum acceptedStatus) {
    this.acceptedStatus = acceptedStatus;
    return this;
  }

  /**
   * Get acceptedStatus
   * @return acceptedStatus
  **/
  @ApiModelProperty(value = "")


  public AcceptedStatusEnum getAcceptedStatus() {
    return acceptedStatus;
  }

  public void setAcceptedStatus(AcceptedStatusEnum acceptedStatus) {
    this.acceptedStatus = acceptedStatus;
  }

  public FreightDTO companyId(Long companyId) {
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

  public FreightDTO createdTime(OffsetDateTime createdTime) {
    this.createdTime = createdTime;
    return this;
  }

  /**
   * Get createdTime
   * @return createdTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(OffsetDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public FreightDTO customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  **/
  @ApiModelProperty(value = "")


  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public FreightDTO deliveryDate(LocalDate deliveryDate) {
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

  public FreightDTO destinationAddress(String destinationAddress) {
    this.destinationAddress = destinationAddress;
    return this;
  }

  /**
   * Get destinationAddress
   * @return destinationAddress
  **/
  @ApiModelProperty(value = "")


  public String getDestinationAddress() {
    return destinationAddress;
  }

  public void setDestinationAddress(String destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public FreightDTO destinationGeopoint(String destinationGeopoint) {
    this.destinationGeopoint = destinationGeopoint;
    return this;
  }

  /**
   * Get destinationGeopoint
   * @return destinationGeopoint
  **/
  @ApiModelProperty(value = "")


  public String getDestinationGeopoint() {
    return destinationGeopoint;
  }

  public void setDestinationGeopoint(String destinationGeopoint) {
    this.destinationGeopoint = destinationGeopoint;
  }

  public FreightDTO destinationPlaceId(String destinationPlaceId) {
    this.destinationPlaceId = destinationPlaceId;
    return this;
  }

  /**
   * Get destinationPlaceId
   * @return destinationPlaceId
  **/
  @ApiModelProperty(value = "")


  public String getDestinationPlaceId() {
    return destinationPlaceId;
  }

  public void setDestinationPlaceId(String destinationPlaceId) {
    this.destinationPlaceId = destinationPlaceId;
  }

  public FreightDTO destionationTime(OffsetDateTime destionationTime) {
    this.destionationTime = destionationTime;
    return this;
  }

  /**
   * Get destionationTime
   * @return destionationTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getDestionationTime() {
    return destionationTime;
  }

  public void setDestionationTime(OffsetDateTime destionationTime) {
    this.destionationTime = destionationTime;
  }

  public FreightDTO distance(Long distance) {
    this.distance = distance;
    return this;
  }

  /**
   * Get distance
   * @return distance
  **/
  @ApiModelProperty(value = "")


  public Long getDistance() {
    return distance;
  }

  public void setDistance(Long distance) {
    this.distance = distance;
  }

  public FreightDTO estimatedAmount(Double estimatedAmount) {
    this.estimatedAmount = estimatedAmount;
    return this;
  }

  /**
   * Get estimatedAmount
   * @return estimatedAmount
  **/
  @ApiModelProperty(value = "")


  public Double getEstimatedAmount() {
    return estimatedAmount;
  }

  public void setEstimatedAmount(Double estimatedAmount) {
    this.estimatedAmount = estimatedAmount;
  }

  public FreightDTO fragile(Boolean fragile) {
    this.fragile = fragile;
    return this;
  }

  /**
   * Get fragile
   * @return fragile
  **/
  @ApiModelProperty(value = "")


  public Boolean isFragile() {
    return fragile;
  }

  public void setFragile(Boolean fragile) {
    this.fragile = fragile;
  }

  public FreightDTO height(Double height) {
    this.height = height;
    return this;
  }

  /**
   * Get height
   * @return height
  **/
  @ApiModelProperty(value = "")


  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public FreightDTO id(Long id) {
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

  public FreightDTO length(Double length) {
    this.length = length;
    return this;
  }

  /**
   * Get length
   * @return length
  **/
  @ApiModelProperty(value = "")


  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  public FreightDTO originalAmount(Double originalAmount) {
    this.originalAmount = originalAmount;
    return this;
  }

  /**
   * Get originalAmount
   * @return originalAmount
  **/
  @ApiModelProperty(value = "")


  public Double getOriginalAmount() {
    return originalAmount;
  }

  public void setOriginalAmount(Double originalAmount) {
    this.originalAmount = originalAmount;
  }

  public FreightDTO pickupAddress(String pickupAddress) {
    this.pickupAddress = pickupAddress;
    return this;
  }

  /**
   * Get pickupAddress
   * @return pickupAddress
  **/
  @ApiModelProperty(value = "")


  public String getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(String pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

  public FreightDTO pickupGeopoint(String pickupGeopoint) {
    this.pickupGeopoint = pickupGeopoint;
    return this;
  }

  /**
   * Get pickupGeopoint
   * @return pickupGeopoint
  **/
  @ApiModelProperty(value = "")


  public String getPickupGeopoint() {
    return pickupGeopoint;
  }

  public void setPickupGeopoint(String pickupGeopoint) {
    this.pickupGeopoint = pickupGeopoint;
  }

  public FreightDTO pickupPlaceId(String pickupPlaceId) {
    this.pickupPlaceId = pickupPlaceId;
    return this;
  }

  /**
   * Get pickupPlaceId
   * @return pickupPlaceId
  **/
  @ApiModelProperty(value = "")


  public String getPickupPlaceId() {
    return pickupPlaceId;
  }

  public void setPickupPlaceId(String pickupPlaceId) {
    this.pickupPlaceId = pickupPlaceId;
  }

  public FreightDTO requestedStatus(RequestedStatusEnum requestedStatus) {
    this.requestedStatus = requestedStatus;
    return this;
  }

  /**
   * Get requestedStatus
   * @return requestedStatus
  **/
  @ApiModelProperty(value = "")


  public RequestedStatusEnum getRequestedStatus() {
    return requestedStatus;
  }

  public void setRequestedStatus(RequestedStatusEnum requestedStatus) {
    this.requestedStatus = requestedStatus;
  }

  public FreightDTO startTime(OffsetDateTime startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(OffsetDateTime startTime) {
    this.startTime = startTime;
  }

  public FreightDTO trackingId(String trackingId) {
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

  public FreightDTO type(String type) {
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

  public FreightDTO vehicleId(Long vehicleId) {
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

  public FreightDTO weight(Double weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Get weight
   * @return weight
  **/
  @ApiModelProperty(value = "")


  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public FreightDTO width(Double width) {
    this.width = width;
    return this;
  }

  /**
   * Get width
   * @return width
  **/
  @ApiModelProperty(value = "")


  public Double getWidth() {
    return width;
  }

  public void setWidth(Double width) {
    this.width = width;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FreightDTO freightDTO = (FreightDTO) o;
    return Objects.equals(this.acceptedStatus, freightDTO.acceptedStatus) &&
        Objects.equals(this.companyId, freightDTO.companyId) &&
        Objects.equals(this.createdTime, freightDTO.createdTime) &&
        Objects.equals(this.customerId, freightDTO.customerId) &&
        Objects.equals(this.deliveryDate, freightDTO.deliveryDate) &&
        Objects.equals(this.destinationAddress, freightDTO.destinationAddress) &&
        Objects.equals(this.destinationGeopoint, freightDTO.destinationGeopoint) &&
        Objects.equals(this.destinationPlaceId, freightDTO.destinationPlaceId) &&
        Objects.equals(this.destionationTime, freightDTO.destionationTime) &&
        Objects.equals(this.distance, freightDTO.distance) &&
        Objects.equals(this.estimatedAmount, freightDTO.estimatedAmount) &&
        Objects.equals(this.fragile, freightDTO.fragile) &&
        Objects.equals(this.height, freightDTO.height) &&
        Objects.equals(this.id, freightDTO.id) &&
        Objects.equals(this.length, freightDTO.length) &&
        Objects.equals(this.originalAmount, freightDTO.originalAmount) &&
        Objects.equals(this.pickupAddress, freightDTO.pickupAddress) &&
        Objects.equals(this.pickupGeopoint, freightDTO.pickupGeopoint) &&
        Objects.equals(this.pickupPlaceId, freightDTO.pickupPlaceId) &&
        Objects.equals(this.requestedStatus, freightDTO.requestedStatus) &&
        Objects.equals(this.startTime, freightDTO.startTime) &&
        Objects.equals(this.trackingId, freightDTO.trackingId) &&
        Objects.equals(this.type, freightDTO.type) &&
        Objects.equals(this.vehicleId, freightDTO.vehicleId) &&
        Objects.equals(this.weight, freightDTO.weight) &&
        Objects.equals(this.width, freightDTO.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acceptedStatus, companyId, createdTime, customerId, deliveryDate, destinationAddress, destinationGeopoint, destinationPlaceId, destionationTime, distance, estimatedAmount, fragile, height, id, length, originalAmount, pickupAddress, pickupGeopoint, pickupPlaceId, requestedStatus, startTime, trackingId, type, vehicleId, weight, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FreightDTO {\n");
    
    sb.append("    acceptedStatus: ").append(toIndentedString(acceptedStatus)).append("\n");
    sb.append("    companyId: ").append(toIndentedString(companyId)).append("\n");
    sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
    sb.append("    destinationAddress: ").append(toIndentedString(destinationAddress)).append("\n");
    sb.append("    destinationGeopoint: ").append(toIndentedString(destinationGeopoint)).append("\n");
    sb.append("    destinationPlaceId: ").append(toIndentedString(destinationPlaceId)).append("\n");
    sb.append("    destionationTime: ").append(toIndentedString(destionationTime)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    estimatedAmount: ").append(toIndentedString(estimatedAmount)).append("\n");
    sb.append("    fragile: ").append(toIndentedString(fragile)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    originalAmount: ").append(toIndentedString(originalAmount)).append("\n");
    sb.append("    pickupAddress: ").append(toIndentedString(pickupAddress)).append("\n");
    sb.append("    pickupGeopoint: ").append(toIndentedString(pickupGeopoint)).append("\n");
    sb.append("    pickupPlaceId: ").append(toIndentedString(pickupPlaceId)).append("\n");
    sb.append("    requestedStatus: ").append(toIndentedString(requestedStatus)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    trackingId: ").append(toIndentedString(trackingId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    vehicleId: ").append(toIndentedString(vehicleId)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    width: ").append(toIndentedString(width)).append("\n");
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

