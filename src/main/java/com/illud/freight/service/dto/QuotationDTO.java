package com.illud.freight.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Quotation entity.
 */
public class QuotationDTO implements Serializable {

    private Long id;

    private Long freightId;

    private Long companyId;

    private Long vehicleId;

    private Double amount;

    private LocalDate deliveryDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFreightId() {
        return freightId;
    }

    public void setFreightId(Long freightId) {
        this.freightId = freightId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QuotationDTO quotationDTO = (QuotationDTO) o;
        if (quotationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quotationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuotationDTO{" +
            "id=" + getId() +
            ", freightId=" + getFreightId() +
            ", companyId=" + getCompanyId() +
            ", vehicleId=" + getVehicleId() +
            ", amount=" + getAmount() +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            "}";
    }
}
