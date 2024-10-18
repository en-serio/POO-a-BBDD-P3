package gace.vista;

import gace.modelo.*;

import java.util.Date;
import java.util.Scanner;

public class VistaInscripciones {
    private Scanner scanner;

    public VistaInscripciones() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarInscripciones(ListaInscripcion listaInscripciones) {
        System.out.println("Lista de Inscripciones:");
        for (Inscripcion inscripcion : listaInscripciones.getListaInsc()) {
            System.out.println(inscripcion);
        }
    }

    /*public String formInscripcion() {
        System.out.print("Ingrese el ID de Inscripción: ");
        String idInscripcion = scanner.nextLine();
        System.out.print("Ingrese el número de socio: ");
        String noSocio = scanner.nextLine();
        System.out.print("Ingrese el código de excursión: ");
        String codigoExcursion = scanner.nextLine();

        Socio socio = listaSocios.buscarSocio(noSocio);
        Excursion excursion = listaExcursiones.getExcursion(codigoExcursion);

        if (socio == null) {
            System.out.println("Modelo.Socio no encontrado.");
            return null;
        }
        if (excursion == null) {
            System.out.println("Excursión no encontrada.");
            return null;
        }

        return new Inscripcion(idInscripcion, socio, excursion, new Date());
    }*/
}
