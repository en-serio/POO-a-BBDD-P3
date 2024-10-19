package gace.vista;

import gace.modelo.Excursion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
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
        String codigo = null;
        String descripcion = null;
        String fechaString = null;
        int noDias = 0;
        double precio = 0.0;

        try {
            System.out.print("Ingrese el codigo de la excursion: ");
            codigo = scanner.nextLine();

            System.out.print("Ingrese la descripcion de la excursion: ");
            descripcion = scanner.nextLine();

            System.out.print("Ingrese la fecha de la excursion (formato: yyyy-MM-dd): ");
            fechaString = scanner.nextLine();
            // Validar formato de la fecha
            validarFecha(fechaString);

            System.out.print("Ingrese el numero de dias: ");
            noDias = scanner.nextInt();
            if (noDias <= 0) {
                throw new IllegalArgumentException("El numero de dias debe ser mayor que cero.");
            }

            System.out.print("Ingrese el precio de la excursion: ");
            precio = scanner.nextDouble();
            if (precio < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada invalida. Asegurate de ingresar un numero valido para los dias o el precio.");
            scanner.nextLine();
        } catch (ParseException e) {
            System.out.println("Error: Fecha invalida. Usa el formato correcto: yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return codigo + ',' + descripcion + ',' + fechaString + ',' + noDias + ',' + precio;
    }
    private void validarFecha(String fechaString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Asegura que el formato de la fecha sea estricto
        Date fecha = dateFormat.parse(fechaString); // Lanza ParseException si la fecha es inválida
    }

}
