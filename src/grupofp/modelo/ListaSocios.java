package grupofp.modelo;

import java.util.ArrayList;

public class ListaSocios {
    private ArrayList<Socio> listaSocios;

    public ListaSocios() {
        this.listaSocios = new ArrayList<>();
    }

    public void agregarSocio(Socio socio) {
        listaSocios.add(socio);
    }

    public Socio buscarSocio(String noSocio) {
        for (Socio socio : listaSocios) {
            if (socio.getNoSocio().equals(noSocio)) {
                return socio;
            }
        }
        return null; // No se encontró el socio
    }

    public ArrayList<Socio> getListaSocios() {
        return listaSocios;
    }
}
