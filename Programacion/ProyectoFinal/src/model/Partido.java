package model;

// Representa un partido oficial de la liga
// Puede estar pendiente (jugado=false) o ya disputado con resultado registrado
public class Partido {

    private String id;            // ID unico del partido (ej: "P001")
    private int jornada;          // numero de jornada (1-5)
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;       // -1 si no se ha jugado todavia
    private int golesVisitante;   // -1 si no se ha jugado todavia
    private Jugador mvp;          // null si no se ha jugado
    private boolean jugado;

    public Partido(String id, int jornada, Equipo equipoLocal, Equipo equipoVisitante) {
        this.id = id;
        this.jornada = jornada;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = -1;
        this.golesVisitante = -1;
        this.mvp = null;
        this.jugado = false;
    }

    // Getters
    public String getId() { return id; }
    public int getJornada() { return jornada; }
    public Equipo getEquipoLocal() { return equipoLocal; }
    public Equipo getEquipoVisitante() { return equipoVisitante; }
    public int getGolesLocal() { return golesLocal; }
    public int getGolesVisitante() { return golesVisitante; }
    public Jugador getMvp() { return mvp; }
    public boolean isJugado() { return jugado; }

    // Registra el resultado y actualiza automaticamente las estadisticas de equipos y jugadores
    public void registrarResultado(int golesLocal, int golesVisitante, Jugador mvp) {
        if (jugado) {
            throw new IllegalStateException("El partido " + id + " ya ha sido registrado.");
        }
        if (golesLocal < 0 || golesVisitante < 0) {
            throw new IllegalArgumentException("Los puntos no pueden ser negativos.");
        }
        if (mvp == null) {
            throw new IllegalArgumentException("Debe designarse un MVP.");
        }

        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.mvp = mvp;
        this.jugado = true;

        // Actualizar victorias/derrotas de los equipos segun el resultado
        if (golesLocal > golesVisitante) {
            equipoLocal.registrarVictoria(golesLocal, golesVisitante);
            equipoVisitante.registrarDerrota(golesVisitante, golesLocal);
        } else if (golesVisitante > golesLocal) {
            equipoVisitante.registrarVictoria(golesVisitante, golesLocal);
            equipoLocal.registrarDerrota(golesLocal, golesVisitante);
        } else {
            // Empate: se actualizan puntos pero no se suma victoria ni derrota a ningun equipo
            equipoLocal.registrarEmpate(golesLocal, golesVisitante);
            equipoVisitante.registrarEmpate(golesVisitante, golesLocal);
        }

        // Incrementar partidas de los titulares de ambos equipos
        actualizarPartidasTitulares(equipoLocal);
        actualizarPartidasTitulares(equipoVisitante);

        // Asignar MVP
        mvp.incrementarMvp();
    }

    // Incrementa el contador de partidas de todos los titulares de un equipo
    private void actualizarPartidasTitulares(Equipo equipo) {
        for (Jugador j : equipo.getTitulares()) {
            if (j != null) j.incrementarPartidas();
        }
    }

    // Devuelve el equipo ganador, o null si hubo empate o no se ha jugado
    public Equipo calcularGanador() {
        if (!jugado) return null;
        if (golesLocal > golesVisitante) return equipoLocal;
        if (golesVisitante > golesLocal) return equipoVisitante;
        return null;
    }

    public void mostrarResultado() {
        System.out.print("Jornada " + jornada + " | "
                + equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre());
        if (jugado) {
            System.out.println(" -> " + golesLocal + " - " + golesVisitante
                    + " | MVP: " + mvp.getNickname());
        } else {
            System.out.println(" -> PENDIENTE");
        }
    }

    @Override
    public String toString() {
        return "Partido{id='" + id + "', jornada=" + jornada + ", "
                + equipoLocal.getNombre() + " vs " + equipoVisitante.getNombre()
                + ", jugado=" + jugado + "}";
    }
}
