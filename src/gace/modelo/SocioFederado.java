package gace.modelo;

public class SocioFederado extends Socio {
    private String nif;
    private Federacion federacion;
    private final double cuotaBase = 100.0;


    public SocioFederado(String nombre, String apellido, String nif, Federacion federacion) {
        super(nombre, apellido);
        this.nif = nif;
        this.federacion = federacion;
    }

    public SocioFederado() {}

    public String getNif() {
        return nif;
    }



    public void setNif(String nif) {
        this.nif = nif;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    @Override
    public String toString() {
        return "Socio nº:" + this.getIdSocio() +", Nombre de socio: " + this.getNombre() +
                ", Apellido: " + this.getApellido() +
                ", Tipo: Federado" +
                ", Nif: '" + nif +
                ", Federación: " + federacion.getNombre() +
                ", Cuota: " + cuotaBase +
                '.';
    }
    @Override
    public double calcularCuota() {
        return cuotaBase * 0.95; // 5% de descuento
    }

    @Override
    public double costeExcursion(double precio) {
        return precio * 0.90; // 10% de descuento
    }
}
