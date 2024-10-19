package gace.vista;

import gace.modelo.Excursion;

import java.util.ArrayList;

public class VistaExcursion {
    private DatosUtil datosUtil;

    public VistaExcursion() {
        this.datosUtil = new DatosUtil();
    }

    public void mostrarExcursiones(ArrayList<Excursion> lista) {
        System.out.println("Lista de Excursiones:");
        for (Excursion excursion : lista) {
            System.out.println(excursion);
        }
    }

    public void detalleExcursion(String excursion) {
        System.out.println(excursion);
    }

    public String formExcursion() {
        System.out.print("Ingrese el codigo de la excursion: ");
        String codigo = datosUtil.devString();

        System.out.print("Ingrese la descripcion de la excursion: ");
        String descripcion = datosUtil.devString();

        System.out.print("Ingrese la fecha de la excursion (formato: yyyy-MM-dd): ");
        String fechaString = datosUtil.devString();

        System.out.print("Ingrese el numero de dias: ");
        int noDias = datosUtil.leerEntero(100,1,"");

        System.out.print("Ingrese el precio de la excursion: ");
        double precio = datosUtil.leerDouble(0,"");
        return codigo + ',' + descripcion + ',' + fechaString + ',' + noDias + ',' + precio;
    }

}
