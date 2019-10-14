package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.PricingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pricing and its DTO PricingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PricingMapper extends EntityMapper<PricingDTO, Pricing> {


    @Mapping(target = "normalPricings", ignore = true)
    @Mapping(target = "fulldayPricings", ignore = true)
    @Mapping(target = "vehicles", ignore = true)
    Pricing toEntity(PricingDTO pricingDTO);

    default Pricing fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pricing pricing = new Pricing();
        pricing.setId(id);
        return pricing;
    }
}
