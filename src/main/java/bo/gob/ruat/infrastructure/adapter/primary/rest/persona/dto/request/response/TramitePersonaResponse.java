package bo.gob.ruat.infrastructure.adapter.primary.rest.persona.dto.request.response;

public class TramitePersonaResponse {
    private Long tramite;
    private Long identificadorPersona;

    public TramitePersonaResponse() {
    }

    public Long getIdentificadorPersona() {
        return identificadorPersona;
    }

    public void setIdentificadorPersona(Long identificadorPersona) {
        this.identificadorPersona = identificadorPersona;
    }

    public Long getTramite() {
        return tramite;
    }

    public void setTramite(Long tramite) {
        this.tramite = tramite;
    }
}
