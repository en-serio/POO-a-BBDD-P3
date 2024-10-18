package gace.vista;

import gace.modelo.Excursion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VistaExcursion {
    private Scanner scanner;

    public VistaExcursion() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarExcursiones(ArrayList<Excursion> lista) {
        System.out.println("Lista de Excursiones:");
        for (Excursion excursion : lista) {
            System.out.println(excursion);
        }
    }

    public void detalleExcursion(String excursion) {
        System.out.println(excursion);
    }

    public String formExcursion() {
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese la descripción de la excursión: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la fecha de la excursión (formato: yyyy-mm-dd): ");
        String fechaString = scanner.nextLine();

        System.out.print("Ingrese el número de días: ");
        int noDias = scanner.nextInt();

        System.out.print("Ingrese el precio de la excursión: ");
        double precio = scanner.nextDouble();

        return codigo + ',' + descripcion + ',' + fechaString + ',' + noDias + ',' + precio;
    }
}
