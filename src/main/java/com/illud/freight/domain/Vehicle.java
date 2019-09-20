package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_no")
    private String registerNo;

    @Column(name = "vehicle_lookup_id")
    private Long vehicleLookupId;

    @Column(name = "current_location_geopoint")
    private String currentLocationGeopoint;

    @Column(name = "occupied")
    private Boolean occupied;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Driver driver;

    @ManyToOne
    @JsonIgnoreProperties("vehicles")
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public Vehicle registerNo(String registerNo) {
        this.registerNo = registerNo;
        return this;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Long getVehicleLookupId() {
        return vehicleLookupId;
    }

    public Vehicle vehicleLookupId(Long vehicleLookupId) {
        this.vehicleLookupId = vehicleLookupId;
        return this;
    }

    public void setVehicleLookupId(Long vehicleLookupId) {
        this.vehicleLookupId = vehicleLookupId;
    }

    public String getCurrentLocationGeopoint() {
        return currentLocationGeopoint;
    }

    public Vehicle currentLocationGeopoint(String currentLocationGeopoint) {
        this.currentLocationGeopoint = currentLocationGeopoint;
        return this;
    }

    public void setCurrentLocationGeopoint(String currentLocationGeopoint) {
        this.currentLocationGeopoint = currentLocationGeopoint;
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public Vehicle occupied(Boolean occupied) {
        this.occupied = occupied;
        return this;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle driver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Company getCompany() {
        return company;
    }

    public Vehicle company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        Vehicle vehicle = (Vehicle) o;
        if (vehicle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", registerNo='" + getRegisterNo() + "'" +
            ", vehicleLookupId=" + getVehicleLookupId() +
            ", currentLocationGeopoint='" + getCurrentLocationGeopoint() + "'" +
            ", occupied='" + isOccupied() + "'" +
            "}";
    }
}
