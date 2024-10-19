package gace.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VistaSocios {
    private Scanner scanner;
    private DatosUtil datosUtil;

    public VistaSocios() {
        this.scanner = new Scanner(System.in);
        this.datosUtil = new DatosUtil();
    }

    public void mostrarSocios(String socio) {
        System.out.println(socio);
    }

    public String formSocio() {
        //scanner.nextLine();
        System.out.print("Ingrese el número de socio: ");
        String noSocio = scanner.nextLine();
        //scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        //scanner.nextLine();
        System.out.print("Ingrese el tipo de socio (ESTÁNDAR, FEDERADO, INFANTIL): ");
        int tipoSocio = scanner.nextInt();
        scanner.nextLine();
        return tipoSocio + "," + noSocio + "," + nombre + "," + apellido;
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

    public String formNif(){
        System.out.print("Ingrese el NIF: ");
        String nif = scanner.nextLine();
        return nif;
    }

    public String formTutor(){
        System.out.print("Ingrese el número de tutor: ");
        return scanner.nextLine();
    }

    public String formFederacion(){
        System.out.print("Ingrese el código de la federación: ");
        String codigoFederacion = scanner.nextLine();
        System.out.print("Ingrese el nombre de la federación: ");
        String nombreFederacion = scanner.nextLine();
        return codigoFederacion + "," + nombreFederacion;
    }

    public String formSeguro(){
        System.out.print("Quin tipus de seguro: 1-COMPLETO 2-ESTÁNDAR");
        int tipoSeguro = scanner.nextInt();
        System.out.print("Preu del seguro:");
        double precioSeguro = scanner.nextDouble();
        return tipoSeguro + "," + precioSeguro;
    }

    public String pedirSocio(){
        System.out.print("Ingrese el número de socio: ");
        return scanner.nextLine();
    }

    public boolean confirmar(){
        return datosUtil.confirmar(" ");
    }
}
