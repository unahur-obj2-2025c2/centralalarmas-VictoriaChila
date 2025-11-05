package ar.edu.unahur.obj2.observer.central;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.observer.excepciones.DominioException;
import ar.edu.unahur.obj2.observer.modelo.Alerta;
import ar.edu.unahur.obj2.observer.modelo.IEntidad;


public class CentralMonitoreo {
    private List<IEntidad> entidades= new ArrayList<>();
    private List<RegistroAlertas> registro = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Central de Monitoreo Activa");
    }

    public void agregarEntidad(IEntidad entidad){
        entidades.add(entidad);
    }
    public void eliminarEntidad(IEntidad entidad){
        entidades.remove(entidad);
    }

    public void emitirAlerta(String tipo, Integer nivel) {
        if (nivel < 1 || nivel > 10){
            throw new DominioException("Nivel de alerta incorrecto");
        } else {
            Alerta alerta = new Alerta(tipo, nivel);
            registro.add(new RegistroAlertas(alerta, entidades.size()));
            notificarEntidades(alerta);
        }
    }

    public void notificarEntidades(Alerta alerta){
        entidades.forEach(e->e.recibirAlerta(alerta));
    }

    public Integer cantidadEntidadesNotificadas(){
        return registro.size();
    }


}
