package gace.modelo;

import java.util.ArrayList;

public class ListaInscripcion {
    private ArrayList<Inscripcion> listaInsc;

    public ListaInscripcion() {
        this.listaInsc = new ArrayList<>();
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        listaInsc.add(inscripcion);
    }

    public ArrayList<Inscripcion> getListaInsc() {
        return listaInsc;
    }
}
