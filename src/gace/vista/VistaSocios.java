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

    public Socio formSocio() {
        System.out.print("Ingrese el número de socio: ");
        String noSocio = scanner.nextLine();
        System.out.print("Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el tipo de socio (ESTÁNDAR, FEDERADO, INFANTIL): ");
        TipoSocio tipoSocio = TipoSocio.valueOf(scanner.nextLine().toUpperCase());

        Socio socio = null; // Solo se declara una vez
        switch (tipoSocio) {
            case ESTÁNDAR:
                System.out.print("Ingrese el NIF: ");
                String nifEstandar = scanner.nextLine();
                System.out.print("Ingrese el precio del seguro: ");
                double precioSeguro = Double.parseDouble(scanner.nextLine());
                Seguro seguroEstandar = new Seguro(1, true, precioSeguro);
                socio = new SocioEstandar(noSocio, nombre, nifEstandar, seguroEstandar); // Se asigna a la variable existente
                break;
            case FEDERADO:
                System.out.print("Ingrese el NIF: ");
                String nifFederado = scanner.nextLine();
                System.out.print("Ingrese el código de la federación: ");
                String codigoFederacion = scanner.nextLine();
                Federacion federacion = new Federacion(codigoFederacion, "Federación de Ejemplo");
                socio = new SocioFederado(noSocio, nombre, nifFederado, federacion);
                break;
            case INFANTIL:
                System.out.print("Ingrese el número de tutor: ");
                String noTutor = scanner.nextLine();
                socio = new SocioInfantil(noSocio, nombre, noTutor); // Aquí se corrige la llamada al constructor
                break;
        }

        return socio;
    }
}
