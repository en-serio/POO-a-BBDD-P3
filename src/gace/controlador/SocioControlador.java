package gace.controlador;


import gace.excepciones.ClienteExistenteException;
import gace.modelo.*;
import gace.modelo.dao.DAOFactory;
import gace.modelo.dao.SocioDao;
import gace.vista.VistaSocios;
import gace.modelo.ListaSocios;
import gace.vista.DatosUtil;

import java.util.ArrayList;


public class SocioControlador {
    private VistaSocios vistaSocios;
    private SocioDao socioDao;
    private DatosUtil datosUtil;

    public SocioControlador(VistaSocios vistaSocios) {
        this.vistaSocios = vistaSocios;
        this.socioDao = DAOFactory.getSocioDao();
        this.datosUtil = new DatosUtil();
    }

    public SocioControlador() {
        this.vistaSocios = new VistaSocios();
        this.socioDao = DAOFactory.getSocioDao();
        this.datosUtil = new DatosUtil();
    }

    public boolean nouSoci(){
        String strSocio = vistaSocios.formSocio();
        if (strSocio == null) {
            datosUtil.mostrarError("Error al crear el socio");
            return false;
        }
        String[] datosSocio = strSocio.split(",");
        if (datosSocio.length < 3) {
            datosUtil.mostrarError("Datos del socio incompletos");
            return false;
        }
        int tipoSocio = Integer.parseInt(datosSocio[0]);
        switch (tipoSocio) {
            //EST
            case 1:
                SocioEstandar socioEst = nouSociEstandar( datosSocio[1], datosSocio[2]);
                if (socioEst == null) {
                    datosUtil.mostrarError("Error al crear el socio estándar");
                    return false;
                }
                break;
            //FED
            case 2:
                SocioFederado socioFed = nouSociFederado(datosSocio[1], datosSocio[2]);
                if (socioFed == null) {
                    datosUtil.mostrarError("Error al crear el socio estándar");
                    return false;
                }
                break;
            //INF
            case 3:
                SocioInfantil socioInf = nouSociInfantil(datosSocio[1], datosSocio[2]);
                if (socioInf == null) {
                    datosUtil.mostrarError("Error al crear el socio estándar");
                    return false;
                }
                listaSocios.agregarSocio(socioInf);
                break;
            default:
                datosUtil.mostrarError("Tipo de socio no válido");
                return false;
        }
        DAOFactory.getSocioDao().insertar(socioEst);
        return true;
    }

    public void comprobarSocio(String numero) throws ClienteExistenteException {
        for(Socio socio : listaSocios.getListaSocios()){
            if(socio.getNoSocio().equals(numero)){
                throw new ClienteExistenteException(numero);
            }
        }
    }

    public SocioEstandar nouSociEstandar(String nombre,String apellido){
        String nif = vistaSocios.formNif();
        if(nif == null){
            datosUtil.mostrarError("Nif no válido.");
            return null;
        }
        if(existeNif(nif)){
            datosUtil.mostrarError("Nif ya existe.");
            return null;
        }
        Seguro seg = nuevoSeg();
        if( seg == null){
            datosUtil.mostrarError("Seguro no válido.");
            return null;
        }
        DAOFactory.getSeguroDao().insertar(seg);
        return new SocioEstandar( nombre, apellido, nif, seg);
    }

    public SocioFederado nouSociFederado(String nombre, String apellido){
        String nif = vistaSocios.formNif();
        if(nif == null){
            datosUtil.mostrarError("Nif no válido.");
            return null;
        }
        if(existeNif(nif)){
            datosUtil.mostrarError("Nif ya existe.");
            return null;
        }
        Federacion fed = nuevaFed();
        if(fed == null){
            datosUtil.mostrarError("Federación no válida.");
            return null;
        }
        DAOFactory.getFederacionDao().insertar(fed);
        return new SocioFederado(nombre, apellido, nif, fed);
    }

    public boolean existeNif(String nif){
        return DAOFactory.getSocioDao().hayNif(nif);
    }

    public SocioInfantil nouSociInfantil(String nombre, String apellido){
        int noTutor = vistaSocios.formTutor();
        if(noTutor == 0){
            return null;
        }
        if(!buscarTutor(noTutor)){
            return null;
        }
        return new SocioInfantil(nombre, apellido, noTutor);
    }

    public SocioInfantil nouSociInfantil(String nombre, String apellido, int noTutor) {
        if(noTutor == 0){
            return null;
        }
        if(!buscarTutor(noTutor)){
            return null;
        }
        return new SocioInfantil(nombre, apellido, noTutor);
    }

    public boolean buscarTutor(int noTutor) {
        Socio socio = DAOFactory.getSocioDao().buscar(noTutor);
        if(socio != null){
            vistaSocios.noTutor();
            return false;
        }
        if(socio instanceof SocioInfantil){
            datosUtil.mostrarError("El tutor no puede ser un socio infantil.");
            return false;
        }
        return true;
    }

    public void mostrarListaSociosSelec(ArrayList<Socio> socios){
        for(Socio socio : socios){
            vistaSocios.mostrarSocio(socio.toString());
        }
    }


    public boolean mostrarSocios(int mostrarFiltro, int filtro) {
        int opcionSocios = 0;
        if(mostrarFiltro == 1){
            opcionSocios = vistaSocios.requerirFiltro();
        }else {
            opcionSocios = filtro;
        }
        switch (opcionSocios) {
            case 1:
                for(Socio socio : DAOFactory.getSocioEstandarDao().listar()) {
                    vistaSocios.mostrarSocio(socio.toString());
                }
            case 2:
                for(Socio socio : DAOFactory.getSocioFederadoDao().listar()) {
                    vistaSocios.mostrarSocio(socio.toString());
                }
            case 3:
                for(Socio socio : DAOFactory.getSocioInfantilDao().listar()) {
                    vistaSocios.mostrarSocio(socio.toString());
                }
            case 4:
                for(Socio socio : DAOFactory.getSocioDao().listar()) {
                    vistaSocios.mostrarSocio(socio.toString());
                }
                break;
            case 0:
                break;
            default:
                datosUtil.mostrarError("Opción no válida. Intente de nuevo.");
        }
        return true;
    }

    public Socio buscarSocio(int noSocio) {
        return DAOFactory.getSocioDao().buscar(noSocio);
    }

    public Federacion nuevaFed(){
        String fed = vistaSocios.formFederacion();
        String[] datosFed = fed.split(",");
        if (datosFed.length < 2) {
            datosUtil.mostrarError("Datos de la federación incompletos");
            return null;
        }
        //todo - validar que es nueva.
        Federacion federacion = new Federacion(datosFed[0], datosFed[1]);
        DAOFactory.getFederacionDao().insertar(federacion);
        return federacion;
    }

    public Seguro nuevoSeg(){
        String seg = vistaSocios.formSeguro();
        if (seg == null) {
            return null;
        }
        String[] datosSeg = seg.split(",");
        if (datosSeg.length < 2) {
            datosUtil.mostrarError("Datos del seguro incompletos");
            return null;
        }
        //todo - validar que es nuevo.

        boolean tipo = Integer.parseInt(datosSeg[0]) == 1;
        return new Seguro(tipo, Double.parseDouble(datosSeg[1]));
    }

    public boolean seleccionarSocio(ArrayList<Socio> socios){
        String codigo = vistaSocios.pedirSocio();
        for(Socio socio : socios){
            if(socio.getNoSocio().equals(codigo)) {
                vistaSocios.mostrarSocio("Es este el socio que desea eliminar " + socio.toString() + "?");
                if (vistaSocios.confirmar()) {
                    listaSocios.getListaSocios().remove(socio);
                    datosUtil.mostrarError("Socio eliminado");
                    return true;
                }
                return false;
            }
        }
        return false;
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
