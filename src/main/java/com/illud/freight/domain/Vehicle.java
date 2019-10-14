package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "vehicle")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VehicleDocument> vehicleDocuments = new HashSet<>();
    @OneToMany(mappedBy = "vehicle")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VehicleStaff> vehicleStaffs = new HashSet<>();
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

    public Set<VehicleDocument> getVehicleDocuments() {
        return vehicleDocuments;
    }

    public Vehicle vehicleDocuments(Set<VehicleDocument> vehicleDocuments) {
        this.vehicleDocuments = vehicleDocuments;
        return this;
    }

    public Vehicle addVehicleDocuments(VehicleDocument vehicleDocument) {
        this.vehicleDocuments.add(vehicleDocument);
        vehicleDocument.setVehicle(this);
        return this;
    }

    public Vehicle removeVehicleDocuments(VehicleDocument vehicleDocument) {
        this.vehicleDocuments.remove(vehicleDocument);
        vehicleDocument.setVehicle(null);
        return this;
    }

    public void setVehicleDocuments(Set<VehicleDocument> vehicleDocuments) {
        this.vehicleDocuments = vehicleDocuments;
    }

    public Set<VehicleStaff> getVehicleStaffs() {
        return vehicleStaffs;
    }

    public Vehicle vehicleStaffs(Set<VehicleStaff> vehicleStaffs) {
        this.vehicleStaffs = vehicleStaffs;
        return this;
    }

    public Vehicle addVehicleStaffs(VehicleStaff vehicleStaff) {
        this.vehicleStaffs.add(vehicleStaff);
        vehicleStaff.setVehicle(this);
        return this;
    }

    public Vehicle removeVehicleStaffs(VehicleStaff vehicleStaff) {
        this.vehicleStaffs.remove(vehicleStaff);
        vehicleStaff.setVehicle(null);
        return this;
    }

    public void setVehicleStaffs(Set<VehicleStaff> vehicleStaffs) {
        this.vehicleStaffs = vehicleStaffs;
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
