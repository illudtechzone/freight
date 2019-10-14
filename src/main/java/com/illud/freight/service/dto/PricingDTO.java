package com.illud.freight.service.dto;
import java.io.Serializable;
import java.util.Objects;
import com.illud.freight.domain.enumeration.RatePlan;

/**
 * A DTO for the Pricing entity.
 */
public class PricingDTO implements Serializable {

    private Long id;

    private RatePlan ratePlan;

    private String additionalDescription;

    private Long freeWaitingInMins;

    private Double waitingChargeInMins;

    private Double nightSurchargeInMins;

    private Double basicRate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public Long getFreeWaitingInMins() {
        return freeWaitingInMins;
    }

    public void setFreeWaitingInMins(Long freeWaitingInMins) {
        this.freeWaitingInMins = freeWaitingInMins;
    }

    public Double getWaitingChargeInMins() {
        return waitingChargeInMins;
    }

    public void setWaitingChargeInMins(Double waitingChargeInMins) {
        this.waitingChargeInMins = waitingChargeInMins;
    }

    public Double getNightSurchargeInMins() {
        return nightSurchargeInMins;
    }

    public void setNightSurchargeInMins(Double nightSurchargeInMins) {
        this.nightSurchargeInMins = nightSurchargeInMins;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PricingDTO pricingDTO = (PricingDTO) o;
        if (pricingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pricingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PricingDTO{" +
            "id=" + getId() +
            ", ratePlan='" + getRatePlan() + "'" +
            ", additionalDescription='" + getAdditionalDescription() + "'" +
            ", freeWaitingInMins=" + getFreeWaitingInMins() +
            ", waitingChargeInMins=" + getWaitingChargeInMins() +
            ", nightSurchargeInMins=" + getNightSurchargeInMins() +
            ", basicRate=" + getBasicRate() +
            "}";
    }
}
