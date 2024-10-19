package gace.controlador;

import gace.vista.DatosUtil;

import java.util.ArrayList;

public class MenuControlador {
    private DatosUtil datosUtil;
    private ExcursionControlador excursionControlador;
    private SocioControlador socioControlador;
    private InscripcionControlador inscripcionControlador;


    public MenuControlador() {
        this.datosUtil = new DatosUtil();
        this.socioControlador = new SocioControlador();
        this.excursionControlador = new ExcursionControlador();
        this.inscripcionControlador = new InscripcionControlador(this.excursionControlador, this.socioControlador);
    }

    public boolean menu(){
        int opcion = datosUtil.mostrarMenu();
        System.out.println("Opción seleccionada: " + opcion);
        switch (opcion) {
            case 1:
                if (socioControlador.nouSoci()) {
                    System.out.println("Socio añadido correctamente.");
                } else {
                    System.out.println("Error al añadir socio.");
                }
                break;
            case 2:
                socioControlador.mostrarSocios(1,0);
                break;
            case 3:
                if (excursionControlador.novaExcursio()) {
                    System.out.println(":D");
                } else {
                    System.out.println(":(");
                }

                break;
            case 4:
                if (excursionControlador.mostrarExcursiones()) {
                    System.out.println(":D");
                } else {
                    System.out.println(":(");
                }
                //vistaExcursion.mostrarExcursiones(listaExcursiones.getListaExcursiones());
                break;
            case 5:
                int ayudaVisualInsc = inscripcionControlador.solicitarAyudaVisual();
                if (inscripcionControlador.novaInscripcio(ayudaVisualInsc)) {
                    System.out.println(":D");
                } else {
                    System.out.println(":(");
                }
                break;
            case 6:
                if (inscripcionControlador.mostrarInscripciones()) {
                    System.out.println(":D");
                } else {
                    System.out.println(":(");
                }
                break;
            case 7:
                int ayuda = datosUtil.asistente();
                inscripcionControlador.mostrarSinInscripciones(ayuda);
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                return false;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
        return true;
    }
}
