package LigaPokemon;

/*
 * CLASE PRINCIPAL — Programa principal
 * ══════════════════════════════════════
 *
 * ENUNCIADO:
 * La Liga Pokémon quiere gestionar la actividad de varios entrenadores.
 * Cada entrenador tiene un id, nombre, región de procedencia y un registro
 * de victorias semanal en 3 tipos de combate (gimnasio, competición, torneo)
 * a lo largo de 5 semanas.
 *
 * Existen dos tipos de entrenadores:
 *   - EntrenadorGimnasio: busca medallas de gimnasio.
 *   - EntrenadorElite:    compite en torneos de alto nivel.
 *
 * El programa debe:
 * a) Crear un array polimórfico de 4 Entrenadores (2 de cada tipo).
 * b) Inicializar manualmente las matrices de victorias.
 * c) Mostrar para cada entrenador: datos, registro, totales, puntuación
 *    y si puede participar en el Campeonato de la Liga.
 * d) Mostrar el entrenador con mayor puntuación.
 * e) Contar cuántos EntrenadorGimnasio y EntrenadorElite hay (usar instanceof).
 * f) Calcular la media de victorias por tipo de combate (gimnasio / competición / torneo).
 * g) Mostrar qué entrenador tuvo más victorias en una sola semana.
 */
public class Principal {

    public static void main(String[] args) {

        // ════════════════════════════════════════════════════════════════
        // a) ARRAY POLIMÓRFICO
        // ════════════════════════════════════════════════════════════════
        /*
         * POLIMORFISMO: la referencia es de tipo Entrenador (clase padre),
         * pero los objetos reales son de las subclases.
         *
         * Esto permite tratar todos los entrenadores de forma uniforme
         * en el bucle, aunque internamente cada uno tenga su comportamiento.
         *
         * Entrenador[] puede guardar EntrenadorGimnasio y EntrenadorElite
         * porque AMBAS heredan de Entrenador.
         */
        Entrenador[] entrenadores = new Entrenador[4];

        // ════════════════════════════════════════════════════════════════
        // b) MATRICES DE VICTORIAS — 5 semanas x 3 tipos (gimnasio/comp/torneo)
        // ════════════════════════════════════════════════════════════════
        /*
         * Inicialización directa de la matriz con dobles llaves:
         * La primera llave agrupa las filas (semanas).
         * La segunda llave agrupa los valores de cada fila (tipos de combate).
         */
        double[][] victorias1 = {
            {5, 3, 1},   // Semana 1: 5 en gimnasio, 3 en competicion, 1 en torneo
            {4, 4, 0},   // Semana 2
            {6, 2, 1},   // Semana 3
            {5, 3, 2},   // Semana 4
            {4, 4, 1}    // Semana 5
        };

        double[][] victorias2 = {
            {3, 5, 0},
            {4, 4, 1},
            {3, 6, 0},
            {5, 3, 1},
            {4, 5, 0}
        };

        double[][] victorias3 = {
            {2, 3, 4},
            {1, 2, 5},
            {3, 2, 4},
            {2, 3, 5},
            {1, 2, 6}
        };

        double[][] victorias4 = {
            {3, 4, 5},
            {2, 3, 6},
            {4, 3, 4},
            {3, 4, 5},
            {2, 3, 7}
        };

        // Creamos los objetos. La referencia es Entrenador, el objeto es la subclase.
        entrenadores[0] = new EntrenadorGimnasio("EG01", "Ash Ketchum",   Region.KANTO,  victorias1, 7);
        entrenadores[1] = new EntrenadorGimnasio("EG02", "Misty Waterflower", Region.KANTO, victorias2, 4);
        entrenadores[2] = new EntrenadorElite("EE01", "Lance Dragon",    Region.JOHTO,  victorias3, 3);
        entrenadores[3] = new EntrenadorElite("EE02", "Cynthia Shirona", Region.SINNOH, victorias4, 5);

        // ════════════════════════════════════════════════════════════════
        // c) RECORRER EL ARRAY Y MOSTRAR DATOS DE CADA ENTRENADOR
        // ════════════════════════════════════════════════════════════════
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   LIGA POKEMON — INFORME DE ENTRENADORES ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        for (Entrenador e : entrenadores) {
            System.out.println("──────────────────────────────────────────");

            // toString() de la subclase correspondiente (polimorfismo en acción)
            System.out.println(e);
            System.out.println();

            // mostrarHorario() está en la clase padre → disponible para todos
            e.mostrarRegistro();

            System.out.printf("Total victorias semana : %.0f%n",   e.calcularTotalVictorias());
            System.out.printf("Puntuacion Liga        : %.1f%n",   e.calcularPuntuacion());

            // puedeParticipar() es de la interfaz Clasificable → cada subclase
            // tiene su propio umbral, pero se llama igual para todos (polimorfismo)
            System.out.println("Puede participar       : " + (e.puedeParticipar() ? "SÍ ✓" : "NO ✗"));
            System.out.println();
        }

        // ════════════════════════════════════════════════════════════════
        // d) ENTRENADOR CON MAYOR PUNTUACION
        // ════════════════════════════════════════════════════════════════
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║         ESTADISTICAS GLOBALES            ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        Entrenador mejor = entrenadores[0]; // empezamos asumiendo que el primero es el mejor
        for (Entrenador e : entrenadores) {
            if (e.calcularPuntuacion() > mejor.calcularPuntuacion()) {
                mejor = e; // actualizamos si encontramos uno con más puntuación
            }
        }
        System.out.println("Entrenador con mayor puntuacion: " + mejor.getNombre() +
                           " (" + String.format("%.1f", mejor.calcularPuntuacion()) + " pts)");

        // ════════════════════════════════════════════════════════════════
        // e) CONTAR TIPOS CON instanceof
        // ════════════════════════════════════════════════════════════════
        /*
         * instanceof comprueba si un objeto es instancia de una clase concreta.
         * Aunque la referencia sea Entrenador, instanceof detecta el tipo real.
         *
         * Esto es posible gracias al polimorfismo: la referencia Entrenador
         * apunta a objetos de subclases distintas.
         */
        int numGimnasio = 0;
        int numElite    = 0;
        for (Entrenador e : entrenadores) {
            if (e instanceof EntrenadorGimnasio) numGimnasio++;
            if (e instanceof EntrenadorElite)    numElite++;
        }
        System.out.println("Entrenadores de Gimnasio : " + numGimnasio);
        System.out.println("Entrenadores de Elite    : " + numElite);
        System.out.println();

        // ════════════════════════════════════════════════════════════════
        // f) MEDIA DE VICTORIAS POR TIPO DE COMBATE
        // ════════════════════════════════════════════════════════════════
        /*
         * Recorremos la columna 0 (gimnasio), 1 (competicion) y 2 (torneo)
         * sumando las 5 semanas de cada entrenador.
         *
         * Total de datos por tipo = numEntrenadores * numSemanas = 4 * 5 = 20
         */
        double[] sumasTipo = new double[3]; // índices: 0=gimnasio, 1=competicion, 2=torneo

        for (Entrenador e : entrenadores) {
            for (int semana = 0; semana < 5; semana++) {
                for (int tipo = 0; tipo < 3; tipo++) {
                    sumasTipo[tipo] += e.getVictorias()[semana][tipo];
                }
            }
        }

        int totalRegistros = entrenadores.length * 5; // 4 entrenadores x 5 semanas
        System.out.printf("Media victorias en Gimnasio    : %.2f%n", sumasTipo[0] / totalRegistros);
        System.out.printf("Media victorias en Competicion : %.2f%n", sumasTipo[1] / totalRegistros);
        System.out.printf("Media victorias en Torneo      : %.2f%n", sumasTipo[2] / totalRegistros);
        System.out.println();

        // ════════════════════════════════════════════════════════════════
        // g) ENTRENADOR CON MAS VICTORIAS EN UNA SOLA SEMANA
        // ════════════════════════════════════════════════════════════════
        Entrenador mejorSemana = entrenadores[0];
        int    indiceSemana   = 0;
        double maxVictorias   = entrenadores[0].calcularVictoriasSemana(0);

        for (Entrenador e : entrenadores) {
            for (int semana = 0; semana < 5; semana++) {
                double vict = e.calcularVictoriasSemana(semana);
                if (vict > maxVictorias) {
                    maxVictorias  = vict;
                    mejorSemana   = e;
                    indiceSemana  = semana;
                }
            }
        }

        System.out.println("Entrenador con mas victorias en una semana: " +
                           mejorSemana.getNombre() +
                           " en Semana " + (indiceSemana + 1) +
                           " con " + (int) maxVictorias + " victorias");
    }
}
