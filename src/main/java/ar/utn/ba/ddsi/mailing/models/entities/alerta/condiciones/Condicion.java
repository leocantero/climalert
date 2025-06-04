package ar.utn.ba.ddsi.mailing.models.entities.alerta.condiciones;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;

public interface Condicion {
    Boolean cumpleCondicionDeAlerta(Clima clima);
}
