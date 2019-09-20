package com.illud.freight.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import com.illud.freight.domain.enumeration.RequestStatus;
import com.illud.freight.domain.enumeration.FreightStatus;

/**
 * A DTO for the Freight entity.
 */
public class FreightDTO implements Serializable {

    private Long id;

    private String type;

    private Long distance;

    private String pickupAddress;

    private String pickupPlaceId;

    private String destinationPlaceId;

    private String destinationAddress;

    private String pickupGeopoint;

    private String destinationGeopoint;

    private String customerId;

    private RequestStatus requestedStatus;

    private FreightStatus acceptedStatus;

    private String vehicleId;

    private String companyId;

    private Long amount;

    private Instant createdTime;

    private Instant startTime;

    private Instant destionationTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupPlaceId() {
        return pickupPlaceId;
    }

    public void setPickupPlaceId(String pickupPlaceId) {
        this.pickupPlaceId = pickupPlaceId;
    }

    public String getDestinationPlaceId() {
        return destinationPlaceId;
    }

    public void setDestinationPlaceId(String destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPickupGeopoint() {
        return pickupGeopoint;
    }

    public void setPickupGeopoint(String pickupGeopoint) {
        this.pickupGeopoint = pickupGeopoint;
    }

    public String getDestinationGeopoint() {
        return destinationGeopoint;
    }

    public void setDestinationGeopoint(String destinationGeopoint) {
        this.destinationGeopoint = destinationGeopoint;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public RequestStatus getRequestedStatus() {
        return requestedStatus;
    }

    public void setRequestedStatus(RequestStatus requestedStatus) {
        this.requestedStatus = requestedStatus;
    }

    public FreightStatus getAcceptedStatus() {
        return acceptedStatus;
    }

    public void setAcceptedStatus(FreightStatus acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getDestionationTime() {
        return destionationTime;
    }

    public void setDestionationTime(Instant destionationTime) {
        this.destionationTime = destionationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FreightDTO freightDTO = (FreightDTO) o;
        if (freightDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), freightDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FreightDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", distance=" + getDistance() +
            ", pickupAddress='" + getPickupAddress() + "'" +
            ", pickupPlaceId='" + getPickupPlaceId() + "'" +
            ", destinationPlaceId='" + getDestinationPlaceId() + "'" +
            ", destinationAddress='" + getDestinationAddress() + "'" +
            ", pickupGeopoint='" + getPickupGeopoint() + "'" +
            ", destinationGeopoint='" + getDestinationGeopoint() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", requestedStatus='" + getRequestedStatus() + "'" +
            ", acceptedStatus='" + getAcceptedStatus() + "'" +
            ", vehicleId='" + getVehicleId() + "'" +
            ", companyId='" + getCompanyId() + "'" +
            ", amount=" + getAmount() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", destionationTime='" + getDestionationTime() + "'" +
            "}";
    }
}
