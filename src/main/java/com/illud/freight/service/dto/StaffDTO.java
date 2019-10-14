package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Staff entity.
 */
public class StaffDTO implements Serializable {

    private Long id;

    private String staffIdpCode;

    private String name;

    private String email;

    private Long phoneNumber;

    private String type;


    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffIdpCode() {
        return staffIdpCode;
    }

    public void setStaffIdpCode(String staffIdpCode) {
        this.staffIdpCode = staffIdpCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        StaffDTO staffDTO = (StaffDTO) o;
        if (staffDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staffDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
            "id=" + getId() +
            ", staffIdpCode='" + getStaffIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", type='" + getType() + "'" +
            ", company=" + getCompanyId() +
            "}";
    }
}
