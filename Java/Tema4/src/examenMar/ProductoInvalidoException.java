package examenMar;

/**
 * Excepción personalizada para productos inválidos.
 * Se lanza cuando el precio o el stock tienen valores incorrectos.
 */
public class ProductoInvalidoException extends Exception {
    
    // Constructor que recibe el mensaje de error
    public ProductoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
