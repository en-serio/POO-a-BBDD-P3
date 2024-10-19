package gace.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DatosUtil {
    private Scanner scanner;

    public DatosUtil() {
        this.scanner = new Scanner(System.in);
    }

    public int leerEntero(int maximo, String mensaje) {
        System.out.print(mensaje);
        do {
            try{
                int valor = scanner.nextInt();
                if (valor >= 0 && valor <= maximo) {
                    return valor;
                }
                System.out.println("Error: Valor fuera de rango. Debe ser un número entre 0 y " + maximo + ".");
            } catch (InputMismatchException e) {
                System.err.println("Error: Entrada invalida. Por favor, ingresa un numero.");
                if(salir()==-1) {
                    return -1;
                }
                leerEntero(maximo, mensaje);
            }
        } while (true);
    }
    public int leerEntero(int maximo, int minimo, String mensaje) {
        System.out.print(mensaje);
        do {
            try{
                int valor = scanner.nextInt();
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Error: Valor fuera de rango. Debe ser un número entre "+ minimo +" y " + maximo + ".");
            } catch (InputMismatchException e) {
                System.err.println("Error: Entrada invalida. Por favor, ingresa un numero.");
                if(salir()==-1) {
                    return -1;
                }
                leerEntero(maximo, mensaje);
            }
        } while (true);
    }

    public int salir() {
        System.out.println("Desea salir? (s/n)");
        if(scanner.nextLine().equalsIgnoreCase("s")) {
            return -1;
        }
        return 0;
    }

    public int mostrarMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Añadir Socio");
        System.out.println("2. Mostrar Socios");
        System.out.println("3. Añadir Excursión");
        System.out.println("4. Mostrar Excursiones");
        System.out.println("5. Inscribir Socio a Excursión");
        System.out.println("6. Mostrar Inscripciones");
        System.out.println("0. Salir");
        int opcion = leerEntero(6, "");
        return opcion;
    }

}
