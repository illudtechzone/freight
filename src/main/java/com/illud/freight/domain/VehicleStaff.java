package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.illud.freight.domain.enumeration.StaffType;

/**
 * A VehicleStaff.
 */
@Entity
@Table(name = "vehicle_staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "vehiclestaff")
public class VehicleStaff implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private StaffType type;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "priority")
    private Long priority;

    @ManyToOne
    @JsonIgnoreProperties("vehicleStaffs")
    private Vehicle vehicle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffType getType() {
        return type;
    }

    public VehicleStaff type(StaffType type) {
        this.type = type;
        return this;
    }

    public void setType(StaffType type) {
        this.type = type;
    }

    public Long getStaffId() {
        return staffId;
    }

    public VehicleStaff staffId(Long staffId) {
        this.staffId = staffId;
        return this;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getPriority() {
        return priority;
    }

    public VehicleStaff priority(Long priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleStaff vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        VehicleStaff vehicleStaff = (VehicleStaff) o;
        if (vehicleStaff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleStaff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleStaff{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", staffId=" + getStaffId() +
            ", priority=" + getPriority() +
            "}";
    }
}
