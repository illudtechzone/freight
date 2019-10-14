package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.FulldayPricingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FulldayPricing and its DTO FulldayPricingDTO.
 */
@Mapper(componentModel = "spring", uses = {PricingMapper.class})
public interface FulldayPricingMapper extends EntityMapper<FulldayPricingDTO, FulldayPricing> {

    @Mapping(source = "pricing.id", target = "pricingId")
    FulldayPricingDTO toDto(FulldayPricing fulldayPricing);

    @Mapping(source = "pricingId", target = "pricing")
    FulldayPricing toEntity(FulldayPricingDTO fulldayPricingDTO);

    default FulldayPricing fromId(Long id) {
        if (id == null) {
            return null;
        }
        FulldayPricing fulldayPricing = new FulldayPricing();
        fulldayPricing.setId(id);
        return fulldayPricing;
    }
}
