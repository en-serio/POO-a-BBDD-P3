package gace;

/*
* Super TODO
*           - Al crear inscripció obliga a crear soci - FET
*           -
*           -
*           -
* */
import gace.controlador.MenuControlador;
public class Main {
    public static void main(String[] args) {

        MenuControlador controlador = new MenuControlador();

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
