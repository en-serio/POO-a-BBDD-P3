package gace.controlador;

import gace.modelo.Excursion;
import gace.modelo.Inscripcion;
import gace.modelo.Socio;
import gace.vista.VistaInscripciones;
import gace.modelo.ListaInscripcion;

import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InscripcionControlador {
    private VistaInscripciones vistaInscripciones;
    private ListaInscripcion listaInscripcion;
    private SocioControlador socioControlador;
    private ExcursionControlador excursionControlador;

    public InscripcionControlador(ExcursionControlador excursionControlador, SocioControlador socioControlador) {
        this.vistaInscripciones = new VistaInscripciones();
        this.listaInscripcion = new ListaInscripcion();
        this.socioControlador = socioControlador;
        this.excursionControlador = excursionControlador;
        this.llenarinsc();
    }
    public InscripcionControlador(){
        this.vistaInscripciones = new VistaInscripciones();
        this.listaInscripcion = new ListaInscripcion();
    }

    public ListaInscripcion getListaInscripcion() {
        return listaInscripcion;
    }

    public void setInscripcionControlador(SocioControlador socioControlador) {
        this.socioControlador = socioControlador;
    }

    public void setExcursionControlador(ExcursionControlador excursionControlador) {
        this.excursionControlador = excursionControlador;
    }

    public void agregarInscripcion(Inscripcion inscripcion) {

    }
    public void mostrarInscripcion(Inscripcion inscripcion) {

    }
    public void eliminarInscripcion(Inscripcion inscripcion){ //repasar condicion de permite eliminar si?

    }


    public int solicitarAyudaVisual() {
        return vistaInscripciones.vistaAyuda();
    }

    public boolean novaInscripcio(int ayuda) {
        if(ayuda == 1){
            socioControlador.mostrarSocios(0,4);
        }
        String strSocio = vistaInscripciones.pedirSocioInsc();
        if (strSocio == null) {
            return false;
        }
        Socio soc = this.socioControlador.buscarSocio(strSocio);
        if(soc == null){
            System.out.println("Socio no encontrado");
            return false;
        }else {
            System.out.println("Socio encontrado");
        }
        if (ayuda == 1) {
            excursionControlador.mostrarExcursiones();
        }
        String strExcursion = vistaInscripciones.pedirExcursionInsc();
        if (strExcursion == null) {
            return false;
        }
        Excursion exc = this.excursionControlador.buscarExcursion(strExcursion);
        if(exc == null){
            System.out.println("Excursión no encontrada");
            return false;
        }else {
            System.out.println("Excursión encontrada");
        }
        String codigoExc= this.getCodigoExcursion(soc.getNoSocio(), exc.getCodigo(), exc.getFecha());

        Inscripcion ins = new Inscripcion(codigoExc, soc, exc, new Date());
        listaInscripcion.anyadirInscripcion(ins);

        return true;
    }

    public String getCodigoExcursion(String socNoSocio, String excCodigo, Date excFecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(excFecha);
        String dia = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
        String mes = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
        return socNoSocio + excCodigo + dia + mes;

    }

    public boolean mostrarInscripciones() {
        for(Inscripcion inscripcion : this.listaInscripcion.getListaInsc()) {
            vistaInscripciones.mostrarInscripciones(inscripcion.toString());
        }
        return true;
    }

    public boolean mostrarSinInscripciones(int ayuda) {
        ArrayList<Socio> sociosSin =  new ArrayList<>();
        for(Socio socio : socioControlador.getLista().getListaSocios()) {
            boolean tieneInscripcion = false;
            for(Inscripcion inscripcion : this.listaInscripcion.getListaInsc()) {
                if(inscripcion.getSocio().equals(socio)){
                    tieneInscripcion = true;
                    break;
                }
            }
            if(!tieneInscripcion){
                sociosSin.add(socio);
            }
        }
        if(ayuda == 1){
            socioControlador.mostrarListaSociosSelec(sociosSin);
        }
        return socioControlador.seleccionarSocio(sociosSin);
    }

    public void llenarinsc(){
        this.listaInscripcion.anyadirInscripcion(new Inscripcion("1", socioControlador.buscarSocio("101"), excursionControlador.buscarExcursion("1"), new Date()));
        this.listaInscripcion.anyadirInscripcion( new Inscripcion("2", socioControlador.buscarSocio("102"), excursionControlador.buscarExcursion("2"), new Date()));
        this.listaInscripcion.anyadirInscripcion(new Inscripcion("3", socioControlador.buscarSocio("103"), excursionControlador.buscarExcursion("3"), new Date()));
        this.listaInscripcion.anyadirInscripcion(new Inscripcion("4", socioControlador.buscarSocio("104"), excursionControlador.buscarExcursion("4"), new Date()));
        this.listaInscripcion.anyadirInscripcion(new Inscripcion("5", socioControlador.buscarSocio("105"), excursionControlador.buscarExcursion("5"), new Date()));
        this.listaInscripcion.anyadirInscripcion( new Inscripcion("6", socioControlador.buscarSocio("106"), excursionControlador.buscarExcursion("6"), new Date()));
        this.listaInscripcion.anyadirInscripcion( new Inscripcion("7", socioControlador.buscarSocio("107"), excursionControlador.buscarExcursion("7"), new Date()));
        this.listaInscripcion.anyadirInscripcion(new Inscripcion("8", socioControlador.buscarSocio("108"), excursionControlador.buscarExcursion("8"), new Date()));
    }

}
