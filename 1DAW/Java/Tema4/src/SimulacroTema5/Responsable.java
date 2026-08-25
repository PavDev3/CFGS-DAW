package SimulacroTema5;

public class Responsable {

    private String dni;
    private String nombre;
    private int antiguedad;

    // constructor
    public Responsable(String dni, String nombre, int antiguedad) {
        this.dni = dni;
        this.nombre = nombre;
        this.antiguedad = antiguedad;
    }

    // getters
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    @Override
    public String toString() {
        return "Responsable [dni=" + dni + ", nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
    }
}
