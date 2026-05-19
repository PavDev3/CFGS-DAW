package structures;

import model.*;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// Clase central de la aplicacion - gestiona todos los elementos de la liga
//
// Estructuras de datos usadas:
//   ArrayList<Equipo>      - lista dinamica de equipos
//   ArrayList<PersonaLiga> - lista dinamica de personas (jugadores + entrenadores)
//   ArrayList<Partido>     - historial de partidos
//   ArrayList<Incidencia>  - lista de incidencias
//   HashSet<String>        - control de IDs y nombres duplicados (busqueda O(1))
//   Queue<Partido>         - cola FIFO de partidos pendientes (LinkedList)
//   ArrayDeque<String>     - pila LIFO de acciones realizadas (historial)
//   ArrayDeque<String>     - pila auxiliar para deshacer sanciones
//   int[][]                - matriz [jornada][equipo] con puntos acumulados
public class Liga {

    private String nombre;

    public static final int MAX_EQUIPOS = 6;
    public static final int NUM_JORNADAS = 5;

    // Listas dinamicas
    private ArrayList<Equipo> equipos;
    private ArrayList<PersonaLiga> personas;
    private ArrayList<Partido> partidos;
    private ArrayList<Incidencia> incidencias;

    // HashSets para evitar duplicados en O(1)
    private HashSet<String> idsPersonas;
    private HashSet<String> nombresEquipos;
    private HashSet<String> idsPartidos;

    // Cola FIFO de partidos pendientes
    private Queue<Partido> colaPartidos;

    // Pila LIFO del historial de acciones del usuario
    private ArrayDeque<String> pilaAcciones;

    // Pila auxiliar: guarda IDs de jugadores sancionados para poder deshacer la ultima sancion
    private ArrayDeque<String> pilaSanciones;

    // Matriz de puntos: matrizPuntos[jornada][indiceEquipo]
    // Una victoria suma 3 puntos, empate 1, derrota 0
    private int[][] matrizPuntos;

    public Liga(String nombre) {
        this.nombre = nombre;
        equipos = new ArrayList<>();
        personas = new ArrayList<>();
        partidos = new ArrayList<>();
        incidencias = new ArrayList<>();
        idsPersonas = new HashSet<>();
        nombresEquipos = new HashSet<>();
        idsPartidos = new HashSet<>();
        colaPartidos = new LinkedList<>();
        pilaAcciones = new ArrayDeque<>();
        pilaSanciones = new ArrayDeque<>();
        matrizPuntos = new int[NUM_JORNADAS][MAX_EQUIPOS];
    }

    // Getters
    public String getNombre() { return nombre; }
    public ArrayList<Equipo> getEquipos() { return equipos; }
    public ArrayList<PersonaLiga> getPersonas() { return personas; }
    public ArrayList<Partido> getPartidos() { return partidos; }
    public ArrayList<Incidencia> getIncidencias() { return incidencias; }
    public Queue<Partido> getColaPartidos() { return colaPartidos; }
    public ArrayDeque<String> getPilaAcciones() { return pilaAcciones; }
    public int[][] getMatrizPuntos() { return matrizPuntos; }

    // ── GESTION DE PERSONAS ────────────────────────────────────────────────

    public void registrarPersona(PersonaLiga persona) {
        if (idsPersonas.contains(persona.getIdentificador())) {
            throw new IllegalArgumentException(
                    "Ya existe una persona con el ID: " + persona.getIdentificador());
        }
        personas.add(persona);
        idsPersonas.add(persona.getIdentificador());
        registrarAccion("Persona registrada: " + persona.getNickname()
                + " (ID: " + persona.getIdentificador() + ")");
    }

    public PersonaLiga buscarPersonaPorId(String id) {
        for (PersonaLiga p : personas) {
            if (p.getIdentificador().equals(id)) return p;
        }
        return null;
    }

    public boolean eliminarPersona(String id) {
        PersonaLiga persona = buscarPersonaPorId(id);
        if (persona == null) return false;
        personas.remove(persona);
        idsPersonas.remove(id);
        registrarAccion("Persona eliminada: " + persona.getNickname() + " (ID: " + id + ")");
        return true;
    }

    // ── GESTION DE EQUIPOS ─────────────────────────────────────────────────

    public void registrarEquipo(Equipo equipo) {
        if (equipos.size() >= MAX_EQUIPOS) {
            throw new IllegalArgumentException(
                    "La liga ya tiene el maximo de " + MAX_EQUIPOS + " equipos.");
        }
        if (nombresEquipos.contains(equipo.getNombre())) {
            throw new IllegalArgumentException(
                    "Ya existe un equipo con el nombre: " + equipo.getNombre());
        }
        equipos.add(equipo);
        nombresEquipos.add(equipo.getNombre());
        registrarAccion("Equipo creado: " + equipo.getNombre());
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        for (Equipo e : equipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }

    // Devuelve el indice del equipo en la lista (necesario para la matriz de puntos)
    public int getIndiceEquipo(Equipo equipo) {
        return equipos.indexOf(equipo);
    }

    // ── GESTION DE PARTIDOS ────────────────────────────────────────────────

    public void registrarPartido(Partido partido) {
        if (partido.getEquipoLocal() == partido.getEquipoVisitante()) {
            throw new IllegalArgumentException("Un equipo no puede jugar contra si mismo.");
        }
        if (idsPartidos.contains(partido.getId())) {
            throw new IllegalArgumentException(
                    "Ya existe un partido con el ID: " + partido.getId());
        }
        partidos.add(partido);
        idsPartidos.add(partido.getId());
        colaPartidos.add(partido); // tambien se encola como pendiente
        registrarAccion("Partido registrado: " + partido.getEquipoLocal().getNombre()
                + " vs " + partido.getEquipoVisitante().getNombre()
                + " (Jornada " + partido.getJornada() + ")");
    }

    // Ver el proximo partido sin sacarlo de la cola
    public Partido verProximoPartido() {
        return colaPartidos.peek();
    }

    // Saca el proximo partido de la cola para jugarlo
    public Partido jugarProximoPartido() {
        return colaPartidos.poll();
    }

    // Actualiza la matriz de puntos tras registrar el resultado de un partido
    public void actualizarMatrizPuntos(Partido partido) {
        if (!partido.isJugado()) return;

        int jornada = partido.getJornada() - 1; // indice 0-based
        int idxLocal = getIndiceEquipo(partido.getEquipoLocal());
        int idxVisitante = getIndiceEquipo(partido.getEquipoVisitante());
        if (idxLocal < 0 || idxVisitante < 0) return;

        Equipo ganador = partido.calcularGanador();
        if (ganador == null) {
            // Empate: 1 punto para cada equipo
            matrizPuntos[jornada][idxLocal] += 1;
            matrizPuntos[jornada][idxVisitante] += 1;
        } else if (ganador == partido.getEquipoLocal()) {
            matrizPuntos[jornada][idxLocal] += 3;
        } else {
            matrizPuntos[jornada][idxVisitante] += 3;
        }
    }

    public void mostrarMatrizPuntos() {
        System.out.println("=== PUNTOS POR JORNADA ===");
        System.out.printf("%-12s", "");
        for (Equipo e : equipos) {
            System.out.printf("%-12s", e.getNombre().substring(0, Math.min(10, e.getNombre().length())));
        }
        System.out.println();
        for (int j = 0; j < NUM_JORNADAS; j++) {
            System.out.printf("%-12s", "Jornada " + (j + 1));
            for (int e = 0; e < equipos.size(); e++) {
                System.out.printf("%-12d", matrizPuntos[j][e]);
            }
            System.out.println();
        }
    }

    // ── GESTION DE SANCIONES ───────────────────────────────────────────────

    // Aplica sancion al jugador y guarda su ID en la pila para poder deshacer
    public void aplicarSancion(Jugador jugador) {
        jugador.setSancionado(true);
        pilaSanciones.push(jugador.getIdentificador());
        registrarAccion("Sancion aplicada a: " + jugador.getNickname()
                + " (ID: " + jugador.getIdentificador() + ")");
    }

    // Deshace la ultima sancion aplicada (operacion undo)
    // Recupera el ID de la cima de la pila y le quita la sancion al jugador
    public String deshacerUltimaSancion() {
        if (pilaSanciones.isEmpty()) {
            return "No hay sanciones que deshacer.";
        }
        String idJugador = pilaSanciones.pop();
        PersonaLiga persona = buscarPersonaPorId(idJugador);
        if (persona instanceof Jugador) {
            Jugador jugador = (Jugador) persona;
            jugador.setSancionado(false);
            registrarAccion("UNDO: Sancion revertida para " + jugador.getNickname());
            return "Sancion revertida para: " + jugador.getNickname();
        }
        return "No se encontro el jugador con ID: " + idJugador;
    }

    // ── GESTION DE INCIDENCIAS ─────────────────────────────────────────────

    public void registrarIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        registrarAccion("Incidencia registrada: " + incidencia.getTipo()
                + " (" + incidencia.getId() + ")");
    }

    // ── HISTORIAL DE ACCIONES (PILA) ───────────────────────────────────────

    // Aniade una accion a la cima de la pila - lo usan todos los metodos que modifican la liga
    private void registrarAccion(String accion) {
        pilaAcciones.push(accion);
    }

    public void mostrarUltimaAccion() {
        if (pilaAcciones.isEmpty()) {
            System.out.println("No hay acciones registradas.");
        } else {
            System.out.println("Ultima accion: " + pilaAcciones.peek());
        }
    }

    public void mostrarHistorialAcciones() {
        System.out.println("=== HISTORIAL DE ACCIONES (mas reciente primero) ===");
        if (pilaAcciones.isEmpty()) {
            System.out.println("No hay acciones registradas.");
            return;
        }
        int i = 1;
        for (String accion : pilaAcciones) {
            System.out.println(i++ + ". " + accion);
        }
    }

    // ── CLASIFICACION ──────────────────────────────────────────────────────

    // Ordenacion: 1o victorias, 2o diferencia de puntos, 3o nombre alfabetico
    public void mostrarClasificacion() {
        ArrayList<Equipo> clasificacion = new ArrayList<>(equipos);

        clasificacion.sort((a, b) -> {
            if (b.getVictorias() != a.getVictorias())
                return b.getVictorias() - a.getVictorias();
            if (b.getDiferenciaPuntos() != a.getDiferenciaPuntos())
                return b.getDiferenciaPuntos() - a.getDiferenciaPuntos();
            return a.getNombre().compareTo(b.getNombre());
        });

        System.out.println("=== CLASIFICACION ===");
        System.out.printf("%-4s %-15s %-5s %-5s %-6s %-6s %-6s%n",
                "Pos", "Equipo", "V", "D", "PF", "PC", "Dif");
        System.out.println("-".repeat(47));
        int pos = 1;
        for (Equipo e : clasificacion) {
            System.out.printf("%-4d %-15s %-5d %-5d %-6d %-6d %-6d%n",
                    pos++, e.getNombre(), e.getVictorias(), e.getDerrotas(),
                    e.getPuntosFavor(), e.getPuntosContra(), e.getDiferenciaPuntos());
        }
    }

    public void mostrarEstadisticasJugadores() {
        System.out.println("=== ESTADISTICAS DE JUGADORES ===");
        for (PersonaLiga p : personas) {
            if (p instanceof Jugador) {
                Jugador j = (Jugador) p;
                System.out.printf("%-12s %-8s Partidas:%-4d MVPs:%-3d Rendimiento:%-6.2f Coste:%-8.2f€ %s%n",
                        j.getNickname(), j.getRol(),
                        j.getPartidasJugadas(), j.getMvpTotales(),
                        j.calcularRendimiento(), j.calcularCosteMensual(),
                        j.isSancionado() ? "[SANCIONADO]" : "");
            }
        }
    }
}
