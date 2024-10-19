package gace.vista;

import java.util.Scanner;

public class VistaInscripciones {
    private Scanner scanner;
    private DatosUtil datosUtil;

    public VistaInscripciones() {
        this.scanner = new Scanner(System.in);
        this.datosUtil = new DatosUtil();
    }

    public void mostrarInscripciones(String listaInscripciones) {
        System.out.println(listaInscripciones);
    }

    public String pedirSocioInsc() {
        System.out.print("Ingrese el número de socio: ");
        return datosUtil.devString();
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
