package LigaPokemon;

/*
 * INTERFAZ (interface)
 * ─────────────────────
 * Una interfaz es un "contrato": cualquier clase que la implemente (implements)
 * ESTÁ OBLIGADA a definir todos sus métodos.
 *
 * Las interfaces NO tienen atributos de instancia ni cuerpo de métodos (en Java básico).
 * Solo declaran la firma (nombre, parámetros, tipo de retorno) de los métodos.
 *
 * ¿Para qué sirve?
 * - Garantiza que ciertos objetos tienen determinado comportamiento.
 * - Permite tratar objetos de distintas clases de forma uniforme si implementan la misma interfaz.
 */
public interface Clasificable {

    /*
     * Método que deben implementar todas las clases que usen esta interfaz.
     * Devuelve true si el entrenador puede participar en el Campeonato de la Liga.
     * Cada subclase decide sus propias condiciones.
     */
    boolean puedeParticipar();
}
