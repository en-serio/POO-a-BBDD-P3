package gace.controlador;

import gace.modelo.*;
import gace.vista.VistaSocios;
import gace.modelo.ListaSocios;

public class SocioControlador {
    private VistaSocios vistaSocios;
    private ListaSocios listaSocios;

    public SocioControlador() {
        this.vistaSocios = new VistaSocios();
        this.listaSocios = new ListaSocios();
    }


    public boolean nouSoci(){
        String strSocio = vistaSocios.formSocio();
        if (strSocio == null) {
            System.out.println("Error al crear el socio");
            return false;
        }
        String[] datosSocio = strSocio.split(",");
        if (datosSocio.length < 3) {
            System.out.println("Datos del socio incompletos");
            return false;
        }
        int tipoSocio = Integer.parseInt(datosSocio[0]);
        Socio socio = null;

        switch (tipoSocio) {
            case 1:
                if (datosSocio.length < 6) {
                    System.out.println("Datos del socio incompletos");
                    return false;
                }
                String noSocio1 = datosSocio[1];
                String nombre1 = datosSocio[2];
                String nif1 = datosSocio[3];
                int tipoSeguro = Integer.parseInt(datosSocio[4]);
                boolean tipoReal = tipoSeguro == 1;
                double precioSeguro = Double.parseDouble(datosSocio[5]);
                Seguro seguro = new Seguro(tipoReal, precioSeguro);
                socio = new SocioEstandar(noSocio1, nombre1, nif1, seguro);
                break;
            case 2:
                if (datosSocio.length < 6) {
                    System.out.println("Datos del socio incompletos");
                    return false;
                }
                String noSocio2 = datosSocio[1];
                String nombre2 = datosSocio[2];
                String nif2 = datosSocio[3];
                String codigoFederacion = datosSocio[4];
                String nombreFederacion = datosSocio[5];
                // Todo - buscar Federación??
                Federacion fede = new Federacion(codigoFederacion, nombreFederacion);
                socio = new SocioFederado(noSocio2, nombre2, nif2, fede);
                break;
            case 3:
                if (datosSocio.length < 4) {
                    System.out.println("Datos del socio incompletos");
                    return false;
                }
                String noSocio3 = datosSocio[1];
                String nombre3 = datosSocio[2];
                String noTutor = datosSocio[3];
                socio = new SocioInfantil(noSocio3, nombre3, noTutor);
                break;
            default:
                System.out.println("Tipo de socio no válido");
                return false;
        }
        vistaSocios.mostrarSocio(socio.toString());
        listaSocios.agregarSocio(socio);
        return true;
    }

}
