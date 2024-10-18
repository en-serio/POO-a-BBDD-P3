package gace;

import gace.controlador.ExcursionControlador;
import gace.controlador.InscripcionControlador;
import gace.controlador.SocioControlador;
import gace.modelo.*;
import gace.vista.*;

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
        InscripcionControlador controladorIns = new InscripcionControlador();
        //SocioControlador controladorSoc = new SocioControlador(vistaSocios, listaSocios);
        SocioControlador controladorSoc = new SocioControlador();


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
                    if (controladorSoc.mostrarSocios()) {
                        System.out.println(":D");
                    } else {
                        System.out.println(":(");
                    }
                    break;
                default:
                    break;
            }

                /*case 3:
                    Excursion nuevaExcursion = vistaExcursion.formExcursion();
                    if (nuevaExcursion != null) {
                        listaExcursiones.anyadirExcursion(nuevaExcursion);
                        System.out.println("Excursión añadida correctamente.");
                    }
                    break;
                case 4:
                    vistaExcursion.mostrarExcursiones(listaExcursiones.getListaExcursiones());
                    break;
                case 5:
                    Inscripcion nuevaInscripcion = vistaInscripciones.formInscripcion(listaSocios, listaExcursiones);
                    if (nuevaInscripcion != null) {
                        listaInscripciones.agregarInscripcion(nuevaInscripcion);
                        System.out.println("Inscripción añadida correctamente.");
                    }
                    break;
                case 6:
                    vistaInscripciones.mostrarInscripciones(listaInscripciones);
                    break;
                case 0:
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }*/
        }
    }
}
