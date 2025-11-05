package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.modelo.Alerta;

public class RiesgoAcumulativo implements ComportamientoRiesgo {

    @Override
    public Double calcularRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.stream().filter(a->a.esCritica()).mapToDouble(a->a.getNivel().doubleValue()).sum();
    }

}
