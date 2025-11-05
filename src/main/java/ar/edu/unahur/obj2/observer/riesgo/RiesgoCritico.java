package ar.edu.unahur.obj2.observer.riesgo;

import java.util.List;

import ar.edu.unahur.obj2.observer.modelo.Alerta;

public class RiesgoCritico implements ComportamientoRiesgo {

    @Override
    public Double calcularRiesgo(List<Alerta> alertasRecibidas) {
        if (alertasRecibidas.getLast().esCritica()){
            return 10.0;
        }else{
            return alertasRecibidas.getLast().getNivel().doubleValue();
        }
    }
}


    


