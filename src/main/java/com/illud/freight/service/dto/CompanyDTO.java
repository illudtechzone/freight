package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Company entity.
 */
public class CompanyDTO implements Serializable {

    private Long id;

    private String companyIdpCode;

    private String name;

    private String email;

    private Long phoneNumber;

    private String address;

    private String locationAddress;

    private String locationGeopoint;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyIdpCode() {
        return companyIdpCode;
    }

    public void setCompanyIdpCode(String companyIdpCode) {
        this.companyIdpCode = companyIdpCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationGeopoint() {
        return locationGeopoint;
    }

    public void setLocationGeopoint(String locationGeopoint) {
        this.locationGeopoint = locationGeopoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyDTO companyDTO = (CompanyDTO) o;
        if (companyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
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
