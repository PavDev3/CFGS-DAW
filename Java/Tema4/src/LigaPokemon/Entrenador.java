package LigaPokemon;

/*
 * CLASE ABSTRACTA
 * ────────────────
 * Una clase abstracta es una clase que NO se puede instanciar directamente
 * (no puedes hacer: new Entrenador(...)).
 *
 * Sirve como PLANTILLA común para sus subclases:
 * - Define atributos y métodos compartidos por todas las subclases.
 * - Puede declarar métodos abstractos: métodos sin cuerpo que cada subclase DEBE implementar.
 *
 * Palabra clave: abstract (en la clase y en los métodos sin implementación).
 *
 * Aquí: Entrenador implementa la interfaz Clasificable, pero deja puedeParticipar()
 * sin implementar (porque cada tipo de entrenador tiene sus propias condiciones).
 * En cambio, calcularPuntuacion() sí se deja abstracta porque la fórmula varía.
 */
public abstract class Entrenador implements Clasificable {

    // ── Atributos ────────────────────────────────────────────────────────────
    // protected: accesibles en esta clase y en las subclases (no desde fuera)
    protected String id;
    protected String nombre;
    protected Region region;

    /*
     * MATRIZ BIDIMENSIONAL de victorias
     * ───────────────────────────────────
     * double[][] victorias → array de arrays (tabla).
     * Dimensiones: 5 filas (semanas 1-5) x 3 columnas (tipos de combate):
     *   columna 0 → combates en Gimnasio
     *   columna 1 → combates en Competición
     *   columna 2 → combates en Torneo
     *
     * Ejemplo de acceso: victorias[2][1] = victorias de la semana 3 en Competición.
     */
    protected double[][] victorias; // siempre 5 x 3

    // ── Constructor ──────────────────────────────────────────────────────────
    /*
     * El constructor de la clase abstracta se llama desde las subclases con super(...)
     * No tiene la palabra abstract — el constructor siempre tiene cuerpo.
     */
    public Entrenador(String id, String nombre, Region region, double[][] victorias) {
        this.id = id;
        this.nombre = nombre;
        this.region = region;
        this.victorias = victorias;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    // Acceso controlado a los atributos privados/protegidos desde fuera de la clase.
    public String getId()            { return id; }
    public String getNombre()        { return nombre; }
    public Region getRegion()        { return region; }
    public double[][] getVictorias() { return victorias; }

    // ── Métodos concretos (con implementación) ───────────────────────────────

    /*
     * Suma TODAS las victorias de la semana (las 5 semanas x los 3 tipos).
     * Usamos dos bucles anidados para recorrer la matriz completa.
     */
    public double calcularTotalVictorias() {
        double total = 0;
        for (int semana = 0; semana < victorias.length; semana++) {        // recorre filas
            for (int tipo = 0; tipo < victorias[semana].length; tipo++) {  // recorre columnas
                total += victorias[semana][tipo];
            }
        }
        return total;
    }

    /*
     * Suma las victorias de UNA semana concreta (suma de sus 3 tipos de combate).
     * Parámetro: semana → índice de la fila (0 = semana 1, ..., 4 = semana 5)
     */
    public double calcularVictoriasSemana(int semana) {
        double totalSemana = 0;
        for (int tipo = 0; tipo < victorias[semana].length; tipo++) {
            totalSemana += victorias[semana][tipo];
        }
        return totalSemana;
    }

    /*
     * Muestra la matriz completa con formato de tabla.
     * Usamos printf para alinear columnas con ancho fijo.
     */
    public void mostrarRegistro() {
        String[] tiposCombate = {"Gimnasio", "Competicion", "Torneo"};
        System.out.println("Registro de victorias de " + nombre + ":");
        System.out.printf("%-12s %-12s %-13s %-8s%n", "Semana", "Gimnasio", "Competicion", "Torneo");
        for (int i = 0; i < victorias.length; i++) {
            System.out.printf("%-12s %-12.0f %-13.0f %-8.0f%n",
                    "Semana " + (i + 1),
                    victorias[i][0],
                    victorias[i][1],
                    victorias[i][2]);
        }
    }

    // ── Método abstracto ─────────────────────────────────────────────────────
    /*
     * MÉTODO ABSTRACTO: no tiene cuerpo ({}).
     * Obliga a que cada subclase (EntrenadorGimnasio, EntrenadorElite)
     * lo implemente con su propia fórmula.
     *
     * Si una subclase no lo implementa → error de compilación.
     */
    public abstract double calcularPuntuacion();

    // ── toString ─────────────────────────────────────────────────────────────
    /*
     * @Override indica que este método sobreescribe el toString() heredado de Object.
     * Devuelve una representación legible del objeto en forma de String.
     */
    @Override
    public String toString() {
        return "Entrenador{id='" + id + "', nombre='" + nombre +
               "', region=" + region + "}";
    }
}
