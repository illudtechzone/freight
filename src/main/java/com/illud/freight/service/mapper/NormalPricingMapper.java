package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.NormalPricingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NormalPricing and its DTO NormalPricingDTO.
 */
@Mapper(componentModel = "spring", uses = {PricingMapper.class})
public interface NormalPricingMapper extends EntityMapper<NormalPricingDTO, NormalPricing> {

    @Mapping(source = "pricing.id", target = "pricingId")
    NormalPricingDTO toDto(NormalPricing normalPricing);

    @Mapping(source = "pricingId", target = "pricing")
    NormalPricing toEntity(NormalPricingDTO normalPricingDTO);

    default NormalPricing fromId(Long id) {
        if (id == null) {
            return null;
        }
        NormalPricing normalPricing = new NormalPricing();
        normalPricing.setId(id);
        return normalPricing;
    }
}
