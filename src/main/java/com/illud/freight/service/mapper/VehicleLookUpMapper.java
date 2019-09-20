package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.VehicleLookUpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VehicleLookUp and its DTO VehicleLookUpDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VehicleLookUpMapper extends EntityMapper<VehicleLookUpDTO, VehicleLookUp> {



    default VehicleLookUp fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleLookUp vehicleLookUp = new VehicleLookUp();
        vehicleLookUp.setId(id);
        return vehicleLookUp;
    }
}
