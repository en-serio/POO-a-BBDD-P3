package gace.excepciones;

public class GaceException extends Exception {
    public GaceException(String message) {
        super(message);
    }

    public GaceException(String message, Throwable cause) {
        super(message, cause);
    }
}
