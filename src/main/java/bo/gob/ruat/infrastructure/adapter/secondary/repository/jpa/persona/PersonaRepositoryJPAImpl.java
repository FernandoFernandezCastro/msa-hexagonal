package bo.gob.ruat.infrastructure.adapter.secondary.repository.jpa.persona;

import bo.gob.ruat.application.port.secondary.persona.IPersonaRepository;
import bo.gob.ruat.domain.Entity.PersonaEntity;

import bo.gob.ruat.infrastructure.adapter.secondary.model.jpa.PersonaJPA;
import bo.gob.ruat.infrastructure.converter.IPersonaEntityConverter;
import bo.gob.ruat.infrastructure.converter.IPersonaJPAConverter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonaRepositoryJPAImpl implements PanacheRepository<PersonaJPA> , IPersonaRepository{

    @Inject
    IPersonaEntityConverter iPersonaEntityConverter;

    @Inject
    IPersonaJPAConverter iPersonaJPAConverter;


    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public PersonaEntity getPersona(Long pNumSecPersona) {
        PersonaJPA vPersonaJPA  = find("numSec", pNumSecPersona).singleResult();
        PersonaEntity vPersonaEntity =  iPersonaEntityConverter.toPersonaEntity(vPersonaJPA);
        return vPersonaEntity;
    }

    @Override
    public void insertPersona(PersonaEntity pPersonaEntity) {
        PersonaJPA vPersonaJPA = iPersonaJPAConverter.toPersonaJPA(pPersonaEntity);        
        persist(vPersonaJPA);

        pPersonaEntity.setNumSec(vPersonaJPA.getNumSec());
    }
    
}
