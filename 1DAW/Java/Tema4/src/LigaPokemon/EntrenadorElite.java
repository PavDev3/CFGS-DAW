package LigaPokemon;

/*
 * HERENCIA — Subclase EntrenadorElite
 * ─────────────────────────────────────
 * Segundo tipo de entrenador. También hereda de Entrenador.
 *
 * Un EntrenadorElite ya ha superado los gimnasios y compite directamente
 * en torneos de alto nivel. Sus victorias en Torneo valen más.
 *
 * Diferencias con EntrenadorGimnasio:
 *   - Atributo propio: torneosGanados (en vez de medallasConseguidas)
 *   - Fórmula de puntuación distinta (multiplica por 5 en vez de por 3)
 *   - Umbral para participar más alto (>= 80 en vez de >= 50)
 */
public class EntrenadorElite extends Entrenador {

    // Atributo propio: número de torneos completos ganados
    private int torneosGanados;

    // ── Constructor ──────────────────────────────────────────────────────────
    public EntrenadorElite(String id, String nombre, Region region,
                           double[][] victorias, int torneosGanados) {
        super(id, nombre, region, victorias); // hereda atributos comunes
        this.torneosGanados = torneosGanados;
    }

    // Getter del atributo propio
    public int getTorneosGanados() { return torneosGanados; }

    // ── Implementación de calcularPuntuacion() ───────────────────────────────
    /*
     * Fórmula: puntuacion = totalVictorias + torneosGanados * 5
     *
     * Ganar un torneo completo vale mucho más que una medalla de gimnasio.
     */
    @Override
    public double calcularPuntuacion() {
        return calcularTotalVictorias() + torneosGanados * 5;
    }

    // ── Implementación de puedeParticipar() ─────────────────────────────────
    /*
     * Un EntrenadorElite necesita >= 80 puntos para participar en el Campeonato.
     * El umbral es más alto porque se le exige más al ser de nivel élite.
     */
    @Override
    public boolean puedeParticipar() {
        return calcularPuntuacion() >= 80;
    }

    // ── toString sobreescrito ────────────────────────────────────────────────
    @Override
    public String toString() {
        return "EntrenadorElite{id='" + id + "', nombre='" + nombre +
               "', region=" + region +
               ", torneosGanados=" + torneosGanados + "}";
    }
}
