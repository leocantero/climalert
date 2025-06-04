package ar.utn.ba.ddsi.mailing.models.dto.input;

import lombok.Data;

@Data
public class EmailInputDTO {
    private String destinatario;
    private String remitente;
    private String asunto;
    private String contenido;
}
