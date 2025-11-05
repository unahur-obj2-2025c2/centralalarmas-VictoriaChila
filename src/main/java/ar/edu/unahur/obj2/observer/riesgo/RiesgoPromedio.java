package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.modelo.Alerta;

public class RiesgoPromedio implements ComportamientoRiesgo {

    @Override
    public Double calcularRiesgo(List<Alerta> alertasRecibidas) {
        return alertasRecibidas.stream().mapToDouble(a->a.getNivel().doubleValue()).average().orElse(0.0);
    }
    

} 
