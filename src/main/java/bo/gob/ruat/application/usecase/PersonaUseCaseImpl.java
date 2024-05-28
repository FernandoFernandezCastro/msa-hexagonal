package bo.gob.ruat.application.usecase;

import java.time.LocalDateTime;

import bo.gob.ruat.application.port.primary.persona.IPersonaUseCase;
import bo.gob.ruat.application.port.secondary.persona.IPersonaRepository;
import bo.gob.ruat.application.port.secondary.persona.ITramitePersonaRepository;
import bo.gob.ruat.application.port.secondary.persona.ITramiteRepository;
import bo.gob.ruat.domain.Entity.PersonaEntity;
import bo.gob.ruat.domain.Entity.TramiteEntity;
import bo.gob.ruat.domain.Entity.TramitePersonaEntity;
import bo.gob.ruat.domain.Entity.PersonaBusquedaEntity;
import bo.gob.ruat.domain.constant.Constant;
import bo.gob.ruat.infrastructure.converter.IPersonaBusquedaEntityConverter;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@RequestScoped
public class PersonaUseCaseImpl implements IPersonaUseCase{


    @Inject
    IPersonaRepository iPersonaRepository;

    @Inject
    ITramiteRepository iTramiteRepository;

    @Inject
    ITramitePersonaRepository iTramitePersonaRepository;

    @Inject
    IPersonaBusquedaEntityConverter iUsuarioBusquedaEntityConverter;

    @Inject
    IPersonaBusquedaEntityConverter iPersonaBusquedaEntityConverter;



    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public PersonaBusquedaEntity buscarPersona(Long pIdentificador) {

        PersonaBusquedaEntity vPersonaBusquedaEntity = iUsuarioBusquedaEntityConverter.toUsuarioBusquedaEntity(pIdentificador);

        // Trámite.
        TramiteEntity vTramiteEntity= seteaDatosTramite();
        iTramiteRepository.insertTramite(vTramiteEntity);
        
        // Recuperamos datos de la persona.
        PersonaEntity vPersonaEntity = iPersonaRepository.getPersona(pIdentificador);


        // Trámite Usuario.
        iTramitePersonaRepository.insertTramitePersona(vTramiteEntity, vPersonaEntity);

        vPersonaBusquedaEntity = iPersonaBusquedaEntityConverter.toPersonaBusquedaEntity(vPersonaEntity, vTramiteEntity);

        if(vPersonaBusquedaEntity.getGenero().equalsIgnoreCase(Constant.FEMENINO)){
            vPersonaBusquedaEntity.setDenominacion(Constant.DENOMINACION_SRTA);
        }else{
            vPersonaBusquedaEntity.setDenominacion(Constant.DENOMINACION_SR);
        }        

        return vPersonaBusquedaEntity;

    }






    // Aux.
    public TramiteEntity seteaDatosTramite(){
        TramiteEntity vTramiteEntity = new TramiteEntity();
        vTramiteEntity.setCodigoTramite("010101");
        vTramiteEntity.setDescripcionTramite("CONSULTA DATOS PERSONA");
        vTramiteEntity.setFechaInicio(LocalDateTime.now());
        vTramiteEntity.setFechaFin(LocalDateTime.now());
        return vTramiteEntity;
    }






    @Override
    public TramitePersonaEntity registrarPersona(PersonaEntity pPersonaEntity) {
        
        TramitePersonaEntity vTramitePersonaEntity = new TramitePersonaEntity();
        
        // Trámite.
        TramiteEntity vTramiteEntity= seteaDatosTramite();
        iTramiteRepository.insertTramite(vTramiteEntity);
        
        // Recuperamos datos de la persona.
        iPersonaRepository.insertPersona(pPersonaEntity);


        // Trámite Usuario.
        iTramitePersonaRepository.insertTramitePersona(vTramiteEntity, pPersonaEntity);


        vTramitePersonaEntity.setNSecTramite(vTramiteEntity.getNumSec());
        vTramitePersonaEntity.setNSecPersona(pPersonaEntity.getNumSec());
        vTramitePersonaEntity.setEstado("AC");

        return vTramitePersonaEntity;
    }

}
