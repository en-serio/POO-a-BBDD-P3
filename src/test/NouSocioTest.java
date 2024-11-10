package test;

import gace.controlador.MenuControlador;
import gace.modelo.Seguro;
import gace.modelo.SocioEstandar;
import gace.modelo.Socio;
import gace.modelo.SocioInfantil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class NouSocioTest {
/*
    @Test
    public void testCrearEst() {
        MenuControlador menuControlador = new MenuControlador();
        Seguro seguro = new Seguro(true, 50.0);
        SocioEstandar socioA = new SocioEstandar("120", "Alex", "Marquez", "545668413N", seguro);
        System.out.println("Socio A :"+socioA);
        menuControlador.getSocioControlador().getLista().agregarSocio(socioA);
        Socio socioB = menuControlador.getSocioControlador().buscarSocio("120");
        System.out.println("Socio B :" + socioB);
        assertEquals(socioA, socioB);
    }

    @Test
    public void testCrearInfantil(){
        MenuControlador menuControlador = new MenuControlador();
        SocioInfantil socioA = menuControlador.getSocioControlador().nouSociInfantil("121", "Alex", "Marquez", "101");
        System.out.println(socioA);
        menuControlador.getSocioControlador().getLista().agregarSocio(socioA);
        Socio socioB = menuControlador.getSocioControlador().buscarSocio("121");
        System.out.println(socioB);
        assertEquals(socioA, socioB);
    }

    @Test
    public void testInfantilNoTutor(){
        MenuControlador menuControlador = new MenuControlador();
        SocioInfantil socioInf = menuControlador.getSocioControlador().nouSociInfantil("121", "Alex", "Marquez", "122");
        //N socio tutor no existe, socio null
        assertNull(socioInf);
    }

    @Test
    public void testMostrarDatos(){
        MenuControlador mc = new MenuControlador();
        boolean exc = mc.getExcursionControlador().mostrarExcursiones();
        boolean soc = mc.getSocioControlador().mostrarSocios(0,4);
        boolean insc = mc.getInscripcionControlador().mostrarInscripciones();
        assertTrue(exc && soc && insc);
    }
*/
}