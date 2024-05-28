package bo.gob.ruat.application.port.primary.persona;

import bo.gob.ruat.domain.Entity.PersonaBusquedaEntity;
import bo.gob.ruat.domain.Entity.PersonaEntity;
import bo.gob.ruat.domain.Entity.TramitePersonaEntity;


public interface IPersonaUseCase {
    PersonaBusquedaEntity buscarPersona(Long pIdentificador);

    TramitePersonaEntity registrarPersona(PersonaEntity pPersonaEntity);
    
}
