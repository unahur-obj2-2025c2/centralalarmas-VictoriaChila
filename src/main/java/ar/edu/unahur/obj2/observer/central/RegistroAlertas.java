package ar.edu.unahur.obj2.observer.central;

import ar.edu.unahur.obj2.observer.modelo.Alerta;

public class RegistroAlertas {
    private Alerta alerta;
    private Integer cantidadNotificados;

    public RegistroAlertas(Alerta alerta, Integer cantidadNotificados){
        this.alerta = alerta;
        this.cantidadNotificados = cantidadNotificados;

    }

    public Alerta getAlerta() {
        return alerta;
    }

    public Integer getCantidadNotificados() {
        return cantidadNotificados;
    }

}
