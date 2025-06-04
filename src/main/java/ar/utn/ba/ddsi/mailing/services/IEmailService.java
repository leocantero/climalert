package ar.utn.ba.ddsi.mailing.services;

import ar.utn.ba.ddsi.mailing.models.dto.input.EmailInputDTO;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import java.util.List;

public interface IEmailService {
    Email crearEmail(EmailInputDTO emailInputDTO);
    void guardarEmail(Email email);
    List<Email> obtenerEmails(Boolean pendiente);
    void procesarPendientes();
    void enviarEmail(Email email);
    void loguearEmailsPendientes();
} 