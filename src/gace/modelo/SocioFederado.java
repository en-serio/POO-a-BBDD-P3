package gace.modelo;

public class SocioFederado extends Socio {
    private String nif;
    private Federacion federacion;
    private final double cuotaBase = 100.0;

    public SocioFederado(String noSocio, String nombre, String nif, Federacion federacion) {
        super(noSocio, nombre);
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
        return "SocioFederado{" +
                "nif='" + nif + '\'' +
                "Nombre: " + this.getNombre() +
                "No Socio: " + this.getNoSocio() +
                ", federacion=" + federacion +
                ", cuotaBase=" + cuotaBase +
                '}';
    }

    @Override
    public double calcularCuota() {
        return cuotaBase * 0.95; // 5% de descuento
    }

    @Override
    public double costeExcursion(Excursion excursion) {
        return excursion.getPrecio() * 0.90; // 10% de descuento
    }
}
