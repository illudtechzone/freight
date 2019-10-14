package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.illud.freight.domain.enumeration.LimitStatus;

/**
 * A DTO for the NormalPricing entity.
 */
public class NormalPricingDTO implements Serializable {

    private Long id;

    private Double ratePerKm;

    private Double startLimitInKms;

    private Double endLimitInKms;

    private LimitStatus limitStatus;


    private Long pricingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        NormalPricingDTO normalPricingDTO = (NormalPricingDTO) o;
        if (normalPricingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), normalPricingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NormalPricingDTO{" +
            "id=" + getId() +
            ", ratePerKm=" + getRatePerKm() +
            ", startLimitInKms=" + getStartLimitInKms() +
            ", endLimitInKms=" + getEndLimitInKms() +
            ", limitStatus='" + getLimitStatus() + "'" +
            ", pricing=" + getPricingId() +
            "}";
    }
}
