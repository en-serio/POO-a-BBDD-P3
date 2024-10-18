package grupofp.modelo;

public class SocioEstandar extends Socio {
    private String nif;
    private Seguro seguro;
    private final double cuotaBase = 100.0; // Ejemplo de cuota base

    public SocioEstandar(String noSocio, String nombre, String nif, Seguro seguro) {
        super(noSocio, nombre, TipoSocio.ESTÁNDAR);
        this.nif = nif;
        this.seguro = seguro;
    }

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
        return cuotaBase; // Cuota íntegra
    }

    @Override
    public double costeExcursion(Excursion excursion) {
        return excursion.getPrecio() + seguro.getPrecio(); // Precio de la excursión más el seguro
    }
}
