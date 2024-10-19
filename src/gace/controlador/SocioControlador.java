package gace.controlador;

// TODO Eliminar todos los souts del controlador o moverlos a vista

import gace.modelo.*;
import gace.vista.VistaSocios;
import gace.modelo.ListaSocios;

import java.util.ArrayList;


public class SocioControlador {
    private VistaSocios vistaSocios;
    private ListaSocios listaSocios;

    public SocioControlador(VistaSocios vistaSocios, ListaSocios listaSocios) {
        this.vistaSocios = vistaSocios;
        this.listaSocios = listaSocios;

    }

    public SocioControlador() {
        this.vistaSocios = new VistaSocios();
        this.listaSocios = new ListaSocios();
        llenarLista();
    }
    public ListaSocios getLista(){
        return this.listaSocios;
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
        switch (tipoSocio) {
            //EST
            case 1:
                SocioEstandar socioEst = nouSociEstandar(datosSocio[1], datosSocio[2], datosSocio[3]);
                if (socioEst == null) {
                    System.out.println("Error al crear el socio estándar");
                    return false;
                }
                /* TODO - Esto es necesario?? o es complicar por complicaR?
                if (!vistaSocios.confirmarSocio(socioEst.toString())) {
                    return false;
                }else {
                    listaSocios.agregarSocio(socioEst);
                }*/
                listaSocios.agregarSocio(socioEst);
                break;
            //FED
            case 2: // Socio federado
                SocioFederado socioFed = nouSociFederado(datosSocio[1], datosSocio[2], datosSocio[3]);
                if (socioFed == null) {
                    System.out.println("Error al crear el socio estándar");
                    return false;
                }
                listaSocios.agregarSocio(socioFed);
                break;
            //INF
            case 3:
                SocioInfantil socioInf = nouSociInfantil(datosSocio[1], datosSocio[2], datosSocio[3]);
                if (socioInf == null) {
                    System.out.println("Error al crear el socio estándar");
                    return false;
                }
                listaSocios.agregarSocio(socioInf);
                break;
            default:
                System.out.println("Tipo de socio no válido");
                return false;
        }
        return true;
    }

    public SocioEstandar nouSociEstandar(String noSocio,String nombre,String apellido){
        String nif = vistaSocios.formNif();
        if(nif == null){
            System.out.println("Nif no válido.");
            return null;
        }
        Seguro seg = nuevoSeg();
        if( seg == null){
            System.out.println("Seguro no válido.");
            return null;
        }
        return new SocioEstandar(noSocio, nombre, apellido, nif, seg);
    }

    public SocioFederado nouSociFederado(String noSocio, String nombre, String apellido){
        String nif = vistaSocios.formNif();
        if(nif == null){
            System.out.println("Nif no válido.");
            return null;
        }
        Federacion fed = nuevaFed();
        if( fed == null){
            System.out.println("Federación no válida.");
            return null;
        }
        return new SocioFederado(noSocio, nombre, apellido, nif, fed);
    }

    public SocioInfantil nouSociInfantil(String noSocio, String nombre, String apellido){
        String noTutor = vistaSocios.formTutor();
        if(noTutor == null){
            return null;
        }
        if(! buscarTutor(noTutor)){
            return null;
        }
        return new SocioInfantil(noSocio, nombre, apellido, noTutor);
    }

    public boolean buscarTutor(String noTutor) {
        for (Socio socio : listaSocios.getListaSocios()) {
            if (socio.getNoSocio().equals(noTutor)) {
                return true;
            }
        }
        return false;
    }


    public boolean mostrarSocios(int mostrarFiltro, int filtro) {
        int opcionSocios = 0; //en java hacia falta inicializar ??
        if(mostrarFiltro == 1){
            opcionSocios = vistaSocios.requerirFiltro();
        }else {
            opcionSocios = filtro;
        }
        ArrayList<Socio> lista = null;
        switch (opcionSocios) {
            case 1:
            case 2:
            case 3:
                lista = listaSocios.getSocioFiltrado(opcionSocios);
                break;
            case 4:
                lista = listaSocios.getListaSocios();
                break;
            /* Todo - implementar
            case 5:
                lista = listaSocios.getSociosSinExcursiones());
                break;*/
            case 0:
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
        if (lista == null) {
            return false;
        }
        for(Socio socio : lista) {
            vistaSocios.mostrarSocio(socio.toString());
        }
        return true;
    }

    public Socio buscarSocio(String noSocio) {
        return listaSocios.buscarSocio(noSocio);
    }

    public Federacion nuevaFed(){
        String fed = vistaSocios.formFederacion();
        String[] datosFed = fed.split(",");
        if (datosFed.length < 2) {
            System.out.println("Datos de la federación incompletos");
            return null;
        }
        //todo - validar que es nueva.
        return new Federacion(datosFed[0], datosFed[1]);
    }

    public Seguro nuevoSeg(){
        String seg = vistaSocios.formSeguro();
        String[] datosSeg = seg.split(",");
        if (datosSeg.length < 2) {
            System.out.println("Datos del seguro incompletos");
            return null;
        }
        //todo - validar que es nuevo.
        boolean tipo = Integer.parseInt(datosSeg[0]) == 1;
        return new Seguro(tipo, Double.parseDouble(datosSeg[1]));
    }

    private void llenarLista() {
        Federacion federacion1 = new Federacion("1234", "Montañeros Unidos");
        Federacion federacion2 = new Federacion("5678", "Excursionistas de Montaña");
        Federacion federacion3 = new Federacion("9101", "Federación de Montañismo");
        this.listaSocios.agregarSocio(new SocioEstandar("101", "Juan", "Ramirez", "12345678A", new Seguro(true, 100)));
        this.listaSocios.agregarSocio( new SocioFederado("102", "Pedro", "Martinez", "87654321B", federacion3));
        this.listaSocios.agregarSocio( new SocioInfantil("103", "Ana", "Lopez", "102"));
        this.listaSocios.agregarSocio( new SocioEstandar("104", "María", "Sanchez", "87654321C", new Seguro(false, 50)));
        this.listaSocios.agregarSocio( new SocioFederado("105", "Luis", "Rodriguez", "12345678D", federacion2));
        this.listaSocios.agregarSocio( new SocioInfantil("106", "Lucía", "Fernandez", "104"));
        this.listaSocios.agregarSocio( new SocioEstandar("107", "Carlos", "Perez", "87654321E", new Seguro(true, 150)));
        this.listaSocios.agregarSocio( new SocioFederado("108", "Sara", "Gonzalez", "12345678F", federacion1));
        this.listaSocios.agregarSocio( new SocioInfantil("109", "Pablo", "Gomez", "105"));
        this.listaSocios.agregarSocio( new SocioEstandar("110", "Elena", "Vazquez", "87654321G", new Seguro(false, 75)));
    }
}
