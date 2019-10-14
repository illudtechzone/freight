package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.VehicleLookUpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VehicleLookUp and its DTO VehicleLookUpDTO.
 */
@Mapper(componentModel = "spring", uses = {PricingMapper.class})
public interface VehicleLookUpMapper extends EntityMapper<VehicleLookUpDTO, VehicleLookUp> {

    @Mapping(source = "pricing.id", target = "pricingId")
    VehicleLookUpDTO toDto(VehicleLookUp vehicleLookUp);

    @Mapping(source = "pricingId", target = "pricing")
    VehicleLookUp toEntity(VehicleLookUpDTO vehicleLookUpDTO);

    default VehicleLookUp fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleLookUp vehicleLookUp = new VehicleLookUp();
        vehicleLookUp.setId(id);
        return vehicleLookUp;
    }
}
