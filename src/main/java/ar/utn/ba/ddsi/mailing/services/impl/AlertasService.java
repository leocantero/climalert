package ar.utn.ba.ddsi.mailing.services.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.Email;
import ar.utn.ba.ddsi.mailing.models.entities.alerta.Alerta;
import ar.utn.ba.ddsi.mailing.models.repositories.IAlertaRepository;
import ar.utn.ba.ddsi.mailing.models.repositories.IClimaRepository;
import ar.utn.ba.ddsi.mailing.services.IAlertasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Arrays;
import java.util.List;

@Service
public class AlertasService implements IAlertasService {
    private static final Logger logger = LoggerFactory.getLogger(AlertasService.class);

    private final IClimaRepository climaRepository;
    private final IAlertaRepository alertasRepository;
    private final EmailService emailService;
    private final String remitente;
    private final List<String> destinatarios;

    public AlertasService(
            IClimaRepository climaRepository, 
            EmailService emailService,
            IAlertaRepository alertasRepository,
            @Value("${email.alertas.remitente}") String remitente,
            @Value("${email.alertas.destinatarios}") String destinatarios) {
        this.climaRepository = climaRepository;
        this.emailService = emailService;
        this.remitente = remitente;
        this.alertasRepository = alertasRepository;
        this.destinatarios = Arrays.asList(destinatarios.split(","));
    }

    @Override
    public Mono<Void> generarAlertasYAvisar() {
        return Mono.fromCallable(() -> climaRepository.findByProcesado(false))
            .flatMap(climas -> {
                logger.info("Procesando {} registros de clima no procesados", climas.size());
                return Mono.just(climas);
            })
            .flatMap(climas -> {
                climas.stream()
                    .filter(clima -> !alertasRepository.findAlertasByCumpleCondicionesDeAlerta(clima).isEmpty())
                    .forEach(this::generarYEnviarEmail);
                
                // Marcar todos como procesados
                climas.forEach(clima -> {
                    clima.setProcesado(true);
                    climaRepository.save(clima);
                });
                
                return Mono.empty();
            })
            .onErrorResume(e -> {
                logger.error("Error al procesar alertas: {}", e.getMessage());
                return Mono.empty();
            })
            .then();
    }

    private List<Clima> obtenerClimasNoProcesados() {
        return climaRepository.findByProcesado(false);
    }

    private void generarYEnviarEmail(Clima clima) {
        List<Alerta> alertas = alertasRepository.findAlertasByCumpleCondicionesDeAlerta(clima);
        for (Alerta alerta : alertas) {
            String asunto = "Alerta de Clima - Condiciones Extremas";
                    String mensaje = String.format(
                        "ALERTA: %s detectada en %s\n\n" +
                        "Temperatura: %.1f°C\n" +
                        "Humedad: %d%%\n" +
                        "Condición: %s\n" +
                        "Velocidad del viento: %.1f km/h\n\n" +
                        "Se recomienda tomar precauciones.",
                        alerta.getDescripcion(),
                        clima.getCiudad(),
                        clima.getTemperaturaCelsius(),
                        clima.getHumedad(),
                        clima.getCondicion(),
                        clima.getVelocidadVientoKmh()
                    );

                    for (String destinatario : destinatarios) {
                        Email email = new Email(destinatario, remitente, asunto, mensaje);
                        emailService.guardarEmail(email);
                        emailService.enviarEmail(email);
                    }

                    logger.info("Email de alerta generado para {} - Enviado a {} destinatarios",
                        clima.getCiudad(), destinatarios.size());
                }
        }

} 