package ar.edu.unahur.obj2.observer.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.riesgo.ComportamientoRiesgo;
import ar.edu.unahur.obj2.observer.riesgo.RiesgoCritico;

public class Entidad implements IEntidad{
    private final String nombre;
    private List<Alerta> alertasRecibidas = new ArrayList<>();
    private ComportamientoRiesgo comportamiento = new RiesgoCritico();

    public Entidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void recibirAlerta(Alerta alerta) {
        alertasRecibidas.add(alerta);
    }

    public Double riesgo(){
        if (alertasRecibidas.isEmpty()){
            return 0.0;
        }else{
            return comportamiento.calcularRiesgo(alertasRecibidas);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }

    public ComportamientoRiesgo getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(ComportamientoRiesgo comportamiento) {
        this.comportamiento = comportamiento;
    }

    

}
