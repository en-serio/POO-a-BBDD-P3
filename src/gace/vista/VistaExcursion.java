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

    public void detalleExcursion(Excursion excursion) {
        System.out.println("Detalles de la Excursión:");
        System.out.println("Código: " + excursion.getCodigo());
        System.out.println("Descripción: " + excursion.getDescripcion());
        System.out.println("Fecha: " + excursion.getFecha());
        System.out.println("Número de Días: " + excursion.getNoDias());
        System.out.println("Precio: " + excursion.getPrecio());
    }

    public Excursion formExcursion() {
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese la descripción de la excursión: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la fecha de la excursión (formato: yyyy-mm-dd): ");
        String fechaString = scanner.nextLine();
        Date fecha = null;
        try {
            // Convertir la cadena a objeto Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha = sdf.parse(fechaString);
        } catch (ParseException e) {
            System.out.println("Fecha no válida. Se asignará la fecha actual.");
            fecha = new Date(); // Asignar la fecha actual si hay un error
        }

        System.out.print("Ingrese el número de días: ");
        int noDias = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el precio de la excursión: ");
        double precio = Double.parseDouble(scanner.nextLine());

        // Crear una nueva instancia de Excursion con los datos recopilados
        return new Excursion(codigo, descripcion, fecha, noDias, precio);
    }
}
