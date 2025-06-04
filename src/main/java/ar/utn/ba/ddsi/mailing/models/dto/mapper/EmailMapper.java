package ar.utn.ba.ddsi.mailing.models.dto.mapper;

import ar.utn.ba.ddsi.mailing.models.dto.input.EmailInputDTO;
import ar.utn.ba.ddsi.mailing.models.dto.output.EmailOutputDTO;
import ar.utn.ba.ddsi.mailing.models.entities.Email;

public class EmailMapper {

    public Email toEntity(EmailInputDTO emailInputDTO){
        return new Email(
                emailInputDTO.getRemitente(),
                emailInputDTO.getDestinatario(),
                emailInputDTO.getAsunto(),
                emailInputDTO.getContenido()
        );
    }

    public EmailOutputDTO toDTO(Email email) {
        EmailOutputDTO emailOutputDTO = new EmailOutputDTO();
        emailOutputDTO.setId(email.getId());
        emailOutputDTO.setDestinatario(email.getDestinatario());
        emailOutputDTO.setRemitente(email.getRemitente());
        emailOutputDTO.setAsunto(email.getAsunto());
        emailOutputDTO.setContenido(email.getContenido());
        emailOutputDTO.setEnviado(email.isEnviado());

        return emailOutputDTO;
    }
}
