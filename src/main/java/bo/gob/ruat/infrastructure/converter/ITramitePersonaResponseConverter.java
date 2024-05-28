package bo.gob.ruat.infrastructure.converter;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bo.gob.ruat.domain.Entity.TramitePersonaEntity;
import bo.gob.ruat.infrastructure.adapter.primary.rest.persona.dto.request.response.TramitePersonaResponse;

@Mapper(componentModel = "jakarta")
public interface ITramitePersonaResponseConverter {
    @Mapping(target= "tramite", source = "nSecTramite")
    @Mapping(target= "identificadorPersona", source = "nSecPersona")
    TramitePersonaResponse toTramitePersonaResponse(TramitePersonaEntity pTramitePersonaEntity);
}
