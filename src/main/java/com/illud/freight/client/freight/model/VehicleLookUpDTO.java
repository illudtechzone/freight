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
 * VehicleLookUpDTO
 */
@Validated
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-18T17:48:13.371+05:30[Asia/Calcutta]")

public class VehicleLookUpDTO   {
  @JsonProperty("height")
  private Double height = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("image")
  private byte[] image = null;

  @JsonProperty("imageContentType")
  private String imageContentType = null;

  @JsonProperty("length")
  private Double length = null;

  @JsonProperty("maxWeight")
  private Double maxWeight = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("pricingId")
  private Long pricingId = null;

  @JsonProperty("width")
  private Double width = null;

  public VehicleLookUpDTO height(Double height) {
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

  public VehicleLookUpDTO id(Long id) {
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

  public VehicleLookUpDTO image(byte[] image) {
    this.image = image;
    return this;
  }

  /**
   * Get image
   * @return image
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$") 
  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public VehicleLookUpDTO imageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
    return this;
  }

  /**
   * Get imageContentType
   * @return imageContentType
  **/
  @ApiModelProperty(value = "")


  public String getImageContentType() {
    return imageContentType;
  }

  public void setImageContentType(String imageContentType) {
    this.imageContentType = imageContentType;
  }

  public VehicleLookUpDTO length(Double length) {
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

  public VehicleLookUpDTO maxWeight(Double maxWeight) {
    this.maxWeight = maxWeight;
    return this;
  }

  /**
   * Get maxWeight
   * @return maxWeight
  **/
  @ApiModelProperty(value = "")


  public Double getMaxWeight() {
    return maxWeight;
  }

  public void setMaxWeight(Double maxWeight) {
    this.maxWeight = maxWeight;
  }

  public VehicleLookUpDTO name(String name) {
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

  public VehicleLookUpDTO pricingId(Long pricingId) {
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

  public VehicleLookUpDTO width(Double width) {
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
    VehicleLookUpDTO vehicleLookUpDTO = (VehicleLookUpDTO) o;
    return Objects.equals(this.height, vehicleLookUpDTO.height) &&
        Objects.equals(this.id, vehicleLookUpDTO.id) &&
        Objects.equals(this.image, vehicleLookUpDTO.image) &&
        Objects.equals(this.imageContentType, vehicleLookUpDTO.imageContentType) &&
        Objects.equals(this.length, vehicleLookUpDTO.length) &&
        Objects.equals(this.maxWeight, vehicleLookUpDTO.maxWeight) &&
        Objects.equals(this.name, vehicleLookUpDTO.name) &&
        Objects.equals(this.pricingId, vehicleLookUpDTO.pricingId) &&
        Objects.equals(this.width, vehicleLookUpDTO.width);
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, id, image, imageContentType, length, maxWeight, name, pricingId, width);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VehicleLookUpDTO {\n");
    
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    imageContentType: ").append(toIndentedString(imageContentType)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    maxWeight: ").append(toIndentedString(maxWeight)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    pricingId: ").append(toIndentedString(pricingId)).append("\n");
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

