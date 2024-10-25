package gace.controlador;

import gace.modelo.Excursion;
import gace.vista.DatosUtil;
import gace.vista.VistaExcursion;
import gace.modelo.ListaExcursion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExcursionControlador {
    private DatosUtil datosUtil;
    private VistaExcursion vistaExcursion;
    private ListaExcursion listaExcursion;

    public ExcursionControlador() {
        this.vistaExcursion = new VistaExcursion();
        this.listaExcursion = new ListaExcursion();
        this.datosUtil = new DatosUtil();
        this.llenarExc();
    }

    public ListaExcursion getListaExcursion() {
        return listaExcursion;
    }


    public boolean novaExcursio(){
        String strExcursio = this.vistaExcursion.formExcursion();
        String[] datosExc = strExcursio.split(",");
        if (datosExc.length < 5) {
            datosUtil.mostrarError("Datos de la excursión incompletos");
            return false;
        }
        Date data = null;
        try{
            data = validarFecha(datosExc[2]);
        } catch (ParseException e) {
            datosUtil.mostrarError("Fecha no válida");
            return false;
        }
        if(data == null){
            datosUtil.mostrarError("Fecha no válida");
            return false;
        }
        Excursion exc = new Excursion(datosExc[0], datosExc[1], data, Integer.parseInt(datosExc[3]), Double.parseDouble(datosExc[4]));
        listaExcursion.anyadirExcursion(exc);
        vistaExcursion.detalleExcursion(exc.toString());
        return true;
    }

    private Date validarFecha(String fechaString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Asegura que el formato de la fecha sea estricto
        return dateFormat.parse(fechaString); // Lanza ParseException si la fecha es inválida
    }

    public boolean mostrarExcursiones(){
        for (Excursion excursion : this.listaExcursion.getListaExcursiones()) {
            vistaExcursion.detalleExcursion(excursion.toString());
        }
        return true;
    }

    public Excursion buscarExcursion(String codigo){
        for(Excursion excursion : this.listaExcursion.getListaExcursiones()){
            if(excursion.getCodigo().equals(codigo)){
                return excursion;
            }
        }
        return null;
    }

    public void mostrar(ArrayList<Excursion> excursiones){
        for (Excursion excursion : excursiones) {
            vistaExcursion.detalleExcursion(excursion.toString());
        }
    }

    public boolean seleccionarExc(ArrayList<Excursion> excursiones){
        String codigo = vistaExcursion.pedirExc();
        for(Excursion excur : excursiones){
            if(excur.getCodigo().equals(codigo)) {
                vistaExcursion.mostrarExcursiones("Es esta la excursion que desea eliminar " + excur.toString() + "?");
                if (vistaExcursion.confExc()) {
                    listaExcursion.getListaExcursiones().remove(excur);
                    datosUtil.mostrarError("Excursion eliminada");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private void llenarExc(){
        Date fecha1 = new Date(2024, 11, 15);
        Date fecha2 = new Date(2024, 11, 20);
        Date fecha3 = new Date(2024, 12, 12);
        this.listaExcursion.anyadirExcursion(new Excursion("1", "Excursión 1", fecha1, 2, 15));
        this.listaExcursion.anyadirExcursion(new Excursion("2", "Excursión 2", fecha2, 5, 45));
        this.listaExcursion.anyadirExcursion( new Excursion("3", "Excursión 3", fecha3, 3, 30));
        this.listaExcursion.anyadirExcursion( new Excursion("4", "Excursión 4", fecha1, 2, 25));
        this.listaExcursion.anyadirExcursion(new Excursion("5", "Excursión 5", fecha2, 3, 30));
        this.listaExcursion.anyadirExcursion( new Excursion("6", "Excursión 6", fecha3, 3, 35));
        this.listaExcursion.anyadirExcursion(new Excursion("7", "Excursión 7", fecha1, 4, 40));
        this.listaExcursion.anyadirExcursion(new Excursion("8", "Excursión 8", fecha1, 4, 40));
        this.listaExcursion.anyadirExcursion(new Excursion("9", "Excursión 9", fecha3, 4, 40));
        this.listaExcursion.anyadirExcursion(new Excursion("10", "Excursión 10", fecha1, 4, 40));
        this.listaExcursion.anyadirExcursion(new Excursion("11", "Excursión 11", fecha2, 4, 40));
    }
}
