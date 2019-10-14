package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.illud.freight.domain.enumeration.StaffType;

/**
 * A DTO for the VehicleStaff entity.
 */
public class VehicleStaffDTO implements Serializable {

    private Long id;

    private StaffType type;

    private Long staffId;

    private Long priority;


    private Long vehicleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StaffType getType() {
        return type;
    }

    public void setType(StaffType type) {
        this.type = type;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehicleStaffDTO vehicleStaffDTO = (VehicleStaffDTO) o;
        if (vehicleStaffDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleStaffDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleStaffDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", staffId=" + getStaffId() +
            ", priority=" + getPriority() +
            ", vehicle=" + getVehicleId() +
            "}";
    }
}
