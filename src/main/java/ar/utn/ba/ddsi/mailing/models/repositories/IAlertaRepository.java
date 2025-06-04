package ar.utn.ba.ddsi.mailing.models.repositories;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.alerta.Alerta;

import java.util.List;

public interface IAlertaRepository {
    List<Alerta> findAlertasByCumpleCondicionesDeAlerta(Clima clima);
    Alerta save(Alerta alerta);
    List<Alerta> findall();
}
