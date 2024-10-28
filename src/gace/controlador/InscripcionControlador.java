package gace.controlador;

import gace.modelo.Excursion;
import gace.modelo.Inscripcion;
import gace.modelo.Socio;
import gace.modelo.dao.InscripcionDao;
import gace.vista.DatosUtil;
import gace.vista.VistaInscripciones;
import gace.modelo.ListaInscripcion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InscripcionControlador {
    private final VistaInscripciones vistaInscripciones;
    private SocioControlador socioControlador;
    private ExcursionControlador excursionControlador;
    private InscripcionDao inscripcionDao;
    private DatosUtil datosUtil;

    public InscripcionControlador(ExcursionControlador excursionControlador, SocioControlador socioControlador) {
        this.vistaInscripciones = new VistaInscripciones();
        this.socioControlador = socioControlador;
        this.excursionControlador = excursionControlador;
        this.datosUtil = new DatosUtil();
    }
    public InscripcionControlador(){
        this.vistaInscripciones = new VistaInscripciones();
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


/*    public boolean mostrarExcVacia(int ayuda){
        ArrayList<Excursion> excursionesSin = new ArrayList<>();
        for (Excursion excursion : this.excursionControlador.getListaExcursion().getListaExcursiones()) {
            boolean tieneInscripcion = false;
            for (Inscripcion inscripcion : this.listaInscripcion.getListaInsc()) {
                if (inscripcion.getExcursion()!= null && inscripcion.getExcursion().equals(excursion)) {
                    tieneInscripcion = true;
                    break;
                }
            }
            if (!tieneInscripcion) {
                excursionesSin.add(excursion);
            }
        }
        if(excursionesSin.isEmpty()){
            datosUtil.mostrarError("No hay excursiones sin inscripciones");
            return false;
        }
        if(ayuda == 1){
            excursionControlador.mostrar(excursionesSin);
        }
        return excursionControlador.seleccionarExc(excursionesSin);
    }*/


    public int solicitarAyudaVisual() {
        return vistaInscripciones.vistaAyuda();
    }

    public boolean novaInscripcio(int ayuda) {
        if(ayuda == 1){
            socioControlador.mostrarSocios(0,4);
        }

        int strSocio = vistaInscripciones.pedirSocioInsc();
        if (strSocio == null) {
            return false;
        }

        Socio soc = this.socioControlador.buscarSocio(strSocio);
        if(soc == null){
            datosUtil.mostrarError("Socio no encontrado");
            return false;
        }

        if (ayuda == 1) {
            excursionControlador.mostrarExcursiones();
        }

        int idExcursion = vistaInscripciones.pedirExcursionInsc();
        if (idExcursion == -1) {
            return false;
        }

        Excursion exc = this.excursionControlador.buscarExcursion(idExcursion);
        if(exc == null){
            datosUtil.mostrarError("Excursión no encontrada");
            return false;
        }

        String codigoExc= this.getCodigoExcursion(soc.getIdSocio(), exc.getCodigo(), exc.getFecha());

        Inscripcion ins = new Inscripcion(idExcursion, codigoExc, soc, exc, new Date());
        if (ins == null){
            return false;
        }
        listaInscripcion.anyadirInscripcion(ins);

        return true;
    }

    public String getCodigoExcursion(int socNoSocio, String excCodigo, Date excFecha){
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
}
