package gace.vista;

import java.util.Scanner;

public class VistaInscripciones {
    private Scanner scanner;

    public VistaInscripciones() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarInscripciones(String listaInscripciones) {
        System.out.println(listaInscripciones);
    }

    public String pedirSocioInsc() {
        scanner.nextLine();
        System.out.print("Ingrese el número de socio: ");
        return scanner.nextLine();
    }

    public int vistaAyuda(){
        System.out.println("¿Quieres disponer de ayudas?");
        System.out.println("1. Si");
        System.out.println("2. No");
        return scanner.nextInt();
    }

    public String pedirExcursionInsc(){
        System.out.print("Ingrese el código de excursión: ");
        return scanner.nextLine();
    }
}
