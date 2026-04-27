package streamEjemplo;

public class Persona {

    private String dni;
    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String dni, String nombre, String apellido, int edad) {
        this.dni      = dni;
        this.nombre   = nombre;
        this.apellido = apellido;
        this.edad     = edad;
    }

    public String getDni()      { return dni; }
    public String getNombre()   { return nombre; }
    public String getApellido() { return apellido; }
    public int getEdad()        { return edad; }

    @Override
    public String toString() {
        return dni + " - " + nombre + " " + apellido + " (" + edad + ")";
    }
}
