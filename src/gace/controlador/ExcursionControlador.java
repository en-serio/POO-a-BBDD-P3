package gace.controlador;

import gace.modelo.Excursion;
import gace.vista.VistaExcursion;
import gace.modelo.ListaExcursion;
import gace.modelo.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcursionControlador {
    private VistaExcursion vistaExcursion;
    private ListaExcursion listaExcursion;

    public ExcursionControlador() {
        this.vistaExcursion = new VistaExcursion();
        this.listaExcursion = new ListaExcursion();
        this.llenarExc();
    }
    private Parser parser;

    public ListaExcursion getListaExcursion() {
        return listaExcursion;
    }

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
        if (datosExc.length < 5) {
            System.out.println("Datos de la excursión incompletos");
            return false;
        }
        Date data = null;
        try{
            data = validarFecha(datosExc[2]);
        } catch (ParseException e) {
            System.out.println("Fecha no válida");
            return false;
        }
        if(data == null){
            System.out.println("Fecha no válida");
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
    }
}
