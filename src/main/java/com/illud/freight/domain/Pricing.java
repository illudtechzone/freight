package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.illud.freight.domain.enumeration.RatePlan;

/**
 * A Pricing.
 */
@Entity
@Table(name = "pricing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pricing")
public class Pricing implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate_plan")
    private RatePlan ratePlan;

    @Column(name = "additional_description")
    private String additionalDescription;

    @Column(name = "free_waiting_in_mins")
    private Long freeWaitingInMins;

    @Column(name = "waiting_charge_in_mins")
    private Double waitingChargeInMins;

    @Column(name = "night_surcharge_in_mins")
    private Double nightSurchargeInMins;

    @Column(name = "basic_rate")
    private Double basicRate;

    @OneToMany(mappedBy = "pricing")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NormalPricing> normalPricings = new HashSet<>();
    @OneToMany(mappedBy = "pricing")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FulldayPricing> fulldayPricings = new HashSet<>();
    @OneToMany(mappedBy = "pricing")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VehicleLookUp> vehicles = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RatePlan getRatePlan() {
        return ratePlan;
    }

    public Pricing ratePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
        return this;
    }

    public void setRatePlan(RatePlan ratePlan) {
        this.ratePlan = ratePlan;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public Pricing additionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
        return this;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public Long getFreeWaitingInMins() {
        return freeWaitingInMins;
    }

    public Pricing freeWaitingInMins(Long freeWaitingInMins) {
        this.freeWaitingInMins = freeWaitingInMins;
        return this;
    }

    public void setFreeWaitingInMins(Long freeWaitingInMins) {
        this.freeWaitingInMins = freeWaitingInMins;
    }

    public Double getWaitingChargeInMins() {
        return waitingChargeInMins;
    }

    public Pricing waitingChargeInMins(Double waitingChargeInMins) {
        this.waitingChargeInMins = waitingChargeInMins;
        return this;
    }

    public void setWaitingChargeInMins(Double waitingChargeInMins) {
        this.waitingChargeInMins = waitingChargeInMins;
    }

    public Double getNightSurchargeInMins() {
        return nightSurchargeInMins;
    }

    public Pricing nightSurchargeInMins(Double nightSurchargeInMins) {
        this.nightSurchargeInMins = nightSurchargeInMins;
        return this;
    }

    public void setNightSurchargeInMins(Double nightSurchargeInMins) {
        this.nightSurchargeInMins = nightSurchargeInMins;
    }

    public Double getBasicRate() {
        return basicRate;
    }

    public Pricing basicRate(Double basicRate) {
        this.basicRate = basicRate;
        return this;
    }

    public void setBasicRate(Double basicRate) {
        this.basicRate = basicRate;
    }

    public Set<NormalPricing> getNormalPricings() {
        return normalPricings;
    }

    public Pricing normalPricings(Set<NormalPricing> normalPricings) {
        this.normalPricings = normalPricings;
        return this;
    }

    public Pricing addNormalPricing(NormalPricing normalPricing) {
        this.normalPricings.add(normalPricing);
        normalPricing.setPricing(this);
        return this;
    }

    public Pricing removeNormalPricing(NormalPricing normalPricing) {
        this.normalPricings.remove(normalPricing);
        normalPricing.setPricing(null);
        return this;
    }

    public void setNormalPricings(Set<NormalPricing> normalPricings) {
        this.normalPricings = normalPricings;
    }

    public Set<FulldayPricing> getFulldayPricings() {
        return fulldayPricings;
    }

    public Pricing fulldayPricings(Set<FulldayPricing> fulldayPricings) {
        this.fulldayPricings = fulldayPricings;
        return this;
    }

    public Pricing addFulldayPricing(FulldayPricing fulldayPricing) {
        this.fulldayPricings.add(fulldayPricing);
        fulldayPricing.setPricing(this);
        return this;
    }

    public Pricing removeFulldayPricing(FulldayPricing fulldayPricing) {
        this.fulldayPricings.remove(fulldayPricing);
        fulldayPricing.setPricing(null);
        return this;
    }

    public void setFulldayPricings(Set<FulldayPricing> fulldayPricings) {
        this.fulldayPricings = fulldayPricings;
    }

    public Set<VehicleLookUp> getVehicles() {
        return vehicles;
    }

    public Pricing vehicles(Set<VehicleLookUp> vehicleLookUps) {
        this.vehicles = vehicleLookUps;
        return this;
    }

    public Pricing addVehicle(VehicleLookUp vehicleLookUp) {
        this.vehicles.add(vehicleLookUp);
        vehicleLookUp.setPricing(this);
        return this;
    }

    public Pricing removeVehicle(VehicleLookUp vehicleLookUp) {
        this.vehicles.remove(vehicleLookUp);
        vehicleLookUp.setPricing(null);
        return this;
    }

    public void setVehicles(Set<VehicleLookUp> vehicleLookUps) {
        this.vehicles = vehicleLookUps;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pricing pricing = (Pricing) o;
        if (pricing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pricing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pricing{" +
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
