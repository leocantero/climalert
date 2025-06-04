package ar.utn.ba.ddsi.mailing.models.dto.output;

import lombok.Data;

@Data
public class EmailOutputDTO {
    private Long id;
    private String destinatario;
    private String remitente;
    private String asunto;
    private String contenido;
    private boolean enviado;
}
