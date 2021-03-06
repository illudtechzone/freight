package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.DriverDocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DriverDocument and its DTO DriverDocumentDTO.
 */
@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface DriverDocumentMapper extends EntityMapper<DriverDocumentDTO, DriverDocument> {

    @Mapping(source = "driver.id", target = "driverId")
    DriverDocumentDTO toDto(DriverDocument driverDocument);

    @Mapping(source = "driverId", target = "driver")
    DriverDocument toEntity(DriverDocumentDTO driverDocumentDTO);

    default DriverDocument fromId(Long id) {
        if (id == null) {
            return null;
        }
        DriverDocument driverDocument = new DriverDocument();
        driverDocument.setId(id);
        return driverDocument;
    }
}
