package ar.utn.ba.ddsi.mailing.models.entities.alerta;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.alerta.condiciones.Condicion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Alerta {
    private Long id;
    private String descripcion;
    private List<Condicion> condiciones;

    public Alerta(List<Condicion> condiciones) {
          this.condiciones = condiciones;
      }

    public Boolean cumpleCondicionesDeAlerta(Clima clima) {
        return condiciones.stream().allMatch(c-> c.cumpleCondicionDeAlerta(clima));
    }
}
