package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.illud.freight.domain.enumeration.LimitStatus;

/**
 * A DTO for the FulldayPricing entity.
 */
public class FulldayPricingDTO implements Serializable {

    private Long id;

    private Double ratePerMinsAboveLimit;

    private Double ratePerKm;

    private Double startLimitInKms;

    private Double endLimitInKms;

    private Double startLimitInHour;

    private Double endLimitInHour;

    private LimitStatus limitStatus;


    private Long pricingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRatePerMinsAboveLimit() {
        return ratePerMinsAboveLimit;
    }

    public void setRatePerMinsAboveLimit(Double ratePerMinsAboveLimit) {
        this.ratePerMinsAboveLimit = ratePerMinsAboveLimit;
    }

    public Double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public Double getStartLimitInKms() {
        return startLimitInKms;
    }

    public void setStartLimitInKms(Double startLimitInKms) {
        this.startLimitInKms = startLimitInKms;
    }

    public Double getEndLimitInKms() {
        return endLimitInKms;
    }

    public void setEndLimitInKms(Double endLimitInKms) {
        this.endLimitInKms = endLimitInKms;
    }

    public Double getStartLimitInHour() {
        return startLimitInHour;
    }

    public void setStartLimitInHour(Double startLimitInHour) {
        this.startLimitInHour = startLimitInHour;
    }

    public Double getEndLimitInHour() {
        return endLimitInHour;
    }

    public void setEndLimitInHour(Double endLimitInHour) {
        this.endLimitInHour = endLimitInHour;
    }

    public LimitStatus getLimitStatus() {
        return limitStatus;
    }

    public void setLimitStatus(LimitStatus limitStatus) {
        this.limitStatus = limitStatus;
    }

    public Long getPricingId() {
        return pricingId;
    }

    public void setPricingId(Long pricingId) {
        this.pricingId = pricingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FulldayPricingDTO fulldayPricingDTO = (FulldayPricingDTO) o;
        if (fulldayPricingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fulldayPricingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FulldayPricingDTO{" +
            "id=" + getId() +
            ", ratePerMinsAboveLimit=" + getRatePerMinsAboveLimit() +
            ", ratePerKm=" + getRatePerKm() +
            ", startLimitInKms=" + getStartLimitInKms() +
            ", endLimitInKms=" + getEndLimitInKms() +
            ", startLimitInHour=" + getStartLimitInHour() +
            ", endLimitInHour=" + getEndLimitInHour() +
            ", limitStatus='" + getLimitStatus() + "'" +
            ", pricing=" + getPricingId() +
            "}";
    }
}
