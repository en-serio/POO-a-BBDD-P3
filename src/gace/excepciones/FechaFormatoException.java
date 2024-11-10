package gace.excepciones;

public class FechaFormatoException extends GaceException {
    public FechaFormatoException(String fechaString) {
        super("La fecha proporcionada (" + fechaString + ") no tiene un formato válido.");
    }
}
