package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.modelo.Alerta;

public interface ComportamientoRiesgo {
    Double calcularRiesgo (List<Alerta> alertasRecibidas);

}
