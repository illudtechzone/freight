package com.illud.freight.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

import com.illud.freight.domain.enumeration.LimitStatus;

/**
 * A FulldayPricing.
 */
@Entity
@Table(name = "fullday_pricing")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "fulldaypricing")
public class FulldayPricing implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate_per_mins_above_limit")
    private Double ratePerMinsAboveLimit;

    @Column(name = "rate_per_km")
    private Double ratePerKm;

    @Column(name = "start_limit_in_kms")
    private Double startLimitInKms;

    @Column(name = "end_limit_in_kms")
    private Double endLimitInKms;

    @Column(name = "start_limit_in_hour")
    private Double startLimitInHour;

    @Column(name = "end_limit_in_hour")
    private Double endLimitInHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "limit_status")
    private LimitStatus limitStatus;

    @ManyToOne
    @JsonIgnoreProperties("fulldayPricings")
    private Pricing pricing;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRatePerMinsAboveLimit() {
        return ratePerMinsAboveLimit;
    }

    public FulldayPricing ratePerMinsAboveLimit(Double ratePerMinsAboveLimit) {
        this.ratePerMinsAboveLimit = ratePerMinsAboveLimit;
        return this;
    }

    public void setRatePerMinsAboveLimit(Double ratePerMinsAboveLimit) {
        this.ratePerMinsAboveLimit = ratePerMinsAboveLimit;
    }

    public Double getRatePerKm() {
        return ratePerKm;
    }

    public FulldayPricing ratePerKm(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
        return this;
    }

    public void setRatePerKm(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public Double getStartLimitInKms() {
        return startLimitInKms;
    }

    public FulldayPricing startLimitInKms(Double startLimitInKms) {
        this.startLimitInKms = startLimitInKms;
        return this;
    }

    public void setStartLimitInKms(Double startLimitInKms) {
        this.startLimitInKms = startLimitInKms;
    }

    public Double getEndLimitInKms() {
        return endLimitInKms;
    }

    public FulldayPricing endLimitInKms(Double endLimitInKms) {
        this.endLimitInKms = endLimitInKms;
        return this;
    }

    public void setEndLimitInKms(Double endLimitInKms) {
        this.endLimitInKms = endLimitInKms;
    }

    public Double getStartLimitInHour() {
        return startLimitInHour;
    }

    public FulldayPricing startLimitInHour(Double startLimitInHour) {
        this.startLimitInHour = startLimitInHour;
        return this;
    }

    public void setStartLimitInHour(Double startLimitInHour) {
        this.startLimitInHour = startLimitInHour;
    }

    public Double getEndLimitInHour() {
        return endLimitInHour;
    }

    public FulldayPricing endLimitInHour(Double endLimitInHour) {
        this.endLimitInHour = endLimitInHour;
        return this;
    }

    public void setEndLimitInHour(Double endLimitInHour) {
        this.endLimitInHour = endLimitInHour;
    }

    public LimitStatus getLimitStatus() {
        return limitStatus;
    }

    public FulldayPricing limitStatus(LimitStatus limitStatus) {
        this.limitStatus = limitStatus;
        return this;
    }

    public void setLimitStatus(LimitStatus limitStatus) {
        this.limitStatus = limitStatus;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public FulldayPricing pricing(Pricing pricing) {
        this.pricing = pricing;
        return this;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
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
        FulldayPricing fulldayPricing = (FulldayPricing) o;
        if (fulldayPricing.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fulldayPricing.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FulldayPricing{" +
            "id=" + getId() +
            ", ratePerMinsAboveLimit=" + getRatePerMinsAboveLimit() +
            ", ratePerKm=" + getRatePerKm() +
            ", startLimitInKms=" + getStartLimitInKms() +
            ", endLimitInKms=" + getEndLimitInKms() +
            ", startLimitInHour=" + getStartLimitInHour() +
            ", endLimitInHour=" + getEndLimitInHour() +
            ", limitStatus='" + getLimitStatus() + "'" +
            "}";
    }
}
