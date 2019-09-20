package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.FreightDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Freight and its DTO FreightDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FreightMapper extends EntityMapper<FreightDTO, Freight> {



    default Freight fromId(Long id) {
        if (id == null) {
            return null;
        }
        Freight freight = new Freight();
        freight.setId(id);
        return freight;
    }
}
