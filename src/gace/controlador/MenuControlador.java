package gace.controlador;

import gace.vista.DatosUtil;

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

    public MenuControlador(DatosUtil datosUtil){
        this.datosUtil = datosUtil;
        this.socioControlador = new SocioControlador();
        this.excursionControlador = new ExcursionControlador();
        this.inscripcionControlador = new InscripcionControlador(this.excursionControlador, this.socioControlador);
    }

    public DatosUtil getDatosUtil() {
        return datosUtil;
    }

    public ExcursionControlador getExcursionControlador() {
        return excursionControlador;
    }

    public SocioControlador getSocioControlador() {
        return socioControlador;
    }

    public InscripcionControlador getInscripcionControlador() {
        return inscripcionControlador;
    }

    public boolean menu(){
        int opcion = datosUtil.mostrarMenu();
        switch (opcion) {
            case 1:
                if (!socioControlador.nouSoci()) { datosUtil.mostrarError("Error al añadir socio."); }
                break;
            case 2:
                if(!socioControlador.mostrarSocios(1,0)){ datosUtil.mostrarError("Error al mostrar socios.");}
                break;
            case 3:
                if (!excursionControlador.novaExcursio()) { datosUtil.mostrarError(":("); }
                break;
            case 4:
                if (!excursionControlador.mostrarExcursiones()) { datosUtil.mostrarError(":("); }
                break;
            case 5:
                int ayudaVisualInsc = inscripcionControlador.solicitarAyudaVisual();
                if (!inscripcionControlador.novaInscripcio(ayudaVisualInsc)) { datosUtil.mostrarError(":("); }
                break;
            case 6:
                if (!inscripcionControlador.mostrarInscripciones()) { datosUtil.mostrarError(":("); }
                break;
            case 7:
                int ayuda = datosUtil.asistente();
                if(!inscripcionControlador.mostrarSinInscripciones(ayuda)){ datosUtil.mostrarError(":("); }
                break;
            case 8:
                int ayudaExc = datosUtil.asistente();
                if(!inscripcionControlador.mostrarExcVacia(ayudaExc)){ datosUtil.mostrarError(":("); }
                break;
            case 0:
                datosUtil.mostrarError("Saliendo del programa...");
                return false;
            default:
                datosUtil.mostrarError("Opción no válida. Inténtelo de nuevo.");
                break;
        }
        return true;
    }

    public void cerrarTeclado(){
        datosUtil.cerrarTeclado();
    }
}
