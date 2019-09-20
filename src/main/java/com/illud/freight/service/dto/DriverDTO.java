package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Driver entity.
 */
public class DriverDTO implements Serializable {

    private Long id;

    private String driverIdpCode;

    private String name;

    private String email;

    private Long phoneNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDriverIdpCode() {
        return driverIdpCode;
    }

    public void setDriverIdpCode(String driverIdpCode) {
        this.driverIdpCode = driverIdpCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DriverDTO driverDTO = (DriverDTO) o;
        if (driverDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), driverDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
            "id=" + getId() +
            ", driverIdpCode='" + getDriverIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
