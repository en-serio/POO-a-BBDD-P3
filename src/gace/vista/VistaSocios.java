package gace.vista;

import gace.modelo.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VistaSocios {
    private Scanner scanner;

    public VistaSocios() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarSocios(ArrayList<Socio> lista) {
        System.out.println("Lista de Socios:");
        for (Socio socio : lista) {
            System.out.println(socio);
        }
    }

    public String formSocio() {
        String nouSoci = null;

        try {
            System.out.print("Ingrese el numero de socio: ");
            String noSocio = scanner.nextLine();

            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el tipo de socio (1-ESTANDAR, 2-FEDERADO, 3-INFANTIL): ");
            int tipoSocio = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer después de nextInt

            String nif = null;
            switch (tipoSocio) {
                case 1:
                    System.out.print("Ingrese el NIF: ");
                    nif = scanner.nextLine();
                    System.out.print("¿Qué tipo de seguro? (1-COMPLETO, 2-ESTANDAR): ");
                    int tipoSeguro = scanner.nextInt();
                    System.out.print("Precio del seguro: ");
                    double precioSeguro = scanner.nextDouble();
                    scanner.nextLine();
                    nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," + nif + "," + tipoSeguro + "," + precioSeguro;
                    break;
                case 2:
                    System.out.print("Ingrese el NIF: ");
                    nif = scanner.nextLine();
                    System.out.print("Ingrese el código de la federacion: ");
                    String codigoFederacion = scanner.nextLine();
                    System.out.print("Ingrese el nombre de la federacion: ");
                    String nombreFederacion = scanner.nextLine();
                    nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," + nif + "," + codigoFederacion + "," + nombreFederacion;
                    break;
                case 3:
                    System.out.print("Ingrese el numero de tutor: ");
                    String noTutor = scanner.nextLine();
                    nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," + noTutor;
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de socio no valido. Debe ser 1, 2 o 3.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada invalida. Por favor, asegurate de ingresar los datos correctos.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return nouSoci;
    }

    public void mostrarSocio(String socio) {
        System.out.println(socio);
    }

    public int requerirFiltro() {
        int opcion = -1;

        try {
            System.out.println("¿Qué socios quieres ver?");
            System.out.println("1. Socios estandar");
            System.out.println("2. Socios federados");
            System.out.println("3. Socios infantiles");
            System.out.println("4. Todos los socios");
            System.out.println("5. Socios sin excursiones");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();

            if (opcion < 0 || opcion > 5) {
                throw new IllegalArgumentException("Opción no valida. Debe ser un número entre 0 y 5.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada invalida. Por favor, ingresa un numero.");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return opcion;
    }
}
