package grupofp.controlador;

import grupofp.modelo.*;
import grupofp.vista.*;
import java.util.ArrayList;

public class Controlador {
    private VistaSocios vistaSocios;
    private VistaExcursion vistaExcursion;
    private VistaInscripciones vistaInscripciones;
    private Datos datos;

    public Controlador() {
        vistaExcursion = new VistaExcursion();
        vistaSocios = new VistaSocios();
        vistaInscripciones = new VistaInscripciones();
        datos = new Datos(); // Inicializar la clase Datos donde gestiones tus datos
    }

    public void agregarSocio(Socio socio) {
        datos.getListaSocios().agregarSocio(socio);
        vistaSocios.mostrarSocios(datos.getListaSocios().getListaSocios()); // Asegúrate que este método existe en ListaSocios
    }

    public void agregarExcursion(Excursion excursion) {
        datos.getListaExcursiones().anyadirExcursion(excursion);
        vistaExcursion.mostrarExcursiones(datos.getListaExcursiones().getListaExcursiones()); // Asegúrate que este método existe en ListaExcursion
    }

    public void inscribirSocio(Inscripcion inscripcion) {
        datos.getListaInscripciones().agregarInscripcion(inscripcion);
        vistaInscripciones.mostrarInscripciones(datos.getListaInscripciones()); // Asegúrate que este método existe en ListaInscripcion
    }

    public void mostrarExcursiones() {
        ArrayList<Excursion> listaExcursiones = datos.getListaExcursiones().getListaExcursiones(); // Ajusta para acceder a la lista de excursiones
        vistaExcursion.mostrarExcursiones(listaExcursiones);
    }

    public void mostrarSocios() {
        ArrayList<Socio> listaSocios = datos.getListaSocios().getListaSocios(); // Ajusta para acceder a la lista de socios
        vistaSocios.mostrarSocios(listaSocios);
    }

    public void mostrarInscripciones() {
        ListaInscripcion listaInscripciones = datos.getListaInscripciones(); // Aquí se obtiene directamente la lista de inscripciones
        vistaInscripciones.mostrarInscripciones(listaInscripciones);
    }
}
