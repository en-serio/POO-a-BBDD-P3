package gace.vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DatosUtil {
    private Scanner scanner;

    public DatosUtil() {
        this.scanner = new Scanner(System.in);
    }

    public int leerEntero(int maximo, String mensaje) {
        if(!mensaje.isEmpty()) {
            System.out.print(mensaje);
        }
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
        if(!mensaje.isEmpty()){
            System.out.print(mensaje);
        }
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

    public String devString() {
        try{
            return scanner.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada invalida. Por favor, ingresa un numero.");
            if(salir()==-1) {
                return "-1";
            }
            devString();
        }
        return null;
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
        System.out.println("7. Eliminar Socio :(");
        System.out.println("0. Salir");
        int opcion = leerEntero(7, "");
        return opcion;
    }

    public int asistente(){
        System.out.println("Deseas ver los socios que no han sido inscritos en ninguna excursión?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero(2, "");
        return opcion;
    }

    public boolean confirmar(String mensaje){
        System.out.println(mensaje);
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero(2, "");
        return opcion == 1;
    }

    public double leerDouble(int minimo, String mensaje) {
        if(!mensaje.isEmpty()){
            System.out.print(mensaje);
        }
        do {
            try{
                double valor = scanner.nextDouble();
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("Error: Valor fuera de rango. Debe ser superior a " + minimo + ".");
            } catch (InputMismatchException e) {
                System.err.println("Error: Entrada invalida. Por favor, ingresa un numero.");
                if(salir()==-1) {
                    return -1;
                }
                leerDouble(minimo, mensaje);
            }
        } while (true);
    }

    public void mostrarError(String mensaje){
        System.err.println(mensaje);
    }

}
