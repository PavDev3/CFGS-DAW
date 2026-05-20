package model;

// Incidencia registrada durante la liga (sancion, expulsion, error tecnico, etc.)
// Se almacena en una lista dinamica dentro de Liga
public class Incidencia {

    private String id;
    private TipoIncidencia tipo;
    private String descripcion;
    private Jugador jugadorAfectado;   // puede ser null si no afecta a un jugador concreto
    private Equipo equipoAfectado;     // puede ser null si no afecta a un equipo concreto
    private String fecha;              // formato "DD/MM/YYYY"

    public Incidencia(String id, TipoIncidencia tipo, String descripcion,
                      Jugador jugadorAfectado, Equipo equipoAfectado, String fecha) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.jugadorAfectado = jugadorAfectado;
        this.equipoAfectado = equipoAfectado;
        this.fecha = fecha;
    }

    // Getters
    public String getId() { return id; }
    public TipoIncidencia getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public Jugador getJugadorAfectado() { return jugadorAfectado; }
    public Equipo getEquipoAfectado() { return equipoAfectado; }
    public String getFecha() { return fecha; }

    // Setters
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public void mostrar() {
        System.out.println("ID: " + id + " | Tipo: " + tipo + " | Fecha: " + fecha);
        System.out.println("  Descripcion: " + descripcion);
        if (jugadorAfectado != null)
            System.out.println("  Jugador: " + jugadorAfectado.getNickname());
        if (equipoAfectado != null)
            System.out.println("  Equipo: " + equipoAfectado.getNombre());
    }

    @Override
    public String toString() {
        return "Incidencia{id='" + id + "', tipo=" + tipo + ", fecha='" + fecha + "'}";
    }
}
