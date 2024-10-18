package gace.modelo;

public class SocioEstandar extends Socio {
    private String nif;
    private Seguro seguro;
    private final double cuotaBase = 100.0;

    public SocioEstandar(String noSocio, String nombre) {
        super(noSocio, nombre);
        this.nif = nif;
        this.seguro = seguro;
    }

    public SocioEstandar() {}

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public double calcularCuota() {
        return cuotaBase;
    }

    @Override
    public double costeExcursion(Excursion excursion) {
        return excursion.getPrecio() + seguro.getPrecio();
    }
}
