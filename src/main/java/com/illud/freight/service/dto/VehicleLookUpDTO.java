package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the VehicleLookUp entity.
 */
public class VehicleLookUpDTO implements Serializable {

    private Long id;

    private String name;

    private Double maxWeight;

    @Lob
    private byte[] image;

    private String imageContentType;
    private Double length;

    private Double width;

    private Double height;


    private Long pricingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Long getPricingId() {
        return pricingId;
    }

    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehicleLookUpDTO vehicleLookUpDTO = (VehicleLookUpDTO) o;
        if (vehicleLookUpDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleLookUpDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleLookUpDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", maxWeight=" + getMaxWeight() +
            ", image='" + getImage() + "'" +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", pricing=" + getPricingId() +
            "}";
    }
}
