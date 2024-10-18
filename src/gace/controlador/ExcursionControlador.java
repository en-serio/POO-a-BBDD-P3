package gace.controlador;

import gace.modelo.Excursion;
import gace.modelo.Socio;
import gace.vista.VistaExcursion;
import gace.modelo.ListaExcursion;
import gace.modelo.Parser;

import java.util.Date;

public class ExcursionControlador {
    private VistaExcursion vistaExcursion;
    private ListaExcursion listaExcursion;

    public ExcursionControlador() {
        this.vistaExcursion = new VistaExcursion();
        this.listaExcursion = new ListaExcursion();
    }
    private Parser parser;

    public void agregarExcursion(Excursion excursion) {
        //listaExcursion.anyadirExcursion(excursion);
      //  vistaExcursion.detalleExcursion(excursion);
    }
    public void mostrarExcursiones(Excursion excursion) {
        //vistaExcursion.detalleExcursion(excursion);
    }
    public void eliminarExcursion(Excursion excursion){
       // listaExcursion.eliminarExcursion(excursion);
    }

    public boolean novaExcursio(){
        parser = new Parser();
        String strExcursio = this.vistaExcursion.formExcursion();
        String[] datosExc = strExcursio.split(",");
        if (datosExc.length < 3) {
            System.out.println("Datos de la excursión incompletos");
            return false;
        }
        Date data = parser.formatearFecha(datosExc[2]);
        if(data == null){
            System.out.println("Fecha no válida");
            return false;
        }
        Excursion exc = new Excursion(datosExc[0], datosExc[1], data, Integer.parseInt(datosExc[3]), Double.parseDouble(datosExc[4]));
        listaExcursion.anyadirExcursion(exc);
        vistaExcursion.detalleExcursion(exc.toString());
        return true;
    }
}
