package com.illud.freight.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import com.illud.freight.domain.enumeration.RequestStatus;

import com.illud.freight.domain.enumeration.FreightStatus;

/**
 * A Freight.
 */
@Entity
@Table(name = "freight")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "freight")
public class Freight implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "distance")
    private Long distance;

    @Column(name = "pickup_address")
    private String pickupAddress;

    @Column(name = "pickup_place_id")
    private String pickupPlaceId;

    @Column(name = "destination_place_id")
    private String destinationPlaceId;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "pickup_geopoint")
    private String pickupGeopoint;

    @Column(name = "destination_geopoint")
    private String destinationGeopoint;

    @Column(name = "customer_id")
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "requested_status")
    private RequestStatus requestedStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "accepted_status")
    private FreightStatus acceptedStatus;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "estimated_amount")
    private Double estimatedAmount;

    @Column(name = "original_amount")
    private Double originalAmount;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "destionation_time")
    private Instant destionationTime;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "tracking_id")
    private String trackingId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Freight type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDistance() {
        return distance;
    }

    public Freight distance(Long distance) {
        this.distance = distance;
        return this;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public Freight pickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
        return this;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getPickupPlaceId() {
        return pickupPlaceId;
    }

    public Freight pickupPlaceId(String pickupPlaceId) {
        this.pickupPlaceId = pickupPlaceId;
        return this;
    }

    public void setPickupPlaceId(String pickupPlaceId) {
        this.pickupPlaceId = pickupPlaceId;
    }

    public String getDestinationPlaceId() {
        return destinationPlaceId;
    }

    public Freight destinationPlaceId(String destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
        return this;
    }

    public void setDestinationPlaceId(String destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public Freight destinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
        return this;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getPickupGeopoint() {
        return pickupGeopoint;
    }

    public Freight pickupGeopoint(String pickupGeopoint) {
        this.pickupGeopoint = pickupGeopoint;
        return this;
    }

    public void setPickupGeopoint(String pickupGeopoint) {
        this.pickupGeopoint = pickupGeopoint;
    }

    public String getDestinationGeopoint() {
        return destinationGeopoint;
    }

    public Freight destinationGeopoint(String destinationGeopoint) {
        this.destinationGeopoint = destinationGeopoint;
        return this;
    }

    public void setDestinationGeopoint(String destinationGeopoint) {
        this.destinationGeopoint = destinationGeopoint;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Freight customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public RequestStatus getRequestedStatus() {
        return requestedStatus;
    }

    public Freight requestedStatus(RequestStatus requestedStatus) {
        this.requestedStatus = requestedStatus;
        return this;
    }

    public void setRequestedStatus(RequestStatus requestedStatus) {
        this.requestedStatus = requestedStatus;
    }

    public FreightStatus getAcceptedStatus() {
        return acceptedStatus;
    }

    public Freight acceptedStatus(FreightStatus acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
        return this;
    }

    public void setAcceptedStatus(FreightStatus acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Freight vehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Freight companyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public Freight estimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
        return this;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public Freight originalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
        return this;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public Freight createdTime(Instant createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Freight startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getDestionationTime() {
        return destionationTime;
    }

    public Freight destionationTime(Instant destionationTime) {
        this.destionationTime = destionationTime;
        return this;
    }

    public void setDestionationTime(Instant destionationTime) {
        this.destionationTime = destionationTime;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Freight deliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean isFragile() {
        return fragile;
    }

    public Freight fragile(Boolean fragile) {
        this.fragile = fragile;
        return this;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public Double getWeight() {
        return weight;
    }

    public Freight weight(Double weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getLength() {
        return length;
    }

    public Freight length(Double length) {
        this.length = length;
        return this;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public Freight width(Double width) {
        this.width = width;
        return this;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public Freight height(Double height) {
        this.height = height;
        return this;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Freight trackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Freight freight = (Freight) o;
        if (freight.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), freight.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Freight{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", distance=" + getDistance() +
            ", pickupAddress='" + getPickupAddress() + "'" +
            ", pickupPlaceId='" + getPickupPlaceId() + "'" +
            ", destinationPlaceId='" + getDestinationPlaceId() + "'" +
            ", destinationAddress='" + getDestinationAddress() + "'" +
            ", pickupGeopoint='" + getPickupGeopoint() + "'" +
            ", destinationGeopoint='" + getDestinationGeopoint() + "'" +
            ", customerId=" + getCustomerId() +
            ", requestedStatus='" + getRequestedStatus() + "'" +
            ", acceptedStatus='" + getAcceptedStatus() + "'" +
            ", vehicleId=" + getVehicleId() +
            ", companyId=" + getCompanyId() +
            ", estimatedAmount=" + getEstimatedAmount() +
            ", originalAmount=" + getOriginalAmount() +
            ", createdTime='" + getCreatedTime() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", destionationTime='" + getDestionationTime() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", fragile='" + isFragile() + "'" +
            ", weight=" + getWeight() +
            ", length=" + getLength() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", trackingId='" + getTrackingId() + "'" +
            "}";
    }
}
