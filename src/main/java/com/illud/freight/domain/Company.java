package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_idp_code")
    private String companyIdpCode;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "location_geopoint")
    private String locationGeopoint;

    @OneToMany(mappedBy = "company")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vehicle> vehicles = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyIdpCode() {
        return companyIdpCode;
    }

    public Company companyIdpCode(String companyIdpCode) {
        this.companyIdpCode = companyIdpCode;
        return this;
    }

    public void setCompanyIdpCode(String companyIdpCode) {
        this.companyIdpCode = companyIdpCode;
    }

    public String getName() {
        return name;
    }

    public Company name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Company email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Company phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Company address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public Company locationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
        return this;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationGeopoint() {
        return locationGeopoint;
    }

    public Company locationGeopoint(String locationGeopoint) {
        this.locationGeopoint = locationGeopoint;
        return this;
    }

    public void setLocationGeopoint(String locationGeopoint) {
        this.locationGeopoint = locationGeopoint;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Company vehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
        return this;
    }

    public Company addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setCompany(this);
        return this;
    }

    public Company removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);
        vehicle.setCompany(null);
        return this;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
        Company company = (Company) o;
        if (company.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), company.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", companyIdpCode='" + getCompanyIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", address='" + getAddress() + "'" +
            ", locationAddress='" + getLocationAddress() + "'" +
            ", locationGeopoint='" + getLocationGeopoint() + "'" +
            "}";
    }
}
