package gace.modelo;

public class SocioEstandar extends Socio {
    private String nif;
    private Seguro seguro;
    private final double cuotaBase = 100.0;

    public SocioEstandar() {}

    public SocioEstandar(String noSocio, String nombre, String apellido, String nif, Seguro seguro) {
        super(noSocio, nombre, apellido);
        this.nif = nif;
        this.seguro = seguro;
    }

    //getters
    public String getNif() {
        return nif;
    }
    public Seguro getSeguro() {
        return seguro;
    }

    //setters
    public void setNif(String nif) {
        this.nif = nif;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        String tipo = seguro.isTipo() ? "Completo" : "Básico";
        return "Socio nº:" + this.getNoSocio() +", Nombre: " + this.getNombre() +
                ", Apellido: " + this.getApellido() +
                ", Tipo: Estandar" +
                ", Nif: '" + nif +
                ", Tipo de Seguro: " + tipo +
                ", cuotaBase=" + cuotaBase +
                '.';
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
