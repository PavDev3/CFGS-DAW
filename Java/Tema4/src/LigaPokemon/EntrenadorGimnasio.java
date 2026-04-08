package LigaPokemon;

/*
 * HERENCIA — Subclase EntrenadorGimnasio
 * ────────────────────────────────────────
 * "extends Entrenador" → hereda todos los atributos y métodos de Entrenador.
 *
 * Esta subclase:
 *   - Añade un atributo propio: medallasConseguidas
 *   - Implementa los métodos abstractos: calcularPuntuacion() y puedeParticipar()
 *   - Sobreescribe toString() para incluir su atributo propio
 *
 * Un EntrenadorGimnasio es un Entrenador que tiene como objetivo
 * conseguir medallas derrotando a los Líderes de Gimnasio de su región.
 */
public class EntrenadorGimnasio extends Entrenador {

    // Atributo propio de esta subclase (no existe en Entrenador)
    private int medallasConseguidas;

    // ── Constructor ──────────────────────────────────────────────────────────
    /*
     * super(...) llama al constructor de la clase padre (Entrenador).
     * SIEMPRE debe ser la primera línea del constructor de la subclase.
     * Después inicializamos el atributo propio de esta subclase.
     */
    public EntrenadorGimnasio(String id, String nombre, Region region,
                              double[][] victorias, int medallasConseguidas) {
        super(id, nombre, region, victorias); // llama al constructor de Entrenador
        this.medallasConseguidas = medallasConseguidas;
    }

    // Getter del atributo propio
    public int getMedallasConseguidas() { return medallasConseguidas; }

    // ── Implementación de calcularPuntuacion() ───────────────────────────────
    /*
     * @Override indica que este método sobreescribe el abstracto de la clase padre.
     * Fórmula: puntuacion = totalVictorias + medallasConceguidas * 3
     *
     * Las medallas valen más porque requieren derrotar a un Líder de Gimnasio.
     */
    @Override
    public double calcularPuntuacion() {
        return calcularTotalVictorias() + medallasConseguidas * 3;
    }

    // ── Implementación de puedeParticipar() (de la interfaz Clasificable) ───
    /*
     * Un EntrenadorGimnasio puede participar en la Liga si su puntuación es >= 50.
     * Esta condición es específica de este tipo de entrenador.
     */
    @Override
    public boolean puedeParticipar() {
        return calcularPuntuacion() >= 50;
    }

    // ── toString sobreescrito ────────────────────────────────────────────────
    /*
     * Llamamos a super.toString() para reutilizar el texto del padre,
     * y añadimos la información propia de esta subclase.
     */
    @Override
    public String toString() {
        return "EntrenadorGimnasio{id='" + id + "', nombre='" + nombre +
               "', region=" + region +
               ", medallas=" + medallasConseguidas + "}";
    }
}
