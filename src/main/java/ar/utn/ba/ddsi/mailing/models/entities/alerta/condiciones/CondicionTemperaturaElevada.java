package ar.utn.ba.ddsi.mailing.models.entities.alerta.condiciones;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CondicionTemperaturaElevada implements Condicion {
    private Double temperaturaMaxima = 35.0; // Temperatura máxima en grados Celsius para activar la alerta

    @Override
    public Boolean cumpleCondicionDeAlerta(Clima clima) {
        return clima.getTemperaturaCelsius() > temperaturaMaxima;
    }
}
