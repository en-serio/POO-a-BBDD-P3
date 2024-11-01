package gace.controlador;

import gace.modelo.*;
import gace.modelo.dao.DAOFactory;
import gace.modelo.dao.InscripcionDao;
import gace.vista.DatosUtil;
import gace.vista.VistaInscripciones;

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
        int tipo = datosUtil.pedirOpcion("¿Elegir socio o Crear uno nuevo?", "Elegir socio", "Crear nuevo socio");
        if(tipo == 1) {
            Socio soc = socioControlador.crearSocio();
        } else if (tipo == 0) {
            return false;
        }
        if(ayuda == 1){
            socioControlador.mostrarSocios(0,4);
        }
        int strSocio = vistaInscripciones.pedirSocioInsc();
        if (strSocio == 0) {
            return false;
        }
        Socio soc = this.socioControlador.buscarSocio(strSocio);
        if(soc == null){
            datosUtil.mostrarError("Socio no encontrado");
            return false;
        }
        String codigo = getCodigoExcursion(soc.getIdSocio(), exc.getCodigo(), exc.getFecha());
        Inscripcion ins = new Inscripcion(codigo, soc, exc);
        if (ins == null){
            return false;
        }
        DAOFactory.getInscripcionDao().insertar(ins);
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
        for(Inscripcion inscripcion : DAOFactory.getInscripcionDao().listar()) {
            vistaInscripciones.mostrarInscripciones(inscripcion.toString());
        }
        return true;
    }

    public Inscripcion buscarInscripcion() {
        int buscar = datosUtil.pedirOpcion("Buscar Inscripcion", "Por identificador", "Por Excursion", "Por Socio");
        if(buscar == 0){
            return null;
        }
        Excursion excursion = null;
        Socio socio = null;
        int idInscripcion = 0;
        ArrayList<Inscripcion> insc = null;
        Inscripcion inscripcion = null;
        if(buscar == 1){
            int forma = datosUtil.pedirOpcion("Buscar Inscripcion", "código", "ID");
            if(forma == 0){
                return null;
            }
            mostrarInscripciones();
            if(forma == 1){
                String codigo = datosUtil.devString("Introduce el código de la inscripción");
                return DAOFactory.getInscripcionDao().buscar(codigo);
            } else {
                idInscripcion = datosUtil.leerEntero(99999,"Introduce el ID de la inscripción" );
                return DAOFactory.getInscripcionDao().buscar(idInscripcion);
            }
        } else if(buscar == 2){
            excursionControlador.mostrarExcursiones();
            excursion = excursionControlador.pedirExcursion();
            if(excursion == null){
                return null;
            }
            insc = DAOFactory.getInscripcionDao().listarXExc(excursion);
            mostrarLista(insc);
            socio = socioControlador.obtenerSocio();
            if(socio == null){
                return null;
            }
            inscripcion = buscarLista(insc, socio);
            return inscripcion;
        } else {
            socioControlador.mostrarSocios(0, 4);
            socio = socioControlador.obtenerSocio();
            if(socio == null){
                return null;
            }else if(socio instanceof SocioEstandar) {
                insc = DAOFactory.getInscripcionDao().ListarXSocioEst(socio);
            }else if(socio instanceof SocioFederado){
                insc = DAOFactory.getInscripcionDao().ListarXSocioFed(socio);
            }else{
                insc = DAOFactory.getInscripcionDao().ListarXSocioInf(socio);
            }
            mostrarLista(insc);
            excursion = excursionControlador.pedirExcursion();
            if(excursion == null){
                return null;
            }
            inscripcion = buscarLista(insc, excursion);
            return inscripcion;
        }
    }

    public void mostrarLista(ArrayList<Inscripcion> inscripciones){
        for(Inscripcion inscripcion : inscripciones){
            vistaInscripciones.mostrarInscripciones(inscripcion.toString());
        }
    }

    public Inscripcion buscarLista(ArrayList<Inscripcion> inscripciones, Socio socio){
        for(Inscripcion inscripcion : inscripciones){
            if(inscripcion.getSocio().equals(socio)){
                vistaInscripciones.mostrarInscripciones(inscripcion.toString());
                return inscripcion;
            }
        }
        return null;
    }
    public Inscripcion buscarLista(ArrayList<Inscripcion> inscripciones, Excursion exc){
        for(Inscripcion inscripcion : inscripciones){
            if(inscripcion.getExcursion().equals(exc)){
                vistaInscripciones.mostrarInscripciones(inscripcion.toString());
                return inscripcion;
            }
        }
        return null;
    }

    public boolean eliminarInscripcion(){
        Inscripcion insc = buscarInscripcion();
        if(insc == null){
            datosUtil.mostrarError("Inscripción no encontrada");
            return false;
        }
        if(compararFecha(insc.getExcursion().getFecha())){
            datosUtil.mostrarError("No se puede eliminar una inscripción de una excursión que ya ha pasado");
            return false;
        }
        DAOFactory.getInscripcionDao().eliminar(insc.getIdInscripcion());
        return true;
    }

    public boolean compararFecha(Date fecha){
        Date fechaActual = new Date();
        return fecha.after(fechaActual) || !fecha.equals(fechaActual);
    }

//    public boolean mostrarSinInscripciones(int ayuda) {
//        ArrayList<Socio> sociosSin =  new ArrayList<>();
//        for(Socio socio : socioControlador.getLista().getListaSocios()) {
//            boolean tieneInscripcion = false;
//            for(Inscripcion inscripcion : this.listaInscripcion.getListaInsc()) {
//                if(inscripcion.getSocio().equals(socio)){
//                    tieneInscripcion = true;
//                    break;
//                }
//            }
//            if(!tieneInscripcion){
//                sociosSin.add(socio);
//            }
//        }
//        if(ayuda == 1){
//            socioControlador.mostrarListaSociosSelec(sociosSin);
//        }
//        return socioControlador.seleccionarSocio(sociosSin);
//    }
    public boolean calcularCuota(){
        Socio socio = socioControlador.obtenerSocio();
        if(socio == null){
            return false;
        }
        ArrayList<Inscripcion> inscripcions = null;
        if(socio instanceof SocioEstandar){
            inscripcions = DAOFactory.getInscripcionDao().ListarXSocioEst(socio);
        }else if(socio instanceof SocioFederado){
            inscripcions = DAOFactory.getInscripcionDao().ListarXSocioFed(socio);
        } else {
            inscripcions = DAOFactory.getInscripcionDao().ListarXSocioInf(socio);
        }
        double totalExc = 0;
        int nExc =0;
        if(inscripcions != null){
            for(Inscripcion inscripcion : inscripcions){
                totalExc += inscripcion.getExcursion().getPrecio();
            }
            nExc = inscripcions.size();
        }
        double cuota = socio.calcularCuota();
        totalExc = socio.costeExcursion(totalExc);
        vistaInscripciones.mostrarCuota(cuota, totalExc, nExc);
        return true;
    }

}
