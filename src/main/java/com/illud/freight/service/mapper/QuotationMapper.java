package com.illud.freight.service.mapper;

import com.illud.freight.domain.*;
import com.illud.freight.service.dto.QuotationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Quotation and its DTO QuotationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuotationMapper extends EntityMapper<QuotationDTO, Quotation> {



    default Quotation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Quotation quotation = new Quotation();
        quotation.setId(id);
        return quotation;
    }
}
