package gace.modelo;

import java.util.ArrayList;

public class ListaSocios {
    private ArrayList<Socio> listaSocios;

    public ListaSocios() {
        this.listaSocios = new ArrayList<Socio>();
    }

    public void agregarSocio(Socio socio) {
        this.listaSocios.add(socio);
    }

    public Socio buscarSocio(String noSocio) {
        for (Socio socio : this.listaSocios) {
            if (socio.getNoSocio().equals(noSocio)) {
                return socio;
            }
        }
        return null;
    }



    public ArrayList<Socio> getListaSocios() {
        return this.listaSocios;
    }

    public ArrayList<Socio> getSocioFiltrado(int tipo){
        ArrayList<Socio> listaFiltrada = new ArrayList<>();
        for(Socio socio : this.listaSocios){
            if(socio instanceof SocioEstandar){
                if(tipo == 1){
                    listaFiltrada.add(socio);
                }
            }else if(socio instanceof SocioFederado){
                if(tipo == 2){
                    listaFiltrada.add(socio);
                }
            }else if(socio instanceof SocioInfantil){
                if(tipo == 3){
                    listaFiltrada.add(socio);
                }
            }
        }
        return listaFiltrada;
    }


}
