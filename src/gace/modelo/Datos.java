package gace.modelo;

import java.util.ArrayList;

public class Datos {
    private ListaSocios listaSocios;
    private ListaExcursion listaExcursiones;
    private ListaInscripcion listaInscripciones;

    public Datos() {
        this.listaSocios = new ListaSocios();
        this.listaExcursiones = new ListaExcursion();
        this.listaInscripciones = new ListaInscripcion();
    }

    public ListaSocios getListaSocios() {
        return listaSocios;
    }

    public ListaExcursion getListaExcursiones() {
        return listaExcursiones;
    }

    public ListaInscripcion getListaInscripciones() {
        return listaInscripciones;
    }
}
