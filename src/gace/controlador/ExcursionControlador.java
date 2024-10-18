package gace.controlador;

import gace.modelo.Excursion;
import gace.vista.VistaExcursion;
import gace.modelo.ListaExcursion;

public class ExcursionControlador {
    private VistaExcursion vistaExcursion;
    private ListaExcursion listaExcursion;

    public void agregarExcursion(Excursion excursion) {
        listaExcursion.anyadirExcursion(excursion);
        vistaExcursion.detalleExcursion(excursion);
    }
    public void mostrarExcursiones(Excursion excursion) {
        vistaExcursion.detalleExcursion(excursion);
    }
    public void eliminarExcursion(Excursion excursion){
        listaExcursion.eliminarExcursion(excursion);
    }
}
