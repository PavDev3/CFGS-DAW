package exceptions;

// Excepcion que se lanza cuando se intenta convocar o fichar
// a un jugador que tiene una sancion activa
public class JugadorSancionadoException extends Exception {

    public JugadorSancionadoException(String mensaje) {
        super(mensaje);
    }
}
