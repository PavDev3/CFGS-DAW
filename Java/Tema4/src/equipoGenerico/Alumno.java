package equipoGenerico;

public class Alumno {

    private String nombre;
    private String dni;

    public Alumno(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public String getDni()    { return dni; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno)) return false;
        return dni.equals(((Alumno) o).dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public String toString() {
        return "Alumno [nombre=" + nombre + ", dni=" + dni + "]";
    }
}
