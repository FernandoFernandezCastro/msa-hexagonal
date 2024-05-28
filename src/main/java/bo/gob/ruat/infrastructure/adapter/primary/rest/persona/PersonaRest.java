package bo.gob.ruat.infrastructure.adapter.primary.rest.persona;

import java.io.IOException;

import bo.gob.ruat.application.port.primary.persona.IPersonaUseCase;
import bo.gob.ruat.domain.Entity.PersonaBusquedaEntity;
import bo.gob.ruat.domain.Entity.PersonaEntity;
import bo.gob.ruat.domain.Entity.TramitePersonaEntity;
import bo.gob.ruat.infrastructure.adapter.primary.rest.persona.dto.request.response.PersonaRequest;
import bo.gob.ruat.infrastructure.adapter.primary.rest.persona.dto.request.response.PersonaResponse;
import bo.gob.ruat.infrastructure.adapter.primary.rest.persona.dto.request.response.TramitePersonaResponse;
import bo.gob.ruat.infrastructure.converter.IPersonaEntityConverter;
import bo.gob.ruat.infrastructure.converter.IPersonaResponseConverter;
//import bo.gob.ruat.lib.core.domain.exception.ExcepcionLogica;
import bo.gob.ruat.infrastructure.converter.ITramitePersonaResponseConverter;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("personas/persona")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaRest {

    @Inject
    IPersonaUseCase iPersonaUseCase;

    @Inject
    IPersonaResponseConverter iPersonaResponseConverter;

    @Inject
    ITramitePersonaResponseConverter iTramitePersonaResponseConverter;
    
    @Inject
    IPersonaEntityConverter iPersonaEntityConverter;

    @GET
    @Path("/consulta-pesona")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class, RuntimeException.class})
    public Response fhhbuscarPersona(
            @QueryParam("identificador") Long pIndentificador){   

        PersonaBusquedaEntity vUsuarioBusquedaEntity = iPersonaUseCase.buscarPersona(pIndentificador);
        PersonaResponse vPersonaResponse = iPersonaResponseConverter.toPersonaResponse(vUsuarioBusquedaEntity);

        return Response.ok(vPersonaResponse).build();

    }

    @POST
    @Path("/registra-pesona")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class, RuntimeException.class})
    public Response registrarPersona(
            PersonaRequest pDatosPersona){

        PersonaEntity vPersonaEntity = iPersonaEntityConverter.toPersonaEntity(pDatosPersona);
        TramitePersonaEntity vTramitePersonaEntity = iPersonaUseCase.registrarPersona(vPersonaEntity);
        TramitePersonaResponse vPersonaResponse = iTramitePersonaResponseConverter.toTramitePersonaResponse(vTramitePersonaEntity);

        return Response.ok(vPersonaResponse).build();

    }
}
