package simulacroV2;

public class Responsable {
	// Atributos
	private String dni;
	private String nombre;
	private int antiguedad;
	
	// constructor
	
	public Responsable(String dni, String nombre, int antiguedad) {
		this.dni = dni;
		this.nombre = nombre;
		this.antiguedad = antiguedad;
	}
	
	// Getters y Setters
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getAntiguedad() {
		return antiguedad;
	}

	
	// toString
	@Override
	public String toString() {
		return "Responsable [dni=" + dni + ", nombre=" + nombre + ", antiguedad=" + antiguedad + "]";
	}
}
