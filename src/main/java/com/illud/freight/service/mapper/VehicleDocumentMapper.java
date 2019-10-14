package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.VehicleDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VehicleDocument and its DTO VehicleDocumentDTO.
 */
@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface VehicleDocumentMapper extends EntityMapper<VehicleDocumentDTO, VehicleDocument> {

    @Mapping(source = "vehicle.id", target = "vehicleId")
    VehicleDocumentDTO toDto(VehicleDocument vehicleDocument);

    @Mapping(source = "vehicleId", target = "vehicle")
    VehicleDocument toEntity(VehicleDocumentDTO vehicleDocumentDTO);

    default VehicleDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        VehicleDocument vehicleDocument = new VehicleDocument();
        vehicleDocument.setId(id);
        return vehicleDocument;
    }
}
