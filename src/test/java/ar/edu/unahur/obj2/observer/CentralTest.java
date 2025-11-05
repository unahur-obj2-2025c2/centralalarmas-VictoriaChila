package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.central.CentralMonitoreo;
import ar.edu.unahur.obj2.observer.modelo.Alerta;
import ar.edu.unahur.obj2.observer.modelo.Entidad;
import ar.edu.unahur.obj2.observer.riesgo.RiesgoAcumulativo;
import ar.edu.unahur.obj2.observer.riesgo.RiesgoPromedio;

public class CentralTest {
    private CentralMonitoreo central;
    private Entidad e1;
    private Entidad e2;
    private Alerta alerta;

    @BeforeEach
    void setUp(){
        central = new CentralMonitoreo();
        alerta = new Alerta("Inundacion", 8);
        e1 = new Entidad("Escuela");
        e2 = new Entidad("Policia");
        central.agregarEntidad(e1);
        central.agregarEntidad(e2);


    }

    @Test
    void test1 () {
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);


        assertEquals(e1.riesgo(), 10);
        assertEquals(e2.riesgo(), 10);
        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
    }

    @Test
    void test2(){
        e1.setComportamiento(new RiesgoPromedio());
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        assertEquals("lluvia", e2.getAlertasRecibidas().getLast().getTipo());
        assertEquals("calor", e1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(e1.riesgo(), 7);
        assertEquals(e2.riesgo(), 10);

    }

    @Test
    void test3(){
        central.emitirAlerta("calor", 6);
        central.emitirAlerta("lluvia", 8);
        central.eliminarEntidad(e1);
        central.emitirAlerta("granizo", 7);

        assertEquals(e1.getAlertasRecibidas().size(), 2);
        assertEquals(e1.riesgo(), 10);
        assertEquals(e2.getAlertasRecibidas().size(), 3);
        assertEquals(e2.riesgo(), 7);
        assertNotEquals(5, central.cantidadEntidadesNotificadas());
    }

    @Test
    void test4(){
        try {
            central.emitirAlerta("viento", 11);
        } catch (Exception e){
            assertEquals("Nivel de alerta incorrecto", e.getMessage());
        }
    }

    @Test
    void test5(){
        e2.setComportamiento(new RiesgoAcumulativo());
        central.emitirAlerta("Tornado", 9);
        central.emitirAlerta("Inundacion", 10);
        central.agregarEntidad(e1);
        central.emitirAlerta("Granizo", 7);
        assertEquals(e1.getAlertasRecibidas().size(), 4);
        assertEquals(central.cantidadEntidadesNotificadas(), 3);
        assertEquals(e2.riesgo(), 19);
    }

    @Test 
    void test6(){
        central.eliminarEntidad(e2);
        central.emitirAlerta("Sismo", 10);
        assertEquals(e1.getAlertasRecibidas().size(), 1);
        assertEquals(e2.getAlertasRecibidas().size(), 0);
        assertEquals(e1.getNombre(), "Escuela");
        assertEquals(e1.getComportamiento().getClass(), ar.edu.unahur.obj2.observer.riesgo.RiesgoCritico.class);

    }
    
    @Test
    void test7(){
        central.emitirAlerta("Incendio", 5);
        assertEquals(e1.getAlertasRecibidas().getFirst().esCritica(), false);
        central.emitirAlerta("Terremoto", 10);
        assertEquals(e2.getAlertasRecibidas().getLast().esCritica(), true);
        alerta = new Alerta("Derrumbe", 7);
        assertEquals(alerta.esCritica(), false);
        assertEquals(alerta.getNivel(), 7);
        assertEquals(alerta.getTipo(), "Derrumbe");
        assertEquals(e1.getAlertasRecibidas().size(), 2);
        assertFalse(e1.getAlertasRecibidas().getFirst().esCritica());
        assertFalse(e1.riesgo().equals(5.0));


    }

}
