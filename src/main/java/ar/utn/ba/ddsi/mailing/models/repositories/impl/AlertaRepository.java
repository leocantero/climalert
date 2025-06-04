package ar.utn.ba.ddsi.mailing.models.repositories.impl;

import ar.utn.ba.ddsi.mailing.models.entities.Clima;
import ar.utn.ba.ddsi.mailing.models.entities.alerta.Alerta;
import ar.utn.ba.ddsi.mailing.models.repositories.IAlertaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class AlertaRepository implements IAlertaRepository {
    public Map<Long, Alerta> alertas = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Alerta> findAlertasByCumpleCondicionesDeAlerta(Clima clima) {
        return alertas.values().stream()
                .filter(a -> a.cumpleCondicionesDeAlerta(clima))
                .toList();
    }

    @Override
    public Alerta save(Alerta alerta) {
        if (alerta.getId() == null) {
            // Es un nuevo email
            Long id = idGenerator.getAndIncrement();
            alerta.setId(id);
            alertas.put(id, alerta);
        } else {
            // Es una actualización
            alertas.put(alerta.getId(), alerta);
        }
        return alerta;
    }

    @Override
    public List<Alerta> findall() {
        return new ArrayList<>(alertas.values());
    }

}
