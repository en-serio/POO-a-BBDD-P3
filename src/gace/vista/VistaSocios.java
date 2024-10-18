package gace.vista;

import gace.modelo.*;

import java.util.ArrayList;
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
        //scanner.nextLine();
        System.out.print("Ingrese el número de socio: ");
        String noSocio = scanner.nextLine();
        //scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        //scanner.nextLine();
        System.out.print("Ingrese el tipo de socio (ESTÁNDAR, FEDERADO, INFANTIL): ");
        int tipoSocio = scanner.nextInt();
        scanner.nextLine();
        String nif= null;
        String nouSoci = null;
        switch (tipoSocio) {
            case 1:
                System.out.print("Ingrese el NIF: ");
                nif = scanner.nextLine();
                System.out.print("Quin tipus de seguro: 1-COMPLETO 2-ESTÁNDAR");
                int tipoSeguro = scanner.nextInt();
                System.out.print("Preu del seguro:");
                double precioSeguro = scanner.nextDouble();
                scanner.nextLine();
                nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," +  nif + "," +  tipoSeguro + "," + precioSeguro;
                break;
            case 2:
                System.out.print("Ingrese el NIF: ");
                nif = scanner.nextLine();
                System.out.print("Ingrese el código de la federación: ");
                String codigoFederacion = scanner.nextLine();
                System.out.print("Ingrese el nombre de la federación: ");
                String nombreFederacion = scanner.nextLine();
                nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," +  nif + "," +  codigoFederacion+ "," + nombreFederacion;
                break;
            case 3:
                System.out.print("Ingrese el número de tutor: ");
                String noTutor = scanner.nextLine();
                nouSoci = tipoSocio + "," + noSocio + "," + nombre + "," +  noTutor;
                break;
            default:
                break;
        }
        return nouSoci;
    }

    public void mostrarSocio(String socio) {
        System.out.println(socio);
    }

    public int requerirFiltro(){
        System.out.println("¿Que socios quieres ver?");
        System.out.println("1. Socios estandar");
        System.out.println("2. Socios federados");
        System.out.println("3. Socios infantiles");
        System.out.println("4. Todos los socios");
        System.out.println("5. Socios sin excursiones");
        System.out.println("0. Salir");
        int opcion = scanner.nextInt();
        return opcion;
    }
}
