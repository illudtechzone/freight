package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Vehicle entity.
 */
public class VehicleDTO implements Serializable {

    private Long id;

    private String registerNo;

    private Long vehicleLookupId;

    private String currentLocationGeopoint;

    private Boolean occupied;


    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(String registerNo) {
        this.registerNo = registerNo;
    }

    public Long getVehicleLookupId() {
        return vehicleLookupId;
    }

    public void setVehicleLookupId(Long vehicleLookupId) {
        this.vehicleLookupId = vehicleLookupId;
    }

    public String getCurrentLocationGeopoint() {
        return currentLocationGeopoint;
    }

    public void setCurrentLocationGeopoint(String currentLocationGeopoint) {
        this.currentLocationGeopoint = currentLocationGeopoint;
    }

    public Boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VehicleDTO vehicleDTO = (VehicleDTO) o;
        if (vehicleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vehicleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
            "id=" + getId() +
            ", registerNo='" + getRegisterNo() + "'" +
            ", vehicleLookupId=" + getVehicleLookupId() +
            ", currentLocationGeopoint='" + getCurrentLocationGeopoint() + "'" +
            ", occupied='" + isOccupied() + "'" +
            ", company=" + getCompanyId() +
            "}";
    }
}
