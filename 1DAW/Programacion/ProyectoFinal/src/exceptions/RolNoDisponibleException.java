package exceptions;

// Excepcion que se lanza cuando se intenta fichar un jugador
// con un rol que ya esta ocupado en los titulares del equipo
public class RolNoDisponibleException extends Exception {

    public RolNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
