package gace.excepciones;

public class ClienteExistenteException extends Exception {
    public ClienteExistenteException(String socio) {
        super("El socio " + socio + " ya existe.");
    }

}
