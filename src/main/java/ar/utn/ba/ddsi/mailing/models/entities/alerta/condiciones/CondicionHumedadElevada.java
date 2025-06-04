package ar.utn.ba.ddsi.mailing.models.entities.alerta.condiciones;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CondicionHumedadElevada implements Condicion {
    private Double humedadMaxima; // Valor por defecto de humedad máxima

    @Override
    public Boolean cumpleCondicionDeAlerta(Clima clima) {
        return clima.getTemperaturaCelsius() > humedadMaxima;
    }
}
