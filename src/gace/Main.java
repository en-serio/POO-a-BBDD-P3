package gace;

import gace.modelo.*;
import gace.vista.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Inicialización de listas y vistas
        ListaSocios listaSocios = new ListaSocios();
        ListaExcursion listaExcursiones = new ListaExcursion();
        ListaInscripcion listaInscripciones = new ListaInscripcion();

        VistaSocios vistaSocios = new VistaSocios();
        VistaExcursion vistaExcursion = new VistaExcursion();
        VistaInscripciones vistaInscripciones = new VistaInscripciones();

        // Menú principal
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
            int opcion = Integer.parseInt(System.console().readLine());

            switch (opcion) {
                case 1:
                    Socio nuevoSocio = vistaSocios.formSocio();
                    if (nuevoSocio != null) {
                        listaSocios.agregarSocio(nuevoSocio);
                        System.out.println("Socio añadido correctamente.");
                    }
                    break;
                case 2:
                    vistaSocios.mostrarSocios(listaSocios.getListaSocios());
                    break;
                case 3:
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
            }
        }
    }
}
