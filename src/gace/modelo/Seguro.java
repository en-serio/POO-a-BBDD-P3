package gace.modelo;

public class Seguro {
    private int idSeguro;
    private boolean tipo;
    private double precio;

    public Seguro(boolean tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    public Seguro() { }

    public int getIdSeguro() {
        return idSeguro;
    }
    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                ", tipo=" + (tipo ? "COMPLETO" : "BÁSICO") +
                ", precio=" + precio +
                '}';
    }
}
