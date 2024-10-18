package gace;

import gace.controlador.ExcursionControlador;
import gace.controlador.InscripcionControlador;
import gace.controlador.SocioControlador;
import gace.modelo.*;
import gace.vista.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExcursionControlador controladorExc = new ExcursionControlador();
        InscripcionControlador controladorIns = new InscripcionControlador();
        SocioControlador controladorSoc = new SocioControlador();
        ListaSocios listaSocios = new ListaSocios();
        ListaExcursion listaExcursiones = new ListaExcursion();
        ListaInscripcion listaInscripciones = new ListaInscripcion();

        VistaSocios vistaSocios = new VistaSocios();
        VistaExcursion vistaExcursion = new VistaExcursion();
        VistaInscripciones vistaInscripciones = new VistaInscripciones();

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
                /*case 2:
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
            */}
        }
    }
}
