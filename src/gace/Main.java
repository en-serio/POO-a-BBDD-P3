package gace;

import gace.controlador.MenuControlador;
public class Main {
    public static void main(String[] args) {
        System.out.println("AAAAA");
        MenuControlador controlador = new MenuControlador();
        System.out.println("BBBBBB");
        boolean running = true;
        while (running) {
            if(!controlador.menu()){
                System.out.println("Saliendo...");
                running = false;
            }
        }
        controlador.cerrarTeclado();
    }
}
