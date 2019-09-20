package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Customer entity.
 */
public class CustomerDTO implements Serializable {

    private Long id;

    private String customerIdpCode;

    private String name;

    private String email;

    private Long phoneNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerIdpCode() {
        return customerIdpCode;
    }

    public void setCustomerIdpCode(String customerIdpCode) {
        this.customerIdpCode = customerIdpCode;
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

        CustomerDTO customerDTO = (CustomerDTO) o;
        if (customerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + getId() +
            ", customerIdpCode='" + getCustomerIdpCode() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
