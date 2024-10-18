package gace;

import gace.controlador.ExcursionControlador;
import gace.controlador.InscripcionControlador;
import gace.controlador.SocioControlador;
import gace.modelo.*;
import gace.vista.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaExcursion listaExcursiones = new ListaExcursion();
        ListaInscripcion listaInscripciones = new ListaInscripcion();
        Scanner scanner = new Scanner(System.in);
        VistaSocios vistaSocios = new VistaSocios();
        VistaExcursion vistaExcursion = new VistaExcursion();
        VistaInscripciones vistaInscripciones = new VistaInscripciones();
        ExcursionControlador controladorExc = new ExcursionControlador();
        //SocioControlador controladorSoc = new SocioControlador(vistaSocios, listaSocios);
        SocioControlador controladorSoc = new SocioControlador();
        InscripcionControlador controladorIns = new InscripcionControlador(controladorExc, controladorSoc);



        Socio socio1 = new SocioEstandar("1", "Juan", "12345678A", new Seguro(true, 100));
        Federacion federacion1 = new Federacion("1234", "Federación de Prueba");
        Federacion federacion2 = new Federacion("5678", "Federación de Prueba 2");
        Federacion federacion3 = new Federacion("9101", "Federación de Prueba 3");
        Socio socio2 = new SocioFederado("2", "Pedro", "87654321B", federacion3);
        Socio socio3 = new SocioInfantil("3", "Ana", "4");
        Socio socio4 = new SocioEstandar("4", "María", "87654321C", new Seguro(false, 50));
        Socio socio5 = new SocioFederado("5", "Luis", "12345678D", federacion2);
        Socio socio6 = new SocioInfantil("6", "Lucía", "7");
        Socio socio7 = new SocioEstandar("7", "Carlos", "87654321E", new Seguro(true, 150));
        Socio socio8 = new SocioFederado("8", "Sara", "12345678F",  federacion1);
        Socio socio9 = new SocioInfantil("9", "Pablo", "10");
        Socio socio10 = new SocioEstandar("10", "Elena", "87654321G", new Seguro(false, 75));
        controladorSoc.getLista().agregarSocio(socio1);
        controladorSoc.getLista().agregarSocio(socio2);
        controladorSoc.getLista().agregarSocio(socio3);
        controladorSoc.getLista().agregarSocio(socio4);
        controladorSoc.getLista().agregarSocio(socio5);
        controladorSoc.getLista().agregarSocio(socio6);
        controladorSoc.getLista().agregarSocio(socio7);
        controladorSoc.getLista().agregarSocio(socio8);
        controladorSoc.getLista().agregarSocio(socio9);
        controladorSoc.getLista().agregarSocio(socio10);

        Date fecha1 = new Date(2021, 6, 1);
        Excursion excursion1 = new Excursion("1", "Excursión 1", fecha1, 10, 100);
        Excursion excursion2 = new Excursion("2", "Excursión 2", fecha1, 15, 150);
        Excursion excursion3 = new Excursion("3", "Excursión 3", fecha1, 20, 200);
        Excursion excursion4 = new Excursion("4", "Excursión 4", fecha1, 25, 250);
        Excursion excursion5 = new Excursion("5", "Excursión 5", fecha1, 30, 300);
        Excursion excursion6 = new Excursion("6", "Excursión 6", fecha1, 35, 350);
        Excursion excursion7 = new Excursion("7", "Excursión 7", fecha1, 40, 400);

        controladorExc.getListaExcursion().anyadirExcursion(excursion1);
        controladorExc.getListaExcursion().anyadirExcursion(excursion2);
        controladorExc.getListaExcursion().anyadirExcursion(excursion3);
        controladorExc.getListaExcursion().anyadirExcursion(excursion4);
        controladorExc.getListaExcursion().anyadirExcursion(excursion5);
        controladorExc.getListaExcursion().anyadirExcursion(excursion6);
        controladorExc.getListaExcursion().anyadirExcursion(excursion7);

        Inscripcion inscripcion1 = new Inscripcion("1", socio1, excursion1, new Date());
        Inscripcion inscripcion2 = new Inscripcion("2", socio2, excursion2, new Date());
        Inscripcion inscripcion3 = new Inscripcion("3", socio3, excursion3, new Date());
        Inscripcion inscripcion4 = new Inscripcion("4", socio4, excursion4, new Date());
        Inscripcion inscripcion5 = new Inscripcion("5", socio5, excursion5, new Date());
        Inscripcion inscripcion6 = new Inscripcion("6", socio6, excursion6, new Date());
        Inscripcion inscripcion7 = new Inscripcion("7", socio7, excursion7, new Date());
        Inscripcion inscripcion8 = new Inscripcion("8", socio8, excursion1, new Date());
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion1);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion2);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion3);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion4);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion5);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion6);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion7);
        controladorIns.getListaInscripcion().anyadirInscripcion(inscripcion8);


        boolean running = true;
        while (running) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Añadir Socio");
            System.out.println("2. Mostrar Socios");
            System.out.println("3. Añadir Excursión");
            System.out.println("4. Mostrar Excursiones");
            System.out.println("5. Inscribir Socio a Excursión");
            System.out.println("6. Mostrar Inscripciones");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    if (controladorSoc.nouSoci()) {
                        System.out.println("Socio añadido correctamente.");
                    } else {
                        System.out.println("Error al añadir socio.");
                    }
                    break;
                case 2:
                    if (controladorSoc.mostrarSocios(1,0)) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }
                    break;
                case 3:
                    if (controladorExc.novaExcursio()) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }

                    break;
                case 4:
                    if (controladorExc.mostrarExcursiones()) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }
                    //vistaExcursion.mostrarExcursiones(listaExcursiones.getListaExcursiones());
                    break;
                case 5:
                    int ayudaVisualInsc = controladorIns.solicitarAyudaVisual();
                    if (controladorIns.novaInscripcio(ayudaVisualInsc)) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }
                    break;
                case 6:
                    if (controladorIns.mostrarInscripciones()) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
