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
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "driver")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_idp_code")
    private String driverIdpCode;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @OneToMany(mappedBy = "driver")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DriverDocument> driverDocuments = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("drivers")
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverIdpCode() {
        return driverIdpCode;
    }

    public Driver driverIdpCode(String driverIdpCode) {
        this.driverIdpCode = driverIdpCode;
        return this;
    }

    public void setDriverIdpCode(String driverIdpCode) {
        this.driverIdpCode = driverIdpCode;
    }

    public String getName() {
        return name;
    }

    public Driver name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Driver email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Driver phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<DriverDocument> getDriverDocuments() {
        return driverDocuments;
    }

    public Driver driverDocuments(Set<DriverDocument> driverDocuments) {
        this.driverDocuments = driverDocuments;
        return this;
    }

    public Driver addDriverDocuments(DriverDocument driverDocument) {
        this.driverDocuments.add(driverDocument);
        driverDocument.setDriver(this);
        return this;
    }

    public Driver removeDriverDocuments(DriverDocument driverDocument) {
        this.driverDocuments.remove(driverDocument);
        driverDocument.setDriver(null);
        return this;
    }

    public void setDriverDocuments(Set<DriverDocument> driverDocuments) {
        this.driverDocuments = driverDocuments;
    }

    public Company getCompany() {
        return company;
    }

    public Driver company(Company company) {
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
        Driver driver = (Driver) o;
        if (driver.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), driver.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", driverIdpCode='" + getDriverIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
