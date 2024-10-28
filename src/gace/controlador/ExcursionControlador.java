package gace.controlador;

import gace.modelo.Excursion;
import gace.modelo.dao.DAOFactory;
import gace.modelo.dao.ExcursionDao;
import gace.modelo.dao.SocioDao;
import gace.vista.DatosUtil;
import gace.vista.VistaExcursion;
import gace.modelo.ListaExcursion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ExcursionControlador {
    private DatosUtil datosUtil;
    private VistaExcursion vistaExcursion;
    private ExcursionDao excursionDao;

    public ExcursionControlador() {
        this.vistaExcursion = new VistaExcursion();
        this.excursionDao = DAOFactory.getExcursionDao();
        this.datosUtil = new DatosUtil();
    }

    public boolean novaExcursio(){
        String strExcursio = this.vistaExcursion.formExcursion();
        String[] datosExc = strExcursio.split(",");
        if (datosExc.length < 4) {
            datosUtil.mostrarError("Datos de la excursión incompletos");
            return false;
        }
        Date data = null;
        try{
            data = validarFecha(datosExc[2]);
        } catch (ParseException e) {
            datosUtil.mostrarError("Fecha no válida");
            return false;
        }
        if(data == null){
            datosUtil.mostrarError("Fecha no válida");
            return false;
        }
        Excursion exc = new Excursion(datosExc[0], datosExc[1], data, Integer.parseInt(datosExc[3]), Double.parseDouble(datosExc[4]));
        excursionDao.insertar(exc);
        vistaExcursion.detalleExcursion(exc.toString());
        return true;
    }

    private Date validarFecha(String fechaString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return dateFormat.parse(fechaString);
    }

    public boolean mostrarExcursiones(){
        for(Excursion excursion : excursionDao.listar()){
            vistaExcursion.detalleExcursion(excursion.toString());
        }
        return true;
    }

    public Excursion buscarExcursion(int id_excursion){
        return excursionDao.buscar(id_excursion);
    }
    public Excursion buscarExcursion(String codigo){
        return excursionDao.buscar(codigo);
    }

    //TODO se usa?
    public void mostrar(ArrayList<Excursion> excursiones){
        for (Excursion excursion : excursiones) {
            vistaExcursion.detalleExcursion(excursion.toString());
        }
    }

    public boolean seleccionarExc(ArrayList<Excursion> excursiones){
        String codigo = vistaExcursion.pedirExc();
        for(Excursion excur : excursiones){
            if(excur.getCodigo().equals(codigo)) {
                vistaExcursion.mostrarExcursiones("Es esta la excursion que desea eliminar " + excur.toString() + "?");
                if (vistaExcursion.confExc()) {
//                    listaExcursion.getListaExcursiones().remove(excur);
                    excursionDao.eliminar(excur.getId());
                    datosUtil.mostrarError("Excursion eliminada");
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
