package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.VehicleStaffDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VehicleStaff and its DTO VehicleStaffDTO.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface VehicleStaffMapper extends EntityMapper<VehicleStaffDTO, VehicleStaff> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    VehicleStaffDTO toDto(VehicleStaff vehicleStaff);

    @Mapping(source = "vehicleId", target = "vehicle")
    VehicleStaff toEntity(VehicleStaffDTO vehicleStaffDTO);

    default VehicleStaff fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleStaff vehicleStaff = new VehicleStaff();
        vehicleStaff.setId(id);
        return vehicleStaff;
    }
}
