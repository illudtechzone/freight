package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.DriverDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Driver and its DTO DriverDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {

    @Mapping(source = "company.id", target = "companyId")
    DriverDTO toDto(Driver driver);

    @Mapping(target = "driverDocuments", ignore = true)
    @Mapping(source = "companyId", target = "company")
    Driver toEntity(DriverDTO driverDTO);

    default Driver fromId(Long id) {
        if (id == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setId(id);
        return driver;
    }
}
