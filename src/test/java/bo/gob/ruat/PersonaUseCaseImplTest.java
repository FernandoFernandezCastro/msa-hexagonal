package bo.gob.ruat;

import bo.gob.ruat.application.port.primary.persona.IPersonaUseCase;
import bo.gob.ruat.domain.Entity.PersonaBusquedaEntity;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class PersonaUseCaseImplTest {

    @Inject
    IPersonaUseCase iPersonaUseCase;

    @Test
    @Transactional
    public void buscarPersonaTest () {
        String nombreEsperado = "MARIA";
        Long id = 1000L;
        PersonaBusquedaEntity vPersonaBusquedaEntity = iPersonaUseCase.buscarPersona(id);
        assertEquals(nombreEsperado,vPersonaBusquedaEntity.getNombre());
    }

}
